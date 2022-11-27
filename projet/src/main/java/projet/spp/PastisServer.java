package projet.spp;

// Input/Output
import java.io.IOException;
import java.io.ObjectInputStream;
// Others
import java.util.Vector;

/***** IMPORTS *****/

// Server
import projet.network_engine.Server;

/***** CLASS *****/

/***
 * PastisServer class that extends the server class from the network engine to 
 * define the server routine and a new constructor
 */
public class PastisServer extends Server {
    MyWorld world;
    Vector<MyEntity> entities;

    /***
     * Constructor for the PastisServer class
     * @param _port
     * @param _clientsNumber
     * @param _world
     */
    public PastisServer(int _port, int _clientsNumber, MyWorld _world) {
        super(_port, _clientsNumber);
        world = _world;
        entities = new Vector<MyEntity>();
    }

    /***
     * Getter for entities to update during a game session
     */
    private void getEntities() {
        Vector<Ball> balls = world.getBalls();
        for (int i=0; i<balls.size(); i++) {
            entities.add(balls.get(i));
        }
    }

    /***
     * Definition of the server routine
     * @param in The input stream of the client
     * @param username The username of the client
     */
    @Override
    public void runningRoutine(ObjectInputStream in, String username) {
        PastisNetworkData data = new PastisNetworkData();
        String message = "";
        Object read = new Object();
        while (true) {
            try {
                read = in.readObject();
            } catch (ClassNotFoundException | IOException e) {}

            if (read instanceof PastisNetworkData) {
                data = (PastisNetworkData) read;
                if (data.message.split(" ")[0].equals("UPDATE")) {
                    getEntities();
                    data.entities = entities;
                    data.message = "UPDATE";
                    diffuseMessage(data, null);
                }
            } else if (read instanceof String) {
                message = (String) read;
                if (message.split(" ")[0].equals("DISCONNECT")) {
                    break;
                }
            }
            
        }
        disconnectClient(message.split(" ")[1]);
    }
}
