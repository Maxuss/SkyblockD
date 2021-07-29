package space.maxus.skyblockd.gui;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.GuiHelper;

import java.util.*;
import java.util.stream.Collectors;

public class RecipeGuiTemplate {
    public Inventory create(int page, Player p) {
        Iterator<Recipe> recipeIterator = SkyblockD.getHost().recipeIterator();

        List<Recipe> recipes = new ArrayList<>();

        while(recipeIterator.hasNext()) {
            Recipe next = recipeIterator.next();
            if(next instanceof ShapedRecipe) {
                ShapedRecipe sr = (ShapedRecipe) next;
                String ns = sr.getKey().getNamespace();
                if(ns.contains("skyblockd")) recipes.add(next);
            } else if(next instanceof ShapelessRecipe) {
                ShapelessRecipe sr = (ShapelessRecipe) next;
                String ns = sr.getKey().getNamespace();
                if(ns.contains("skyblockd")) recipes.add(next);
            }
        }

        Recipe[][] pages = splitInto(recipes.toArray(new Recipe[0]), 28);

        Recipe[] actualContents;
        if(page > pages.length) actualContents = pages[pages.length-1];
        else if(page < 0) actualContents = pages[0];
        else {
            try {
                actualContents = pages[page];
            } catch (ArrayIndexOutOfBoundsException e) {
                actualContents = pages[pages.length-1];
            }
        }

        Inventory inv = Bukkit.createInventory(p, 54, "Recipe Browser "+page);
        ItemStack gls = GuiHelper.getMenuGlass();
        ItemStack air = new ItemStack(Material.AIR);
        ItemStack nxt = GuiHelper.genSimpleMenuItem(ChatColor.GREEN+"Next Page", Material.ARROW, Collections.emptyList());
        ItemStack bck = GuiHelper.genSimpleMenuItem(ChatColor.RED+"Previous Page", Material.ARROW, Collections.emptyList());
        inv.setContents(new ItemStack[]{
                gls, gls, gls, gls, gls, gls, gls, gls, gls,
                gls, air, air, air, air, air, air, air, gls,
                gls, air, air, air, air, air, air, air, gls,
                gls, air, air, air, air, air, air, air, gls,
                gls, air, air, air, air, air, air, air, gls,
                gls, gls, bck, gls, gls, gls, nxt, gls, gls
        });

        List<ItemStack> stacks = Arrays.stream(actualContents).map(
                recipe -> {
                    ItemStack result = recipe.getResult();
                    ItemMeta m = result.getItemMeta();
                    assert m != null;
                    String name = "";
                    if(recipe instanceof ShapedRecipe) {
                        ShapedRecipe rec = (ShapedRecipe) recipe;
                        name = rec.getKey().getKey();
                    } else if(recipe instanceof ShapelessRecipe) {
                        ShapelessRecipe rec = (ShapelessRecipe) recipe;
                        name = rec.getKey().getKey();
                    }
                    m.getPersistentDataContainer().set(SkyblockD.getKey("recipe"), PersistentDataType.STRING, name);
                    result.setItemMeta(m);
                    return result;
                }
        ).collect(Collectors.toList());
        ItemStack[] contents = stacks.stream().map(i -> i == null ? i = new ItemStack(Material.OAK_LOG) : i).toArray(ItemStack[]::new);
        inv.addItem(contents);

        return inv;
    }

