package xyz.voidmoment.skyblockd.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.voidmoment.skyblockd.SkyblockD;

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
            if(!(sender instanceof Player)){
                sender.sendMessage(ChatColor.RED+"This command is only for players!");
                return true;
            }
            if(args.length < 1) return false;
            Player p = (Player) sender;
            String id = args[0];
            TreeMap<String, ItemStack> ci = SkyblockD.getCustomItems();
            if(!ci.containsKey(id)) {
                p.sendMessage(ChatColor.YELLOW+"Item with id "+ChatColor.RED+id+ChatColor.YELLOW+" is not registered!");
                return true;
            }
            ItemStack it = ci.get(id);
            int free = p.getInventory().firstEmpty();
            if(free == -1){
                sender.sendMessage(ChatColor.RED+"You do not have enough inventory space!");
                return true;
            }
            p.getInventory().setItem(free, it);
            p.updateInventory();
            String msg = ChatColor.YELLOW+"Successfuly gave you item " + Objects.requireNonNull(it.getItemMeta()).getDisplayName();
            p.sendMessage(msg);
            return true;
        });
    }
}
