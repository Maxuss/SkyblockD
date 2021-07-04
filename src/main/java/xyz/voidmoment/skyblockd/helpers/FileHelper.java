package xyz.voidmoment.skyblockd.helpers;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.*;
import java.net.URL;

public class FileHelper {
    public static String readFromStream(InputStream stream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    @SuppressWarnings("UnstableApiUsage")
    public static String readFromFile(File file) throws IOException {
        return Files.toString(file, Charsets.UTF_8);
    }

    public static String readFromUrl(URL url) throws IOException {
        return readFromFile(ResourceHelper.getFileFromURL(url));
    }

    public static void writeFile(String path, String contents) throws IOException {
        FileWriter w = new FileWriter(path);
        w.write(contents);
        w.flush();
        w.close();
    }
}
