package projet.physicEngine;

import projet.physicEngine.common.*;

public class CollisionListener{
  /**
  * Permet de déterminer si les cercles sont en intersection, puis prends le
    points se trouvant à équidistance des deux centres de cercles
  * @param un premier cercle
  * @param le deuxième cercle
  * @return un point d'intersection entre les deux formes si il existe, null sinon
  */
  private static Point circleIntersection(CircleShape ca, CircleShape cb){
    float distance = ca.getCenter().distance(cb.getCenter());
    float max = ca.getRay() + cb.getRay();

    if(distance <= max){ //Il y a intersection
      //On calcule l'isobarycentre entre les deux centres
      float x = (float)(ca.getCenter().getX() + cb.getCenter().getX())/2.0f;
      float y = (float)(ca.getCenter().getY() + cb.getCenter().getY())/2.0f;
      return new Point(x,y);
    }

    return null;
  }

  /**
  * Permet de déterminer si un cercle et un polygone sont en intersection, puis
    prends le projecter orthogonale du centre du cercle sur un côté du polygone
  * @param le cercle
  * @param le polygone
  * @return un point d'intersection entre les deux formes si il existe, null sinon
  */
  private static Point circlePolygonIntersection(CircleShape ca, PolygonShape pb){
    int i;
    for(i = 0; i < pb.getNbVertex(); i++){
      Vector2D vAB; //Vecteur du segment
      Vector2D vAC; //Vecteur entre le centre du cercle et l'extrémité 1 du segment
      Vector2D vBC; //Vecteur entre le centre du cercle et l'extrémité 2 du segment
      Point pA; //Extrémité 1 du segment
      Point pB; //Extrémité 2 du segment

      if(i == pb.getNbVertex() - 1){ //si c'est le dernier sommet
        pA = pb.getVertex(i);
        pB = pb.getVertex(0);
        vAB = new Vector2D(pb.getVertex(i), pb.getVertex(0));
        vAC = new Vector2D(pb.getVertex(i), ca.getCenter());
        vBC = new Vector2D(pb.getVertex(0), ca.getCenter());
      }else{ //Sinon
        pA = pb.getVertex(i);
        pB = pb.getVertex(i+1);
        vAB = new Vector2D(pb.getVertex(i), pb.getVertex(i+1));
        vAC = new Vector2D(pb.getVertex(i), ca.getCenter());
        vBC = new Vector2D(pb.getVertex(i+1), ca.getCenter());
      }

      //On cherche à déterminer la valeur de la norme du vecteur CI
      //Ou I est le projecté orthogonale de C sur (AB)
      //CI = (||prod_vect(AC,AB)||) / ||AB||
      float num = Math.abs(vAC.getCoordY()*vAB.getCoordX() - vAC.getCoordX()*vAB.getCoordY());
      float den = vAB.norme2();

      float normCI = num/den;
      //Si cette norme est inférieur au rayon, on en déduit que I est dans le cercle
      if(normCI <= ca.getRay()){ //I dans cercle ca et I sur droite (AB)
          //Mais est-ce que I est sur le segment [AB] ?
          //On calcul les produits scalaires
          //prod1 = vAB.vAC
          float prod1 = vAB.scalarProduct(vAC);
          //prod2 = vBA.vBC
          float prod2 = (-vAB.getCoordX())*vBC.getCoordX() + (-vAB.getCoordY()*vBC.getCoordY());

          //Si les deux positifs, alors C est entre A et B, donc I aussi
          if(prod1 >= 0 && prod2 >= 0){
            Point pI = ca.getCenter().orthoProjection(pA, pB);
            return pI;
          }
          //Autre solution, A est dans le cercle
          if(ca.isInside(pA)){
            return pA;
          }
          //Autre solution, B est dans le cercle
          if(ca.isInside(pB)){
            return pB;
          }
      }
    }

    //Sinon, ya pas d'intersection
    return null;

  }

