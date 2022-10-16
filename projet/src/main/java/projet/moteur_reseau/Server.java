package projet.moteur_reseau;

/***** IMPORTS *****/

// Input/Output
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

// Components
import java.util.Vector;

// Networking things
import java.net.ServerSocket;

/***** CLASS *****/

/***
 * Server class
 */
public class Server {

    /***** PARAMETERS *****/
    
    ServerSocket listener;
    Vector<Thread> clientThreads;
    Vector<ObjectOutputStream> out;         // Output streams for every client
    Vector<ObjectInputStream> in;           // Input stream for every client
    int clientsConnected;
    int port;

    /***** METHODS *****/

    /***
     * Constructor
     * @param _port Where the server will listen
     */
    Server(int _port) {
        try {
            port = _port;
            listener = new ServerSocket(_port);
            //clients = new HashMap<>();
            clientThreads = new Vector<>();
            out = new Vector<>();
            in = new Vector<>();
            clientsConnected = 0;
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
            out.removeElementAt(_clientID);
        }
        if (in.elementAt(_clientID) != null) {
            in.removeElementAt(_clientID);
        }
    }

    /***
     * Diffuse a message to every connected client
     * @param _message  The message to diffuse
     */
    synchronized public void diffuseMessage(Data _message)
    {
        ObjectOutputStream send;
        for (int i=0; i<out.size(); i++) {
            send = out.elementAt(i);
            if (send != null) {
                try {
                    send.writeObject(_message);
                    send.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void end() {
        for (int i=0; i<clientThreads.size(); i++) {
            try {
                System.out.println("Disconnecting from client " + i);
                clientThreads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /***
     * Run method, where the server will lauch client threads for every new connection
     */
    public void run() {
        while (true) {
            // Trying to handle a connection
            try {
                new ClientThread(listener.accept(), this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /***
     * Main method, in charge of running the run method
     * @param args
     * @throws InterruptedException
     */        
    public static void main(String[] args) throws InterruptedException {
        Server server = new Server(4000);
        server.run();
        Thread.sleep(5000);
        server.end();
    }
}