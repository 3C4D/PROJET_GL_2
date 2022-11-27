package projet.network_engine;

/***** IMPORTS *****/

// Input/Output
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.EOFException;
import java.io.IOException;

// Networking things
import java.net.InetAddress;
import java.net.Socket;

/***** CLASS *****/

/***
 * Client class
 */
public class Client {

    /***** PARAMETERS *****/

    // Networking
    private Socket connection;

    // Input/Output
    private ObjectOutputStream out;
    private ObjectInputStream in;

    // Others
    private String username;

    /***** METHODS *****/

    /***
     * Constructor
     */
    public Client() {
        // Nothing to do
    }

    /***
     * Connect to a server
     * @param ip IP address of the server
     * @param port Port of the server
     * @param username Username of the client
     */
    public void connect(InetAddress _address, int _port, String _username) {
        try {
            username = _username;
            connection = new Socket(_address, _port);
            out = new ObjectOutputStream(connection.getOutputStream());
            in = new ObjectInputStream(connection.getInputStream());
            sendMessage(new NetworkData("CONNECT " + username));
        } catch (IOException e) {
            System.out.println("Invalid IP address or port number");
        }
    }

    /***
     * Disconnect from the server
     */
    public void disconnect() {
        try {
            sendMessage(new NetworkData("DISCONNECT " + username));
            in.close();
            out.close();
            connection.close();
        } catch (IOException e) {
            System.out.println("Error while disconnecting");
        }
    }

    /***
     * Send a message to the server
     * @param message  The message to send
     */
    public void sendMessage(Object message) {
        try {
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * Get a message from the server if there is one, return null otherwise
     */
    public Object getMessage() throws EOFException {
        try {
            if (in.available() > 0) {
                return in.readObject();
            } else {
                return null;
            }
        } catch (ClassNotFoundException | IOException e) {
        }
        return null;
    }
}