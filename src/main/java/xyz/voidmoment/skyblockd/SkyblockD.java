package xyz.voidmoment.skyblockd;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.*;
public class SkyblockD extends JavaPlugin {

    public static SkyblockD instance;

    public static Server host;
    public static ConsoleCommandSender sender;
    public static String serverName;

    @Override
    public void onEnable() {
        instance = this;
        host = this.getServer();
        sender = host.getConsoleSender();
        serverName = host.getName();

        this.getCommand("");

        sender.sendMessage(ChatColor.BOLD +"["+ ChatColor.GOLD +"SkyblockD"+ChatColor.RESET+""+ChatColor.BOLD+"]"+ChatColor.RESET+" > Plugin initialized!");
    }

    @Override
    public void onDisable() {
        sender.sendMessage(ChatColor.BOLD +"["+ ChatColor.GOLD +"SkyblockD"+ChatColor.RESET+""+ChatColor.BOLD+"]"+ChatColor.RESET+" > Plugin disabled!");
        instance = null;
        host = null;
        sender = null;
        serverName = null;
    }
}
