package space.maxus.skyblockd.commands;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

public class DeveloperCommand implements ChatCommand {
    @Override
    public String getName() {
        return "dev";
    }

    @SuppressWarnings("unchecked")
    @Override
    public CommandExecutor getExecutor() {
        return ((s, command, label, args) -> {
            if(args.length < 1) {
                s.sendMessage(ChatColor.RED+"Wrong syntax!");
                return true;
            }
            if(args[0].equals("fields")){
                if(args.length < 2){
                    s.sendMessage(ChatColor.RED+"Wrong syntax!");
                    return true;
                }
                String cl = args[1];
                Class<?> clazz;
                try {
                    clazz = Class.forName(cl);
                } catch (ClassNotFoundException e){
                    s.sendMessage(ChatColor.RED+"Wrong syntax!");
                    return true;
                }
                Set<Field> fields = ReflectionUtils.getAllFields(clazz);
                if(fields.isEmpty()){
                    s.sendMessage( "Could not find any properties!");
                    return true;
                }
                for (Field f: fields) {
                    Object val;
                    try {
                        val = f.get(null);
                    } catch (IllegalAccessException | NullPointerException e){
                        val = ChatColor.RED+"non-static!";
                    }
                    s.sendMessage(ChatColor.GOLD+"Found a field property by name '"+f.getName()+"'!\n" +
                            "Field data:\n" +
                            "Annotations: "+ Arrays.toString(f.getAnnotations())+"\n"+
                            "Type: "+ f.getType()+"\n"+
                            "Value: "+ChatColor.GREEN+val);
                }
            } else if (args[0].equals("methods")){
                if(args.length < 2){
                    s.sendMessage(ChatColor.RED+"Wrong syntax!");
                    return true;
                }
                String cl = args[1];
                Class<?> clazz;
                try {
                    clazz = Class.forName(cl);
                } catch (ClassNotFoundException e){
                    s.sendMessage(ChatColor.RED+"Wrong syntax!");
                    return true;
                }
                Set<Method> methods = ReflectionUtils.getAllMethods(clazz);
                if(methods.isEmpty()) {
                    s.sendMessage( "Could not find any methods!");
                    return true;
                }
                for (Method m: methods) {
                    s.sendMessage(ChatColor.GOLD+"Found a method by name '#"+m.getName()+"()'!\n" +
                            "Method data:\n" +
                            "Annotations: "+ Arrays.toString(m.getAnnotations())+"\n"+
                            "Return type: "+ m.getReturnType()+"\n"+
                            "Parameters: Count: "+m.getParameterCount()+" As array: "+ Arrays.toString(m.getParameters()));
                }
            }
            else if(args[0].equals("nbt")){
                if(!(s instanceof Player)){
                    s.sendMessage(ChatColor.RED+"Only for players!");
                    return true;
                }
                Player p = (Player) s;
                ItemStack item = p.getInventory().getItemInMainHand();
                if(item == null || !item.hasItemMeta()){
                    s.sendMessage(ChatColor.RED+"You dont hold any item!");
                    return true;
                }
                ItemMeta m = item.getItemMeta();
                Set<NamespacedKey> keys = m.getPersistentDataContainer().getKeys();
                if(m.getPersistentDataContainer().isEmpty()){
                    s.sendMessage(ChatColor.RED+"Could not find any keys!");
                    return true;
                }
                for (NamespacedKey k: keys) {
                    p.sendMessage(ChatColor.GOLD+"Found a Data key! "+ChatColor.GREEN+k.getKey());
                }
                return true;
            }
            return true;
        });
    }
}
