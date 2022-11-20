package projet.game;

import projet.physicEngine.CollisionListener;
import projet.physicEngine.Body.BodyType;
import projet.physicEngine.*;
import projet.physicEngine.common.*;
import projet.physicEngine.Shape.ShapeType;

import projet.sound_engine.SoundPlayer;

import java.util.ArrayList;
import java.lang.Math;

public class MyCollisionListener extends CollisionListener{
  public SoundPlayer[] sp = new SoundPlayer[20];
  public int count = 0;

  public MyCollisionListener(MyPhysicWorld pw){
    this.physicW = pw;

    try{
      for(int i = 0; i < 20; i++){
        sp[i] = new SoundPlayer("sounds/oof.wav");
      }
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }


  /**
  * @return le numéro du côté par lequel la boule touche la raquette
  * 0 pour le coté haut
  * 1 pour le côté droit
  * 2 pour le côté gauche
  * 3 pour le côté bas
  * -1 si il n'y a pas d'intersection
  */
  public int racketCollision(CircleShape ball, PolygonShape racket){
    Point s1, s2, s3, s4;
    Point p;

    s1 = racket.getVertex(0);
    s2 = racket.getVertex(1);
    s3 = racket.getVertex(2);
    s4 = racket.getVertex(3);
    if(ball.getType() == ShapeType.CIRCLE){

      p = new Point(s1.getX(), ball.getCenter().getY());
      if(p.distance(ball.getCenter()) <= ball.getRay()){
        return 3;
      }

      p = new Point(s3.getX(), ball.getCenter().getY());
      if(p.distance(ball.getCenter()) <= ball.getRay()){
        return 1;
      }


      p = new Point(ball.getCenter().getX(), s2.getY());
      if(p.distance(ball.getCenter()) <= ball.getRay()){
        return 0;
      }

      p = new Point(ball.getCenter().getX(), s4.getY());
      if(p.distance(ball.getCenter()) <= ball.getRay()){
        return 2;
      }


    }
    return -1; //Pas d'intersection
  }



  public void touchRacket(Body ball, PolygonShape racket){
    int i;
    float impulseCoeff = 0.5f;
    Vector2D velocity = ball.getVelocity();
    Vector2D normal;
    Vector2D oppositeRotation;
    float alpha, theta=0, prodS;

    int interNum = racketCollision((CircleShape)ball.getShape(), racket);
    System.out.println("COLLISION"+interNum);
    Vector2D newVel;
    switch (interNum) {
      case 1:
        normal = new Vector2D(1,0);
        if(velocity.getCoordY() > 0){ //arrive du bas
          newVel = ball.getVelocity().opposite();
          prodS = newVel.scalarProduct(normal);

          //On calcul la valeur de l'angle
          alpha = (float)Math.acos( (double)(prodS / newVel.norme2()));

          //On oriente l'angle
          alpha = - alpha;
        }else{ //arrive du haut
          newVel = ball.getVelocity().opposite();
          prodS = newVel.scalarProduct(normal);

          //On calcul la valeur de l'angle
          alpha = (float)Math.acos( (double)(prodS / newVel.norme2()));

        }
        oppositeRotation = newVel.vectorRotation(2*alpha);
        ball.setVelocity(oppositeRotation);

      break;

      case 3:
        normal = new Vector2D(-1,0);
        if(velocity.getCoordY() > 0){ //arrive du bas
          newVel = ball.getVelocity().opposite();
          prodS = newVel.scalarProduct(normal);

          //On calcul la valeur de l'angle
          alpha = (float)Math.acos( (double)(prodS / newVel.norme2()));

          //On oriente l'angle
          alpha = - alpha;
        }else{ //arrive du haut
          newVel = ball.getVelocity().opposite();
          prodS = newVel.scalarProduct(normal);

          //On calcul la valeur de l'angle
          alpha = (float)Math.acos( (double)(prodS / newVel.norme2()));

        }
        oppositeRotation = newVel.vectorRotation(2*alpha);
        ball.setVelocity(oppositeRotation);

      break;

      default:
      break;
    }

    }

  @Override
  public void insideGameOutline(Body body, PolygonShape outline){
    int interNum = outlineCollision(body.getShape(), outline);

    switch(interNum){
      case 0: //Tentative de sortie par le haut
      body.setVelocity(new Vector2D(0,0));
      body.applyImpulse(new Vector2D(0, 10));
      break;

      case 3: // Tentative de sortir par le bas
      body.setVelocity(new Vector2D(0,0));
      body.applyImpulse(new Vector2D(0, -10));
      break;
    }
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

      //On vérifie que le corps soit toujours dans la fenetre de jeu
      if(ba.getFilter().getCategoryBits() == MyFilter.RACKET_CATEGORY){
        // System.out.println("COLLISION RACKET");
        insideGameOutline(ba, gameOutline);
      }

      for(j = i+1; j < bodyList.size(); j++){
          bb = bodyList.get(j);

          //On vérifie que le corps soit toujours dans la fenetre de jeu
          if(bb.getFilter().getCategoryBits() == MyFilter.RACKET_CATEGORY){
            // System.out.println("COLLISION RACKET-CONTOUR");
            insideGameOutline(bb, gameOutline);
          }
          inter = areInCollision(ba, bb); //On regarde s'il y a collision

          if(inter != null && ba != bb){ //IL existe un point d'intersection
          System.out.println("COLLISION? "+inter);
          System.out.println("BA "+ba.getShape());
          System.out.println("BB "+bb.getShape());

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
