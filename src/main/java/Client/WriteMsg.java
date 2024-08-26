package Client;

import Utils.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class WriteMsg extends Thread {
    public static Logger logger = new Logger();
    private final PrintWriter out;
    private final BufferedReader stdIn;
    private final String userName;
    private final Socket socket;

    public WriteMsg(PrintWriter out, BufferedReader stdIn, String userName, Socket socket) {
        this.out = out;
        this.stdIn = stdIn;
        this.userName = userName;
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Напишите сообщение: ");
            try {
                String clientInput = stdIn.readLine();
                if (clientInput.equals("exit")) {
                    out.println("exit");
                    logger.logInfo("[" + userName + "]  " + "Вышел из чата");
                    stopService();
                    break;
                }
                out.println("[" + userName + "]  " + clientInput);
                logger.logMsg(clientInput, userName);

            } catch (IOException e) {
                try {
                    stopService();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    private void stopService() {
        try {
            if (!socket.isClosed()) {
                stdIn.close();
                out.close();
                socket.close();
            }
        } catch (IOException ignored) {
        }
    }
}
