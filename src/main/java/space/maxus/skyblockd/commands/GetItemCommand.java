package space.maxus.skyblockd.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.ArmorSet;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

@CommandInfo(name = "getitem", permission = "skyblockd.admin", playerOnly = true, configReq = "")
public class GetItemCommand extends ChatCommand {

    private void give(Player p, ItemStack it){
        int free = p.getInventory().firstEmpty();
        if (free == -1) {
            p.sendMessage(ChatColor.RED + "You do not have enough inventory space!");
            return;
        }
        p.getInventory().setItem(free, it);
        p.updateInventory();
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (args.length < 1) return false;
        Player p = (Player) sender;
        String id = args[0];
        if(id.equals("dev#getMap()")){
            p.sendMessage(ChatColor.GOLD+"[dev $ injected]: Keys "+ SkyblockD.getArmorSets().keySet());
            p.sendMessage(ChatColor.GOLD+"[dev $ injected]: Object representation "+SkyblockD.getArmorSets());
            return true;
        }
        if(id.startsWith("set::")){
            if(!SkyblockD.getArmorSets().containsKey(id)){
                p.sendMessage(ChatColor.GOLD+"Armor set with ID of "+ChatColor.RED+id+ChatColor.GOLD+" does not exist!");
                return true;
            }
            ArmorSet s = SkyblockD.getArmorSets().get(id);
            PlayerInventory i = p.getInventory();
            i.addItem(s.getBoots(), s.getLeggings(), s.getChestplate(), s.getHelmet());
            p.sendMessage(ChatColor.YELLOW+"Successfully gave you set of armor!");
            return true;
        }
        TreeMap<String, ItemStack> ci = SkyblockD.getCustomItems();
        if (!ci.containsKey(id)) {
            try {
                SkyblockMaterial mat = SkyblockMaterial.valueOf(id);
                give(p, mat.getItem());
                ItemMeta meta = mat.getItem().getItemMeta();
                if(meta != null) {
                    String msg = ChatColor.YELLOW + "Successfuly gave you item " + meta.getDisplayName();
                    p.sendMessage(msg);
                }
            } catch(IllegalArgumentException e) {
                p.sendMessage(ChatColor.YELLOW + "Item with id " + ChatColor.RED + id + ChatColor.YELLOW + " is not registered!");
            }
            return true;
        }
        ItemStack it = ci.get(id);
        give(p, it);
        String msg = ChatColor.YELLOW + "Successfuly gave you item " + Objects.requireNonNull(it.getItemMeta()).getDisplayName();
        p.sendMessage(msg);
        return true;
    }

    @Override
    public List<String> onTab(CommandSender s, String[] args) {
        return Arrays.stream(SkyblockMaterial.values()).map(Enum::name).collect(Collectors.toList());
    }
}
