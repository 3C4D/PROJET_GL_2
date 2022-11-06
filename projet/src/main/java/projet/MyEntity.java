package projet;

import projet.kernel.PEntity;

public class MyEntity extends PEntity{
  public static int BALL = 0;
  protected int entityType;

  public MyEntity(int type){
    super();
    this.entityType = type;
  }
}
