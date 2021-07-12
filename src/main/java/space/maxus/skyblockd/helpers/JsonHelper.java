package space.maxus.skyblockd.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

public class JsonHelper<T> {
    private final Gson g;
    private final Class<T> cl;

    public JsonHelper(Class<T> tClass, boolean prettify) {
        cl = tClass;
        g = prettify ? new GsonBuilder().setPrettyPrinting().create() : new Gson();
    }

    public T deserializeJson(String json, Type t) {
        return g.fromJson(json, t);
    }

    public String serializeJson(T json) {
        return g.toJson(json);
    }

    public T jsonFromFile(String path) throws IOException {
        Type t = new TypeToken<T>(){}.getType();
        return deserializeJson(FileHelper.readFromFile(new File(path)), t);
    }

    public T jsonFromResource(String rpath)  {
        Type t = new TypeToken<T>(){}.getType();
        return deserializeJson(ResourceHelper.readResource(rpath), t);
    }

    public static String readJsonFile(String path) throws IOException {
        return FileHelper.readFromFile(new File(path));
    }

    public static String readJsonResource(String rpath) throws IOException {
        return ResourceHelper.readResourceSafely(rpath);
    }

    public static void writeJson(String path, Object object) throws IOException {
        JsonHelper<Object> j = new JsonHelper<>(Object.class, true);
        FileHelper.writeFile(path, j.serializeJson(object));
    }

    public static HashMap<String, Object> mapJson(String json) {
        Type type = new TypeToken<HashMap<String, String>>(){}.getType();
        return new Gson().fromJson(json, type);
    }
}
