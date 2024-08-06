import Client.Client;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ClientTest {
    @Test
    public void testGetName() throws IOException {
        Client client = mock(Client.class);
        ByteArrayInputStream in = new ByteArrayInputStream("Julie".getBytes());
        InputStream inputStream = System.in;
        System.setIn(in);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String actualName = "Julie";
        String name = client.getUserName(br);
        assertEquals(name, actualName);
        System.setIn(inputStream);
    }

    @Test
    public void testSendUserName() throws IOException {
        PrintWriter mockOut = mock(PrintWriter.class);
        BufferedReader mockStdIn = mock(BufferedReader.class);
        Client.sendUserName(mockOut, "Julie");

        verify(mockOut).println("Julie");
    }
}
