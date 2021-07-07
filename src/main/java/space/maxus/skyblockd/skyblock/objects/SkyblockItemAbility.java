package space.maxus.skyblockd.skyblock.objects;

import org.bukkit.ChatColor;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.util.ArrayList;
import java.util.List;

public class SkyblockItemAbility {
    private final String n;
    private final SkyblockAbilityType type;
    private final List<String> description;
    private int manaCost = 0;
    private int cooldown = 0;
    private int soulflowCost = 0;

    public String getName(){return n;}
    public SkyblockAbilityType getType() {return type;}
    public List<String> getDescription() {return description;}
    public int getManaCost() {return manaCost;}
    public int getCooldown() { return cooldown; }
    public int getSoulflowCost() { return soulflowCost; }

    public void setManaCost(int cost){manaCost = cost;}
    public void setCooldown(int cd) {cooldown = cd;}
    public void setSoulflowCost(int cost) {soulflowCost = cost;}

    public SkyblockItemAbility(String name, SkyblockAbilityType t, List<String> descr){
        n = name;
        type = t;
        description = descr;
    }

    List<String> generate(){
        List<String> f = new ArrayList<>();
        f.add(getType().shown.replace("%NAME%", getName()));
        f.addAll(getDescription());
        if(getManaCost() <= 0) f.add(ChatColor.DARK_GRAY+"Mana cost: "+ChatColor.AQUA+getManaCost());
        if(getCooldown() <= 0) f.add(ChatColor.DARK_GRAY+"Cooldown: "+ChatColor.GREEN+getCooldown());
        if(getSoulflowCost() <= 0) f.add(ChatColor.DARK_GRAY+"Soulflow cost: "+ChatColor.DARK_AQUA+getSoulflowCost()+ SkyblockConstants.OVERFLOW);
        f.add("");
        return f;
    }
}
