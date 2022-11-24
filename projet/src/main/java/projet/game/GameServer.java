package projet.game;

/***** IMPORTS *****/

import java.io.ObjectInputStream;
import projet.network_engine.Server;

/***** CLASS *****/

/***
 * GameServer class that extends the server class from the network engine to define the server routine
 */
public class GameServer extends Server {
    public GameServer(int _port, int _clientsNumber) {
        super(_port, _clientsNumber);
    }

    /***
     * Definition of the server routine
     * @param in The input stream of the client connected to the client thread
     * @param username The username of the client connected to the client thread
     */
    @Override
    public void runningRoutine(ObjectInputStream in, String username) {

    }
}
