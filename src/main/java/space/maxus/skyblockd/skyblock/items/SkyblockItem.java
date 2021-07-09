package space.maxus.skyblockd.skyblock.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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

    @SuppressWarnings("unchecked")
    public ItemStack generate(){
        SkyblockItemConfig cfg = getConfig();

        List<SkyblockItemAbility> abils = cfg.getAbilities();
        List<String> desc = cfg.getDescription();
        String name = cfg.getName();
        Material mat = cfg.getMaterial();
        SkyblockRarity rar = cfg.getRarity();
        SkyblockItemType t = cfg.getType();

        item = new ItemStack(mat, 1);
        ItemMeta m = item.getItemMeta();
        assert m != null;
        m.setDisplayName(name);
        //#region lore
        List<String> lore = new ArrayList<>();

        if(t.isConsumable()) lore.add(ChatColor.DARK_GRAY+"Consumed on use");

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
                String stat = (String) g.invoke(stats);
                if(!stat.equals("")){
                    lore.add(stat);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                SkyblockD.logger.severe("Could not set stats to object! " + Arrays.toString(e.getStackTrace()));
            }
        }

        if(abils != null){
            lore.add("");
            for (SkyblockItemAbility a: abils) {
                lore.addAll(a.generate());
            }
        }

        if(desc != null) lore.addAll(desc);

        lore.add("");
        if(t.isReforgeable()) lore.add(ChatColor.DARK_GRAY+"This item can be reforged!");

        lore.add(rar.displayColor + "" + ChatColor.BOLD + rar.unformattedName+ " " + t.getDisplay());
        //#endregion lore
        m.setLore(lore);
        GuiHelper.setHideAllFlags(m);

        if(hasGlint()) ItemHelper.applyGlint(m);

        // add nbt
        addSkyblockTag(m);

        item.setItemMeta(m);

        // finish the initialization by calling postInit
        postInit(item);

        SkyblockD.getItemRegisterer().register(this);

        return item;
    }
}
