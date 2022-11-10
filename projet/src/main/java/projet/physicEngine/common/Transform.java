package projet.physicEngine.common;

import projet.physicEngine.Shape;
import projet.physicEngine.CircleShape;
import projet.physicEngine.PolygonShape;
import projet.physicEngine.Shape.ShapeType;

/**
* Permet de définir les transformation dans le plan 2D
*/
public class Transform{


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

  /**
  * Permet de translater un polygone par un certain vecteur
  * @param le polygone à translater
  * @param le vecteur de translation
  */
  public static void translationPolygon(PolygonShape ps, Vector2D trans){
    int i;
    int nbVertex = ps.getNbVertex();

      for(i = 0; i < nbVertex; i++){
        Point peak = ps.getVertex(i);
        peak.setX(peak.getX() + trans.getCoordX());
        peak.setY(peak.getY() + trans.getCoordY());
      }
  }

  /**
  * Permet de translater un cercle par un certain vecteur
  * @param le polygone à translater
  * @param le vecteur de translation
  */
  public static void translationCircle(CircleShape cs, Vector2D trans){
    (cs.getCenter()).setX(cs.getCenter().getX() + trans.getCoordX());
    (cs.getCenter()).setY(cs.getCenter().getY() + trans.getCoordY());
  }

  /**
  * Permet de translater un shape par un certain vecteur
  * @param le shape à translater
  * @param le vecteur de translation
  */
  public static void translateShape(Shape s, Vector2D trans){
    if(s.getType() == Shape.ShapeType.POLYGON){
      Transform.translationPolygon((PolygonShape)s, trans);
    }
    else if(s.getType() == Shape.ShapeType.CIRCLE){
      Transform.translationCircle((CircleShape)s, trans);
    }
  }


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
  * Permet d'appliquer la roatation d'angle angle autour du point origin au
    point p
  * @param le point à modifier
  * @param le centre de la rotation
  * @param la valeur de l'angle de la rotation en radian
  */
  public static Point rotation(Point p, Point origin, float angle){
    float newX, newY;

    newX = p.getX() - origin.getX();
    newY = p.getY() - origin.getY();

    return new Point(
              (float)(newX*Math.cos(angle) + newY*Math.sin(angle) + origin.getX())
              ,(float)(-newX*Math.sin(angle) + newY*Math.cos(angle) + origin.getY())
              );
  }

  /**
  * Permet de faire subir une rotation à un polygone
  * @param le polygone
  * @param le point parraport auquel on fait la rotation
  * @param l'angle de la rotation en radian
  */
  public static void rotationPolygon(PolygonShape ps, Point origin, float a){
    int i;
    if(origin == null){
      origin = ps.getIsobarycenter();
    }
    for(i = 0; i < ps.getNbVertex(); i++){
      ps.setVertex(i, rotation(ps.getVertex(i), origin, a));
    }
  }

  /**
  * Permet de faire subir une rotation à un cercle
  * @param le cercle
  * @param le point parraport auquel on fait la rotation
  * @param l'angle de la rotation en radian
  */
  public static void rotationCircle(CircleShape cs, Point origin, float a){
    if(!cs.getCenter().equals(origin)){ //Si le centre de rotation n'est pas le centre du cercle
      cs.setCenter(rotation(cs.getCenter(), origin, a));
    }
    //Sinon, le cercle reste identique
  }

  public static void rotationShape(Shape s, Point origin, float a){
    if(s.getType() == ShapeType.CIRCLE){
      rotationCircle((CircleShape)s, origin, a);
    }
    if(s.getType() == ShapeType.POLYGON){
      rotationPolygon((PolygonShape)s, origin, a);
    }
  }
}
