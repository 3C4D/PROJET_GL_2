package projet.game;

import java.io.Serializable;

import projet.kernel.PEntity;

/**
* Classe représentant une entité du jeu
*/
public class MyEntity extends PEntity implements Serializable {
  public static int BALL = 0;
  public static int RACKET= 1;
  public static int TABLE = 2;
  public static int ZONE = 4;
  public static int RACKET_A = 7;
  public static int RACKET_B = 8;

  /**
  * @param le type de l'entit&
  */
  public MyEntity(int type){
    super(type);
  }

}
