package space.maxus.skyblockd.listeners;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.gui.*;
import space.maxus.skyblockd.helpers.*;
import space.maxus.skyblockd.nms.BaseByteUtils;
import space.maxus.skyblockd.objects.BetterListener;
import space.maxus.skyblockd.objects.PlayerContainer;
import space.maxus.skyblockd.objects.SkillContainer;
import space.maxus.skyblockd.objects.WardrobeSlot;
import space.maxus.skyblockd.skyblock.elixirs.ElixirEffect;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.reforges.SkyblockReforge;
import space.maxus.skyblockd.skyblock.skills.created.*;
import space.maxus.skyblockd.skyblock.utility.SkillHelper;

import java.util.*;
import java.util.stream.Collectors;

import static org.bukkit.event.Event.Result;
@SuppressWarnings("deprecation")
public class InventoryListener extends BetterListener {

    @EventHandler
    public void onClose(@NotNull final InventoryCloseEvent e) {
        Inventory inv = e.getInventory();
        String title = ChatColor.stripColor(e.getView().getTitle());
        if(title.equalsIgnoreCase("Wardrobe")) {
            if(!(e.getPlayer() instanceof Player)) return;
            Player p = (Player) e.getPlayer();
            PlayerContainer pc = ContainerHelper.getPlayer(p);
            List<WardrobeSlot> wardrobe = pc.wardrobe;

            for(int i = 0; i < 7; i++) {
                ItemStack he = inv.getItem(10+i);
                ItemStack ch = inv.getItem(19+i);
                ItemStack le = inv.getItem(28+i);
                ItemStack bo = inv.getItem(37+i);
                WardrobeSlot cur = wardrobe.get(i);
                ItemStack trueHelm = he == null || he.getType().equals(Material.LIME_STAINED_GLASS_PANE) ? null : he;
                ItemStack trueChest = ch == null || ch.getType().equals(Material.LIME_STAINED_GLASS_PANE) ? null : ch;
                ItemStack trueLeg = le == null || le.getType().equals(Material.LIME_STAINED_GLASS_PANE) ? null : le;
                ItemStack trueBoots = bo == null || bo.getType().equals(Material.LIME_STAINED_GLASS_PANE) ? null : bo;
                String data = BaseByteUtils.itemStackArrayToBase64(new ItemStack[] {trueHelm, trueChest, trueLeg, trueBoots});
                cur.setContents(data);
            }

            ContainerHelper.updatePlayers();
            UniversalHelper.setPlayer(pc, p);
            ContainerHelper.updatePlayers();
        }
    }

