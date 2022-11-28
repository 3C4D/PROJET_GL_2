package projet.game;

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
    private Vector<MyEntity> entities;
    private Vector<PastisRacket> rackets;
    private String message;

    /***
     * Constructor
     */
    PastisNetworkData() {
        super();
        entities = new Vector<>();
        rackets = new Vector<>();
    }

    /***
     * Getter for entities
     * @return A vector of the current entities
     */
    public Vector<MyEntity> getEntities() {
        return entities;
    }

    /***
     * Getter for rackets
     * @return A vector of the current rackets
     */
    public Vector<PastisRacket> getRackets() {
        return rackets;
    }

    /***
     * Getter for the message
     * @return The current message
     */
    public String getMessage() {
        return message;
    }

    /***
     * Setter for entities
     * @param _entities New vector for the entities
     */
    public void setEntities(Vector<MyEntity> _entities) {
        entities = new Vector<>();
        entities = _entities;
    }

    /***
     * Setter for rackets
     * @param _rackets New vector for the rackets
     */
    public void setRackets(Vector<PastisRacket> _rackets) {
        rackets = new Vector<>();
        rackets = _rackets;
    }

    /***
     * Setter for one racket
     * @param _racket New racket
     */
    public void setRacket(PastisRacket _racket) {
        rackets.setElementAt(_racket, _racket.getId());
    }

    /***
     * Setter for the message
     * @param _entities New vector for the rackets
     */
    public void setMessage(String _message) {
        message = _message;
    }
}
