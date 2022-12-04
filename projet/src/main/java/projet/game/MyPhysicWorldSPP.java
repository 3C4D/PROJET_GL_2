package projet.game;

import projet.physicEngine.PhysicWorld;
import projet.physicEngine.common.Point;

/**
* Classe définissant le monde physique du jeu
*/
public class MyPhysicWorldSPP extends PhysicWorld{
  private float width;
  private float height;

  /**
  * @param sa largeur
  * @param sa hauteur
  */
  public MyPhysicWorldSPP(float width, float height){
    super(width, height);
    // On initialise l'écouteur de collision
    this.collisionL = new MyCollisionListenerSPP(this);
    this.width = width;
    this.height = height;
  }

  /**
  * @return le point d'origine du monde (le point central)
  */
  public Point getOrigin(){
    return new Point(width/2f, height/2f);
  }

}
