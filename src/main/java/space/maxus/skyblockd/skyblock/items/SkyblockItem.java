package space.maxus.skyblockd.skyblock.items;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.reflections.ReflectionUtils;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.GuiHelper;
import space.maxus.skyblockd.helpers.ItemHelper;
import space.maxus.skyblockd.skyblock.objects.*;
import space.maxus.skyblockd.skyblock.utility.SkyblockFeature;
import space.maxus.skyblockd.skyblock.utility.StatPosition;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public abstract class SkyblockItem implements SkyblockFeature {

    public abstract SkyblockItemConfig getConfig();
    public abstract boolean hasGlint();
    public abstract String getSkyblockId();
    public abstract ItemStack postInit(ItemStack i);
    private ItemStack item;

    public ItemStack getItem() { return item; }

    public ItemStack generate(){
        item = new ItemStack(getConfig().getMaterial(), 1);
        return inheritanceGeneration(item);
    }

    @SuppressWarnings("unchecked")
    protected ItemStack inheritanceGeneration(ItemStack item){
        SkyblockItemConfig cfg = getConfig();

        List<SkyblockItemAbility> abils = cfg.getAbilities();
        List<String> desc = cfg.getDescription();
        String name = cfg.getName();
        SkyblockRarity rar = cfg.getRarity();
        SkyblockItemType t = cfg.getType();

        ItemMeta m = item.getItemMeta();
        assert m != null;
        m.setDisplayName(name);
        //#region lore
        List<String> lore = new ArrayList<>();

        if(t.isConsumable()) {
            lore.add(ChatColor.DARK_GRAY+"Consumed on use");
            lore.add(" ");
        }

        SkyblockItemStats stats = cfg.getStats();

        Set<Method> getters = ReflectionUtils.getAllMethods(stats.getClass(),
                ReflectionUtils.withModifier(Modifier.PUBLIC), ReflectionUtils.withPrefix("get"));

        HashMap<Integer, Method> d = new HashMap<>();

        getters.forEach((get) -> {
            StatPosition a = get.getAnnotation(StatPosition.class);
            int v = a.value();
            d.put(v, get);
        });

        TreeMap<Integer, Method> nd = new TreeMap<>(d);

        for(int i = 0; i < getters.size(); i++){
            Method g = nd.get(i);
            try {
                if(i == 6) {
                    if(stats.hasRedStats() && stats.hasGreenStats()) {
                        lore.add(" ");
                    }
                } else {
                    String stat = (String) g.invoke(stats);
                    if (!stat.equals("")) {
                        lore.add(stat);
                    }
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                SkyblockD.logger.severe("Could not set stats to object! " + Arrays.toString(e.getStackTrace()));
            }
        }

        if(abils != null) {
            lore.add(" ");
            for (SkyblockItemAbility a: abils) {
                lore.addAll(a.generate());
            }
        }

        if(desc != null){
            lore.add(" ");
            lore.addAll(desc);
        }

        if(t.isReforgeable()) {
            if(desc == null || abils == null) lore.add(" ");
            lore.add(ChatColor.DARK_GRAY+"This item can be reforged!");
        }

        lore.add(rar.displayColor + "" + ChatColor.BOLD + rar.unformattedName+ " " + t.getDisplay());
        //#endregion lore
        m.setLore(lore);
        GuiHelper.setHideAllFlags(m);

        if(hasGlint()) ItemHelper.applyGlint(m);

        // add nbt
        addSkyblockTag(m);
        m.getPersistentDataContainer().set(SkyblockD.getKey("itemRarity"), PersistentDataType.INTEGER, rar.getIndex());

        item.setItemMeta(m);

        // finish the initialization by calling postInit
        postInit(item);

        SkyblockD.getItemRegisterer().register(this);

        return item;
    }

    public static ItemStack recombobulate(ItemStack item){
        ItemMeta m = item.getItemMeta();
        assert m != null;
        PersistentDataContainer c = m.getPersistentDataContainer();
        NamespacedKey rKey = SkyblockD.getKey("itemRarity");
        if(c.has(SkyblockD.getKey("recombobulated"), PersistentDataType.STRING)) return item;
        if(c.has(rKey, PersistentDataType.INTEGER)){
            Integer rarity = c.get(rKey, PersistentDataType.INTEGER);
            assert rarity != null;
            SkyblockRarity r = SkyblockRarity.byIndex(rarity+1);
            String sname = ChatColor.stripColor(m.getDisplayName());
            String name = ChatColor.RESET+""+r.displayColor+sname;
            String rdisp = r.displayColor+""+ChatColor.BOLD+""+ChatColor.MAGIC+"a "+ChatColor.RESET+r.displayName+ChatColor.BOLD+""+ChatColor.MAGIC+" a";
            List<String> lore = m.getLore();
            if (lore != null) {
                lore.set(lore.size()-1, rdisp);
            }
            m.setLore(lore);
            m.setDisplayName(name);
            c.set(SkyblockD.getKey("recombobulated"), PersistentDataType.STRING, "true");
            item.setItemMeta(m);
        }
        return item;
    }
}
