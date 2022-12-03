package projet.game;

import projet.network_engine.ClientThread;

/***** IMPORTS *****/

// Server
import projet.network_engine.Server;

// Others 
import java.util.LinkedList;

/***** CLASS *****/

/**
 * PastisServer class that extends the server class from the network engine to
 * define the server routine and a new constructor
 */
public class PastisServer extends Server {

    /***** PARAMETERS *****/

    PastisNetworkData data;
    MyWorldSPP world;

    /***** CONSTRUCTORS *****/

    /**
     * Constructor for the PastisServer class
     * 
     * @param _port
     * @param _clientsNumber
     * @param _world
     */
    public PastisServer(int _port, int _clientsNumber, MyWorldSPP _world) {
        super(_port, _clientsNumber);
        world = _world;
        data = new PastisNetworkData();
    }

    /***** METHODS *****/

    /**
     * Update current entities during game session
     */
    private void updateEntities() {
        data.setEntities(world.getBalls());
    }

    /**
     * Definition of the server routine
     * 
     * @param in       The input stream of the client
     * @param username The username of the client
     */
    @Override
    public void runningRoutine(ClientThread c, String username) {
        PastisNetworkData receive = new PastisNetworkData();
        Object read = new Object();
        while (true) {
            if (c.messages.size() > 0) {
                read = c.messages.remove();
                System.out.println("Sereur LIS");
                System.out.println(read);
                if (read != null) {
                    if (read instanceof PastisNetworkData) {
                        receive = (PastisNetworkData) read;
                        System.out.println(receive.getMessage());
                        if (receive.getMessage().split(" ")[0].equals("INIT")) {
                            diffuseMessage(world, null);
                        } else if (receive.getMessage().split(" ")[0].equals("UPDATE")) {
                            updateEntities();
                            data.setMessage("UPDATE");
                            diffuseMessage(data, null);
                        } else if (receive.getMessage().split(" ")[0].equals("RACKET")) {
                            System.out.println(receive.getRackets().get(0));
                            data.setRacket(receive.getRackets().get(0));
                            data.setMessage("RACKETS");
                            diffuseMessage(data, null);
                        }
                    } else if (read instanceof String) {
                        receive.setMessage((String) read);
                        if (receive.getMessage().split(" ")[0].equals("DISCONNECT")) {
                            break;
                        }
                    }
                }
            }
        }
        disconnectClient(receive.getMessage().split(" ")[1]);
    }
}