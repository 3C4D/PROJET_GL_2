package projet.network_engine;

/***** IMPORTS *****/

// Input/Output
import java.io.ObjectOutputStream;
import java.io.IOException;

// Networking things
import java.net.ServerSocket;

// Threads
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Components
import java.util.HashMap;

/***** CLASS *****/

/***
 * Server class
 */
public class Server extends Thread {

    /***** PARAMETERS *****/
    
    // Networking
    ServerSocket listener;

    // Threads
    ExecutorService executor;

    // Clients
    HashMap<String, ObjectOutputStream> clients;    // Clients' names and output streams
    int clientsConnected;                           // The current number of clients connected
    int clientsNumber;                              // The maximum number of clients that should be connected

    // Others
    boolean complete = false;                               // Is the server complete ?
    int port;                                       // Port where the server is listenning

    /***** METHODS *****/

    /***
     * Constructor
     * @param _port Where the server will listen
     */
    public Server(int _port, int _clientsNumber) {
        try {
            
            port = _port;
            clientsNumber = _clientsNumber;
            listener = new ServerSocket(port);
            executor = Executors.newFixedThreadPool(clientsNumber);
            clientsConnected = 0;
            clients = new HashMap<String, ObjectOutputStream>();
        } catch (IOException e) {
            System.out.println("Invalid port number");
        }
    }

    /***
     * Return the number of clients connected
     * @return The number of clients connected
     */
    public int getClientsConnected() {
        return clientsConnected;
    }

    /***
     * Connect a client to the server
     * @param _out The output stream of this client
     * @param _in The input stream of this client
     */
    synchronized public void connectClient(String username, ObjectOutputStream out) {
        clientsConnected++;
        clients.put(username, out);
        sendUserList();
        if (clientsConnected == clientsNumber) {
            complete = true;
        }
    }

    /***
     * Disconnect a client from the server
     * @param _clientID The ID of the client who will be disconnected
     */
    synchronized public void disconnectClient(String username) {
        clientsConnected--;
        clients.remove(username);
        if (clientsConnected > 0) {
            sendUserList();
        }
    }

    /***
     * Diffuse a message to every connected client
     * @param _message  The message to diffuse
     */
    synchronized public void diffuseMessage(String message, String username) {
        if (clientsConnected > 0) {
            for (String client : clients.keySet()) {
                if (!client.equals(username)) {
                    try {
                        clients.get(client).writeObject(message);
                    } catch (IOException e) {
                        System.out.println("Error while sending message to " + client);
                    }
                }
            }
        }
    }

    /***
     * Send a message to a client
     * @param _message  The message to send
     * @param _clientID The ID of the client
     */
    synchronized public void sendMessage(String message, String username)
    {
        ObjectOutputStream send;
        send = clients.get(username);
        if (send != null) {
            try {
                send.writeObject(message);
                send.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /***
     * Send the list of connected clients to every connected client
     */
    synchronized public void sendUserList() {
        Object[] users = clients.keySet().toArray();
        String message = "USERLIST";
        for (int i=0; i<clients.size(); i++) {
            message += " " + users[i].toString();
        }
        diffuseMessage(message, "");
    }

    /***
     * Wait for the end of client threads
     * @throws InterruptedException
     */
    synchronized public void end() throws InterruptedException {
        diffuseMessage("STOP", "");
        executor.shutdown(); 
    }

    /***
     * Run method, where the server will lauch client threads for every new connection
     */
    public void run() {
        try {
            while (true) {
                // Trying to handle a connection
                Runnable worker = new ClientThread(listener.accept(), this);
                executor.execute(worker);
            }
        } catch (IOException e) {
        }
    }
}