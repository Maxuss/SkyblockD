package space.maxus.skyblockd.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import space.maxus.skyblockd.helpers.UniversalHelper;

@CommandInfo(name="skills", permission = "skyblockd.admin", playerOnly = true)
public class SkillCommand extends ChatCommand {
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        if(args.length < 2) return false;
        UniversalHelper.giveSkillExperience(p, args[0], Integer.parseInt(args[1]));
        return true;
    }
}
