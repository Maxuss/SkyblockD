package space.maxus.skyblockd.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.items.CustomItem;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;

import java.util.Objects;

@CommandInfo(name = "recombobulate", permission = "skyblockd.admin", playerOnly = true, configReq = "")
public class RecombobulateCommand extends ChatCommand {
    @Override
    public boolean execute(CommandSender sender, String[] args) {
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
        return true;
    }
}
