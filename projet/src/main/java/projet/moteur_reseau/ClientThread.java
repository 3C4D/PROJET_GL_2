package projet.moteur_reseau;

/***** IMPORTS *****/

// Input/Output
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

// Networking things
import java.net.Socket;

/***** CLASS *****/

/***
 * Client thread class : used to handle one connection between the server and a client
 */
public class ClientThread implements Runnable {

    /***** PARAMETERS *****/

    // Networking
    private Socket connection;
    private Server server;

    // Input/Output
    private ObjectOutputStream out;
    private ObjectInputStream in;

    // Others
    private Data clientMessage;
    private Data serverMessage;
    private int clientID;

    /***** METHODS *****/

    /***
     * Constructor
     * @param _connection The socket used to discuss with the client
     * @param _server The server that runs everything
     */
    ClientThread(Socket _connection, Server _server) {
        connection = _connection;
        server = _server;

        // Getting input and output stream
        try {
            out = new ObjectOutputStream(connection.getOutputStream());
            in = new ObjectInputStream(connection.getInputStream());
            clientID = server.connectClient(out, in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * Run method : instructions the server will execute with every client
     */
    public void run() {
        do {
            try {
                clientMessage = (Data) in.readObject();
                if (clientMessage != null) {
                    serverMessage = new Data("Client " + clientID + " says, " + clientMessage.toString());
                    server.diffuseMessage(serverMessage);
                }
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        } while (true);
    }
}
