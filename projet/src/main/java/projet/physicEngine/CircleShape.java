package projet.physicEngine;

import projet.physicEngine.common.Point;

/**
* Permet de créer des box en forme de cercle
*/
public class CircleShape extends Shape{
  private Point center;
  private float ray;

  /**
  * @param le centre du cercle
  * @param son rayon
  */
  public CircleShape(Point center, float ray){
    super(Shape.ShapeType.CIRCLE);
    this.center = Point.copy(center);
    this.ray = ray;
  }

  /**
  * Crée un cercle de centre (0+ray,0+ray)
  * @param son rayon
  */
  public CircleShape(float ray){
    super(Shape.ShapeType.CIRCLE);
    this.center = new Point(ray,ray);
    this.ray = ray;
  }

  /**
  * Permet de savoir si un point est dans le polygone courant
  * @param le point en question
  * @return true si p est strictement dans le polygone, false sinon
  */
  public boolean isInside(Point p){
    /*Si la distance entre le centre du cercle et le point p est inférieur strict
     au rayon du cercle, alors p est dans le cercle*/
     if(p.distance(this.center) <= this.ray){
       return true;
     }else{
       return false;
     }
  }

  /**
  *
  */
  public Point getIsobarycenter(){
    return this.center;
  }

  /**
  *
  */
  public Point getCenter(){
    return this.center;
  }

  /**
  *
  */
  public float getRay(){
    return this.ray;
  }

  /**
  *
  */
  public void setCenter(Point newCenter){
    this.center = newCenter;
  }

  public String toString(){
    return " Centre "+this.center+" rayon : "+this.ray;
  }

}
