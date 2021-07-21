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

    public Class<T> getTClass() {return cl;}

    public JsonHelper(Class<T> tClass, boolean prettify) {
        cl = tClass;
        g = prettify ? new GsonBuilder().setPrettyPrinting().create() : new Gson();
    }

    public T deserializeJson(String json, Type t) {
        String j;
        if(!json.startsWith("[") && !json.startsWith("[")){
            j = "{" + json;
        } else j = json;
        return g.fromJson(j, t);
    }

    public String serializeJson(T json) {
        return g.toJson(json);
    }

    public T jsonFromFile(String path) throws IOException {
        Type t = new TypeToken<T>(){}.getType();
        return deserializeJson(FileHelper.readFromFile(new File(path)), t);
    }

    public T jsonFromResource(String rpath) throws IOException {
        Type t = new TypeToken<T>(){}.getType();
        return deserializeJson(ResourceHelper.readResource(rpath), t);
    }

    public static String readJsonFile(String path) throws IOException {
        return FileHelper.readFromFile(new File(path));
    }

    public static String readJsonResource(String rpath) throws IOException {
        return ResourceHelper.readResourceSafely(rpath);
    }

    public static <T> void writeJson(String path, T object) throws IOException {
        Gson gs = new GsonBuilder().setPrettyPrinting().create();
        FileHelper.writeFile(path, gs.toJson(object, new TypeToken<T>(){}.getType()));
    }

    public static HashMap<String, Object> mapJson(String json) {
        Type type = new TypeToken<HashMap<String, String>>(){}.getType();
        return new Gson().fromJson(json, type);
    }
}
