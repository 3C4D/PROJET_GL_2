package projet.spp;

import projet.physicEngine.CollisionListener;
import projet.physicEngine.Body.BodyType;
import projet.physicEngine.*;
import projet.physicEngine.common.*;
import projet.physicEngine.Shape.ShapeType;

import projet.sound_engine.SoundPlayer;

import java.util.ArrayList;
import java.lang.Math;

/**
* Classe définissant les comportenants des entités suite à une collision
*/
public class MyCollisionListener extends CollisionListener{
  public SoundPlayer[] sp = new SoundPlayer[20];
  public int count = 0;

  /**
  * Son constructeur
  */
  public MyCollisionListener(MyPhysicWorld pw){
    this.physicW = pw;
  }


  /**
  * Permet de calculer sur quel côté la balle touche la raquette
  * @param une enveloppe de raquette
  * @param une enveloppe de balle
  * @return le numéro du côté par lequel la boule touche la raquette
  * i le côté du sommet (sisi+1)
  * -1 si il n'y a pas d'intersection
  */
  public int racketCollision(CircleShape ball, PolygonShape racket){
    Point s0, s1, s2, s3, s4, s5;
    Point p;
    Vector2D vAB; //Vecteur du segment
    Vector2D vAC; //Vecteur entre le centre du cercle et l'extrémité 1 du segment
    Vector2D vBC; //Vecteur entre le centre du cercle et l'extrémité 2 du segment
    Point pA; //Extrémité 1 du segment
    Point pB; //Extrémité 2 du segment=

    // Si il y a collision avec une balle
    if(ball.getType() == ShapeType.CIRCLE){
      for(int i = 0; i<racket.getNbVertex(); i++){
        if(i == racket.getNbVertex() - 1){ //si c'est le dernier sommet
        pA = racket.getVertex(i);
        pB = racket.getVertex(0);
        vAB = new Vector2D(racket.getVertex(i), racket.getVertex(0));
        vAC = new Vector2D(racket.getVertex(i), ball.getCenter());
        vBC = new Vector2D(racket.getVertex(0), ball.getCenter());
      }else{ //Sinon
        pA = racket.getVertex(i);
        pB = racket.getVertex(i+1);
        vAB = new Vector2D(racket.getVertex(i), racket.getVertex(i+1));
        vAC = new Vector2D(racket.getVertex(i), ball.getCenter());
        vBC = new Vector2D(racket.getVertex(i+1), ball.getCenter());
      }

      //Dans le cas où la droite (pApB) est paralèle à un axe
      if(Math.abs(pA.getY() - pB.getY()) <= 0.001){
        p = new Point(ball.getCenter().getX(), pA.getY());
        if((p.getX() <= pB.getX() && p.getX() >= pA.getX())
        || (p.getX() <= pA.getX() && p.getX() >= pB.getX())){
          if(p.distance(ball.getCenter()) <= ball.getRay()){
            return i;
          }
        }
      }
      else if(Math.abs(pA.getX() - pB.getX()) <= 0.001 ){
        p = new Point(pA.getX(), ball.getCenter().getY());
        if((p.getY() <= pB.getY() && p.getY() >= pA.getY())
        || (p.getY() >= pB.getY() && p.getY() <= pA.getY())){
          if(p.distance(ball.getCenter()) <= ball.getRay()){
            return i;
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
        if(normCI <= ball.getRay()){ //I dans cercle ca et I sur droite (AB)
          //Mais est-ce que I est sur le segment [AB] ?
          //On calcul les produits scalaires
          //prod1 = vAB.vAC
          float prod1 = vAB.scalarProduct(vAC);
          //prod2 = vBA.vBC
          float prod2 = (-vAB.getCoordX())*vBC.getCoordX() + (-vAB.getCoordY()*vBC.getCoordY());

          //Si les deux positifs, alors C est entre A et B, donc I aussi
          if(prod1 >= 0 && prod2 >= 0){
            Point pI = ball.getCenter().orthoProjection(pA, pB);
            return i;
          }
          //Autre solution, A est dans le cercle
          if(ball.isInside(pA)){
            return i;
          }
          //Autre solution, B est dans le cercle
          if(ball.isInside(pB)){
            return i;
          }
        }
      }
      }
    }
    return -1; //Pas d'intersection
  }


  /**
  * Permet de savoir si la balle est au dessus(à gauche) ou en dessous (a droite)
    de la droite de vecteur directeur v
  * @param l'enveloppe de la balle en question
  * @param le vecteur directeur de la droite
  * @param le point d'origine de la droite v
  * @return 0 si à gauche(au dessus), 1 si à droite(en dessous)
  */
  public int leftOrRightCote(CircleShape ball, Vector2D v, Point start){
    float c, a,b, yc;

    //Vecteur directeur v (vx , vy)
    //Equation cartesienne : vy*x - vx*y + c = 0
    //On calcul c
    c = v.getCoordX()*v.getStart().getY() - v.getCoordY()*v.getStart().getX();

    //vy*xc - vx*yc + c > 0 --> point (xc, yc) au dessus de la droite
    //Sinon en dessous
    if( (v.getCoordY()*ball.getCenter().getX() - v.getCoordX()*ball.getCenter().getY() + c) >= 0 ){
      // System.out.println("TOUCHE DE LA DROITE");
      return 0;
    }else{
      // System.out.println("TOUCHE DE LA GAUCHE");
      return 1;
    }
  }


  /**
  * Définit le comportement d'une balle lors de sa collision avec une raquettz
  * @param une balle
  * @param l'enveloppe d'une raquette
  */
  public void touchRacket(Body ball, PolygonShape racket){
    int i;
    Vector2D velocity = ball.getVelocity();
    Vector2D normal;
    Vector2D newVel;
    Vector2D oppositeRotation;
    float alpha=0, theta=0, prodS;
    Point s0,s1,s2,s3,s4,s5, middle;

    //On prends les 4 sommets de la rackette
    s0 = racket.getVertex(0);
    s1 = racket.getVertex(1);
    s2 = racket.getVertex(2);
    s3 = racket.getVertex(3);
    s4 = racket.getVertex(4);
    s5 = racket.getVertex(5);

    //On cherche le numéro du côté de la raquette que la balle touche
    int interNum = racketCollision((CircleShape)ball.getShape(), racket);
    if(interNum !=0) System.out.println("TOUCHE COTE : "+interNum);
    switch (interNum) {
      case 0:
        normal = new Vector2D(s3,s2);
        middle = new Point((s0.getX()+s1.getX())/2f, (s0.getY()+s1.getY())/2f);
        if(leftOrRightCote((CircleShape)ball.getShape(), normal, middle) == 0){//A gauche
          prodS = normal.scalarProduct(velocity);
          alpha = (float)(prodS) / (velocity.norme2()*normal.norme2());

          theta = (float)(Math.PI -  alpha); //Angle de rotation
        }else{
          normal = normal.opposite();
          prodS = velocity.scalarProduct(normal);
          alpha = (float)(prodS) / (velocity.norme2()*normal.norme2());

          theta = (float)(alpha - Math.PI); //Angle de rotation
        }
      break;

      case 1:
        normal = new Vector2D(s5,s0);
        middle = new Point((s1.getX()+s2.getX())/2f, (s1.getY()+s2.getY())/2f);
        if(leftOrRightCote((CircleShape)ball.getShape(), normal, middle) == 0){//A gauche
          prodS = normal.scalarProduct(velocity);
          alpha = (float)(prodS) / (velocity.norme2()*normal.norme2());

          theta = (float)(Math.PI - alpha); //Angle de rotation
        }else{
          normal = normal.opposite();
          prodS = velocity.scalarProduct(normal);
          alpha = (float)(prodS) / (velocity.norme2()*normal.norme2());

          theta = (float)(alpha - Math.PI); //Angle de rotation
        }
      break;

      case 2:
        normal = new Vector2D(s4,s3);
        middle = new Point((s2.getX()+s3.getX())/2f, (s2.getY()+s3.getY())/2f);
        if(leftOrRightCote((CircleShape)ball.getShape(), normal, middle) == 0){//A gauche
          prodS = normal.scalarProduct(velocity);
          alpha = (float)(prodS) / (velocity.norme2()*normal.norme2());

          theta = (float)(Math.PI - alpha); //Angle de rotation
        }else{
          normal = normal.opposite();
          prodS = velocity.scalarProduct(normal);
          alpha = (float)(prodS) / (velocity.norme2()*normal.norme2());

          theta = (float)(alpha - Math.PI); //Angle de rotation
        }
      break;

      case 3:
        normal = new Vector2D(s2,s3);
        middle = new Point((s3.getX()+s4.getX())/2f, (s3.getY()+s4.getY())/2f);
        if(leftOrRightCote((CircleShape)ball.getShape(), normal, middle) == 0){//A gauche
          prodS = normal.scalarProduct(velocity);
          alpha = (float)(prodS) / (velocity.norme2()*normal.norme2());

          theta = (float)(Math.PI - alpha); //Angle de rotation
        }else{
          normal = normal.opposite();
          prodS = velocity.scalarProduct(normal);
          alpha = (float)(prodS) / (velocity.norme2()*normal.norme2());

          theta = (float)(alpha - Math.PI); //Angle de rotation
        }
      break;

      case 4:
        normal = new Vector2D(s3,s4);
        middle = new Point((s4.getX()+s5.getX())/2f, (s4.getY()+s5.getY())/2f);
        if(leftOrRightCote((CircleShape)ball.getShape(), normal, middle) == 0){//A gauche
          prodS = normal.scalarProduct(velocity);
          alpha = (float)(prodS) / (velocity.norme2()*normal.norme2());

          theta = (float)(Math.PI - alpha); //Angle de rotation
        }else{
          normal = normal.opposite();
          prodS = velocity.scalarProduct(normal);
          alpha = (float)(prodS) / (velocity.norme2()*normal.norme2());

          theta = (float)(alpha - Math.PI); //Angle de rotation
        }
      break;

      case 5:
        normal = new Vector2D(s2,s1);
        middle = new Point((s5.getX()+s1.getX())/2f, (s5.getY()+s1.getY())/2f);
        if(leftOrRightCote((CircleShape)ball.getShape(), normal, middle) == 0){//A gauche
          prodS = normal.scalarProduct(velocity);
          alpha = (float)(prodS) / (velocity.norme2()*normal.norme2());

          theta = (float)(Math.PI - alpha); //Angle de rotation
        }else{
          normal = normal.opposite();
          prodS = velocity.scalarProduct(normal);
          alpha = (float)(prodS) / (velocity.norme2()*normal.norme2());

          theta = (float)(alpha - Math.PI); //Angle de rotation
        }
      break;

      default:
      break;
    }
    // System.out.println("VITESSE AVANT : "+ball.getVelocity().getCoordX()+" , "+ball.getVelocity().getCoordY()+" )");
    // System.out.println("ANGLE ROT: "+theta);
    oppositeRotation = ball.getVelocity().opposite().vectorRotation(2*theta);
    ball.setVelocity(oppositeRotation); //MAJ de la vitesse
    // System.out.println("VITESSE APRES : "+ball.getVelocity().getCoordX()+" , "+ball.getVelocity().getCoordY()+" )");
    }


  @Override
  public void listenTo(){
    int i,j;
    Body ba, bb;
    Point inter;
    ArrayList<Body> bodyList = this.physicW.getBodyList();
    PolygonShape gameOutline = this.physicW.getGameOutline();

    Vector2D vectImpuls, invVectImpuls;

    //On regarde l'intersection de nos corps deux à deux
    for(i = 0; i < bodyList.size(); i++){
      ba = bodyList.get(i);

      for(j = i+1; j < bodyList.size(); j++){
          bb = bodyList.get(j);

          inter = areInCollision(ba, bb); //On regarde s'il y a collision

          if(inter != null && ba != bb){ //IL existe un point d'intersection

             if(ba.getFilter().getCategoryBits() == MyFilter.RACKET_CATEGORY){
               //Alors la deuxième enité est forcément une balle
               touchRacket(bb, (PolygonShape)ba.getShape());
             }
            else if(bb.getFilter().getCategoryBits() == MyFilter.RACKET_CATEGORY){
              //Alors la première entité est forcément une balle
              touchRacket(ba, (PolygonShape)bb.getShape());
            }
        }
      }
    }
  }

}
