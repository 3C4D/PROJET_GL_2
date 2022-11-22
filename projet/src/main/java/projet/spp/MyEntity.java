package projet.spp;

import projet.kernel.PEntity;

/**
* Classe représentant une entité du jeu
*/
public class MyEntity extends PEntity{
  public static int BALL = 0;
  public static int RACKET= 1;
  public static int TABLE = 2;
  public static int ZONE = 4;

  /**
  * @param le type de l'entit&
  */
  public MyEntity(int type){
    super(type);
  }

}
