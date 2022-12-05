package projet.game;

/***** IMPORTS *****/

// Client
import projet.network_engine.Client;
import projet.physicEngine.common.Vector2D;
import projet.physicEngine.common.Point;
import java.awt.Color;


/***** CLASS *****/

/**
 * PongPlayer class is a simple client to exchange information with a PongServer
 */
public class PongPlayer extends Client implements IConfig {

    /***** PARAMETERS *****/

    MyWorldPong world;

    /***** METHODS *****/

    /**
     * Instanciate world
     * @param world
     */
    void setWorld(MyWorldPong world) {
        this.world = world;
    }

    /**
     * Manage one message from the PongServer
     */
    void manageMessage() {
        String message = "";
        String concern = "";
        String vector = "";

        //System.out.println("Le nombre de messages : " + messages.size());
        if (messages.size() > 0) {
            message = messages.remove().toString();
            System.out.println("Mon message : " + message);

            if (message.split(" ")[0].equals("MVT")) {
                concern = message.split(" ")[1];
                vector = message.split(" ")[2];

                switch (concern) {
                    case "INITBALL":
                        world.removeBall();
                        Ball ball = new Ball(new Point(WIDTH/2f,HEIGHT/2f), MyWorldPong.BALL_SIZE, Color.WHITE);
                        ball.getBody().setVelocity((new Vector2D(
                                Float.parseFloat(vector.split(";")[0]),
                                Float.parseFloat(vector.split(";")[1]))));
                        world.addEntity(ball);
                        world.setX(Float.parseFloat(vector.split(";")[0]));
                        world.setY(Float.parseFloat(vector.split(";")[1]));
                        System.out.println("Création nouvelle balle");
                        break;
                    case "BALL":
                        world.getBall().getBody().setVelocity(new Vector2D(
                                Float.parseFloat(vector.split(";")[0]),
                                Float.parseFloat(vector.split(";")[1])));
                        world.setX(Float.parseFloat(vector.split(";")[0]));
                        world.setY(Float.parseFloat(vector.split(";")[1]));
                        System.out.println("Mise à jour de balle");
                        break;

                    case "RACKET_A":
                        world.getRacketA().getBody().setVelocity(new Vector2D(
                                Float.parseFloat(vector.split(";")[0]),
                                Float.parseFloat(vector.split(";")[1])));
                        System.out.println("Mise à jour de raquette A");
                        break;

                    case "RACKET_B":
                        world.getRacketB().getBody().setVelocity(new Vector2D(
                                Float.parseFloat(vector.split(";")[0]),
                                Float.parseFloat(vector.split(";")[1])));
                        System.out.println("Mise à jour de raquette B");
                        break;
                }
            }
        }
    }
}
