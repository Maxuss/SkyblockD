package space.maxus.skyblockd.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@CommandInfo(name = "dev", permission = "skyblockd.admin", playerOnly = false, configReq = "skyblockd.commands.devmode")
public class DeveloperCommand extends ChatCommand {

    @Override
    @SuppressWarnings("unchecked")
    public boolean execute(CommandSender s, String[] args) {
        if(args.length < 1) {
            s.sendMessage(ChatColor.RED+"Wrong syntax!");
            return true;
        }
        switch (args[0]) {
            case "fields": {
                if (args.length < 2) {
                    s.sendMessage(ChatColor.RED + "Wrong syntax!");
                    return true;
                }
                String cl = args[1];
                Class<?> clazz;
                try {
                    clazz = Class.forName(cl);
                } catch (ClassNotFoundException e) {
                    s.sendMessage(ChatColor.RED + "Wrong syntax!");
                    return true;
                }
                Set<Field> fields = ReflectionUtils.getAllFields(clazz);
                if (fields.isEmpty()) {
                    s.sendMessage("Could not find any properties!");
                    return true;
                }
                for (Field f : fields) {
                    Object val;
                    try {
                        val = f.get(null);
                    } catch (IllegalAccessException | NullPointerException e) {
                        val = ChatColor.RED + "non-static!";
                    }
                    s.sendMessage(ChatColor.GOLD + "Found a field property by name '" + f.getName() + "'!\n" +
                            "Field data:\n" +
                            "Annotations: " + Arrays.toString(f.getAnnotations()) + "\n" +
                            "Type: " + f.getType() + "\n" +
                            "Value: " + ChatColor.GREEN + val);
                }
                break;
            }
            case "methods": {
                if (args.length < 2) {
                    s.sendMessage(ChatColor.RED + "Wrong syntax!");
                    return true;
                }
                String cl = args[1];
                Class<?> clazz;
                try {
                    clazz = Class.forName(cl);
                } catch (ClassNotFoundException e) {
                    s.sendMessage(ChatColor.RED + "Wrong syntax!");
                    return true;
                }
                Set<Method> methods = ReflectionUtils.getAllMethods(clazz);
                if (methods.isEmpty()) {
                    s.sendMessage("Could not find any methods!");
                    return true;
                }
                for (Method m : methods) {
                    s.sendMessage(ChatColor.GOLD + "Found a method by name '#" + m.getName() + "()'!\n" +
                            "Method data:\n" +
                            "Annotations: " + Arrays.toString(m.getAnnotations()) + "\n" +
                            "Return type: " + m.getReturnType() + "\n" +
                            "Parameters: Count: " + m.getParameterCount() + " As array: " + Arrays.toString(m.getParameters()));
                }
                break;
            }
            case "nbt":
                if (!(s instanceof Player)) {
                    s.sendMessage(ChatColor.RED + "Only for players!");
                    return true;
                }
                Player p = (Player) s;
                ItemStack item = p.getInventory().getItemInMainHand();
                if (item.getType() != Material.AIR || !item.hasItemMeta()) {
                    s.sendMessage(ChatColor.RED + "You dont hold any item!");
                    return true;
                }
                ItemMeta m = item.getItemMeta();
                assert m != null;
                Set<NamespacedKey> keys = m.getPersistentDataContainer().getKeys();
                if (m.getPersistentDataContainer().isEmpty()) {
                    s.sendMessage(ChatColor.RED + "Could not find any keys!");
                    return true;
                }
                for (NamespacedKey k : keys) {
                    p.sendMessage(ChatColor.GOLD + "Found a Data key! " + ChatColor.GREEN + k.getKey());
                }
                return true;
        }
        return true;
    }

    public List<String> onTabComplete(CommandSender s, String[] args) {
        return Arrays.asList("fields", "methods", "nbt");
    }
}
