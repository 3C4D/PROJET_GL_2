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
    Data serverMessage;

    /***** METHODS *****/

    /***
     * Constructor
     * @param _address Server's IP address
     * @param _port Server's listenning port
     */
    public Client(InetAddress _address, int _port) {
        try {
            connection = new Socket(_address, _port);
            out = new ObjectOutputStream(connection.getOutputStream());
            in = new ObjectInputStream(connection.getInputStream());
        } catch (IOException e) {
            System.out.println("Invalid IP address or port number");
        }
    }

    /***
     * Send a message to the server
     * @param _message  The message to send
     */
    public void sendMessage(Data _message)
    {
        try {
            out.writeObject(_message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * Get a message to the server
     */
    public void getMessage()
    {
        try {
            serverMessage = (Data) in.readObject();
            if (serverMessage != null) {
                System.out.println("From server -> " + serverMessage);
            }
        } catch (EOFException | ClassNotFoundException end) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
