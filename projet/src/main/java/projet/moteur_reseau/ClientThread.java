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
    private ObjectInputStream in;
    private ObjectOutputStream out;

    // Others
    private String username;
    private String message;

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
            in = new ObjectInputStream(connection.getInputStream());
            out = new ObjectOutputStream(connection.getOutputStream());
            username = in.readObject().toString().split(" ")[1];
            server.connectClient(username, out);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /***
     * Run method : instructions the server will execute with every client
     */
    public void run() {
        do {
            try {
                message = in.readObject().toString();
                if (!message.split(" ")[0].equals("DISCONNECT")) {
                    server.diffuseMessage(message, username);
                }
            } catch (ClassNotFoundException | IOException e) {
                break;
            }
        } while (!message.split(" ")[0].equals("DISCONNECT"));
        System.out.println(message.split(" ")[1] + " disconnected");
        server.disconnectClient(message.split(" ")[1]);
        try {
            in.close();
            out.close();
        } catch (IOException e) {
        }
    }
}