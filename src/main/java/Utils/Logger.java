package Utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {
    LocalDateTime lt = LocalDateTime.now();

    public void logInfo(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(lt).append("]").append(" ").append("'").append(s).append("'");
        fileWrite(String.valueOf(sb));
    }

    public void logMsg(String s, String userName) {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(lt).append("]").append(" ").append(userName).append(":").append("'").append(s).append("'");
        fileWrite(String.valueOf(sb));
    }

    public void fileWrite(String s) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/file.log", true));
            writer.write(s);
            writer.append("\n");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

