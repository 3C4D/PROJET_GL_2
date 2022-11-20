package projet.game;

/**
* Interface contenant tous les paramètres du jeu nécessaire
*/
public interface IConfig{
  public static int WIDTH = 500; // Largeur de la fenetre
  public static int HEIGHT = 500; // Hauteur de la fenetre

  public static float DELTA_T = 1000f/600f; // Fréquence de rafraichissement des frames

  public static float RACKET_VELOCITY = 0.15f; // Vitesse des raquettes
}
