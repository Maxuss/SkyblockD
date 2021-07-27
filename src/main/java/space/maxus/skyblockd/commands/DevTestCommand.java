package space.maxus.skyblockd.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import space.maxus.skyblockd.helpers.UniversalHelper;

@CommandInfo(name = "sbdevtest", permission = "skyblockd.admin", playerOnly = true, configReq = "skyblockd.commands.devmode")
public class DevTestCommand extends ChatCommand {
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        if(args.length < 2) return true;
        UniversalHelper.giveSkillExperience(p, args[0], Integer.parseInt(args[1]));
        return true;
    }
}
