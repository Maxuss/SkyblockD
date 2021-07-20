package space.maxus.skyblockd.events;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.gui.MainMenuGUI;
import space.maxus.skyblockd.gui.SkillsGui;
import space.maxus.skyblockd.helpers.*;
import space.maxus.skyblockd.nms.NMSColor;
import space.maxus.skyblockd.nms.PacketUtils;
import space.maxus.skyblockd.objects.PlayerContainer;
import space.maxus.skyblockd.objects.SkillContainer;
import space.maxus.skyblockd.skyblock.elixirs.ElixirEffect;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.skills.created.*;
import space.maxus.skyblockd.skyblock.utility.SkillHelper;

import java.util.*;

import static org.bukkit.event.Event.Result;

public class InventoryListener extends BetterListener{

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        String title = e.getView().getTitle();
        if (title.equalsIgnoreCase(ChatColor.DARK_GRAY + "SkyblockD Menu")) {
            menu(e, p);
        } else if (title.equalsIgnoreCase("Recombobulate an item")) {
            recombobulator(e, p);
        } else if (title.equalsIgnoreCase("Your Skills")) {
            skills(e, p);
        } else if (title.contains("Skill")) {
            skillClaim(e, p);
        } else if (title.equalsIgnoreCase("Elixir Brewer")) {
            elixirs(e, p);
        }
    }

    private void elixirs(InventoryClickEvent e, Player p) {
        ItemStack is = e.getCurrentItem();
        e.setResult(Result.DENY);
        if(is != null) {
            String item = ChatColor.stripColor(Objects.requireNonNull(is.getItemMeta()).getDisplayName());
            Inventory ui = e.getInventory();
            if(item.equals("Brew")) {
                ItemStack ingredient = ui.getItem(13);
                ItemStack base = ui.getItem(40);
                if(ingredient == null) {
                    p.sendMessage(ChatColor.RED+"Please put an ingredient!");
                    return;
                }
                if(base == null || base.getType() != Material.HONEY_BOTTLE) {
                    p.sendMessage(ChatColor.RED+"Please put bottle of honey to apply effects to!");
                    return;
                }

                PersistentDataContainer lc = Objects.requireNonNull(ingredient.getItemMeta()).getPersistentDataContainer();
                PersistentDataContainer bc = Objects.requireNonNull(base.getItemMeta()).getPersistentDataContainer();

                NamespacedKey sb = SkyblockD.getKey("skyblockNative");

                if(!lc.has(sb, PersistentDataType.STRING) ||
                        !bc.has(sb, PersistentDataType.STRING)) {
                    p.sendMessage(ChatColor.GRAY+"You can not use this items as ingredients!");
                    p.sendMessage(ChatColor.GRAY+"Tip: try dropping the item on ground and trying again!");
                    return;
                }

                ItemStack pot = new ItemStack(Material.POTION, 1);
                PotionMeta m = (PotionMeta) pot.getItemMeta();
                assert m != null;
                ItemHelper.applyGlint(m);
                m.setColor(Color.fromRGB(108,34,122));
                pot.setItemMeta(GuiHelper.setHideAllFlags(m));
                pot.setItemMeta(GuiHelper.setHideAllFlags(Objects.requireNonNull(pot.getItemMeta())));
                ElixirEffect eff = MaterialHelper.getEffect(ingredient, pot);

                ui.setItem(40, eff.getItem());
                ui.setItem(13, null);

                p.sendMessage(ChatColor.GREEN + "Brewing finished! Your elixir received following effect: " + eff.getEffectName());
                p.playSound(p.getLocation(), Sound.BLOCK_BREWING_STAND_BREW, 1, 0.5f);

                List<PlayerContainer> players = UniversalHelper.filter(SkyblockD.players, c -> c.uuid.equals(p.getUniqueId()));
                PlayerContainer pc = players.get(players.size()-1);

                SkillContainer mysticism = pc.skills.data.get("mysticism");
                int lvl = mysticism.currentLevel;
                int tlvl = lvl == 0 ? 1 : lvl;
                float modifier = SkillHelper.getModifier(tlvl);

                float exp = modifier * 25.5f * (new Random().nextFloat() + 1);

                double d = Math.floor(exp);

                String sxp = String.valueOf(d).replace(",", ".");

                PacketUtils.sendActionbar(p, "+"+sxp+" Mysticism Experience", NMSColor.DARK_AQUA);
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 2);

                pc.skills.totalExp += exp;

                mysticism.totalExp += exp;
                mysticism.levelExp += exp;
                int toNext = SkyblockD.getMapManager().getMaps().get("mysticism").getExperience().table
                        .get(mysticism.currentLevel + 1);
                int div = mysticism.levelExp - toNext;
                if(div >= 0) {
                    mysticism.levelExp = div;
                    p.sendMessage(new String[]{
                                    ChatColor.GOLD + "" + ChatColor.BOLD + "-----------------------------",
                                    ChatColor.YELLOW + "" + ChatColor.BOLD + "MYSTICISM LEVEL UP!",
                                    " ",
                                    ChatColor.GREEN + "You are now combat level " + (mysticism.currentLevel + 1) + "!",
                                    ChatColor.GREEN + "Check out new level rewards in Skyblock Menu!",
                                    ChatColor.GOLD + "" + ChatColor.BOLD + "-----------------------------"
                            }
                    );
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 0.5f);
                    int level = mysticism.currentLevel;
                    mysticism.currentLevel++;
                    String levl = "unlocked."+level;
                    mysticism.collectedRewards.put(levl, true);
                    ContainerHelper.updatePlayers();
                    setPlayer(pc, p);
                }
            }
            else {
                if(is.getType() == Material.LIME_STAINED_GLASS_PANE || is.getType() == Material.GRAY_STAINED_GLASS_PANE) return;
                else if(is.getType() == Material.HONEY_BOTTLE && e.getClickedInventory() == p.getInventory()) {
                    if(ui.getItem(40) != null) {
                        p.getInventory().addItem(ui.getItem(40));
                    }
                    ui.setItem(40, is);
                    removeItem(is, p.getInventory(), is.getAmount());
                } else if(e.getClickedInventory() == p.getInventory()){
                    if(ui.getItem(13) != null) {
                        p.getInventory().addItem(ui.getItem(13));
                    }
                    ui.setItem(13, is);
                    removeItem(is, p.getInventory(), is.getAmount());
                } else {
                    p.getInventory().addItem(is);
                    ui.remove(is);
                }
                p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1.5f);
            }
        }
    }

    private void removeItem(ItemStack item, Inventory i, int amount) {
        for(ItemStack it : i.getContents()) {
            if(it != null && it.getType() != Material.AIR && it.isSimilar(item)) {
                it.setAmount(it.getAmount()-amount);
                it.setType(Material.AIR);
                return;
            }
        }
    }

    private void setPlayer(PlayerContainer p, Player pl){
        List<PlayerContainer> conts = UniversalHelper.filter(SkyblockD.getPlayers(), c -> c.uuid.equals(pl.getUniqueId()));
        SkyblockD.players.remove(conts.get(conts.size() - 1));
        SkyblockD.players.add(p);
        ContainerHelper.updatePlayers();
    }

    private void skills(InventoryClickEvent e, Player p){
        if(e.getCurrentItem() == null) return;
        e.setCancelled(true);
        switch(ChatColor.stripColor(Objects.requireNonNull(e.getCurrentItem().getItemMeta()).getDisplayName())) {
            // added skills
            case "Combat":
                p.closeInventory();
                p.openInventory(new Combat(p).generateMenu());
                break;
            case "Foraging":
                p.closeInventory();
                p.openInventory(new Foraging(p).generateMenu());
                break;
            case "Excavating":
                p.closeInventory();
                p.openInventory(new Excavating(p).generateMenu());
                break;
            case "Mining":
                p.closeInventory();
                p.openInventory(new Mining(p).generateMenu());
                break;
            case "Mysticism":
                p.closeInventory();
                p.openInventory(new Mysticism(p).generateMenu());
                break;
            case "Crafting":
                p.closeInventory();
                p.openInventory(new Crafting(p).generateMenu());
                break;
            case "Farming":
                p.closeInventory();
                p.openInventory(new Farming(p).generateMenu());
                break;
            case "Go back":
                MainMenuGUI mmg = new MainMenuGUI();
                mmg.setPlayer(p);
                p.closeInventory();
                p.openInventory(mmg.generateContains(Bukkit.createInventory(mmg.getHolder(p), mmg.getSize(), mmg.getName())));
                break;
            default: break;
        }
    }

    private void menu(InventoryClickEvent e, Player p) {
        if(e.getCurrentItem() == null) return;
        if(Objects.requireNonNull(e.getCurrentItem().getItemMeta())
                .getDisplayName().equalsIgnoreCase(ChatColor.GREEN+"Your Skills")) {
            SkillsGui ui = new SkillsGui();
            ui.setPlayer(p);
            Inventory inv = Bukkit.createInventory(ui.getHolder(p), ui.getSize(), ui.getName());
            p.openInventory(ui.generateContains(inv));
        }
        e.setResult(Result.DENY);
        p.updateInventory();
    }

    private void recombobulator(InventoryClickEvent e, Player p) {
        ItemStack i = e.getCurrentItem();
        if (i != null) {
            PersistentDataContainer c = Objects.requireNonNull(i.getItemMeta()).getPersistentDataContainer();
            String name = ChatColor.stripColor(Objects.requireNonNull(i.getItemMeta()).getDisplayName());
            Inventory inv = e.getInventory();
            if (c.has(SkyblockD.getKey("recombobulated"), PersistentDataType.STRING)) {
                p.sendMessage(ChatColor.RED+"You can not recombobulate an item twice!");
            } else if(!c.has(SkyblockD.getKey("itemRarity"), PersistentDataType.INTEGER)){
                p.sendMessage(ChatColor.RED+"This item can not be recombobulated!");
            } else if(i.getAmount() > 1){
                p.sendMessage(ChatColor.RED+"You can only recombobulate one item at a time!");
            } else if(
                    !name.contains("Recombobulator")
                            && i.getType() != Material.GRAY_STAINED_GLASS_PANE
                            && i.getType() != Material.ANVIL) {
                if(inv.getItem(13) != null){
                    p.getInventory().addItem(inv.getItem(13));
                }
                inv.setItem(13, i);
                p.getInventory().remove(i);
            }

            if (name.equalsIgnoreCase("Recombobulate!")){
                ItemStack lable = inv.getItem(13);
                if(lable == null){
                    p.sendMessage(ChatColor.RED+"Please choose an item to recombobulate!");
                } else{
                    ItemStack recombed = SkyblockItem.recombobulate(lable);
                    p.getInventory().addItem(recombed);
                    p.closeInventory();
                    p.sendMessage(ChatColor.GOLD+"Successfully upgraded rarity of " + Objects.requireNonNull(lable.getItemMeta()).getDisplayName()+ChatColor.GOLD+"!");
                    return;
                }
            }
            e.setResult(Result.DENY);
            p.updateInventory();
        }
    }

    private void skillClaim(InventoryClickEvent e, Player p) {
        e.setResult(Result.DENY);
        p.updateInventory();
        if(e.getCurrentItem() != null && ChatColor.stripColor(Objects.requireNonNull(e.getCurrentItem().getItemMeta()).getDisplayName()).equals("Go back")) {
            SkillsGui m = new SkillsGui();
            m.setPlayer(p);
            Inventory inv = Bukkit.createInventory(p, m.getSize(), m.getName());
            m.generateContains(inv);
            p.openInventory(inv);
            p.updateInventory();
        }
        String skill = ChatColor.stripColor(e.getView().getTitle().replace(" Skill", "")).toLowerCase(Locale.ENGLISH);
        int index = e.getSlot();

        List<Integer> mgl = Arrays.asList(
                0, 1, 2, 3, 4, 5, 6, 7, 8,
                9, 17, 18, 26, 27, 35, 36,
                44, 45, 46, 47, 48, 49
        );

        if(mgl.contains(index)) return;

        // -10
        List<Integer> row1 = Arrays.asList(
                10, 11, 12, 13, 14, 15, 16
        );
        // -12
        List<Integer> row2 = Arrays.asList(
                19, 20, 21, 22, 23, 24, 25
        );
        // -14
        List<Integer> row3 = Arrays.asList(
                28, 29, 30, 31, 32, 33, 34
        );
        // -16
        List<Integer> row4 = Arrays.asList(
                37, 38, 39, 40, 41, 42, 43
        );

        if(row1.contains(index)) index -= 10;
        else if(row2.contains(index)) index -= 12;
        else if(row3.contains(index)) index -= 14;
        else if(row4.contains(index)) index -= 16;

        switch(skill) {
            case "mining":
                new Mining(p).claimReward(index, p);
                break;
            case "foraging":
                new Foraging(p).claimReward(index, p);
                break;
            case "excavating":
                new Excavating(p).claimReward(index, p);
                break;
            case "combat":
                new Combat(p).claimReward(index, p);
                break;
            case "mysticism":
                new Mysticism(p).claimReward(index, p);
                break;
            case "farming":
                new Farming(p).claimReward(index, p);
                break;
            case "crafting":
                new Crafting(p).claimReward(index, p);
                break;
            default:
                break;
        }
    }
}
