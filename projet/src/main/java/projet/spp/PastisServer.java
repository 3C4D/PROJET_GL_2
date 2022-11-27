package projet.spp;

/***** IMPORTS *****/

// Server
import projet.network_engine.Server;

// Input/Output
import java.io.ObjectInputStream;
import java.io.IOException;

// Others
import java.util.Vector;

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
        do {
            try {
                data = (PastisNetworkData) in.readObject();
            } catch (ClassNotFoundException | IOException e) {
                break;
            }
            if (data.message.split(" ")[0].equals("UPDATE")) {
                getEntities();
                data.entities = entities;
                data.message = "UPDATE";
                diffuseMessage(data, null);
            }
        } while (data.message.split(" ")[0].equals("DISCONNECT"));
        disconnectClient(data.message.split(" ")[1]);
    }
}
