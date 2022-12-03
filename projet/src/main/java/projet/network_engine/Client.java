package projet.network_engine;

/***** IMPORTS *****/

// Input/Output
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.EOFException;
import java.io.IOException;

// Networking things
import java.net.InetAddress;
import java.net.Socket;

// Others
import java.util.LinkedList;

/***** CLASS *****/

/**
 * Client class
 */
public class Client {

    /***** PARAMETERS *****/

    // Networking
    private Socket connection;

    // Input/Output
    public LinkedList<Object> messages;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    // Others
    private String username;

    private class ReadingThread extends Thread implements Serializable {
        Client c;

        ReadingThread(Client _c) {
            c = _c;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Object msg = new Object();
                    msg = c.getMessage();
                    if (msg != null) {
                        messages.add(msg);
                    } 
                } catch (EOFException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /***** CONSTRUCTORS *****/

    /**
     * Default constructor
     */
    public Client() {
        messages = new LinkedList<>();
    }

    /***** METHODS *****/

    /**
     * Connect to a server
     * 
     * @param ip       IP address of the server
     * @param port     Port of the server
     * @param username Username of the client
     */
    public void connect(InetAddress _address, int _port, String _username) {
        try {
            username = _username;
            connection = new Socket(_address, _port);
            out = new ObjectOutputStream(connection.getOutputStream());
            in = new ObjectInputStream(connection.getInputStream());
            sendMessage("CONNECT " + username);
        } catch (IOException e) {
            System.out.println("Invalid IP address or port number");
        }
    }

    /**
     * Disconnect from the server
     */
    public void disconnect() {
        try {
            sendMessage("DISCONNECT " + username);
            in.close();
            out.close();
            connection.close();
        } catch (IOException e) {
            System.out.println("Error while disconnecting");
        }
    }

    /**
     * Send a message to the server
     * 
     * @param message The message to send
     */
    public void sendMessage(Object message) {
        try {
            System.out.println("Je send");
            out.writeObject(message);
            System.out.println("C'est ici la merde");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get a message from the server if there is one, return null otherwise
     */
    public Object getMessage() throws EOFException {
        try {
            return in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            return null;
        }
    }

    public void startReading() {
        new ReadingThread(this).start();
    }
}