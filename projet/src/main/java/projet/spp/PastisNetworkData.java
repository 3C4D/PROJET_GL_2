package projet.spp;

/***** IMPORTS *****/

// NetworkData
import projet.network_engine.NetworkData;

// Others
import java.util.Vector;

/***** CLASS *****/

/***
 * Data that will be exchanged between the GameServer and GamePlayers
 */
public class PastisNetworkData  extends NetworkData {
    Vector<MyEntity> entities;
    String message;

    PastisNetworkData() {
        entities = new Vector<>();
    }
}
