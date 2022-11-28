package projet.game;

import projet.physicEngine.PhysicWorld;

/**
* Classe définissant le monde physique du jeu
*/
public class MyPhysicWorldSPP extends PhysicWorld{

  /**
  * @param sa largeur
  * @param sa hauteur
  */
  public MyPhysicWorldSPP(float width, float height){
    super(width, height);
    // On initialise l'écouteur de collision
    this.collisionL = new MyCollisionListenerSPP(this);
  }

}
