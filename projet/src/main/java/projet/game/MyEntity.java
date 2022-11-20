package projet.game;

import projet.kernel.PEntity;

public class MyEntity extends PEntity{
  public static int BALL = 0;
  public static int RACKET_A = 1;
  public static int RACKET_B = 2;

  public MyEntity(int type){
    super(type);
  }

}
