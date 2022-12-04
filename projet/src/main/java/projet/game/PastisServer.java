package projet.game;

import projet.network_engine.ClientThread;

/***** IMPORTS *****/

// Server
import projet.network_engine.Server;

/***** CLASS *****/

/**
 * PastisServer class that extends the server class from the network engine to
 * define the server routine and a new constructor
 */
public class PastisServer extends Server {

    /***** PARAMETERS *****/

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
    }

    /***** METHODS *****/

    /**
     * Definition of the server routine
     * 
     * @param in       The input stream of the client
     * @param username The username of the client
     */
    @Override
    public void runningRoutine(String username) {
        // TODO
    }
}