package space.maxus.skyblockd.helpers;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import space.maxus.skyblockd.SkyblockD;

import java.io.*;
import java.net.URL;

public class FileHelper {

    // unsafe!

    public static String readFromStream(InputStream stream)  {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {
            while (br.readLine() != null) {
                resultStringBuilder.append(br.readLine()).append("\n");
            }
        } catch (IOException e){
            SkyblockD.logger.severe("IO Exception happened! " + e.getCause());
            SkyblockD.logger.info("It might happen sometimes if you reload server too much!");
        } catch (Exception e){
            SkyblockD.logger.severe("An unexpected exception happened! " + e.getCause());
        }
        return resultStringBuilder.toString();
    }

    @SuppressWarnings("UnstableApiUsage")
    public static String readFromFile(File file) throws IOException {
        return Files.toString(file, Charsets.UTF_8);
    }

    public static void writeFile(String path, String contents) throws IOException {
        File f = new File(path);
        if(!f.exists()) {boolean ignored = f.createNewFile();}
        FileWriter w = new FileWriter(f);
        w.write(contents);
        w.flush();
        w.close();
    }

}
