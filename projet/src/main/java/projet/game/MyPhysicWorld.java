package projet.game;

import projet.physicEngine.PhysicWorld;

/**
* Classe définissant le monde physique du jeu
*/
public class MyPhysicWorld extends PhysicWorld{

  /**
  * @param sa largeur
  * @param sa hauteur
  */
  public MyPhysicWorld(float width, float height, MyWorld w){
    super(width, height);
    // On initialise l'écouteur de collision
    this.collisionL = new MyCollisionListener(this, w);
  }


}
