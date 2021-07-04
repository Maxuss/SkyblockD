package xyz.voidmoment.skyblockd.commands;

import org.bukkit.command.CommandExecutor;
import xyz.voidmoment.skyblockd.SkyblockD;
import xyz.voidmoment.skyblockd.helpers.ManagerBase;

import java.util.Objects;

public class CommandManager extends ManagerBase<ChatCommand> {

    @Override
    public void register(){
        if(!contains.isEmpty()) {
            for (ChatCommand cmd : contains) {
                try {
                    String n = cmd.getName();
                    CommandExecutor e = cmd.getExecutor();
                    Objects.requireNonNull(SkyblockD.getInstance().getCommand(n)).setExecutor(e);
                    SkyblockD.logger.info("Successfully registered command " + n+ "!");
                }
                catch(Exception ex){
                    SkyblockD.logger.info("Could not register command " + cmd.getName() +"! Exception: "+ex.getMessage());
                }
            }
        }
    }
}
