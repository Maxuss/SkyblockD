package space.maxus.skyblockd.listeners;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.ItemHelper;
import space.maxus.skyblockd.helpers.MaterialHelper;
import space.maxus.skyblockd.helpers.UniversalHelper;
import space.maxus.skyblockd.items.CustomItem;
import space.maxus.skyblockd.objects.*;
import space.maxus.skyblockd.skyblock.entities.EntitySummon;
import space.maxus.skyblockd.skyblock.entities.SkyblockEntity;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;
import space.maxus.skyblockd.skyblock.utility.SkillHelper;
import space.maxus.skyblockd.util.WeightedList;

import java.util.*;

public class KillListener extends BetterListener {
    public static int endermanCounter = 0;

    @EventHandler
    public void onKill(EntityDeathEvent e) {
        LivingEntity en = e.getEntity();
        Player p = en.getKiller();
        if(p == null || p.hasMetadata("NPC")) return;
        List<ItemStack> drops = e.getDrops();

        if(en.getType() == EntityType.ENDER_DRAGON) {
            operateDragonLoot(en);
        } else if(en.getType() == EntityType.ENDERMAN && en.getWorld().getEnvironment() == World.Environment.THE_END) {
            endermanCounter++;
            ItemHelper.trySpawnRareMob(EntitySummon.CORRUPTED_FANATIC, 100, p);
            if(endermanCounter == 30) {
                p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_DEATH, 10, 0);
                Bukkit.broadcastMessage(ChatColor.RED+""+ChatColor.BOLD+"You hear distant tremor from beneath the earth");
            } else if(endermanCounter == 60) {
                p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_DEATH, 10, 0);
                Bukkit.broadcastMessage(ChatColor.RED+""+ChatColor.BOLD+"The tremor from the earth is becoming stronger");
            } else if(endermanCounter == 90) {
                p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_DEATH, 10, 0);
                Bukkit.broadcastMessage(ChatColor.RED+""+ChatColor.BOLD+"The ground starts shaking rapidly!");
            } else if(endermanCounter >= 100) {
                endermanCounter = 0;
                Bukkit.broadcastMessage(ChatColor.RED+""+ChatColor.BOLD+"Behold, the endstone protector arises from the earth!");
                LivingEntity golem = (LivingEntity) EntitySummon.ENDSTONE_PROTECTOR.getEntity().generate(p);
                golem.damage(0.1d, p);
                Location loc = new Location(golem.getWorld(), 25, 70, 25);
                golem.teleport(loc);
                golem.getWorld().playSound(golem.getLocation(), Sound.ENTITY_IRON_GOLEM_DEATH, 5, 0);
            }
        } else if(en.getPersistentDataContainer().has(SkyblockD.getKey("ENDSTONE_PROTECTOR"), PersistentDataType.BYTE)) {
            operateProtectorLoot();
        } else if(en.getType().equals(EntityType.WITHER)) {
            operateWitherLoot(SkyblockEntity.WitherType.values()[en.getPersistentDataContainer().get(SkyblockD.getKey("witherType"), PersistentDataType.INTEGER)]);
        }

        if(en.getPersistentDataContainer().has(SkyblockD.getKey("FANATIC"), PersistentDataType.BYTE)) {
            ItemHelper.trySendRareDrop(SkyblockMaterial.SHADED_EYE.getItem(), 1, p, ItemHelper.DropRarity.SCRIPTED_RARE);
        }

        if(ItemHelper.hasMagnet(p) && !drops.isEmpty()) {
            for (int i = 0; i < drops.size(); i++) {
                if(p.getInventory().firstEmpty() != -1) {
                    ItemStack item = drops.get(i);
                    if(item == null) return;
                    assert item.getItemMeta() != null;
                    if(!item.getItemMeta().getPersistentDataContainer().has(SkyblockD.getKey("skyblockNative"), PersistentDataType.STRING)) {
                        CustomItem.toSkyblockItem(item);
                    }
                    p.getInventory().addItem(item);
                    ItemHelper.usePress(p, item);
                    drops.set(i, new ItemStack(Material.AIR));
                } else {
                    for (ItemStack left : drops) {
                        if(left.getType() != Material.AIR) p.getWorld().dropItemNaturally(en.getLocation(), left);
                        drops.remove(left);
                    }
                    break;
                }
            }
        }
        List<PlayerContainer> players = UniversalHelper.filter(SkyblockD.players, c -> c.uuid.equals(p.getUniqueId()));
        PlayerContainer pc = players.get(players.size()-1);

        if(en.getPersistentDataContainer().has(SkyblockD.getKey("FISHED"), PersistentDataType.BYTE)) {
            Set<NamespacedKey> keys = en.getPersistentDataContainer().getKeys();
            // removing all keys except for one we need
            keys.remove(SkyblockD.getKey("FISHED"));
            keys.remove(SkyblockD.getKey("skyblockNative"));
            keys.remove(SkyblockD.getKey("entityLevel"));
            keys.remove(SkyblockD.getKey("entityName"));
            List<NamespacedKey> tkeys = new ArrayList<>(keys);
            NamespacedKey fishingEntity = tkeys.get(tkeys.size()-1);
            String tkey = fishingEntity.getKey().replace("skyblockd:", "");

            SeaCreatureContainer scc = SkyblockD.getServerData().fishingData.mobs.skyblock.get(tkey.toUpperCase());
            int xp = scc.exp;

            SkillContainer combat = pc.skills.data.get("fishing");
            int lvl = combat.currentLevel;
            int tlvl = lvl == 0 ? 1 : lvl;
            float modifier = SkillHelper.getModifier(tlvl);

            float exp = (xp+5)*modifier;

            operateSkill("fishing", (int) exp, p, pc);

            return;
        }

        SkillContainer combat = pc.skills.data.get("combat");
        int lvl = combat.currentLevel;
        int tlvl = lvl == 0 ? 1 : lvl;
        float modifier = SkillHelper.getModifier(tlvl);

        int baseExp = MaterialHelper.matchCombat(en.getType());
        float exp = modifier * baseExp;

        int fullExp = (int) ((int) Math.round(Objects.requireNonNull(en.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue() / 6) + exp);

        operateSkill("combat", fullExp, p, pc);
    }

    public static void operateWitherLoot(SkyblockEntity.WitherType witherType) {
        if(DamageListener.witherDamagers.isEmpty()) {
            Bukkit.broadcastMessage(ChatColor.RED+"Wither was defeated by mysterious force! It's spirit seeks for revenge!");
            return;
        }
        HashMap<UUID, Double> damagers = DamageListener.witherDamagers;
        LinkedHashMap<UUID, Double> parsed = new LinkedHashMap<>(damagers);
        int i = 0;

        List<String> displays = new ArrayList<>();
        displays.add(ChatColor.RED+
                "-----------------------------------------------------");
        displays.add(ChatColor.DARK_RED+""+ChatColor.BOLD+
                "                      "+ChatColor.stripColor(witherType.name())+" DOWN!                ");
        displays.add(ChatColor.GOLD+
                "                         Most Damage dealt:");
        displays.add(" ");

        HashMap<UUID, WeightedList<ItemStack>> drops = new HashMap<>();

        for(Map.Entry<UUID, Double> ent : parsed.entrySet()) {
            UUID id = ent.getKey();
            Double dmg = ent.getValue();
            drops.put(id, WeightedList.getWitherDrops(witherType));

            if(i >= 3) break;

            if(Bukkit.getPlayer(id) == null) continue;

            String playerName = Objects.requireNonNull(Bukkit.getPlayer(id)).getDisplayName();
            displays.add("  "+ChatColor.YELLOW+""+ChatColor.BOLD+(i+1)+". Place: "+ChatColor.RESET+playerName+ChatColor.YELLOW+" "+Math.round(dmg)*5+" Damage");
            i++;
        }
        displays.add(ChatColor.RED+
                "-----------------------------------------------------");

        displays.add(" ");

        for (Map.Entry<UUID, WeightedList<ItemStack>> entry: drops.entrySet()) {
            Player p = Bukkit.getPlayer(entry.getKey());
            if(p == null) continue;
            WeightedList<ItemStack> items = entry.getValue();

            Random prand = new Random();

            ItemHelper.trySendRareDrop(SkyblockMaterial.THE_SEAL.getItem(), 500, p, ItemHelper.DropRarity.INSANE);

            for(int j = 0; j < 1; j++) {
                ItemStack dropped = items.get(prand);
                assert dropped.getItemMeta() != null;
                displays.add(p.getDisplayName()+ChatColor.RED+" has obtained "+dropped.getItemMeta().getDisplayName()+" "+dropped.getAmount()+"x ");
                if(p.getInventory().firstEmpty() != -1) {
                    p.getInventory().addItem(dropped);
                } else p.getWorld().dropItemNaturally(p.getLocation(), dropped);
            }
            ItemStack ecoal = SkyblockMaterial.ENCHANTED_COAL.getItem().clone();
            ecoal.setAmount(new Random().nextInt(1)+2);
            if(p.getInventory().firstEmpty() != -1) {
                p.getInventory().addItem(ecoal);
            } else {
                p.getWorld().dropItemNaturally(p.getLocation(), ecoal);
            }
        }

        Bukkit.broadcastMessage(String.join("\n", displays));
        DamageListener.protectorDamagers.clear();
    }

    public static void operateProtectorLoot() {
        if(DamageListener.protectorDamagers.isEmpty()) {
            Bukkit.broadcastMessage(ChatColor.GOLD+"Endstone protector was defeated by mysterious force!");
            return;
        }
        HashMap<UUID, Double> damagers = DamageListener.protectorDamagers;
        LinkedHashMap<UUID, Double> parsed = new LinkedHashMap<>(damagers);
        int i = 0;

        List<String> displays = new ArrayList<>();
        displays.add(ChatColor.YELLOW+
                "-----------------------------------------------------");
        displays.add(ChatColor.GOLD+""+ChatColor.BOLD+
                "                  ENDSTONE PROTECTOR DOWN!                ");
        displays.add(ChatColor.GREEN+
                "                         Most Damage dealt:");
        displays.add(" ");

        HashMap<UUID, WeightedList<ItemStack>> drops = new HashMap<>();

        for(Map.Entry<UUID, Double> ent : parsed.entrySet()) {
            UUID id = ent.getKey();
            Double dmg = ent.getValue();

            drops.put(id, WeightedList.getProtectorDrops());

            if(i >= 3) break;

            if(Bukkit.getPlayer(id) == null) continue;

            String playerName = Objects.requireNonNull(Bukkit.getPlayer(id)).getDisplayName();
            displays.add("  "+ChatColor.YELLOW+""+ChatColor.BOLD+(i+1)+". Place: "+ChatColor.RESET+playerName+ChatColor.YELLOW+" "+Math.round(dmg)*5+" Damage");
            i++;
        }
        displays.add(ChatColor.YELLOW+
                "-----------------------------------------------------");

        displays.add(" ");

        for (Map.Entry<UUID, WeightedList<ItemStack>> entry: drops.entrySet()) {
            Player p = Bukkit.getPlayer(entry.getKey());
            if(p == null) continue;
            WeightedList<ItemStack> items = entry.getValue();

            Random prand = new Random();

            for(int j = 0; j < 1; j++) {
                ItemStack dropped = items.get(prand);
                assert dropped.getItemMeta() != null;
                displays.add(p.getDisplayName()+ChatColor.YELLOW+" has obtained "+dropped.getItemMeta().getDisplayName()+" "+dropped.getAmount()+"x ");
                if(p.getInventory().firstEmpty() != -1) {
                    p.getInventory().addItem(dropped);
                } else p.getWorld().dropItemNaturally(p.getLocation(), dropped);
            }
            ItemStack moonstone = SkyblockMaterial.MOON_STONE.getItem().clone();
            moonstone.setAmount(new Random().nextInt(1)+3);
            if(p.getInventory().firstEmpty() != -1) {
                p.getInventory().addItem(moonstone);
            } else {
                p.getWorld().dropItemNaturally(p.getLocation(), moonstone);
            }
        }

        Bukkit.broadcastMessage(String.join("\n", displays));
        DamageListener.protectorDamagers.clear();
    }

    public static void operateDragonLoot(LivingEntity edrag) {
        if(DamageListener.dragonDamagers.isEmpty()) {
            Bukkit.broadcastMessage(ChatColor.GOLD+"The Dragon was defeated by mysterious force!");
            return;
        }
        HashMap<UUID, Double> damagers = DamageListener.dragonDamagers;
        LinkedHashMap<UUID, Double> parsed = new LinkedHashMap<>(damagers);
        int i = 0;

        List<String> displays = new ArrayList<>();
        displays.add(ChatColor.LIGHT_PURPLE+
                     "-----------------------------------------------------");
        displays.add(ChatColor.YELLOW+""+ChatColor.BOLD+
                     "                  ENDER DRAGON DOWN!                ");
        displays.add(ChatColor.GOLD+
                     "                         Most Damage dealt:");
        displays.add(" ");

        HashMap<UUID, List<WeightedList<ItemStack>>> drops = new HashMap<>();
        DragonLoot loot = SkyblockD.getServerData().dragonLoot;
        for(Map.Entry<UUID, Double> ent : parsed.entrySet()) {
            UUID id = ent.getKey();
            Double dmg = ent.getValue();
            float weight = (float) (dmg / 1600f);

            List<WeightedList<ItemStack>> l = new ArrayList<>();
            l.add(WeightedList.getDragonDrops(loot, false, weight));
            l.add(WeightedList.getDragonDrops(loot, true, weight));
            drops.put(id, l);

            if(i >= 3) break;

            if(Bukkit.getPlayer(id) == null) continue;

            String playerName = Objects.requireNonNull(Bukkit.getPlayer(id)).getDisplayName();
            displays.add("  "+ChatColor.YELLOW+""+ChatColor.BOLD+(i+1)+". Place: "+ChatColor.RESET+playerName+ChatColor.YELLOW+" "+Math.round(dmg)*5+" Damage");
            i++;
        }
        displays.add(ChatColor.LIGHT_PURPLE+
                "-----------------------------------------------------");

        displays.add(" ");

        for (Map.Entry<UUID, List<WeightedList<ItemStack>>> entry: drops.entrySet()) {
            Player p = Bukkit.getPlayer(entry.getKey());
            if(p == null) continue;
            WeightedList<ItemStack> vanilla = entry.getValue().get(0);
            WeightedList<ItemStack> sb = entry.getValue().get(1);

            Random prand = new Random();

            for(int j = 0; j < 1; j++) {
                ItemStack dropped = vanilla.get(prand);
                CustomItem.toSkyblockItem(dropped);
                assert dropped.getItemMeta() != null;
                if(p.getInventory().firstEmpty() != -1) {
                    p.getInventory().addItem(dropped);
                } else p.getWorld().dropItemNaturally(p.getLocation(), dropped);
            }
            for(int j = 0; j < 1; j++) {
                ItemStack dropped = sb.get(prand);
                assert dropped.getItemMeta() != null;
                displays.add(p.getDisplayName()+ChatColor.YELLOW+" has obtained "+dropped.getItemMeta().getDisplayName()+" "+dropped.getAmount()+"x ");
                if(p.getInventory().firstEmpty() != -1) {
                    p.getInventory().addItem(dropped);
                } else p.getWorld().dropItemNaturally(p.getLocation(), dropped);
            }
            ItemStack guaranteed = WeightedList.getGuaranteedDrop(loot);
            ItemStack moonstone = SkyblockMaterial.MOON_STONE.getItem().clone();
            moonstone.setAmount(new Random().nextInt(1)+3);
            if(p.getInventory().firstEmpty() != -1) {
                p.getInventory().addItem(guaranteed);
                p.getInventory().addItem(moonstone);
            } else {
                p.getWorld().dropItemNaturally(p.getLocation(), guaranteed);
                p.getWorld().dropItemNaturally(p.getLocation(), moonstone);
            }
        }

        Bukkit.broadcastMessage(String.join("\n", displays));
        DamageListener.dragonDamagers.clear();
    }

    private void operateSkill(String name, int xp, Player p, PlayerContainer pc) {
        SkillContainer combat = pc.skills.data.get(name);
        int lvl = combat.currentLevel;
        int tlvl = lvl == 0 ? 1 : lvl;
        float modifier = SkillHelper.getModifier(tlvl);
        UniversalHelper.giveSkillExperience(p, name, Math.round(modifier*xp));
    }
}
