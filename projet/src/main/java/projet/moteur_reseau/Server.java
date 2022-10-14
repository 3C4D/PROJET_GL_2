package projet.moteur_reseau;

/***** IMPORTS *****/

// Input/Output
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.EOFException;
import java.io.IOException;

// Networking things
import java.net.ServerSocket;
import java.net.Socket;

/***** CLASS *****/

/***
 * Server class
 */
public class Server extends Thread {

    /***** PARAMETERS *****/
    
    ObjectOutputStream out;
    ObjectInputStream in;
    ServerSocket listener;
    Socket connection;
    Data msg;

    /***** METHODS *****/

    /***
     * Constructor
     * @param _port Where the server will listen
     */
    Server(int _port) {
        try {
            listener = new ServerSocket(_port);
        } catch (IOException e) {
            System.out.println("Invalid port number");
        }
    }

    /***
     * Run method, where the server will be launched and accept connections
     */
    public void run() {
        try {
            // Trying to handle a connection
            connection = listener.accept();
            // Getting input and output stream
            out = new ObjectOutputStream(connection.getOutputStream());
            in = new ObjectInputStream(connection.getInputStream());
            System.out.println("Connection established with " + connection.getInetAddress().getHostName());

            // Reading and displaying things
            do {
                try {
                    
                    msg = (Data) in.readObject();
                    System.out.println("From client -> " + msg);
                } catch (EOFException | ClassNotFoundException end) {
                    break;
                }
            } while (msg != null);
            
            in.close();
            out.close();
            connection.close();
            listener.close();

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
        Server server = new Server(4000);
        server.start();
        server.join();
    }
}