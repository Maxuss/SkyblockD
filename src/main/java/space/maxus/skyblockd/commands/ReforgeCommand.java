package space.maxus.skyblockd.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.skyblock.reforges.SkyblockReforge;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@CommandInfo(name = "reforge", permission="skyblockd.admin", playerOnly=true)
public class ReforgeCommand extends ChatCommand {

    @Override
    public boolean execute(CommandSender sender, String @NotNull [] args) {
        Player p = (Player) sender;
        ItemStack mh = p.getInventory().getItemInMainHand();
        if(mh.getType() == Material.AIR || !mh.hasItemMeta()) {
            p.sendMessage(ChatColor.RED+"You don't hold any item!");
            return true;
        }
        if(args.length < 1) {
            return false;
        }
        SkyblockReforge reforge;
        try {
            reforge = SkyblockReforge.valueOf(args[0].toUpperCase(Locale.ENGLISH));
        } catch (IllegalArgumentException e) {
            p.sendMessage(ChatColor.RED+"This reforge does not exist!");
            return true;
        }
        reforge.getBase().apply(mh);

        p.sendMessage(ChatColor.GOLD+"Successfully applied reforge " + reforge.getDisplayName() + " to your " + Objects.requireNonNull(mh.getItemMeta()).getDisplayName());

        return true;
    }

    @Override
    public List<String> onTab(CommandSender sender, String[] args) {
        return Arrays.stream(SkyblockReforge.values()).map(Enum::name).collect(Collectors.toList());
    }
}
