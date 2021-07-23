package space.maxus.skyblockd.skyblock.reforges;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.reflections.ReflectionUtils;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.items.CustomItem;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;
import space.maxus.skyblockd.skyblock.utility.SkyblockFeature;
import space.maxus.skyblockd.skyblock.utility.StatPosition;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

public abstract class ReforgeBase implements SkyblockFeature {
    public String getSkyblockId() {
        return SkyblockD.getNamespace(getReforge().getDisplayName()).toLowerCase(Locale.ENGLISH).replace(" ", "_");
    }

    public abstract float getRarityWeight();
    public abstract SkyblockReforge getReforge();
    public abstract SkyblockItemStats getBaseDisplayStats();
    public abstract void applyBaseStats(int modifier, ItemStack item);

    public ItemStack apply(ItemStack item) throws InvocationTargetException, IllegalAccessException {
        if(item == null) return null;
        if(!item.hasItemMeta()) return item;

        ItemMeta m = item.getItemMeta();
        assert m != null;
        PersistentDataContainer c = m.getPersistentDataContainer();
        if(!c.has(SkyblockD.getKey("skyblockNative"), PersistentDataType.BYTE)) {
            CustomItem.toSkyblockItem(item);
        }
        c.set(SkyblockD.getKey("reforged"), PersistentDataType.BYTE, (byte)1);
        c.set(SkyblockD.getKey("reforgeData"), PersistentDataType.INTEGER, getReforge().getIndex());

        Integer rar = c.get(SkyblockD.getKey("itemRarity"), PersistentDataType.INTEGER);
        assert rar != null;
        SkyblockRarity itemRarity = SkyblockRarity.byIndex(rar);

        String[] newName = m.getDisplayName().split("\\s");
        newName[0] = CustomItem.capitalize(getReforge().getDisplayName());
        String name = itemRarity.displayColor + String.join(" ", newName);

        m.setDisplayName(name);

        List<String> lore = m.getLore();
        assert lore != null;

        SkyblockItemStats stats = getBaseDisplayStats();

        @SuppressWarnings("unchecked")
        Set<Method> getters = ReflectionUtils.getAllMethods(stats.getClass(),
                ReflectionUtils.withModifier(Modifier.PUBLIC), ReflectionUtils.withPrefix("get"));

        HashMap<Integer, Method> d = new HashMap<>();

        getters.forEach((get) -> {
            StatPosition a = get.getAnnotation(StatPosition.class);
            int v = a.value();
            d.put(v, get);
        });

        TreeMap<Integer, Method> nd = new TreeMap<>(d);

        List<String> loreStats = lore.stream().map(str -> str.replaceFirst("[A-Z].+(?=\\+\\d+)", "")).collect(Collectors.toList());
        List<String> loreNames = lore.stream().map(str -> str.replaceFirst(":\\s\\+\\d+", "")).collect(Collectors.toList());

        for(int i = 0; i < getters.size(); i++){
            Method g = nd.get(i);
            try {
                if(i == 6) {
                    if(stats.hasRedStats() && stats.hasGreenStats()) {
                        int emptyIndex = loreStats.indexOf(" ");
                        if(emptyIndex > 0) continue;
                        lore.add(emptyIndex, " ");
                    }
                } else {
                    String stat = (String) g.invoke(stats);
                    if (!stat.equals("")) {
                        String replaced = stat.replaceFirst(":\\s\\+\\d+", "");
                        int indx = loreNames.indexOf(replaced);
                        String amount = stat.replaceFirst("[A-Z].+(?=\\+\\d+)", "");
                        int intAmount = Integer.parseInt(ChatColor.stripColor(amount.replace(": +", "")));
                        if(indx < 0) {
                            lore.add(i, stat + ChatColor.BLUE + " ("+getReforge().getDisplayName()+" "+amount+")");
                            continue;
                        }
                        String n = replaced + ": " + amount.replaceFirst("\\+\\d+", "") + "+"
                                + (intAmount + Integer.parseInt(amount.replace(": +", "")))
                                + ChatColor.BLUE + " ("+getReforge().getDisplayName()+" "+amount+")";
                        lore.set(i, n);
                    }
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                SkyblockD.logger.severe("Could not set stats to object! " + Arrays.toString(e.getStackTrace()));
            }
        }

        m.setLore(lore);

        item.setItemMeta(m);

        applyBaseStats(Math.round(rar-getRarityWeight()), item);

        return item;
    }
}
