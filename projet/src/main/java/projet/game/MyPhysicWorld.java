package projet.game;

import projet.physicEngine.PhysicWorld;

public class MyPhysicWorld extends PhysicWorld{
  public MyPhysicWorld(float width, float height){
    super(width, height);

    this.collisionL = new MyCollisionListener(this);
  }

}
