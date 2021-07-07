package space.maxus.skyblockd.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.TestSkyblockItem;

public class DevTestCommand implements ChatCommand {
    @Override
    public String getName() {
        return "sbdevtest";
    }

    @Override
    public CommandExecutor getExecutor() {
        return ((sender, command, label, args) -> {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                ItemStack i = SkyblockD.getItemRegisterer().get("skyblockd:SB_ITEM_TEST");
                if(i == null){
                    TestSkyblockItem tsi = new TestSkyblockItem();
                    p.getInventory().addItem(tsi.generate());
                }
                else p.getInventory().addItem(i);
                return true;
            }
            return false;
        });
    }
}
