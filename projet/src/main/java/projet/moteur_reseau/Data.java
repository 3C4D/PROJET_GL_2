package projet.moteur_reseau;

/***** IMPORTS *****/

import java.io.Serializable;    // To allow network transmission

/***** CLASS *****/

/***
 * Data exchanged between Client and Server
 */
public class Data implements Serializable {

    /***** PARAMETERS *****/

    String message;

    /***** METHOD *****/

    /***
     * Constructor
     * @param _message
     */
    Data(String _message) {
        message = _message;
    }

    /***
     * Define a string from Data to allow casting or displaying
     */
    @Override
    public String toString() {
        return message;
    }
}
