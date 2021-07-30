package space.maxus.skyblockd.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import space.maxus.skyblockd.objects.BetterListener;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.util.HashMap;

public class MovementListener extends BetterListener {
        public static final HashMap<Biome, String> importantBiomes = new HashMap<Biome, String>() {
        {
            put(Biome.SAVANNA, ChatColor.GOLD+""+ChatColor.BOLD+"ᖲ Savanna ᖲ");
            put(Biome.SHATTERED_SAVANNA, ChatColor.GOLD+""+ChatColor.BOLD+"ᴥ Shattered Savanna ᴥ");
            put(Biome.TAIGA, ChatColor.DARK_GREEN+""+ChatColor.BOLD+SkyblockConstants.TRUE_DEFENCE + " Taiga " + SkyblockConstants.TRUE_DEFENCE);
            put(Biome.GIANT_SPRUCE_TAIGA, ChatColor.DARK_GREEN+""+ChatColor.BOLD+"ψ Giant Taiga ψ");
            put(Biome.FLOWER_FOREST, ChatColor.GREEN+""+ChatColor.BOLD+"߷ Flower Forest ߷");
            put(Biome.BADLANDS, ChatColor.RED+""+ChatColor.BOLD+"ᢴ Badlands ᢵ");
            put(Biome.BASALT_DELTAS, ChatColor.DARK_GRAY+""+ChatColor.BOLD+"$ Basalt Deltas $");
            put(Biome.THE_END, ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+"ᮜ The End ᮜ");
            put(Biome.END_BARRENS, ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"ᮘ End Barrens ᮘ");
            put(Biome.SMALL_END_ISLANDS, ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"ᮜ End Islands ᮜ");
            put(Biome.END_HIGHLANDS, ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"ᮮ End Highlands ᮮ");
            put(Biome.FOREST, ChatColor.GREEN+""+ChatColor.BOLD+"Forest");
            put(Biome.PLAINS, ChatColor.GREEN+""+ChatColor.BOLD+"Plains");
            put(Biome.DARK_FOREST, ChatColor.GRAY+""+ChatColor.BOLD+"ℓ Dark Forest ℓ");
            put(Biome.OCEAN, ChatColor.BLUE+""+ChatColor.BOLD+"α The Ocean α");
            put(Biome.DESERT, ChatColor.YELLOW+""+ChatColor.BOLD+"⌛ The Desert ⌛");
            put(Biome.THE_VOID, ChatColor.BLACK+""+ChatColor.ITALIC+"ᮜ VOID ᮜ");
            put(Biome.SWAMP, ChatColor.GREEN+""+ChatColor.BOLD+"☔ The Swamp ☔");
            put(Biome.NETHER_WASTES, ChatColor.RED+""+ChatColor.BOLD+"Nether Wastes");
            put(Biome.MOUNTAINS, ChatColor.GREEN+""+ChatColor.BOLD+"⧋ The Mountains ⧋");
            put(Biome.SOUL_SAND_VALLEY, ChatColor.DARK_AQUA+""+ChatColor.BOLD+"⨕ Soul Sand Valley ⨕");
            put(Biome.ERODED_BADLANDS, ChatColor.GOLD+""+ChatColor.BOLD+"⭍ Eroded Badlands ⭍");
            put(Biome.JUNGLE, ChatColor.GREEN+""+ChatColor.BOLD+"# The Jungle #");
            put(Biome.WARPED_FOREST, ChatColor.AQUA+""+ChatColor.BOLD+"⸎ Warped Forest ⸎");
            put(Biome.CRIMSON_FOREST, ChatColor.RED+""+ChatColor.BOLD+"☣ Crimson Forest ☣");
            put(Biome.SNOWY_TUNDRA, ChatColor.WHITE+""+ChatColor.BOLD+"❄ Tundra ❄");
            put(Biome.SNOWY_TAIGA, ChatColor.WHITE+""+ChatColor.BOLD+"❄ Snowy Taiga ❄");
            put(Biome.SNOWY_MOUNTAINS, ChatColor.WHITE+""+ChatColor.BOLD+"❄ Snowy Mountains ❄");
        }
    };

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Location from = e.getFrom();
        Location to = e.getTo();
        if(to != null) {
            Biome prev = from.getBlock().getBiome();
            Biome cur = to.getBlock().getBiome();
            if(cur.equals(prev) || !importantBiomes.containsKey(cur)) return;

            Player p = e.getPlayer();
            String name = importantBiomes.get(cur);
            p.sendTitle(name, ChatColor.GREEN+"Now entering...", 10, 30, 10);
        }
    }
}
