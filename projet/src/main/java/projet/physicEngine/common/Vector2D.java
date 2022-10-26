package projet.physicEngine.common;

import java.lang.Math;

public class Vector2D{
  private Point start;
  private float coordX;
  private float coordY;

  /**
  * @param end est l'extrémité de fin du vecteur
  * Crée un vecteur de direction end positionné à l'origine (0,0)
  */
  public Vector2D(Point end){
    // Valeur par défaut de la position est l'origine (0,0)
    this.start = new Point(0f,0f);

    this.coordX = end.getX();
    this.coordY = end.getY();
  }

  /**
  * Crée un vecteur de start à end
  * @param start est l'origine du vecteur
  * @param end est la deuxième extrémité du vecteur
  */
  public Vector2D(Point start, Point end){
    this.start = Point.copy(start);

    this.coordX = end.getX() - start.getX();
    this.coordY = end.getY() - start.getY();
  }

  /**
  * Crée un vecteur de start à end
  * @param start est l'origine du vecteur
  * @param end est la deuxième extrémité du vecteur
  */
  public Vector2D(Point start, float coordX, float coordY){
    this.start = Point.copy(start);

    this.coordX = coordX;
    this.coordY = coordY;
  }

  /**
  * @return la norme du vecteur
  */
  public float norme2(){
    return (float)Math.sqrt( coordX*coordX + coordY*coordY );
  }


  /**
  *
  */
  public float scalarProduct(Vector2D v2){
    return (this.coordX*v2.coordX + this.coordY*v2.coordY);
  }

  /**
  * @return la norme du vecteur au carré
  */
  public float norme2Square(){
    return  coordX*coordX + coordY*coordY;
  }

  /**
  * Permet de copier un vecteur
  * @param le vecteur que l'on veut copier
  * @return la copie du vecteur v
  */
  public static Vector2D copy(Vector2D v){
    return new Vector2D(
                        v.getStart(),
                        v.getCoordX(),
                        v.getCoordY()
                        );
  }

  /**
  *
  */
  public Point getStart(){
    return this.start;
  }

  /**
  *
  */
  public float getCoordX(){
    return this.coordX;
  }

  /**
  *
  */
  public float getCoordY(){
    return this.coordY;
  }

  /**
  *
  */
  public void setCoordX(float x){
   this.coordX = x;
  }

  /**
  *
  */
  public void setCoordY(float y){
   this.coordY = y;
  }

  /**
  *
  */
  public void setStart(Point p){
    this.start = p;
  }
}
