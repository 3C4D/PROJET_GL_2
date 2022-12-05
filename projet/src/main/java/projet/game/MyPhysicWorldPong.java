package projet.game;

import projet.physicEngine.PhysicWorld;

/**
* Classe définissant le monde physique du jeu
*/
public class MyPhysicWorldPong extends PhysicWorld{

  /**
  * @param width sa largeur
  * @param height sa hauteur
  */
  public MyPhysicWorldPong(float width, float height, MyWorldPong w){
    super(width, height);
    // On initialise l'écouteur de collision
    this.collisionL = new MyCollisionListenerPong(this, w);
  }


}
