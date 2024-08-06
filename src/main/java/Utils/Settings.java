package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Settings {
    private static int port;

    public static int readSetting() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/settings.txt"))) {
            String s;
            while ((s = reader.readLine()) != null) {
                if (s.contains("port")) {
                    String[] arr = s.split(" ");
                    port = Integer.parseInt(arr[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return port;
    }
}

