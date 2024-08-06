package Server;

import Utils.Logger;
import Utils.Settings;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    private static final int PORT = Settings.readSetting();
    public static ArrayList<ClientThread> connectionsList = new ArrayList<ClientThread>();
    static Logger logger = new Logger();
    private static Socket clientSocket;

    public ChatServer() {
    }

    public static void removeClient(ClientThread client) {
        connectionsList.remove(client);
    }

    public static void main(String[] args) throws IOException {
        new ChatServer().start();
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        try {
            System.out.println("Server start  " + serverSocket);
            logger.logInfo("Сервер начал работу " + serverSocket);
            while (true) {
                clientSocket = serverSocket.accept();
                try {
                    System.out.println("Connect new client: " + clientSocket);
                    logger.logInfo("Новое подключение: " + clientSocket);
                    ClientThread clientThread = new ClientThread(clientSocket);
                    connectionsList.add(clientThread);
                    clientThread.start();
                } catch (IOException e) {
                    clientSocket.close();
                    logger.logInfo("Сокет: " + clientSocket + " закрыт");
                }
            }
        } finally {
            serverSocket.close();
            logger.logInfo("Сокет: " + serverSocket + " закрыт");
        }
    }
}
