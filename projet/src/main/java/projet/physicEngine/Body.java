
package projet.physicEngine;

import projet.physicEngine.common.Transform;
import projet.physicEngine.common.Vector2D;
import projet.physicEngine.common.Point;

import java.util.Vector;


public class Body{
  private BodyType bodyType;
  private Shape shape;
  private Point center;
  private float mass = 0;
  private Filter filter;

  private Vector2D velocity;

  public enum BodyType{
    STATIC, DYNAMIC
  }

  /**
  * Permet de créer un corps avec :
  * Son type est par défaut STATIC
  * Sa position initial correspond au centre (0,0)
  * @param une box de collision
  */
  public Body(Shape shape){
    this.center = shape.getIsobarycenter();
    this.shape = shape;
    this.bodyType = BodyType.STATIC;
    this.velocity = new Vector2D(center, 0,0);
  }

  /**
  * Permet de créer un corps avec :
  * @param une position
  * @param une box de collision
  * @param son type
  */
  public Body(Point position, Shape shape, BodyType type){
    this.shape = shape;
    //On vérifie que le centre de gravité soit dans l'enveloppe
    if(!shape.isInside(position)){
      this.center = shape.getIsobarycenter();
    }else{
      this.center = position;
    }
    this.bodyType = type;
    this.velocity = new Vector2D(center, 0,0);
    this.filter = new Filter();
  }


  /**
  * Permet de créer un corps avec :
  * @param une position
  * @param une box de collision
  * @param son type
  * @param son filtre (utile pour la gestion des collision)
  */
  public Body(Point position, Shape shape, BodyType type, Filter f){
    this.shape = shape;
    //On vérifie que le centre de gravité soit dans l'enveloppe
    if(!shape.isInside(position)){
      this.center = shape.getIsobarycenter();
    }else{
      this.center = position;
    }
    this.bodyType = type;
    this.velocity = new Vector2D(center, 0,0);
    this.filter = f;
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
  * Permet de simuler une impulsion sur un corps
  * @param le point d'application de l'impulsion
  * @param le vecteur d'impulsion
  */
  public void applyImpulse(Vector2D impulseVector){
    center.setX(center.getX() + impulseVector.getCoordX());
    center.setY(center.getY() + impulseVector.getCoordY());

    Transform.translateShape(shape, impulseVector);
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

  /**
  * @return le type du corps
  */
  public BodyType getBodyType(){
    return this.bodyType;
  }

  /**
  * @return la norme du vecteur vitesse
  */
  public float getVelocityValue(){
    return velocity.norme2();
  }

  /**
  * @return le vecteur vitesse du corps
  */
  public Vector2D getVelocity(){
    return velocity;
  }

  /**
  * @param la nouvelle valeur de la masse
  */
  public void setMass(float mass){
    this.mass = mass;
  }

  /**
  * @param le nouveau vecteur vitesse
  */
  public void setVelocity(Vector2D velocity){
    this.velocity = velocity;
  }

  /**
  * @return la masse du corps
  */
  public float getMass(){
    return this.mass;
  }

  /**
  * @return le filtre de collision
  */
  public Filter getFilter(){
    return this.filter;
  }

  /**
  * @param le nouveau filtre
  */
  public void setFilter(Filter f){
    this.filter = f;
  }


  @Override
  public String toString(){
    String str;
    str = "Position :"+center.toString();
    return str;
  }


}
