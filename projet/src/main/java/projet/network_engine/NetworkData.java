package projet.network_engine;

/***** IMPORTS *****/

import java.io.Serializable;    // To allow network transmission

/***** CLASS *****/

/***
 * Data exchanged between Client and Server
 */
public class NetworkData implements Serializable {

    /***** PARAMETERS *****/

    String message;

    /***** METHOD *****/

    /***
     * Constructor
     * @param _message
     */
    public NetworkData(String _message) {
        message = _message;
    }

    /***
     * Transform a NetworkData object into a String
     * @return The String
     */
    @Override
    public String toString(){
        return message;
    }

    /***
     * Transform a string into a NetworkData object
     * @return The NetworkData object
     */
    public NetworkData toNetworkData(String message) {
        return new NetworkData(message);
    }
}