  /**
  * Permet de déterminer si deux polygones sont en intersection, puis
    calcul un des points d'intersection
  * @param le premier polygone
  * @param le deuxième polygone
  * @return un point d'intersection entre les deux formes si il existe, null sinon
  */
  private static Point polygonsIntersection(PolygonShape pa, PolygonShape pb){
    int i, j;
    Vector2D seg1, seg2;
    Point pA, pB, pC, pD;
    boolean in = true;

    //On regarde d'abord si pa est dans pb, ou si pb est dans pa
    for(i = 0; i < pa.getNbVertex(); i++){
      if(!pb.isInside(pa.getVertex(i))){
        in = false;
      }
    }
    if(in){
      return Point.copy(pa.getVertex(0));
    }

    in = true;
    for(j = 0; j < pb.getNbVertex(); j++){
      if(!pa.isInside(pb.getVertex(j))){
        in = false;
      }
    }
    if(in){
      return Point.copy(pb.getVertex(0));
    }

    //On calcule l'intersection de chaque côté de pa, avec chaque côté de pb
    for(i = 0; i < pa.getNbVertex(); i++){
      //On definit le 1er segment
      if(i == pa.getNbVertex() -1){
        pA = pa.getVertex(i);
        pB = pa.getVertex(0);
        seg1 = new Vector2D(pa.getVertex(i), pa.getVertex(0));
      }else{
        pA = pa.getVertex(i);
        pB = pa.getVertex(i+1);
        seg1 = new Vector2D(pa.getVertex(i), pa.getVertex(i+1));
      }
      for(j = 0; j < pb.getNbVertex(); j++){
        //Pui le deuxième
        if(j == pb.getNbVertex() -1){
          pC = pb.getVertex(j);
          pD = pb.getVertex(0);
          seg2 = new Vector2D(pb.getVertex(j), pb.getVertex(0));
        }else{
          pC = pb.getVertex(j);
          pD = pb.getVertex(j+1);
          seg2 = new Vector2D(pb.getVertex(j), pb.getVertex(j+1));
        }

        //Puis on regarde leur intersection

        float k = (pC.getX()*seg2.getCoordY() + pA.getY()*seg2.getCoordX() - pC.getY()*seg2.getCoordX() - pA.getX()*seg2.getCoordY())/(seg2.getCoordY()*seg1.getCoordX() - seg2.getCoordX()*seg1.getCoordY());

        if(k >= 0f && k <= 1f){
          //Pt d'intersection sur AB
          // Est-ce que il est aussi sur CD ?
          float l = (pA.getX() + k*seg1.getCoordX() - pC.getX())/(seg2.getCoordX());
          if(l >= 0f && l <= 1f){
            return new Point(pA.getX() + k*seg1.getCoordX(), pA.getY() + k*seg1.getCoordY());
          }
        }
      }
    }

    return null; //Pas d'intersection
  }


  /**
  * Permet de savoir s'il y a une collision entre deux Body, et de connaitre
    un des points d'intersections s'il en existe au moins un
  * @param un premier Body
  * @param un deuxième
  * @return un point d'intersection s'il en existe un, null sinon
  */
  public static Point areInCollision(Body ba, Body bb){
    Shape sa = ba.getShape();
    Shape sb = bb.getShape();

    if(sa.getType() == Shape.ShapeType.CIRCLE){
      if(sb.getType() == Shape.ShapeType.CIRCLE){
         return circleIntersection((CircleShape)sa, (CircleShape)sb);
      }

      if(sb.getType() == Shape.ShapeType.POLYGON){
        return circlePolygonIntersection((CircleShape)sa, (PolygonShape)sb);
      }
    }

    if(sa.getType() == Shape.ShapeType.POLYGON){
      if(sb.getType() == Shape.ShapeType.CIRCLE){
        return circlePolygonIntersection((CircleShape)sb, (PolygonShape)sa);
      }

      if(sb.getType() == Shape.ShapeType.POLYGON){
        return polygonsIntersection((PolygonShape)sa, (PolygonShape)sb);
      }
    }

    return null;
  }

}
