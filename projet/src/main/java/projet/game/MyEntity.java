package projet.game;

import projet.kernel.PEntity;

public class MyEntity extends PEntity{
  public static int BALL = 0;
  public static int SEED = 1;
  protected int entityType;

  public MyEntity(int type){
    super();
    this.entityType = type;
  }
}
