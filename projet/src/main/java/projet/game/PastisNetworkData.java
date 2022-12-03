package projet.game;

/***** IMPORTS *****/

// NetworkData
import projet.network_engine.NetworkData;

// Others
import java.util.Vector;

/***** CLASS *****/

/**
 * Data that will be exchanged between the GameServer and GamePlayers
 */
public class PastisNetworkData  extends NetworkData {

    /***** PARAMETERS *****/

    private Vector<PastisRacket> rackets;           // Rackets list
    private Vector<MyEntity> entities;              // Game entities list
    private String message;

    /***** CONSTRUCTORS *****/

    /**
     * Default constructor
     */
    PastisNetworkData() {
        super();
        entities = new Vector<>();
        rackets = new Vector<>();
    }

    /***** GETTERS/SETTERS *****/

    /**
     * Getter for the list of entities
     * @return A vector of the current entities
     */
    public Vector<MyEntity> getEntities() {
        return entities;
    }

    /**
     * Getter for the list of rackets
     * @return A vector of the current rackets
     */
    public Vector<PastisRacket> getRackets() {
        return rackets;
    }

    /**
     * Getter for the message
     * @return The current message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter for entities
     * @param _entities New vector for the entities
     */
    public void setEntities(Vector<MyEntity> _entities) {
        entities = new Vector<>();
        entities = _entities;
    }

    /**
     * Setter for rackets
     * @param _rackets New vector for the rackets
     */
    public void setRackets(Vector<PastisRacket> _rackets) {
        rackets = new Vector<>();
        rackets = _rackets;
    }

    /**
     * Setter for one racket
     * @param _racket New racket
     */
    public void setRacket(PastisRacket _racket) {
        boolean found = false;
        for (int i=0; i<rackets.size(); i++) {
            if (rackets.get(i).getId() == _racket.getId()) {
                rackets.setElementAt(_racket, i);
                found = true;
            }
        }
        if (!found) {
            rackets.add(_racket);
        }
        
    }

    /**
     * Setter for the message
     * @param _entities New vector for the rackets
     */
    public void setMessage(String _message) {
        message = _message;
    }
}
