package projet.physicEngine;

import projet.physicEngine.common.Transform;
import projet.physicEngine.common.Vector2D;
import projet.physicEngine.common.Point;

public class Body{
  private BodyType type;
  private Shape shape;
  private Point center;


  private Vector2D velocity;

  public enum BodyType{
    STATIC, DYNAMIC
  }


  /**
  * Permet de créer un corps une box de collision.
  * Sa position initial correspond au centre (0,0)
  * Son type est par défaut STATIC
  */
  public Body(Shape shape){
    this.center = shape.getIsobarycenter();
    this.shape = shape;
    this.type = BodyType.STATIC;
  }

  /**
  * Permet de créer un corps avec une position, une box de collision, et son type
  */
  public Body(Point position, Shape shape, BodyType type){
    this.shape = shape;
    //On vérifie que le centre de gravité soit dans l'enveloppe
    if(!shape.isInside(position)){
      this.center = shape.getIsobarycenter();
    }else{
      this.center = position;
    }
    this.type = type;
  }

  /**
  * Permet de faire un mouvement linéaire en fonction de la vitesse de l'objet
  * @param le temps écoulé depuis le dernier mouvement
  */
  public void linearMovement(float dt){
    Vector2D motion = new Vector2D(dt*velocity.getCoordX(),  dt*velocity.getCoordY());

    center.setX(center.getX() + motion.getCoordX());
    center.setY(center.getY() + motion.getCoordY());

    Transform.translateShape(shape, motion);

  }




  /**
  * @return la box de collision du corps
  */
  public Shape getShape(){
    return shape;
  }

  /**
  * @return la position centrale actuelle du corps
  */
  public Point getCenter(){
      return this.center;
    }


  // /**
  // * @return lz densité du corps
  // */
  // public float getDensity(){
  //   return density;
  // }
  //
  // /**
  // * @return la friction du corps
  // */
  // public float getFriction(){
  //   return friction;
  // }
  //
  // /**
  // * @param la nouvelle position
  // */
  // public void setPosition(Point newP){
  //   this.center = newP;
  // }
  //
  // /**
  // * @param la nouvelle densité
  // */
  // public void setDensity(float density){
  //   this.density = density;
  // }
  //
  // /**
  // * @param la nouvelle friction
  // */
  // public void setFriction(float friction){
  //   this.friction = friction;
  // }

}
