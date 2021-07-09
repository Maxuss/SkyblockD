package space.maxus.skyblockd.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.utils.ManagerBase;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * @deprecated - Should not be used because of difficulties with player related inventories
 */
@Deprecated
public class InventoryManager extends ManagerBase<InventoryBase> {
    public TreeMap<String, Inventory> generated;

    private Player p;

    public void setPlayer(Player p){this.p = p;}

    @Override
    public void register() {
        generated = new TreeMap<>();
        for (InventoryBase inv : contains) {
            try {
                Inventory i = Bukkit.createInventory(inv.getHolder(p), inv.getSize(), inv.getName());
                Inventory _final = inv.generateContains(i);
                generated.put(inv.getId(), _final);
                SkyblockD.logger.info("Successfully registered inventory with ID " + inv.getId());
            } catch (Exception ex) {
                SkyblockD.logger.info("Could not register inventory with ID " + inv.getId() + "! Exception: " + ex.getMessage() + "\nTrace: " + Arrays.toString(ex.getStackTrace()));
            }
        }
    }
}
