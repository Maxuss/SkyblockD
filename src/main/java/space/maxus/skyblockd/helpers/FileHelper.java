package space.maxus.skyblockd.helpers;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;

import java.io.*;

public class FileHelper {

    // unsafe!

    public static @NotNull String readFromStream(@NotNull InputStream stream)  {
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
    public static String readFromFile(@NotNull File file) throws IOException {
        return Files.toString(file, Charsets.UTF_8);
    }

    public static void writeFile(@NotNull String path, @NotNull String contents) throws IOException {
        File f = new File(path);
        if(!f.exists()) {boolean ignored = f.createNewFile();}
        FileWriter w = new FileWriter(f);
        w.write(contents);
        w.flush();
        w.close();
    }

}
