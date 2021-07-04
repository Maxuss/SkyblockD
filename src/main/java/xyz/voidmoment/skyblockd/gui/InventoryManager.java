package xyz.voidmoment.skyblockd.gui;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import xyz.voidmoment.skyblockd.SkyblockD;
import xyz.voidmoment.skyblockd.helpers.ManagerBase;

import java.util.Arrays;
import java.util.TreeMap;

public class InventoryManager extends ManagerBase<InventoryBase> {
    public TreeMap<String,Inventory> generated;

    @Override
    public void register() {
        generated = new TreeMap<>();
        for (InventoryBase inv: contains) {
            try {
                Inventory i = Bukkit.createInventory(inv.getHolder(), inv.getSize(), inv.getName());
                Inventory _final = inv.generateContains(i);
                generated.put(inv.getId(), _final);
                SkyblockD.logger.info("Successfully registered inventory with ID " + inv.getId());
            }
            catch(Exception ex){
                SkyblockD.logger.info("Could not register inventory with ID " + inv.getId()+"! Exception: "+ex.getMessage()+"\nTrace: "+ Arrays.toString(ex.getStackTrace()));
            }
        }
    }
}
