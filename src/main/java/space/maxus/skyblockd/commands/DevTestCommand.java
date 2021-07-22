package space.maxus.skyblockd.commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.enchants.ReplenishEnchantment;

@CommandInfo(name = "sbdevtest", permission = "skyblockd.admin", playerOnly = true, configReq = "skyblockd.commands.devmode")
public class DevTestCommand extends ChatCommand {
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        ItemStack hoe = new ItemStack(Material.NETHERITE_HOE);
        hoe.addEnchantment(new ReplenishEnchantment(SkyblockD.getKey("replenish")), 1);
        p.getInventory().addItem(hoe);
        p.updateInventory();
        return true;
    }
}
