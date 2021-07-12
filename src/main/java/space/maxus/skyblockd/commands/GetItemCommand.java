package space.maxus.skyblockd.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.ArmorSet;

import java.util.Objects;
import java.util.TreeMap;

public class GetItemCommand implements ChatCommand {
    @Override
    public String getName() {
        return "getitem";
    }

    @Override
    public CommandExecutor getExecutor() {
        return ((sender, command, label, args) -> {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "This command is only for players!");
                return true;
            }
            if (args.length < 1) return false;
            Player p = (Player) sender;
            String id = args[0];
            if(id.equals("dev#getMap()")){
                p.sendMessage(ChatColor.GOLD+"[dev $ injected]: Keys "+SkyblockD.getArmorSets().keySet());
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
                p.sendMessage(ChatColor.YELLOW + "Item with id " + ChatColor.RED + id + ChatColor.YELLOW + " is not registered!");
                return true;
            }
            ItemStack it = ci.get(id);
            give(p, it);
            String msg = ChatColor.YELLOW + "Successfuly gave you item " + Objects.requireNonNull(it.getItemMeta()).getDisplayName();
            p.sendMessage(msg);
            return true;
        });
    }

    private void give(Player p, ItemStack it){
        int free = p.getInventory().firstEmpty();
        if (free == -1) {
            p.sendMessage(ChatColor.RED + "You do not have enough inventory space!");
            return;
        }
        p.getInventory().setItem(free, it);
        p.updateInventory();
    }
}
