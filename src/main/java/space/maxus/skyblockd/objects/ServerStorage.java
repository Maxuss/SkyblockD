package space.maxus.skyblockd.objects;

import com.google.gson.reflect.TypeToken;
import space.maxus.skyblockd.helpers.ServerHelper;
import space.maxus.skyblockd.skyblock.objects.SkillModifier;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.HashMap;

public class ServerStorage {
    public final SkillModifier modifier;
    public final HashMap<String, Integer> farming;
    public final HashMap<String, Integer> foraging;
    public final HashMap<String, Integer> mining;
    public final HashMap<String, Integer> excavating;
    public final BufferedImage onlineIcon;
    public final BufferedImage offlineIcon;
    public final PluginMeta meta;

    public ServerStorage() throws IOException {
        Type mapType = new TypeToken<HashMap<String, Integer>>(){}.getType();
        modifier = ServerHelper.makeJsonRequest("https://cdn.maxus.space/files/plugins/modifiers.json", new TypeToken<SkillModifier>(){}.getType());
        farming = ServerHelper.makeJsonRequest("https://cdn.maxus.space/files/plugins/farming.json", mapType);
        foraging = ServerHelper.makeJsonRequest("https://cdn.maxus.space/files/plugins/foraging.json", mapType);
        mining = ServerHelper.makeJsonRequest("https://cdn.maxus.space/files/plugins/mining.json", mapType);
        excavating = ServerHelper.makeJsonRequest("https://cdn.maxus.space/files/plugins/excavating.json", mapType);
        meta = ServerHelper.makeJsonRequest("https://cdn.maxus.space/files/plugins/versions.json", new TypeToken<PluginMeta>(){}.getType());
        URL iconUrl = new URL("https://cdn.maxus.space/files/plugins/online.png");
        onlineIcon = ImageIO.read(iconUrl);
        URL offlineUrl = new URL("https://cdn.maxus.space/files/plugins/offline.png");
        offlineIcon = ImageIO.read(offlineUrl);
    }
}
