package space.maxus.skyblockd.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.bukkit.entity.Player;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.objects.PlayerContainer;
import space.maxus.skyblockd.objects.PlayerSkills;
import space.maxus.skyblockd.objects.RankContainer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ContainerHelper {
    public static HashMap<String, Object> getGroups() throws IOException {
        HashMap<String, Object> dat = ServerHelper.makeJsonRequest("https://cdn.maxus.space/files/plugins/rgroups.json", new TypeToken<HashMap<String, Object>>(){}.getType());
        return dat;
    }

    public static void updatePlayers() {
        try {
            JsonHelper.writeJson(SkyblockD.getInstance().getDataFolder().toPath() + "\\players.json", SkyblockD.getPlayers());
            Gson g = new GsonBuilder().setPrettyPrinting().create();
            SkyblockD.players = g.fromJson(JsonHelper.readJsonFile(SkyblockD.getInstance().getDataFolder().toPath() + "\\players.json"), new TypeToken<List<PlayerContainer>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PlayerContainer getPlayer(Player player) {
        List<PlayerContainer> filtered = UniversalHelper.filter(SkyblockD.players, c -> c.uuid.equals(player.getUniqueId()));
        if(!filtered.isEmpty()) {
            return filtered.get(filtered.size()-1);
        }
        PlayerContainer n = new PlayerContainer(
                new RankContainer(player.getUniqueId().toString(), player.getName()),
                player.getUniqueId(), PlayerSkills.EMPTY, player.hasPermission("skyblockd.admin"));

        SkyblockD.players.add(n);
        updatePlayers();

        return n;
    }
}