    public static class ItemRecipeTemplate {
        public Inventory create(Recipe rec, Player p) {
            Inventory inv = Bukkit.createInventory(p, 54, "View Recipe");
            ItemStack gls = GuiHelper.getMenuGlass();
            ItemStack bck = GuiHelper.genSimpleMenuItem(ChatColor.RED+"Go back", Material.ARROW, Collections.emptyList());
            ItemStack air = new ItemStack(Material.AIR);

            ItemStack[] contents = {
                    gls, gls, gls, gls, gls, gls, gls, gls, gls,
                    gls, air, air, air, gls, gls, gls, gls, gls,
                    gls, air, air, air, gls, gls, air, gls, gls,
                    gls, air, air, air, gls, gls, gls, gls, gls,
                    gls, gls, gls, gls, gls, gls, gls, gls, gls,
                    gls, gls, gls, gls, bck, gls, gls, gls, gls,
            };
            inv.setContents(contents);

            if(rec != null) {
                inv.setItem(24, rec.getResult());

                if(rec instanceof ShapedRecipe) {
                    ShapedRecipe shaped = (ShapedRecipe) rec;
                    ItemStack[] matrix = getMatrix(shaped);

                    inv.setItem(10, matrix[0]);
                    inv.setItem(11, matrix[1]);
                    inv.setItem(12, matrix[2]);

                    inv.setItem(19, matrix[3]);
                    inv.setItem(20, matrix[4]);
                    inv.setItem(21, matrix[5]);

                    inv.setItem(28, matrix[6]);
                    inv.setItem(29, matrix[7]);
                    inv.setItem(30, matrix[8]);
                } else if(rec instanceof ShapelessRecipe) {
                    ShapelessRecipe shapeless = (ShapelessRecipe) rec;
                    ItemStack[] matrix = getMatrix(shapeless);

                    inv.setItem(10, matrix[0]);
                    inv.setItem(11, matrix[1]);
                    inv.setItem(12, matrix[2]);

                    inv.setItem(19, matrix[3]);
                    inv.setItem(20, matrix[4]);
                    inv.setItem(21, matrix[5]);

                    inv.setItem(28, matrix[6]);
                    inv.setItem(29, matrix[7]);
                    inv.setItem(30, matrix[8]);
                }
            }

            return inv;
        }

        public Inventory create(ItemStack clicked, Player p) {
            String recipeKey = Objects.requireNonNull(clicked.getItemMeta()).getPersistentDataContainer().get(SkyblockD.getKey("recipe"), PersistentDataType.STRING);
            assert recipeKey != null;
            NamespacedKey rkey = SkyblockD.getKey(recipeKey.replace("skyblockd:", "").toUpperCase(Locale.ENGLISH));

            Recipe rec = SkyblockD.getHost().getRecipe(rkey);

            return create(rec, p);
        }

        private ItemStack[] getMatrix(ShapelessRecipe recipe) {
            ItemStack[] items = new ItemStack[9];
            List<ItemStack> list = recipe.getIngredientList();
            for (int i = 0; i < list.size(); i++) {
                items[i] = list.get(i);
            }
            return items;
        }

        private String nineChars(String[] shape) {
            StringBuilder f = new StringBuilder();
            if (shape.length == 1) {
                f.append("      ");
            } else if (shape.length == 2) {
                f.append("   ");
            }
            byte b;
            int i;
            String[] arrayOfString;
            for (i = (arrayOfString = shape).length, b = 0; b < i; ) {
                String row = arrayOfString[b];
                if (row.length() == 1) {
                    f.append("  ");
                } else if (row.length() == 2) {
                    f.append(" ");
                }
                f.append(row);
                b++;
            }
            return f.toString();
        }

        private ItemStack[] getMatrix(ShapedRecipe recipe) {
            ItemStack[] items = new ItemStack[9];
            String chars = nineChars(recipe.getShape());
            for (int i = 0; i < 9; i++) {
                items[i] = recipe.getIngredientMap().get(chars.charAt(i));
            }
            return items;
        }
    }

    private Recipe[][] splitInto(Recipe[] chunk, final int chunkSize) {
        final int length = chunk.length;
        final Recipe[][] dest = new Recipe[(length + chunkSize - 1)/chunkSize][];
        int destIndex = 0;
        int stopIndex = 0;

        for (int startIndex = 0; startIndex + chunkSize <= length; startIndex += chunkSize)
        {
            stopIndex += chunkSize;
            dest[destIndex++] = Arrays.copyOfRange(chunk, startIndex, stopIndex);
        }

        if (stopIndex < length)
            dest[destIndex] = Arrays.copyOfRange(chunk, stopIndex, length);

        return dest;
    }
}
