package projet.spp;

// Input/Output
import java.io.IOException;
import java.io.ObjectInputStream;
// Others
import java.util.Vector;

import projet.game.Racket;

/***** IMPORTS *****/

// Server
import projet.network_engine.Server;

/***** CLASS *****/

/***
 * PastisServer class that extends the server class from the network engine to 
 * define the server routine and a new constructor
 */
public class PastisServer extends Server {
    PastisNetworkData data;
    MyWorld world;
    
    /***
     * Constructor for the PastisServer class
     * @param _port
     * @param _clientsNumber
     * @param _world
     */
    public PastisServer(int _port, int _clientsNumber, MyWorld _world) {
        super(_port, _clientsNumber);
        world = _world;
    }

    /***
     * Update current entities during game session
     */
    private void updateEntities() {
        data.setEntities(world.getBalls());
    }

    /***
     * Definition of the server routine
     * @param in The input stream of the client
     * @param username The username of the client
     */
    @Override
    public void runningRoutine(ObjectInputStream in, String username) {
        PastisNetworkData receive = new PastisNetworkData();
        Object read = new Object();
        while (true) {
            try {
                read = in.readObject();
            } catch (ClassNotFoundException | IOException e) {}

            if (read instanceof PastisNetworkData) {
                receive = (PastisNetworkData) read;
                if (receive.getMessage().split(" ")[0].equals("UPDATE")) {
                    updateEntities();
                    data.setMessage("UPDATE");
                    diffuseMessage(data, null);
                } else if (receive.getMessage().split(" ")[0].equals("RACKET")) {
                    data.setRacket(receive.getRackets().get(0));
                    data.setMessage("RACKET");
                    diffuseMessage(data, receive.getMessage().split(" ")[1]);
                }
            } else if (read instanceof String) {
                receive.setMessage((String) read);
                if (receive.getMessage().split(" ")[0].equals("DISCONNECT")) {
                    break;
                }
            }            
        }
        disconnectClient(receive.getMessage().split(" ")[1]);
    }
}
