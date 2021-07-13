package space.maxus.skyblockd.helpers;

import com.google.gson.reflect.TypeToken;
import space.maxus.skyblockd.SkyblockD;

import java.io.IOException;
import java.util.HashMap;

public class RankHelper {
    public static HashMap<String, Object> getGroups() throws IOException {
        HashMap<String, Object> dat = ServerHelper.makeJsonRequest("https://cdn.maxus.space/files/plugins/rgroups.json", new TypeToken<HashMap<String, Object>>(){}.getType());
        SkyblockD.logger.info(dat.toString());
        return dat;
    }

    public static void updateRanks() {
        try {
            JsonHelper.writeJson(SkyblockD.getInstance().getDataFolder().toPath() + "\\ranks.json", SkyblockD.playerRanks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
