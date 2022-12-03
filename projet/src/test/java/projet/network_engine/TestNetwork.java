package projet.network_engine;

/***** IMPORTS *****/

// Tests
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

// Exceptions
import java.net.UnknownHostException;
import java.io.EOFException;

// Networking
import java.net.InetAddress;

/**
 * Test class for the network engine
 */
public class TestNetwork {
    class TestServer extends Server {
        public TestServer(int _port, int _clientsNumber) {
            super(_port, _clientsNumber);
        }

        @Override 
        public void runningRoutine(ClientThread c, String username) {
            String message = "";
            do {
                message = c.messages.remove().toString();
                if (!message.split(" ")[0].equals("DISCONNECT")) {
                    diffuseMessage(message, username);
                }
            } while (!message.split(" ")[0].equals("DISCONNECT"));
            System.out.println(message.split(" ")[1] + " disconnected");
            disconnectClient(message.split(" ")[1]);
        }
    }

    @Test
    public void testConnection() throws UnknownHostException, InterruptedException {
        TestServer server = new TestServer(4000, 2);
        server.start();
        Client client1 = new Client();
        Client client2 = new Client();
        client1.connect(InetAddress.getLocalHost(), 4000, "Client1");
        Thread.sleep(1000);
        assertEquals(1, server.getClientsConnected());
        client2.connect(InetAddress.getLocalHost(), 4000, "Client2");
        Thread.sleep(1000);
        assertEquals(2, server.getClientsConnected());
    }

    @Test
    public void testMessage() throws UnknownHostException, InterruptedException, EOFException {
        TestServer server = new TestServer(4001, 2);
        server.start();
        Client client1 = new Client();
        Client client2 = new Client();
        client1.connect(InetAddress.getLocalHost(), 4001, "Client1");
        client1.startReading();
        client2.connect(InetAddress.getLocalHost(), 4001, "Client2");
        client2.startReading();
        client1.sendMessage("Hello");
        Thread.sleep(2000);
        assertEquals("Hello", client2.messages.removeFirst().toString());
    }

    @Test
    public void testUserList() throws UnknownHostException, InterruptedException, EOFException {
        TestServer server = new TestServer(4002, 2);
        server.start();
        Client client1 = new Client();
        Client client2 = new Client();
        client1.connect(InetAddress.getLocalHost(), 4002, "Client1");
        client2.connect(InetAddress.getLocalHost(), 4002, "Client2");
        Thread.sleep(1000);
        assertEquals("USERLIST Client1", client1.getMessage());
        assertEquals("USERLIST Client1 Client2", client1.getMessage());
        assertEquals("USERLIST Client1 Client2", client2.getMessage());
    }

    @Test
    public void testDisconnection() throws UnknownHostException, InterruptedException, EOFException {
        TestServer server = new TestServer(4003, 2);
        server.start();
        Client client1 = new Client();
        Client client2 = new Client();
        client1.connect(InetAddress.getLocalHost(), 4003, "Client1");
        client2.connect(InetAddress.getLocalHost(), 4003, "Client2");
        Thread.sleep(1000);
        client1.disconnect();
        Thread.sleep(2000);
        assertEquals(1, server.getClientsConnected());
        client2.disconnect();
        Thread.sleep(2000);
        assertEquals(0, server.getClientsConnected());
    }
}