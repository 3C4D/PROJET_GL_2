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

    ObjectOutputStream out;
    ObjectInputStream in;
    Socket connection;
    Data msg;

    /***** METHODS *****/

    /***
     * Constructor
     * @param _address Server's IP address
     * @param _port Server's listenning port
     */
    Client(InetAddress _address, int _port) {
        try {
            connection = new Socket(_address, _port);
            System.out.println("Connected to " + _address.getHostName() + " on port " + _port);
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
            out.writeObject(new Data("Hello server !"));
            out.flush();
            out.writeObject(new Data("I am your best client"));
            out.flush();
            out.writeObject(null);
            out.flush();

            // Reading and displaying things
            do {
                try {
                    
                    msg = (Data) in.readObject();
                    System.out.println("From server -> " + msg);
                } catch (EOFException | ClassNotFoundException end) {
                    break;
                }
            } while (msg != null);

            // Closing I/O streams and socket
            in.close();
            out.close();
            connection.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * Main method, in charge of running the run method
     * @param args No arguments needed here
     * @throws InterruptedException
     */
    public static void main(String[] args) throws Exception {
        byte addr[] = { 127, 0, 0, 1 };
        Client client = new Client(InetAddress.getByAddress(addr), 4000);
        client.run();
    }
}
