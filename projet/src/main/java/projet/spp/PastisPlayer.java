package projet.spp;

/***** IMPORTS *****/

// Client
import projet.network_engine.Client;

/***** CLASS *****/

/***
 * GamePlayer class is a simple client to exchange information with a GameServer
 */
public class PastisPlayer extends Client {
    /***
     * Ask for an update to the server
     */
    public void askForUpdate() {
        PastisNetworkData data = new PastisNetworkData();
        data.setMessage("UPDATE");
        sendMessage(data);
    }
}
