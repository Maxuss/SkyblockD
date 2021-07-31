package space.maxus.skyblockd.helpers;

import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerHelper {
    public static @NotNull String makeRequest(@NotNull String url) throws IOException {
        StringBuilder result = new StringBuilder();
        URL _url = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) _url.openConnection();
        conn.setRequestMethod("GET");
        SkyblockD.logger.info("Established connection with " + url);
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        SkyblockD.logger.info("Successfully read HTTP data from " + url);
        SkyblockD.logger.info("Data: "+ result);
        return result.toString();
    }

    public static <T> T makeJsonRequest(@NotNull String url, Type t) throws IOException {
        String data = makeRequest(url);
        return new Gson().fromJson(data, t);
    }
}
