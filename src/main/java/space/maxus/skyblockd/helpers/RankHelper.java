package space.maxus.skyblockd.helpers;

import space.maxus.skyblockd.SkyblockD;

import java.io.IOException;
import java.util.HashMap;

public class RankHelper {
    public static HashMap<String, Object> getGroups() throws IOException {
        return JsonHelper.mapJson(JsonHelper.readJsonResource("rankdata/rgroups.json"));
    }

    public static void updateRanks() {
        try {
            JsonHelper.writeJson(SkyblockD.getCurrentDir() + "\\ranks.json", SkyblockD.playerRanks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
