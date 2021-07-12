package space.maxus.skyblockd.helpers;

import space.maxus.skyblockd.SkyblockD;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ResourceHelper {
    @Deprecated
    public static URL getResourceURL(String path) {
        return ResourceHelper.class.getClassLoader().getResource(path);
    }

    public static File getFileFromURL(URL url) {
        File file;
        try {
            file = new File(url.toURI());
        } catch (Exception e) {
            file = new File(url.getPath());
        }
        return file;
    }

    public static InputStream getResource(String path) {
        ClassLoader loader = ResourceHelper.class.getClassLoader();
        SkyblockD.logger.info(Objects.requireNonNull(loader.getResourceAsStream(path)).toString());
        return loader.getResourceAsStream(path);
    }


    public static String readResource(String path) {
        return FileHelper.readFromStream(getResource(path));
    }

    @Deprecated
    public static String readResourceFile(String rpath) throws IOException {
        return FileHelper.readFromUrl(getResourceURL(rpath.startsWith("/") ? rpath : "/" + rpath));
    }

    public static String readResourceSafely(String rpath) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(ResourceHelper.class.getClassLoader().getResourceAsStream(rpath)), StandardCharsets.UTF_8));
        String line;
        while((line = br.readLine()) != null){
            sb.append(line).append("\n");
        }
        return sb.toString();
    }
}
