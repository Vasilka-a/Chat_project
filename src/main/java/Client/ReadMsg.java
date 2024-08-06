package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class ReadMsg extends Thread {
    private BufferedReader in;
    private Socket socket;

    public ReadMsg(BufferedReader in, Socket socket) {
        this.in = in;
        this.socket = socket;
    }

    @Override
    public void run() {
        String msg;
        try {
            while (true) {
                    msg = in.readLine();
                    if (msg.equals("exit")) {
                        System.out.println(msg);
                        stopService();
                        break;
                    }
                    System.out.println(msg);
            }
        } catch (IOException e) {
            try {
                stopService();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void stopService() {
        try {
            if (!socket.isClosed()) {
                in.close();
                socket.close();
            }
        } catch (IOException ignored) {
        }
    }
}
