package projet.network_engine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.EOFException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.jupiter.api.Test;

import projet.moteur_reseau.Client;
import projet.moteur_reseau.Server;

public class TestNetwork {
    @Test
    public void testConnection() throws UnknownHostException, InterruptedException {
        Server server = new Server(4000, 2);
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
        Server server = new Server(4001, 2);
        server.start();
        Client client1 = new Client();
        Client client2 = new Client();
        client1.connect(InetAddress.getLocalHost(), 4001, "Client1");
        client2.connect(InetAddress.getLocalHost(), 4001, "Client2");
        client1.sendMessage("Hello");
        Thread.sleep(1000);
        client2.getMessage();
        assertEquals("Hello", client2.getMessage());
    }

    @Test
    public void testUserList() throws UnknownHostException, InterruptedException, EOFException {
        Server server = new Server(4002, 2);
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
        Server server = new Server(4003, 2);
        server.start();
        Client client1 = new Client();
        Client client2 = new Client();
        client1.connect(InetAddress.getLocalHost(), 4003, "Client1");
        client2.connect(InetAddress.getLocalHost(), 4003, "Client2");
        Thread.sleep(1000);
        client1.disconnect();
        Thread.sleep(1000);
        assertEquals(1, server.getClientsConnected());
        client2.disconnect();
        Thread.sleep(1000);
        assertEquals(0, server.getClientsConnected());
    }
}