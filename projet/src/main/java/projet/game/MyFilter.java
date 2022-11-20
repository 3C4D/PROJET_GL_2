package projet.game;

import projet.physicEngine.Filter;

/**
* Classe définissant les valeurs possibles des filtres
*/
public class MyFilter extends Filter{
  public static byte RACKET_CATEGORY = 00000010;
  public static byte BALL_CATEGORY = 00000001;

  public static byte RACKET_MASK = BALL_CATEGORY;
  public static byte BALL_MASK = RACKET_CATEGORY;
}
