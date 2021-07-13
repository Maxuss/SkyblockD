package space.maxus.skyblockd.objects;

import com.google.gson.reflect.TypeToken;
import space.maxus.skyblockd.helpers.ServerHelper;
import space.maxus.skyblockd.skyblock.objects.SkillModifier;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

public class ServerStorage {
    public final SkillModifier modifier;
    public final HashMap<String, Integer> farming;
    public final HashMap<String, Integer> foraging;
    public final HashMap<String, Integer> mining;

    public ServerStorage() throws IOException {
        Type mapType = new TypeToken<HashMap<String, Integer>>(){}.getType();
        modifier = ServerHelper.makeJsonRequest("https://cdn.maxus.space/files/plugins/modifiers.json", new TypeToken<SkillModifier>(){}.getType());
        farming = ServerHelper.makeJsonRequest("https://cdn.maxus.space/files/plugins/farming.json", mapType);
        foraging = ServerHelper.makeJsonRequest("https://cdn.maxus.space/files/plugins/foraging.json", mapType);
        mining = ServerHelper.makeJsonRequest("https://cdn.maxus.space/files/plugins/mining.json", mapType);
    }

}
