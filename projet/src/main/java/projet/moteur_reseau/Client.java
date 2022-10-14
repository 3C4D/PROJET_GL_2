package projet.moteur_reseau;

/***** IMPORTS *****/

// Input/Output
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

// Networking things
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/***** CLASS *****/

/***
 * Client class
 */
public class Client {

    /***** PARAMETERS *****/

    Socket connection;
    ObjectOutputStream out;
    ObjectInputStream in;

    /***** METHODS *****/

    /***
     * Constructor
     * @param address Server's IP address
     * @param port Server's listenning port
     */
    Client(InetAddress address, int port) {
        try {
            connection = new Socket(address, port);
            System.out.println("Connected to " + address.getHostName() + " on port " + port);
        } catch (IOException e) {
            System.out.println("Invalid IP address or port number");
        }
    }

    /***
     * Run method, where the server will be launched and accept connections
     */
    public void run() {
        try {
            // Getting input and output stream
            out = new ObjectOutputStream(connection.getOutputStream());
            in = new ObjectInputStream(connection.getInputStream());

            // Sending messages
            out.writeObject("Hello server !");
            out.flush();
            out.writeObject("I am your best client");
            out.flush();

            in.close();
            out.close();
            connection.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * Main method, in charge of running the run method
     * @param args
     */
    public static void main(String[] args) {
        byte addr[] = { 127, 0, 0, 1 };
        try {
            Client client = new Client(InetAddress.getByAddress(addr), 4000);
            client.run();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
