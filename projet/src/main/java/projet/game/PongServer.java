package projet.game;

/***** IMPORTS *****/

// Server
import projet.network_engine.Server;

/***** CLASS *****/

/**
 * PongServer class that extends the server class from the network engine to
 * define the server routine and a new constructor
 */
public class PongServer extends Server {

    /***** CONSTRUCTORS *****/

    /**
     * Constructor for the PongServer class
     *
     * @param _port
     * @param _clientsNumber
     */
    public PongServer(int _port, int _clientsNumber) {
        super(_port, _clientsNumber);
    }

    /***** METHODS *****/

    /**
     * Definition of the server routine
     *
     * @param username The username of the client
     */
    @Override
    public void runningRoutine(String username) {
        String message;
        while (true) {
            synchronized (messages) {
                if (messages.size() > 0) {
                    message = messages.remove().toString();
                    diffuseMessage(message, message.split(" ")[1]);
                }
            }
        }
    }
}
