import Server.ChatServer;
import Server.ClientThread;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClientThreadTest {
    @Test
    public void testSendMsg() throws IOException {
        String msg = "Test message";
        Socket socket = mock(Socket.class);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(msg.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        when(socket.getInputStream()).thenReturn(inputStream);
        when(socket.getOutputStream()).thenReturn(outContent);

        ClientThread client = new ClientThread(socket);

        client.sendMsg(msg);

        assertEquals("Test message\r\n", outContent.toString());
    }

    @Test
    public void testSendMsgAll() throws IOException {
        String msg = "Test message";
        Socket socket = mock(Socket.class);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(msg.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        when(socket.getInputStream()).thenReturn(inputStream);
        when(socket.getOutputStream()).thenReturn(outContent);

        ClientThread clientThread1 = new ClientThread(socket);
        ClientThread clientThread2 = mock(ClientThread.class);
        ClientThread clientThread3 = mock(ClientThread.class);

        ChatServer.connectionsList.add(clientThread1);
        ChatServer.connectionsList.add(clientThread2);
        ChatServer.connectionsList.add(clientThread3);
        clientThread1.sendMsgALL(msg, clientThread1);

        verify(clientThread2, times(1)).sendMsg(msg);
        verify(clientThread3, times(1)).sendMsg(msg);
    }
}
