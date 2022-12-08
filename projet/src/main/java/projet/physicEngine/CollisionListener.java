package projet.physicEngine;

import projet.physicEngine.common.*;
import projet.physicEngine.Body.BodyType;
import projet.physicEngine.Shape.ShapeType;
import java.lang.Math;
import java.util.ArrayList;
public class CollisionListener{
  protected PhysicWorld physicW;

  public CollisionListener(){
    physicW = null;
  }
  public CollisionListener(PhysicWorld physicW){
    this.physicW = physicW;
  }

  /**
  * Permet de déterminer si les cercles sont en intersection, puis prends le
    points se trouvant à équidistance des deux centres de cercles
  * @param un premier cercle
  * @param le deuxième cercle
  * @return un point d'intersection entre les deux formes si il existe, null sinon
  */
  private Point circleIntersection(CircleShape ca, CircleShape cb){
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
  private Point circlePolygonIntersection(CircleShape ca, PolygonShape pb){
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

      if(Math.abs(pA.getY() - pB.getY()) <= 0.001){
        Point p = new Point(ca.getCenter().getX(), pA.getY());
        if((p.getX() <= pB.getX() && p.getX() >= pA.getX())
        || (p.getX() <= pA.getX() && p.getX() >= pB.getX())){
          if(p.distance(ca.getCenter()) <= ca.getRay()){
            return p;
          }
        }
      }
      else if(Math.abs(pA.getX() - pB.getX()) <= 0.001 ){
        Point p = new Point(pA.getX(), ca.getCenter().getY());
        if((p.getY() <= pB.getY() && p.getY() >= pA.getY())
        || (p.getY() >= pB.getY() && p.getY() <= pA.getY())){
          if(p.distance(ca.getCenter()) <= ca.getRay()){
            return p;
          }
        }
      }


      else{
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
  private Point polygonsIntersection(PolygonShape pa, PolygonShape pb){
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
  public Point areInCollision(Body ba, Body bb){
    Shape sa = ba.getShape();
    Shape sb = bb.getShape();

    if(!CollisionFilter.shouldCollide(ba, bb)){
      return null;
    }

    if(sa.getType() == Shape.ShapeType.CIRCLE){
      if(sb.getType() == Shape.ShapeType.CIRCLE){
         return circleIntersection((CircleShape)sa, (CircleShape)sb);
      }

      if(sb.getType() == Shape.ShapeType.POLYGON){
        Point p = circlePolygonIntersection((CircleShape)sa, (PolygonShape)sb);
        return p;
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



  /**
  * Permet de savoir si une enveloppe touche un côté du contour
  * @param l'enveloppe qu'on observe
  * @param le contour de jeu (qui doit être rectangulaire)
  * @return -1 si il n'y a pas d'intersection
            0 si il y a interesection avec le côté du haut

            1 si il y a intersection avec le côté gauche
            2 si il y a interesection avec le côté de droite
            3 si il y a interesection avec le côté du bas
           -2 si le paramètre outline n'est pas un rectangle ou le shape n'a pas de type
  */
  public int outlineCollision(Shape shape, PolygonShape outline){
    Point s1, s2, s3, s4;
    Vector2D c1, c2, c3, c4;
    int j;

    if(outline.getNbVertex() != 4) return -2;

    s1 = outline.getVertex(0);
    s2 = outline.getVertex(1);
    s3 = outline.getVertex(2);
    s4 = outline.getVertex(3);

    c1 = new Vector2D(s1,s2);
    c2 = new Vector2D(s2,s3);
    c3 = new Vector2D(s3,s4);
    c4 = new Vector2D(s4,s1);

    if(shape.getType() == ShapeType.CIRCLE){
      CircleShape cs = (CircleShape)shape;
      Point c = cs.getCenter();
      Vector2D s1C, s2C, s3C, s4C;
      float normCI, num, den;

      s1C = new Vector2D(s1, c);
      s2C = new Vector2D(s2, c);
      s3C = new Vector2D(s3, c);
      s4C = new Vector2D(s4, c);

      //On regarde les intersections avec chaque côté

      //////////////// Coté 1
      //On cherche à déterminer la valeur de la norme du vecteur CI
      //Ou I est le projecté orthogonale de C sur le coté

      //CI = (||prod_vect(siC,ci)||) / ||ci||
      num = Math.abs(s1C.getCoordY()*c1.getCoordX() - s1C.getCoordX()*c1.getCoordY());
      den = c1.norme2();

      normCI = num/den;
      //Si cette norme est inférieur au rayon, on en déduit que I est dans le cercle
      if(normCI <= cs.getRay()){ //I dans cercle ca et I sur droite (AB)
          //Mais est-ce que I est sur le segment [AB] ?
          //On calcul les produits scalaires
          //prod1 = vAB.vAC
          float prod1 = c1.scalarProduct(s1C);
          //prod2 = vBA.vBC
          float prod2 = (-c1.getCoordX())*s2C.getCoordX() + (-c1.getCoordY()*s2C.getCoordY());

          //Si les deux positifs, alors C est entre A et B, donc I aussi
          if(prod1 >= 0 && prod2 >= 0){
            Point pI = cs.getCenter().orthoProjection(s1, s2);
            return 0;
          }
      }

      //////////////// Coté 2
      //On cherche à déterminer la valeur de la norme du vecteur CI
      //Ou I est le projecté orthogonale de C sur le coté

      //CI = (||prod_vect(siC,ci)||) / ||ci||
      num = Math.abs(s2C.getCoordY()*c2.getCoordX() - s2C.getCoordX()*c2.getCoordY());
      den = c2.norme2();

      normCI = num/den;
      //Si cette norme est inférieur au rayon, on en déduit que I est dans le cercle
      if(normCI <= cs.getRay()){ //I dans cercle ca et I sur droite (AB)
          //Mais est-ce que I est sur le segment [AB] ?
          //On calcul les produits scalaires
          //prod1 = vAB.vAC
          float prod1 = c2.scalarProduct(s2C);
          //prod2 = vBA.vBC
          float prod2 = (-c2.getCoordX())*s3C.getCoordX() + (-c2.getCoordY()*s3C.getCoordY());

          //Si les deux positifs, alors C est entre A et B, donc I aussi
          if(prod1 >= 0 && prod2 >= 0){
            Point pI = cs.getCenter().orthoProjection(s2, s3);
            return 1;
          }
      }


      //////////////// Coté 3
      //On cherche à déterminer la valeur de la norme du vecteur CI
      //Ou I est le projecté orthogonale de C sur le coté

      //CI = (||prod_vect(siC,ci)||) / ||ci||
      num = Math.abs(s3C.getCoordY()*c3.getCoordX() - s3C.getCoordX()*c3.getCoordY());
      den = c3.norme2();

      normCI = num/den;
      //Si cette norme est inférieur au rayon, on en déduit que I est dans le cercle
      if(normCI <= cs.getRay()){ //I dans cercle ca et I sur droite (AB)
          //Mais est-ce que I est sur le segment [AB] ?
          //On calcul les produits scalaires
          //prod1 = vAB.vAC
          float prod1 = c3.scalarProduct(s3C);
          //prod2 = vBA.vBC
          float prod2 = (-c3.getCoordX())*s4C.getCoordX() + (-c3.getCoordY()*s4C.getCoordY());

          //Si les deux positifs, alors C est entre A et B, donc I aussi
          if(prod1 >= 0 && prod2 >= 0){
            Point pI = cs.getCenter().orthoProjection(s3, s4);
            return 2;
          }
      }


      //////////////// Coté 4
      //On cherche à déterminer la valeur de la norme du vecteur CI
      //Ou I est le projecté orthogonale de C sur le coté

      //CI = (||prod_vect(siC,ci)||) / ||ci||
      num = Math.abs(s4C.getCoordY()*c4.getCoordX() - s4C.getCoordX()*c4.getCoordY());
      den = c4.norme2();

      normCI = num/den;
      //Si cette norme est inférieur au rayon, on en déduit que I est dans le cercle
      if(normCI <= cs.getRay()){ //I dans cercle ca et I sur droite (AB)
          //Mais est-ce que I est sur le segment [AB] ?
          //On calcul les produits scalaires
          //prod1 = vAB.vAC
          float prod1 = c4.scalarProduct(s4C);
          //prod2 = vBA.vBC
          float prod2 = (-c4.getCoordX())*s1C.getCoordX() + (-c4.getCoordY()*s1C.getCoordY());

          //Si les deux positifs, alors C est entre A et B, donc I aussi
          if(prod1 >= 0 && prod2 >= 0){
            Point pI = cs.getCenter().orthoProjection(s4, s1);
            return 3;
          }
      }

      return -1; //Pas d'intersection

    }

    if(shape.getType() == ShapeType.POLYGON){
      PolygonShape pb = (PolygonShape)shape;
      Vector2D seg;
      Point pA, pB;
      float k;

      for(j = 0; j < pb.getNbVertex(); j++){
        //On défini le côté du polygone qu'on regarde
        if(j == pb.getNbVertex() -1){
          pA = pb.getVertex(j);
          pB = pb.getVertex(0);
          seg = new Vector2D(pb.getVertex(j), pb.getVertex(0));
        }else{
          pA = pb.getVertex(j);
          pB = pb.getVertex(j+1);
          seg = new Vector2D(pb.getVertex(j), pb.getVertex(j+1));
        }

        if(Math.abs(pA.getY() - pB.getY()) <= 0.001){
          if(Math.abs(s1.getY() - pA.getY()) <= 0.001){
            return 0;
          }
          if(Math.abs(s4.getY() - pA.getY()) <= 0.001){
            return 3;
          }
        }
        else if(Math.abs(pA.getX() - pB.getX()) <= 0.001 ){
          if(Math.abs(s2.getX() - pA.getX()) <= 0.001){
            return 1;
          }
          if(Math.abs(s3.getX() - pA.getX()) <= 0.001){
            return 2;
          }
        }

        else{
          //Puis on regarde son intersection avec les 4 côtés du contour

          // Avec le côté 1
          k = (pA.getX()*seg.getCoordY() + s1.getY()*seg.getCoordX() - pA.getY()*seg.getCoordX() - s1.getX()*seg.getCoordY())/(seg.getCoordY()*c1.getCoordX() - seg.getCoordX()*c1.getCoordY());

          //Pt d'intersection entre la droite défini par le vecteur côté c1 et seg
          if(k >= 0f && k <= 1f){ //Sur le segment c1
            // Est-ce que il est aussi sur seg?
            float l = (s1.getX() + k*c1.getCoordX() - pA.getX())/(seg.getCoordX());
            if(l >= 0f && l <= 1f){ //Oui
              return 0;
            }
          }

          // Avec le côté 2
          k = (pA.getX()*seg.getCoordY() + s2.getY()*seg.getCoordX() - pA.getY()*seg.getCoordX() - s2.getX()*seg.getCoordY())/(seg.getCoordY()*c2.getCoordX() - seg.getCoordX()*c2.getCoordY());

          //Pt d'intersection entre la droite défini par le vecteur côté c1 et seg
          if(k >= 0f && k <= 1f){ //Sur le segment c1
            // Est-ce que il est aussi sur seg?
            float l = (s2.getX() + k*c2.getCoordX() - pA.getX())/(seg.getCoordX());
            if(l >= 0f && l <= 1f){ //Oui
              return 1;
            }
          }


          // Avec le côté 3
          k = (pA.getX()*seg.getCoordY() + s3.getY()*seg.getCoordX() - pA.getY()*seg.getCoordX() - s3.getX()*seg.getCoordY())/(seg.getCoordY()*c3.getCoordX() - seg.getCoordX()*c3.getCoordY());

          //Pt d'intersection entre la droite défini par le vecteur côté c1 et seg
          if(k >= 0f && k <= 1f){ //Sur le segment c1
            // Est-ce que il est aussi sur seg?
            float l = (s3.getX() + k*c3.getCoordX() - pA.getX())/(seg.getCoordX());
            if(l >= 0f && l <= 1f){ //Oui
              return 2;
            }
          }

          // Avec le côté 4
          k = (pA.getX()*seg.getCoordY() + s4.getY()*seg.getCoordX() - pA.getY()*seg.getCoordX() - s4.getX()*seg.getCoordY())/(seg.getCoordY()*c4.getCoordX() - seg.getCoordX()*c4.getCoordY());

          //Pt d'intersection entre la droite défini par le vecteur côté c1 et seg
          if(k >= 0f && k <= 1f){ //Sur le segment c1
            // Est-ce que il est aussi sur seg?
            float l = (s4.getX() + k*c4.getCoordX() - pA.getX())/(seg.getCoordX());
            if(l >= 0f && l <= 1f){ //Oui
              return 3;
            }
          }
        }
    }


      return -1; // Pas d'intersection
    }

    return -2;
  }

  /**
  * Permet de garder un corps dans une certaine box
  * @param le corps à observer
  * @param le contour limite où doit être le corps, il doit être rectangulaire
  */
  public void insideGameOutline(Body body, PolygonShape outline){
    int i;
    float impulseCoeff = 0.5f;

    int interNum = outlineCollision(body.getShape(), outline);

    switch (interNum) {
      case 0: //Côté haut
        body.applyImpulse(new Vector2D(0, impulseCoeff));
      break;
      case 1: //Côté droite
        body.applyImpulse(new Vector2D(-impulseCoeff, 0));
      break;
      case 2: //Côté bas
        body.applyImpulse(new Vector2D(0, -impulseCoeff));
      break;
      case 3: //Côté gauche
        body.applyImpulse(new Vector2D(impulseCoeff, 0));
      break;
      default: //Pas d'intersection ou erreur
      break;
    }

  }


  /**
  * Ecoute de collision
  * Cette fonction permet de calculer toutes les collisions possibles
  */
  public void listenTo(){
    int i,j;
    Body ba, bb;
    Point inter;
    Vector2D vectImpuls, invVectImpuls;
    ArrayList<Body> bodyList = this.physicW.getBodyList();
    PolygonShape gameOutline = physicW.getGameOutline();
    float impulseCoeff = 0.5f;


    //On regarde l'intersection des corps deux à deux
    for(i = 0; i < bodyList.size(); i++){
      ba = bodyList.get(i);
      //On vérifie que le corps soit toujours dans la fenetre de jeu
      insideGameOutline(ba, gameOutline);

      for(j = i+1; j < bodyList.size(); j++){
          bb = bodyList.get(j);
          inter = areInCollision(ba, bb); //On regarde s'il y a collision
          if(inter != null){ //IL existe un point d'intersection
            if(ba.getBodyType() == BodyType.STATIC){
              if(bb.getBodyType() == BodyType.DYNAMIC){ //ba statique et bb dynamique
                vectImpuls = new Vector2D(inter, impulseCoeff*bb.getVelocity().getCoordX(), impulseCoeff*bb.getVelocity().getCoordY());
                invVectImpuls = vectImpuls.vectorRotation((float)Math.PI);

                bb.applyImpulse(invVectImpuls);
              }
               // Les deux statics, il ne se passe rien.
            }else{

              if(bb.getBodyType() == BodyType.DYNAMIC){ //ba et bb dynamiques
                //Le vecteur d'impulsion est proportionnelle au vecteur vitesse le plus grand des deux
                if(bb.getVelocityValue() > ba.getVelocityValue()){
                  vectImpuls = new Vector2D(inter, impulseCoeff*bb.getVelocity().getCoordX(), impulseCoeff*bb.getVelocity().getCoordY());
                  invVectImpuls = vectImpuls.vectorRotation((float)Math.PI);

                  ba.applyImpulse(vectImpuls);
                  bb.applyImpulse(invVectImpuls);
                }else{
                  vectImpuls = new Vector2D(inter, impulseCoeff*ba.getVelocity().getCoordX(), impulseCoeff*ba.getVelocity().getCoordY());
                  invVectImpuls = vectImpuls.vectorRotation((float)Math.PI);

                  bb.applyImpulse(vectImpuls);
                  ba.applyImpulse(invVectImpuls);
                }


              }else{//ba dynamique et bb static
                vectImpuls = new Vector2D(inter, impulseCoeff*ba.getVelocity().getCoordX(), impulseCoeff*ba.getVelocity().getCoordY());
                invVectImpuls = vectImpuls.vectorRotation((float)Math.PI);
                ba.applyImpulse(invVectImpuls);
              }
            }

          }

      }
    }
  }

}