    @EventHandler
    public void onClick(@NotNull InventoryClickEvent e) {
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
        } else if (title.contains("The Vault")) {
            vault(e, p, title);
        } else if (title.contains("Vault Get")) {
            vaultGet(e, p);
        } else if (title.equalsIgnoreCase("Reforge Item")) {
            reforge(e, p);
        } else if(title.contains("Recipe Browser")) {
            recipeBrowser(e, p, title);
        } else if(title.equalsIgnoreCase("View Recipe")) {
            itemRecipe(e, p);
        } else if(title.equalsIgnoreCase("Wardrobe")) {
            wardrobe(e, p);
        }
    }

    private void wardrobe(InventoryClickEvent e, Player p) {
        ItemStack held = p.getItemOnCursor();
        ItemStack cur = e.getCurrentItem();
        Inventory inv = e.getInventory();
        if(cur == null) return;
        e.setResult(Result.DENY);
        if(held.getType().isEmpty() || !held.hasItemMeta()) {
            String name = ChatColor.stripColor(cur.getItemMeta().getDisplayName());
            ItemStack put = GuiHelper.genSimpleMenuItem(
                    " ", Material.LIME_STAINED_GLASS_PANE, Collections.emptyList());
            if(name.equalsIgnoreCase("Select this set")) {
                int index = e.getSlot();
                PlayerInventory pi = p.getInventory();
                ItemStack boot = inv.getItem(index-9);
                ItemStack leggings = inv.getItem(index-18);
                ItemStack chest = inv.getItem(index-27);
                ItemStack helm = inv.getItem(index-36);
                ItemStack pboot = pi.getBoots();
                ItemStack pleg = pi.getLeggings();
                ItemStack pchest = pi.getChestplate();
                ItemStack phelm = pi.getHelmet();

                ItemStack newBoot = boot == null || boot.getType().equals(Material.LIME_STAINED_GLASS_PANE) ? new ItemStack(Material.AIR) : boot;
                ItemStack newLeg = leggings == null || leggings.getType().equals(Material.LIME_STAINED_GLASS_PANE) ? new ItemStack(Material.AIR) : leggings;
                ItemStack newChest = chest == null || chest.getType().equals(Material.LIME_STAINED_GLASS_PANE) ? new ItemStack(Material.AIR) : chest;
                ItemStack newHelm = helm == null || helm.getType().equals(Material.LIME_STAINED_GLASS_PANE) ? new ItemStack(Material.AIR) : helm;

                ItemStack repBoot = pboot == null || pboot.getType().isAir() ? put.clone() : pboot;
                ItemStack repLeg = pleg == null || pleg.getType().isAir() ? put.clone() : pleg;
                ItemStack repChest = pchest == null || pchest.getType().isAir() ? put.clone() : pchest;
                ItemStack repHelm = phelm == null || phelm.getType().isAir() ? put.clone() : phelm;

                inv.setItem(index-9, repBoot);
                inv.setItem(index-18, repLeg);
                inv.setItem(index-27, repChest);
                inv.setItem(index-36, repHelm);

                pi.setBoots(newBoot);
                pi.setLeggings(newLeg);
                pi.setChestplate(newChest);
                pi.setHelmet(newHelm);

                p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, 0.5f, 1);

            }
        }
    }

    private void itemRecipe(@NotNull InventoryClickEvent e, @NotNull Player p) {
        e.setResult(Result.DENY);
        if(e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.GRAY_STAINED_GLASS_PANE) return;
        ItemStack cur = e.getCurrentItem();
        String itemName = ChatColor.stripColor(Objects.requireNonNull(cur.getItemMeta()).getDisplayName()).toLowerCase(Locale.ENGLISH);

        if(itemName.equals("go back")) {
            p.openInventory(new RecipeGuiTemplate().create(0, p));
            p.updateInventory();
            return;
        }

        List<Recipe> possibleRecipes = SkyblockD.getHost().getRecipesFor(cur).stream().filter(rec -> rec instanceof ShapedRecipe || rec instanceof ShapelessRecipe).collect(Collectors.toList());
        if(possibleRecipes.isEmpty()) return;

        Recipe recipe = possibleRecipes.get(0);
        Inventory inv = new RecipeGuiTemplate.ItemRecipeTemplate().create(recipe, p);
        p.openInventory(inv);

        p.updateInventory();
    }

    private void recipeBrowser(@NotNull InventoryClickEvent e, @NotNull Player p, @NotNull String title) {
        if(e.getCurrentItem() == null || e.getCurrentItem().getType().isAir()) return;
        int curPage = Integer.parseInt(title.replace("Recipe Browser ", ""));

        ItemStack cur = e.getCurrentItem();
        RecipeGuiTemplate temp = new RecipeGuiTemplate();
        String itemName = ChatColor.stripColor(Objects.requireNonNull(cur.getItemMeta()).getDisplayName()).toLowerCase(Locale.ENGLISH);
        e.setResult(Result.DENY);
        if(itemName.equals("previous page")) {
            curPage -= curPage <= 0 ? 0 : 1;
            Inventory previousPage = temp.create(curPage, p);
            p.closeInventory();
            p.openInventory(previousPage);
            p.updateInventory();
        } else if(itemName.equals("next page")) {
            curPage++;
            Inventory nextPage = temp.create(curPage, p);
            p.closeInventory();
            p.openInventory(nextPage);
            p.updateInventory();
        } else if(e.getCurrentItem().getType() == Material.GRAY_STAINED_GLASS_PANE) {
            p.updateInventory();
        } else if(Objects.requireNonNull(e.getClickedInventory()).getSize() == 54) {
            p.updateInventory();
            p.openInventory(new RecipeGuiTemplate.ItemRecipeTemplate().create(e.getCurrentItem(), p));
            p.updateInventory();
        }
    }

    private void reforge(@NotNull InventoryClickEvent e, @NotNull Player p) {
        if(e.getClickedInventory() == null) return;
        if(e.getClickedInventory().getSize() == 54) {
            if(e.getSlot() != 34 && e.getSlot() != 28) e.setResult(Result.DENY);
            Inventory inv = e.getClickedInventory();
            ItemStack i = e.getCurrentItem();
            if(i == null || i.getType() == Material.AIR || !i.hasItemMeta()) return;
            if(ChatColor.stripColor(Objects.requireNonNull(i.getItemMeta()).getDisplayName()).equalsIgnoreCase("Reforge")) {
                ItemStack stone = inv.getItem(28);
                ItemStack tool = inv.getItem(34);
                if (tool == null || !tool.hasItemMeta() ||
                        !Objects.requireNonNull(tool.getItemMeta())
                                .getPersistentDataContainer()
                                .has(SkyblockD.getKey("skyblockNative"), PersistentDataType.STRING)) {
                    p.sendMessage(ChatColor.RED + "Put an item to be reforged on the right!");
                    return;
                }
                if (stone == null || !stone.hasItemMeta() ||
                        !Objects.requireNonNull(stone.getItemMeta())
                                .getPersistentDataContainer()
                                .has(SkyblockD.getKey("reforgeStone"), PersistentDataType.INTEGER)) {
                    p.sendMessage(ChatColor.RED + "Put reforge stone on the right!");
                    return;
                }
                if (tool.getType().isBlock()) {
                    p.sendMessage(ChatColor.RED + "You can't reforge blocks!");
                    return;
                }
                Integer refIndex = Objects.requireNonNull(stone.getItemMeta())
                        .getPersistentDataContainer()
                        .get(SkyblockD.getKey("reforgeStone"), PersistentDataType.INTEGER);
                assert refIndex != null;
                SkyblockReforge ref = SkyblockReforge.byIndex(refIndex);
                String name = tool.getItemMeta().getDisplayName();
                ref.getBase().apply(tool);
                inv.setItem(28, new ItemStack(Material.AIR));
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
                p.sendMessage(ChatColor.YELLOW+"Successfully applied "+ChatColor.BLUE+ref.getDisplayName()+ChatColor.YELLOW+" reforge to your "+name);
            }
        }
    }

    private void vaultGet(@NotNull InventoryClickEvent e, @NotNull Player p) {
        ItemStack item = e.getCurrentItem();
        if(item == null) return;
        e.setResult(Result.DENY);
        if(ChatColor.stripColor(Objects.requireNonNull(item.getItemMeta()).getDisplayName().toLowerCase(Locale.ENGLISH)).equals("previous page")) {
            p.openInventory(new VaultGuiTemplate().create(0, p));
            return;
        }
        if(item.getType() != Material.GRAY_STAINED_GLASS_PANE && Objects.requireNonNull(e.getClickedInventory()).getSize() == 54) {
            p.getInventory().addItem(e.getCurrentItem());
        }
    }

    private void vault(@NotNull InventoryClickEvent e, @NotNull Player p, @NotNull String title) {
        int curPage = Integer.parseInt(title.replace("The Vault ", ""));

        if(e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
        VaultGuiTemplate temp = new VaultGuiTemplate();
        String itemName = ChatColor.stripColor(Objects.requireNonNull(e.getCurrentItem().getItemMeta()).getDisplayName()).toLowerCase(Locale.ENGLISH);
        e.setResult(Result.DENY);
        if(itemName.equals("previous page")) {
            curPage -= curPage <= 0 ? 0 : 1;
            Inventory previousPage = temp.create(curPage, p);
            p.closeInventory();
            p.openInventory(previousPage);
            p.updateInventory();
        } else if(itemName.equals("next page")) {
            curPage++;
            Inventory nextPage = temp.create(curPage, p);
            p.closeInventory();
            p.openInventory(nextPage);
            p.updateInventory();
        } else if(e.getCurrentItem().getType() == Material.GRAY_STAINED_GLASS_PANE) {
            p.updateInventory();
        } else if(Objects.requireNonNull(e.getClickedInventory()).getSize() == 54) {
            p.updateInventory();
            if(e.getClick() == ClickType.RIGHT)
                p.openInventory(new VaultGuiTemplate.VaultItemTemplate().create(e.getCurrentItem(), p));
            else p.getInventory().addItem(e.getCurrentItem());
            p.updateInventory();
        }
    }


    private void elixirs(@NotNull InventoryClickEvent e, @NotNull Player p) {
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

                UniversalHelper.giveSkillExperience(p, "mysticism", Math.round(exp));
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

    private void removeItem(ItemStack item, @NotNull Inventory i, int amount) {
        for(ItemStack it : i.getContents()) {
            if(it != null && it.getType() != Material.AIR && it.isSimilar(item)) {
                it.setAmount(it.getAmount()-amount);
                it.setType(Material.AIR);
                return;
            }
        }
    }

    private void skills(@NotNull InventoryClickEvent e, @NotNull Player p){
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
            case "Fishing":
                p.closeInventory();
                p.openInventory(new Fishing(p).generateMenu());
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

    private void menu(@NotNull InventoryClickEvent e, @NotNull Player p) {
        if(e.getCurrentItem() == null) return;
        String displayName = ChatColor.stripColor((Objects.requireNonNull(e.getCurrentItem().getItemMeta()).getDisplayName()));
        if(displayName.equalsIgnoreCase("Your Skills")) {
            SkillsGui ui = new SkillsGui();
            ui.setPlayer(p);
            Inventory inv = Bukkit.createInventory(ui.getHolder(p), ui.getSize(), ui.getName());
            p.openInventory(ui.generateContains(inv));
            p.updateInventory();
        } else if(displayName.equalsIgnoreCase("Reforge Anvil")) {
            ReforgeGui ui = new ReforgeGui();
            Inventory inv = ui.generateContains(Bukkit.createInventory(ui.getHolder(p), ui.getSize(), ui.getName()));
            p.openInventory(inv);
            p.updateInventory();
        } else if(displayName.equalsIgnoreCase("Recipe Book")) {
            RecipeGuiTemplate temp = new RecipeGuiTemplate();
            Inventory inv = temp.create(0, p);
            p.openInventory(inv);
            p.updateInventory();
        } else if(displayName.equalsIgnoreCase("Wardrobe")) {
            WardrobeGui ui = new WardrobeGui();
            Inventory inv = ui.generateContains(Bukkit.createInventory(ui.getHolder(p), ui.getSize(), ui.getName()));
            p.openInventory(inv);
            p.updateInventory();
        } else if(displayName.equalsIgnoreCase("Crafting Table")) {
            Inventory inv = Bukkit.createInventory(p, InventoryType.CRAFTING);
            p.openInventory(inv);
            p.updateInventory();
        }
        e.setResult(Result.DENY);
        p.updateInventory();
    }

    private void recombobulator(@NotNull InventoryClickEvent e, @NotNull Player p) {
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

    private void skillClaim(@NotNull InventoryClickEvent e, @NotNull Player p) {
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
            case "fishing":
                new Fishing(p).claimReward(index, p);
                break;
            default:
                break;
        }
    }
}
