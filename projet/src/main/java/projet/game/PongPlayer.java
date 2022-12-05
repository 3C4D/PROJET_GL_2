package projet.game;

/***** IMPORTS *****/

// Client
import projet.network_engine.Client;
import projet.physicEngine.common.Vector2D;

/***** CLASS *****/

/**
 * PongPlayer class is a simple client to exchange information with a PongServer
 */
public class PongPlayer extends Client {

    /***** PARAMETERS *****/

    MyWorldPong world;

    /***** CONSTRUCTORS *****/

    /**
     * Constructor
     * @param world
     */
    PongPlayer(MyWorldPong world) {
        super();
        this.world = world;
    }

    /***** METHODS *****/

    /**
     * Manage one message from the PongServer
     */
    void manageMessage() {
        String message = "";
        String concern = "";
        String vector = "";
        
        if (messages.size() > 0) {
            do {
                if (messages.size() > 0) {
                    message = messages.remove().toString();
                }  
            } while (!message.split(" ")[0].equals("MVT"));
            System.out.println(message);
            concern = message.split(" ")[1];
            vector = message.split(" ")[2];

            switch (concern) {
                case "BALL" : 
                    world.getBall().getBody().setVelocity(new Vector2D(
                        Float.parseFloat(vector.split(";")[0]), 
                        Float.parseFloat(vector.split(";")[1]))
                    );
                    break;
                
                case "RACKET_A" :
                    world.getRacketA().getBody().setVelocity(new Vector2D(
                        Float.parseFloat(vector.split(";")[0]), 
                        Float.parseFloat(vector.split(";")[1]))
                    );
                    break;

                case "RACKET_B" :
                    world.getRacketB().getBody().setVelocity(new Vector2D(
                        Float.parseFloat(vector.split(";")[0]), 
                        Float.parseFloat(vector.split(";")[1]))
                    );
                    break;
            }
        }
    }
}