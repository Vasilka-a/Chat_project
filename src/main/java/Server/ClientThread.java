package Server;

import Utils.Logger;

import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread {
    public static Logger logger = new Logger();
    private static BufferedReader in;
    private Socket socket;
    private PrintWriter out;

    public ClientThread(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
    }

    public synchronized void run() {
        try {
            String name = in.readLine();
            if (name.equals("exit")) {
                logger.logInfo("Пользователь вышел из чата");
                this.close();
            } else {
                for (ClientThread ct : ChatServer.connectionsList) {
                    ct.sendMsg("Подключился новый пользователь: " + name);
                    logger.logInfo("Подключился новый пользователь: " + name);
                }
            }
            while (true) {
                String incoming = in.readLine();
                if (incoming.equals("exit")) {
                    sendMsg("Пользователь " + name + " вышел из чата");
                    out.write(incoming);
                    this.close();
                    break;
                }
                sendMsgALL(incoming, this);
            }
        } catch (IOException e) {
            try {
                logger.logInfo(this.getName() + ": connection ended");
                this.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void sendMsg(String msg) {
        out.println(msg);
    }

    public void sendMsgALL(String msg, ClientThread clientThread) {
        for (ClientThread ct : ChatServer.connectionsList) {
            if (ct != clientThread) {
                ct.sendMsg(msg);
            }
        }
    }

    public void close() throws IOException {
        ChatServer.removeClient(this);
        in.close();
        out.close();
        socket.close();
    }
}


