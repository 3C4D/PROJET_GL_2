package projet.spp;

import java.lang.Math;

/**
* Interface contenant tous les paramètres du jeu nécessaire
*/
public interface IConfig{

  public static int WIDTH = 800; // Largeur de la fenetre
  public static int HEIGHT = 800; // Hauteur de la fenetre

  public static float TABLE_SIZE = 800;

  public static float DELTA_T = 1000f/600f; // Fréquence de rafraichissement des frames

  public static float RACKET_VELOCITY = (float)Math.PI*7/(100*5);// Vitesse des raquettes

  public static float RACKET_WIDTH = 50;
  public static float RACKET_HEIGHT = 25;

  public static int PLAYERS_NB = 15;
}