package space.maxus.skyblockd.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.items.CustomItem;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;

import java.util.Objects;

public class RecombobulateCommand implements ChatCommand {
    @Override
    public String getName() {
        return "recombobulate";
    }

    @Override
    public CommandExecutor getExecutor() {
        return ((sender, command, label, args) -> {
            if(!(sender instanceof Player)){
                sender.sendMessage(ChatColor.RED+"This command is only for players!");
                return false;
            } else {
                Player p = (Player) sender;
                ItemStack i = p.getInventory().getItemInMainHand();
                if(i.getItemMeta() != null) {
                    PersistentDataContainer c = Objects.requireNonNull(i.getItemMeta()).getPersistentDataContainer();
                    if (!c.has(SkyblockD.getKey("skyblockNative"), PersistentDataType.STRING)) {
                        CustomItem.toSkyblockItem(i);
                    }
                    ItemStack ni = SkyblockItem.recombobulate(i);
                    p.getInventory().setItemInMainHand(ni);
                }
            }
            return true;
        });
    }
}
