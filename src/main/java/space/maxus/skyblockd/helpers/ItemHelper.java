package space.maxus.skyblockd.helpers;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.enchants.ItemGlint;
import space.maxus.skyblockd.items.CustomItem;
import space.maxus.skyblockd.skyblock.entities.EntitySummon;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemType;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class ItemHelper {
    private static final List<String> illegals = new ArrayList<>(Arrays.asList(
            "ENCHANTED_NETHERITE_BLOCK",
            "ENCHANTED_BONE_BLOCK",
            "ENCHANTED_CLAY_BLOCK",
            "ENCHANTED_COAL_BLOCK",
            "ENCHANTED_DIAMOND_BLOCK",
            "ENCHANTED_REDSTONE_BLOCK",
            "ENCHANTED_LAPIS_BLOCK",
            "ENCHANTED_EMERALD_BLOCK",
            "ENCHANTED_IRON_BLOCK",
            "ENCHANTED_SLIME_BLOCK",
            "ENCHANTED_MAGMA_BLOCK",
            "ENCHANTED_GOLD_BLOCK",
            "ENCHANTED_RED_MUSHROOM_BLOCK",
            "ENCHANTED_BROWN_MUSHROOM_BLOCK",
            "ENCHANTED_WISP",
            "ENCHANTED_BLAZE_ROD",
            "ENCHANTED_NETHER_STAR",
            "ENCHANTED_EYE_OF_ENDER",
            "ENCHANTED_DRIED_KELP",
            "ENCHANTED_COOKED_COD",
            "ENCHANTED_COOKED_SALMON",
            "ENCHANED_WET_SPONGE",
            "ENCHANTED_DARK_PRISMARINE",
            "ENCHANTED_QUARTZ_BLOCK",
            "ENCHANTED_MELON"
            ));

    public static @NotNull ItemMeta applyGlint(@NotNull ItemMeta in){
        NamespacedKey key = new NamespacedKey(SkyblockD.getInstance(), SkyblockD.getInstance().getDescription().getName());
        ItemGlint g = new ItemGlint(key);
        in.addEnchant(g, 1, true);
        return in;
    }

    public static void getExtraStats(@NotNull ItemStack base) {
        if(!base.hasItemMeta()) return;
        assert base.getItemMeta() != null;
        if(base.getItemMeta().getPersistentDataContainer().has(SkyblockD.getKey("skyblockNative"), PersistentDataType.STRING)) return;
        if(base.getType().isBlock()) return;
        ItemMeta m = base.getItemMeta();
        HashMap<String, Integer> statValues = new HashMap<>();
        if(m.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE) != null) {
            int amount = (int) Math.round(new ArrayList<>(Objects.requireNonNull(m.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE))).get(0).getAmount());
            statValues.put(ChatColor.GRAY+"Damage: "+ChatColor.RED, amount);
        }
        if(m.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED) != null) {
            int amount = (int) Math.round(new ArrayList<>(Objects.requireNonNull(m.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED))).get(0).getAmount());
            statValues.put(ChatColor.GRAY+"Bonus Attack Speed: "+ChatColor.RED, amount);
        }
        statValues.put(" ",0);
        if(m.getAttributeModifiers(Attribute.GENERIC_ARMOR) != null) {
            int amount = (int) Math.round(new ArrayList<>(Objects.requireNonNull(m.getAttributeModifiers(Attribute.GENERIC_ARMOR))).get(0).getAmount());
            statValues.put(ChatColor.GRAY+"Defence: "+ChatColor.GREEN, amount);
        }
        if(m.getAttributeModifiers(Attribute.GENERIC_MOVEMENT_SPEED) != null) {
            int amount = (int) Math.round(new ArrayList<>(Objects.requireNonNull(m.getAttributeModifiers(Attribute.GENERIC_MOVEMENT_SPEED))).get(0).getAmount());
            statValues.put(ChatColor.GRAY+"Speed: "+ChatColor.GREEN, amount);
        }
        List<String> lore = new ArrayList<>();
        for (Map.Entry<String, Integer> entry: statValues.entrySet()) {
            String val = entry.getValue() <= 0 ? "" : entry.getValue().toString();
            lore.add(entry.getKey()+val);
        }
        m.setLore(lore);
        base.setItemMeta(m);
    }

    public static @NotNull SkyblockItemType getType(@NotNull Material mat) {
        String name = mat.name();
        if(name.endsWith("_SWORD")) return SkyblockItemType.SWORD;
        else if(name.endsWith("BOW")) return SkyblockItemType.BOW;
        else if(name.endsWith("CHESTPLATE")) return SkyblockItemType.CHESTPLATE;
        else if(name.endsWith("LEGGINGS")) return SkyblockItemType.LEGGINGS;
        else if(name.endsWith("BOOTS")) return SkyblockItemType.BOOTS;
        else if(name.endsWith("HELMET")) return SkyblockItemType.HELMET;
        else if(name.endsWith("_HOE")) return SkyblockItemType.HOE;
        else if(name.endsWith("FISHING_ROD")) return SkyblockItemType.FISHING_ROD;
        else return SkyblockItemType.OTHER_NONCONSUMABLE;
    }

    public static @NotNull SkyblockRarity getRarity(@NotNull Material m){
        // i am sorry for this switch
        if(m.name().startsWith("WARPED_") || m.name().startsWith("CRIMSON_")) return SkyblockRarity.UNCOMMON;
        switch(m){
            // uncommon
            case TROPICAL_FISH_BUCKET:
            case TROPICAL_FISH:
            case BLAZE_POWDER:
            case BLAZE_ROD:
            case MAGMA_CREAM:
            case GHAST_TEAR:
            case SOUL_TORCH:
            case SOUL_CAMPFIRE:
            case SOUL_LANTERN:
            case SOUL_SOIL:
            case SOUL_SAND:
            case BONE_BLOCK:
            case SHULKER_SHELL:
            case TUBE_CORAL:
            case HORN_CORAL:
            case FIRE_CORAL:
            case BUBBLE_CORAL:
            case BRAIN_CORAL:
            case FIREWORK_ROCKET:
            case GLOWSTONE_DUST:
            case GLOWSTONE:
            case BAMBOO:
            case NAME_TAG:
            case SPLASH_POTION:
            case POTION:
            case SKELETON_SKULL:
            case ZOMBIE_HEAD:
            case END_STONE:
            case CHIPPED_ANVIL:
            case ANVIL:
            case OBSIDIAN:
            case ENDER_PEARL:
            case IRON_BLOCK:
            case GOLD_BLOCK:
            case EMERALD:
            case EMERALD_ORE:
            case REDSTONE_BLOCK:
            case DIAMOND_SWORD:
            case DIAMOND_SHOVEL:
            case DIAMOND:
            case DIAMOND_AXE:
            case DIAMOND_ORE:
            case DIAMOND_BOOTS:
            case DIAMOND_CHESTPLATE:
            case DIAMOND_HELMET:
            case DIAMOND_HOE:
            case DIAMOND_HORSE_ARMOR:
            case DIAMOND_LEGGINGS:
            case DIAMOND_PICKAXE: return SkyblockRarity.UNCOMMON;

            // rare
            case WET_SPONGE:
            case SPONGE:
            case HEART_OF_THE_SEA:
            case WITHER_ROSE:
            case YELLOW_SHULKER_BOX:
            case WHITE_SHULKER_BOX:
            case RED_SHULKER_BOX:
            case PURPLE_SHULKER_BOX:
            case PINK_SHULKER_BOX:
            case ORANGE_SHULKER_BOX:
            case MAGENTA_SHULKER_BOX:
            case LIME_SHULKER_BOX:
            case LIGHT_GRAY_SHULKER_BOX:
            case LIGHT_BLUE_SHULKER_BOX:
            case GREEN_SHULKER_BOX:
            case GRAY_SHULKER_BOX:
            case CYAN_SHULKER_BOX:
            case BROWN_SHULKER_BOX:
            case BLUE_SHULKER_BOX:
            case BLACK_SHULKER_BOX:
            case SHULKER_BOX:
            case LINGERING_POTION:
            case DRAGON_BREATH:
            case TURTLE_HELMET:
            case CREEPER_HEAD:
            case END_ROD:
            case END_STONE_BRICKS:
            case END_STONE_BRICK_WALL:
            case END_STONE_BRICK_STAIRS:
            case END_STONE_BRICK_SLAB:
            case NETHERITE_SCRAP:
            case ANCIENT_DEBRIS:
            case EMERALD_BLOCK:
            case DIAMOND_BLOCK:
            case ENCHANTING_TABLE:
            case ENCHANTED_BOOK:
            case ENDER_EYE:
            case PLAYER_HEAD:
            case ENDER_CHEST: return SkyblockRarity.RARE;

            // epic
            case CONDUIT:
            case NETHER_STAR:
            case TOTEM_OF_UNDYING:
            case ELYTRA:
            case DRAGON_HEAD:
            case WITHER_SKELETON_SKULL:
            case END_CRYSTAL:
            case NETHERITE_PICKAXE:
            case NETHERITE_LEGGINGS:
            case NETHERITE_SWORD:
            case NETHERITE_SHOVEL:
            case NETHERITE_INGOT:
            case NETHERITE_AXE:
            case NETHERITE_BOOTS:
            case NETHERITE_CHESTPLATE:
            case NETHERITE_HELMET:
            case NETHERITE_HOE: return SkyblockRarity.EPIC;

            // legendary
            case NETHERITE_BLOCK:
            case BEACON:
            case DRAGON_EGG: return SkyblockRarity.LEGENDARY;

            // special
            case SPAWNER:
            case BARRIER: return SkyblockRarity.SPECIAL;

            default: return SkyblockRarity.COMMON;
        }
    }

    public static @NotNull Multimap<Attribute, AttributeModifier> generateAttributes(Attribute attribute, AttributeModifier modifier){
        Multimap<Attribute, AttributeModifier> mm = ArrayListMultimap.create();
        mm.put(attribute, modifier);
        return mm;
    }

    public static boolean isOnCooldown(@Nullable ItemStack item, float cd, @NotNull Player p, boolean displayMessage) {
        if(item == null || !item.hasItemMeta()) return false;
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        PersistentDataContainer c = meta.getPersistentDataContainer();
        NamespacedKey cdKey = SkyblockD.getKey("lastUse");
        double time = System.currentTimeMillis() / 1000d;
        if(!c.has(cdKey, PersistentDataType.INTEGER)) {
            c.set(SkyblockD.getKey("lastUse"), PersistentDataType.INTEGER, (int) time);
            item.setItemMeta(meta);
            p.sendMessage(ChatColor.RED+"Please wait a bit before using this again!");
            return true;
        }
        Integer lastTime = c.get(cdKey, PersistentDataType.INTEGER);
        if (lastTime == null || lastTime == 0) {
            c.set(SkyblockD.getKey("lastUse"), PersistentDataType.INTEGER, (int) time);
            item.setItemMeta(meta);
            return false;
        }
        if (time - cd > lastTime) {
            c.set(SkyblockD.getKey("lastUse"), PersistentDataType.INTEGER, (int) time);
            item.setItemMeta(meta);
            return false;
        }
        float timeLeft = (float) time - lastTime;
        timeLeft = cd - timeLeft;
        if(displayMessage) p.sendMessage(ChatColor.RED+"Please wait " + Math.round(timeLeft) + "s before using this again!");
        return true;
    }

    public static boolean hasMagnet(@NotNull Player player) {
        return player.getInventory().contains(SkyblockMaterial.MAGIC_MAGNET.getItem());
    }

    public static boolean hasPress(@NotNull Player player) {
        return player.getInventory().contains(SkyblockMaterial.PERSONAL_COMPACTOR.getItem());
    }

    public static int calcMagicDamage(Player p, float base) {
        return Math.round(base*(UniversalHelper.getAbilityDamage(p)+50f)/10f);
    }

    public static void usePress(@NotNull Player p, @NotNull ItemStack i) {
        if(!hasPress(p)) return;

        String name = i.getType().name();
        try {
            SkyblockMaterial mat = SkyblockMaterial.valueOf("ENCHANTED_"+name);
            if(illegals.contains(mat.name())) {
                throw new IllegalArgumentException();
            }
            int amount = 0;
            for(ItemStack it : p.getInventory().getContents()) {
                if(it != null && it.getType() != Material.AIR) {
                    if (it.isSimilar(i) &&!Objects.requireNonNull(it.getItemMeta()).getPersistentDataContainer().has(SkyblockD.getKey("compacted"), PersistentDataType.BYTE))
                        amount += it.getAmount();
                }
            }
            if(amount < 9) return;
            amount -= (amount % 9);
            int crafted = amount / 9;
            ItemStack nonsb = new ItemStack(i.getType(), amount);
            CustomItem.toSkyblockItem(nonsb);
            p.getInventory().removeItem(nonsb);
            ItemStack craft = mat.getItem();
            craft.setAmount(crafted);
            p.getInventory().addItem(craft);
        } catch(IllegalArgumentException ignored) {}
    }

    public static int calculateDropChance(int base, int mod) {
        return Math.min(Math.round(base * (150f/mod)), base+50);
    }

    public static int getStatFromItems(@NotNull Player p, String stat) {
        PlayerInventory inv = p.getInventory();
        ItemStack mh = inv.getItemInMainHand();
        ItemStack[] armor = inv.getArmorContents();

        int total = 0;
        total += getStatItem(mh, stat);
        for(ItemStack ap : armor) {
            total += getStatItem(ap, stat);
        }

        return total;
    }

    public static boolean trySendRareDrop(@NotNull ItemStack drop, int chance, @NotNull Player p, DropRarity rarity) {
        Random r = new Random();
        int m = r.nextInt(chance);
        if(m <= 1) {
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
            String percented = Float.toString(round((1f/chance)*100)).replace(",", ".")+"%";
            p.sendMessage(rarity +""+ChatColor.AQUA+" ("+percented+" "+ SkyblockConstants.MAGIC_FIND+" Chance) "+ Objects.requireNonNull(drop.getItemMeta()).getDisplayName()+ChatColor.YELLOW+"!");
            if(p.getInventory().firstEmpty() != -1) {
                p.getInventory().addItem(drop);
            } else p.getWorld().dropItem(p.getLocation(), drop);
            if(chance > 500 || rarity.equals(DropRarity.INSANE) || rarity.equals(DropRarity.RNGESUS)) {
                Bukkit.broadcastMessage(p.getDisplayName()+ChatColor.YELLOW+" just got "+rarity.toString().replace("!", "")+ChatColor.RESET+" "+Objects.requireNonNull(drop.getItemMeta()).getDisplayName()+ChatColor.YELLOW+"!");
                for(Player pl : SkyblockD.getHost().getOnlinePlayers()) {
                    pl.playSound(pl.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
                }
            }
            return true;
        }
        return false;
    }

    public static void trySpawnRareMob(@NotNull EntitySummon summon, int chance, @NotNull Player p) {
        Random r = new Random();
        int m = r.nextInt(chance);
        if(m <= 1) {
            p.playSound(p.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 1, 1);
            p.sendMessage(ChatColor.GREEN+"A "+summon.getEntity().getName()+ChatColor.GREEN+" has spawned nearby!");
            int randX = r.nextInt(5);
            int randZ = r.nextInt(5);

            Location l = new Location(p.getWorld(), randX, p.getLocation().getBlockY()-1, randZ);

            if(l.getBlock().getType().isAir()) {
                l = p.getLocation();
            }

            Entity en = summon.getEntity().generate(p);
            Location loc = en.getLocation();
            en.teleport(l);
        }
    }

    private static Integer getStatItem(@Nullable ItemStack it, String statName) {
        if(it == null) return 0;
        ItemMeta meta = it.getItemMeta();
        if(meta == null) return 0;
        PersistentDataContainer c = meta.getPersistentDataContainer();

        if(!c.has(SkyblockD.getKey(statName), PersistentDataType.INTEGER)) return 0;

        return c.get(SkyblockD.getKey(statName), PersistentDataType.INTEGER);
    }

    public static boolean isUndead(@NotNull EntityType type) {
        switch(type){
            case WITHER:
            case STRAY:
            case HUSK:
            case WITHER_SKULL:
            case WITHER_SKELETON:
            case SKELETON_HORSE:
            case SKELETON:
            case ZOMBIFIED_PIGLIN:
            case ZOMBIE_VILLAGER:
            case ZOMBIE_HORSE:
            case ZOGLIN:
            case ZOMBIE: return true;
            default: return false;
        }
    }

    private static float round(float value) {
        BigDecimal bd = new BigDecimal(Float.toString(value));
        bd = bd.setScale(1, RoundingMode.HALF_UP);
        return bd.floatValue();
    }

    public static void addAttribute(Attribute att, double amount, ItemMeta m) {
        m.addAttributeModifier(att, new AttributeModifier(
                UUID.randomUUID(), att.getKey().getKey(), amount, AttributeModifier.Operation.ADD_NUMBER
        ));
    }

    public static void addAttribute(Attribute att, double amount, ItemMeta m, EquipmentSlot slot) {
        m.addAttributeModifier(att, new AttributeModifier(
                UUID.randomUUID(), att.getKey().getKey(), amount, AttributeModifier.Operation.ADD_NUMBER, slot
        ));
    }

    public enum DropRarity {
        UNCOMMON("UNCOMMON", ChatColor.GREEN),
        RARE("RARE", ChatColor.AQUA),
        SCRIPTED_RARE("RARE", ChatColor.GOLD),
        VERY_RARE("VERY RARE", ChatColor.DARK_PURPLE),
        SUPER_RARE("SUPER RARE", ChatColor.DARK_PURPLE),
        RNGESUS("CRAZY RARE", ChatColor.LIGHT_PURPLE),
        INSANE("INSANE", ChatColor.RED)
        ;
        public String name;
        public ChatColor color;

        DropRarity(String name, ChatColor color) {
            this.name = name;
            this.color = color;
        }

        @Override
        public @NotNull String toString() {
            return color + "" + ChatColor.BOLD + name + " DROP!";
        }
    }
}
