package projet.game;

import java.io.Serializable;

/***** IMPORTS *****/

// Client
import projet.network_engine.Client;

/***** CLASS *****/

/**
 * GamePlayer class is a simple client to exchange information with a GameServer
 */
public class PastisPlayer extends Client implements Serializable {
    /**
     * Ask for initialisation to the server
     */
    public void askForInit() {
        PastisNetworkData data = new PastisNetworkData();
        data.setMessage("INIT");
        sendMessage(data);
    }

    /**
     * Ask for an update to the server
     */
    public void askForUpdate() {
        PastisNetworkData data = new PastisNetworkData();
        data.setMessage("UPDATE");
        sendMessage(data);
    }
}