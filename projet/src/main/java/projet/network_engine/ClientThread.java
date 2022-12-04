package projet.network_engine;

/***** IMPORTS *****/

// Input/Output
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

// Networking things
import java.net.Socket;

/***** CLASS *****/

/**
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

    private class ReadingThread extends Thread {
        @Override
        public void run() {
            while (true) {
                Object msg = getMessage();
                if (msg != null) {
                    server.messages.add(msg);
                }
            }
        }
    }

    /***** CONSTRUCTORS *****/

    /**
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
            String message = in.readObject().toString();
            username = message.split(" ")[1];
            server.connectClient(username, out, in);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /***** METHODS *****/

    /**
     * Get a message from a client
     * @return The object read by the server if there is one, null otherwise
     */
    public Object getMessage() {
        try {
            return in.readObject();

        } catch (ClassNotFoundException | IOException e) {
            return null;
        }
    }

    /**
     * Run method : instructions the server will execute with every client
     */
    public void run() {
        new ReadingThread().start();
        server.runningRoutine(this, username);
    }
}