package xyz.voidmoment.skyblockd.helpers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ResourceHelper {
    @Deprecated
    public static URL getResourceURL(String path){
        return ResourceHelper.class.getClassLoader().getResource(path);
    }

    public static File getFileFromURL(URL url){
        File file;
        try {
            file = new File(url.toURI());
        } catch (Exception e) {
            file = new File(url.getPath());
        }
        return file;
    }

    public static InputStream getResource(String path){
        ClassLoader loader = ResourceHelper.class.getClassLoader();
        return loader.getResourceAsStream(path);
    }


    public static String readResource(String path) throws IOException {
        return FileHelper.readFromStream(getResource(path));
    }

    @Deprecated
    public static String readResourceFile(String rpath) throws IOException {
        return FileHelper.readFromUrl(getResourceURL(rpath.startsWith("/") ? rpath : "/" + rpath));
    }
}
