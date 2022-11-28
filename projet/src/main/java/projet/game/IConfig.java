package projet.game;

import java.lang.Math;

/**
* Interface contenant tous les paramètres du jeu nécessaire
*/
public interface IConfig{

  public static int WIDTH = 800; // Largeur de la fenetre
  public static int HEIGHT = 800; // Hauteur de la fenetre

  public static float DELTA_T = 1000f/600f; // Fréquence de rafraichissement des frames

  public static float RACKET_VELOCITY = (float)Math.PI*7/(100*5);// Vitesse des raquettes

  public static int PLAYERS_NB = 20;
}
