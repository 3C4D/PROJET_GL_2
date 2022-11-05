package projet.moteur_reseau;

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

// Others
import java.util.concurrent.TimeUnit;

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
    boolean isRunning = true;                       // Is the server running ?
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
     * Connect a client to the server
     * @param _out The output stream of this client
     * @param _in The input stream of this client
     */
    synchronized public void connectClient(String username, ObjectOutputStream out) {
        clientsConnected++;
        clients.put(username, out);
        sendUserList();
    }

    /***
     * Disconnect a client from the server
     * @param _clientID The ID of the client who will be disconnected
     */
    synchronized public void disconnectClient(String username) {
        clientsConnected--;
        clients.remove(username);
        sendUserList();
    }

    /***
     * Diffuse a message to every connected client
     * @param _message  The message to diffuse
     */
    synchronized public void diffuseMessage(String message)
    {
        Object[] users = clients.keySet().toArray();
        for (int i=0; i<clients.size(); i++) {
            sendMessage(message, users[i].toString());
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
        diffuseMessage(message);
    }

    /***
     * Wait for the end of client threads
     */
    synchronized public void end() {
        isRunning = false;
        executor.shutdown(); 
        try {
            while (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                System.out.println("Disconnecting...");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }  
        System.out.println("Server is closed"); 
    }

    /***
     * Run method, where the server will lauch client threads for every new connection
     */
    public void run() {
        try {
            while (isRunning) {
                // Trying to handle a connection
                Runnable worker = new ClientThread(listener.accept(), this);
                executor.execute(worker);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}