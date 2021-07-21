package space.maxus.skyblockd.helpers;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.items.CustomItem;
import space.maxus.skyblockd.objects.PlayerSkills;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;
import space.maxus.skyblockd.util.ItemGlint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
            "ENCHANTED_QUARTZ_BLOCK"
            ));

    public static ItemMeta applyGlint(ItemMeta in){
        NamespacedKey key = new NamespacedKey(SkyblockD.getInstance(), SkyblockD.getInstance().getDescription().getName());
        ItemGlint g = new ItemGlint(key);
        in.addEnchant(g, 1, true);
        return in;
    }

    public static SkyblockRarity getRarity(Material m){
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

    public static Multimap<Attribute, AttributeModifier> generateAttributes(Attribute attribute, AttributeModifier modifier){
        Multimap<Attribute, AttributeModifier> mm = ArrayListMultimap.create();
        mm.put(attribute, modifier);
        return mm;
    }

    public static boolean isOnCooldown(ItemStack item, float cd, Player p, boolean displayMessage) {
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
        if(displayMessage) p.sendMessage(ChatColor.RED+"Please wait " + timeLeft + "s before using this again!");
        return true;
    }

    public static boolean hasMagnet(Player player) {
        return player.getInventory().contains(SkyblockMaterial.MAGIC_MAGNET.getItem());
    }

    public static boolean hasPress(Player player) {
        return player.getInventory().contains(SkyblockMaterial.PERSONAL_COMPACTOR.getItem());
    }

    public static int calcMagicDamage(Player p, float base) {
        PlayerSkills skills = ContainerHelper.getPlayer(p).skills;
        int mystMod = skills.data.get("mysticism").currentLevel + 1;
        int craftMod = skills.data.get("crafting").currentLevel + 1;
        float dmg = ((craftMod + mystMod) * base) / 2f;
        return Math.round(dmg);
    }

    public static void usePress(Player p, ItemStack i) {
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
}
