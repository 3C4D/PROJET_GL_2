package projet.physicEngine.common;

/**
* Permet de définir les transformation dans le plan 2D
*/
public class Transform{

  /**
  * Permet d'appliquer une rotation au vecteur v
  * @param la valeur de l'angle de la rotation
  */
  public static void rotationOrigin(Vector2D v, float angle){
    v.setCoordX((float)(v.getCoordX()*Math.cos(angle) - v.getCoordY()*Math.sin(angle)));
    v.setCoordY((float)(v.getCoordX()*Math.sin(angle) + v.getCoordY()*Math.cos(angle)));
  }

  /**
  * Permet d'appliquer une rotation au point p
  * @param la valeur de l'angle de la rotation
  */
  public static void rotationOrigin(Point p, float angle){
    p.setX((float)(p.getX()*Math.cos(angle) - p.getY()*Math.sin(angle)));
    p.setY((float)(p.getX()*Math.sin(angle) + p.getY()*Math.cos(angle)));
  }

  /**
  * Permet d'appliquer une translation vers l'origine à un vecteur
  * @param le vecteur en question
  */
  public static void translationOrigin(Vector2D v){
    v.setStart(new Point(0,0));
  }

  /**
  * Permet d'appliquer une translation vers un point à un vecteur
  * @param le vecteur en question
  * @param le point de translation
  */
  public static void translation(Vector2D v, Point p){
    v.setStart(Point.copy(p));
  }
}
