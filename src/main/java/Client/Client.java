package Client;

import Utils.Settings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = Settings.readSetting();
    private static Socket clientSocket;
    private static BufferedReader in;
    private static PrintWriter out;
    private static BufferedReader stdIn;

    public Client() {

    }

    public static void main(String[] args) throws IOException {
        new Client().start();
    }

    public static String getUserName(BufferedReader stdIn) throws IOException {
        System.out.println("Напишите свое имя для входа в чат: ");
        return stdIn.readLine();
    }

    public static void sendUserName(PrintWriter out, String userName) throws IOException {
        out.println(userName);
    }

    public void start() throws IOException {
        clientSocket = new Socket(SERVER_ADDRESS, PORT);
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            String name = getUserName(stdIn);
            sendUserName(out, name);
            if (!name.equals("exit")) {
                new ReadMsg(in, clientSocket).start();
                new WriteMsg(out, stdIn, name, clientSocket).start();
            }
        } catch (IOException e) {
            clientSocket.close();
        }
    }
}



