package projet.moteur_reseau;

/***** IMPORTS *****/

// Input/Output
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

// Networking things
import java.net.ServerSocket;

// Threads
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Components
import java.util.Vector;

/***** CLASS *****/

/***
 * Server class
 */
public class Server {

    /***** PARAMETERS *****/
    
    // Networking
    ServerSocket listener;

    // Threads
    //Vector<Thread> clientThreads;
    ExecutorService executor;

    // Input/Output
    Vector<ObjectOutputStream> out;         // Output streams for every client
    Vector<ObjectInputStream> in;           // Input stream for every client
 
    // Other
    boolean isRunning;
    int clientsConnected;                   // The current number of clients connected
    int clientsNumber;                      // The maximum number of clients that should be connected
    int port;                               // Port where the server is listenning

    /***** METHODS *****/

    /***
     * Constructor
     * @param _port Where the server will listen
     */
    Server(int _port, int _clientsNumber) {
        try {
            port = _port;
            listener = new ServerSocket(_port);
            //clients = new HashMap<>();
            //clientThreads = new Vector<>();
            out = new Vector<>();
            in = new Vector<>();
            clientsConnected = 0;
            clientsNumber = _clientsNumber;
            isRunning = true;
            executor = Executors.newFixedThreadPool(_clientsNumber);
        } catch (IOException e) {
            System.out.println("Invalid port number");
        }
    }

    /***
     * Connect a client to the server
     * @param _out The output stream of this client
     * @param _in The input stream of this client
     * @return The ID of the client just connected
     */
    synchronized public int connectClient(ObjectOutputStream _out, ObjectInputStream _in) {
        clientsConnected++;
        out.addElement(_out);
        in.addElement(_in);
        return out.size()-1;
    }

    /***
     * Disconnect a client from the server
     * @param _clientID The ID of the client who will be disconnected
     */
    synchronized public void disconnectClient(int _clientID) {
        clientsConnected--;
        if (out.elementAt(_clientID) != null) {
            out.setElementAt(null,_clientID);
        }
        if (in.elementAt(_clientID) != null) {
            in.setElementAt(null,_clientID);
        }
    }

    /***
     * Diffuse a message to every connected client
     * @param _message  The message to diffuse
     */
    synchronized public void diffuseMessage(Data _message)
    {
        for (int i=0; i<out.size(); i++) {
            sendMessage(_message, i);
        }
    }

    /***
     * Send a message to a client
     * @param _message  The message to diffuse
     * @param _clientID The ID of the client
     */
    synchronized public void sendMessage(Data _message, int _clientID)
    {
        ObjectOutputStream send;
        send = out.elementAt(_clientID);
        if (send != null) {
            try {
                send.writeObject(_message);
                send.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /***
     * Wait for the end of client threads
     */
    synchronized public void end() {
        isRunning = false;
        /*for (int i=0; i<clientThreads.size(); i++) {
            try {
                long truc = clientThreads.get(i).getId();
                clientThreads.get(i).join();
                System.out.println(truc + " is stopping... " + clientsConnected);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        executor.shutdown();  
        while (!executor.isTerminated()) {
            System.out.println("Disconnecting...");
        }  
        System.out.println("Finished all threads");  
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

    /***
     * Main method, in charge of running the run method
     * @param args
     * @throws InterruptedException
     */        
    public static void main(String[] args) throws InterruptedException {
        Server server = new Server(4000, 2);
        server.run();
    }
}