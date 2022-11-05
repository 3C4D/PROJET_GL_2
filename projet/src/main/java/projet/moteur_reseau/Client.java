package projet.moteur_reseau;

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
    Socket connection;

    // Input/Output
    ObjectOutputStream out;
    ObjectInputStream in;

    // Others
    String username;
    String message;

    /***** METHODS *****/

    /***
     * Constructor
     * @param _address Server's IP address
     * @param _port Server's listenning port
     */
    public Client(InetAddress _address, int _port, String _username) {
        try {
            username = _username;
            connection = new Socket(_address, _port);
            out = new ObjectOutputStream(connection.getOutputStream());
            in = new ObjectInputStream(connection.getInputStream());
            sendMessage("USERNAME " + username);
        } catch (IOException e) {
            System.out.println("Invalid IP address or port number");
        }
    }

    /***
     * Send a message to the server
     * @param message  The message to send
     */
    public void sendMessage(String message) {
        try {
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * Get a message to the server
     */
    public String getMessage() throws EOFException {
        try {
            message = in.readObject().toString();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return message;
    }
}
