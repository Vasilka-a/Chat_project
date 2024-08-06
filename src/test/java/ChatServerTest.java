import Server.ChatServer;
import Server.ClientThread;
import Utils.Settings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class ChatServerTest {

    @Test
    public void readingSettingTest() {
        int portActual = 8081;
        int portExpected = Settings.readSetting();
        assertEquals(portExpected, portActual);
    }

    @Test
    public void testRemoveClient() {
        ClientThread client1 = mock(ClientThread.class);
        ClientThread client2 = mock(ClientThread.class);
        ClientThread client3 = mock(ClientThread.class);

        ChatServer.connectionsList.add(client1);
        ChatServer.connectionsList.add(client2);
        ChatServer.connectionsList.add(client3);

        ChatServer.removeClient(client2);
        assertEquals(2, ChatServer.connectionsList.size());
    }
}