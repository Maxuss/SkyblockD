package xyz.voidmoment.skyblockd.helpers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

public class JsonHelper<T> {
    private static Gson g = new Gson();
    private Class<T> cl;

    public JsonHelper(Class<T> tClass){
        cl = tClass;
    }

    public T deserializeJson(String json){
        return g.fromJson(json, cl);
    }

    public String serializeJson(T json){
        return g.toJson(json);
    }

    public T jsonFromFile(String path) throws IOException {
        return deserializeJson(FileHelper.readFromFile(new File(path)));
    }

    public T jsonFromResource(String rpath) throws IOException {
        return deserializeJson(ResourceHelper.readResource(rpath));
    }

    public static String readJsonFile(String path)throws IOException {
        return FileHelper.readFromFile(new File(path));
    }

    public static String readJsonResource(String rpath)throws IOException {
        return ResourceHelper.readResource(rpath);
    }

    public static void writeJson(String path, Object object) throws IOException {
        JsonHelper<Object> j = new JsonHelper<>(Object.class);
        FileHelper.writeFile(path, j.serializeJson(object));
    }

    public static HashMap<String, Object> mapJson(String json){
        Type type = new TypeToken<HashMap<String, Object>>(){}.getType();
        return g.fromJson(json, type);
    }
}
