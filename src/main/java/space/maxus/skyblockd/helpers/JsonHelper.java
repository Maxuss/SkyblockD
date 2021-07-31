package space.maxus.skyblockd.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;

public class JsonHelper<T> {
    private final @NotNull Gson g;

    public JsonHelper(boolean prettify) {
        g = prettify ? new GsonBuilder().setPrettyPrinting().create() : new Gson();
    }

    public T deserializeJson(@NotNull String json, Type t) {
        String j;
        if(!json.startsWith("[") && !json.startsWith("[")){
            j = "{" + json;
        } else j = json;
        return g.fromJson(j, t);
    }

    public String serializeJson(T json) {
        return g.toJson(json);
    }

    public static String readJsonFile(@NotNull String path) throws IOException {
        return FileHelper.readFromFile(new File(path));
    }

    public static <T> void writeJson(@NotNull String path, T object) throws IOException {
        Gson gs = new GsonBuilder().setPrettyPrinting().create();
        FileHelper.writeFile(path, gs.toJson(object, new TypeToken<T>(){}.getType()));
    }
}
