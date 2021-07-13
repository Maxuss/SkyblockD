package space.maxus.skyblockd.helpers;

import space.maxus.skyblockd.SkyblockD;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.stream.Collectors;

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
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(rpath)) {
            try (InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader reader = new BufferedReader(isr)) {
                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }
    }
}
