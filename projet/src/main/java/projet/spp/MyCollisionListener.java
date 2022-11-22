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
  * 0 pour le coté haut
  * 1 pour le côté droit
  * 2 pour le côté bas
  * 3 pour le côté gauche
  * -1 si il n'y a pas d'intersection
  */
  public int racketCollision(CircleShape ball, PolygonShape racket){
    Point s0, s1, s2, s3, s4, s5;
    Point p;

    //On prends les 4 sommets de la rackette
    s0 = racket.getVertex(0);
    s1 = racket.getVertex(1);
    s2 = racket.getVertex(2);
    s3 = racket.getVertex(3);
    s4 = racket.getVertex(4);
    s5 = racket.getVertex(5);

    // Si il y a collision avec une balle
    if(ball.getType() == ShapeType.CIRCLE){

      //TO DO

    }
    return -1; //Pas d'intersection
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
    float alpha, theta=0, prodS;
    Point s0,s1,s2,s3,s4,s5;
    
    //On prends les 4 sommets de la rackette
    s0 = racket.getVertex(0);
    s1 = racket.getVertex(1);
    s2 = racket.getVertex(2);
    s3 = racket.getVertex(3);
    s4 = racket.getVertex(4);
    s5 = racket.getVertex(5);

    //On cherche le numéro du côté de la raquette que la balle touche
    int interNum = racketCollision((CircleShape)ball.getShape(), racket);
    switch (interNum) {
      case 0:
        normal = new Vector2D(s3,s2);

      break;

      case 1:
        normal = new Vector2D(s5,s0);
      break;

      case 2:
        normal = new Vector2D(s4,s3);
      break;

      case 3:
        normal = new Vector2D(s2,s3);
      break;

      case 4:
        normal = new Vector2D(s3,s4);
      break;

      case 5:
        normal = new Vector2D(s2,s1);
      break;

      default:
      break;
    }
    oppositeRotation = ball.getVelocity().opposite().vectorRotation(2*theta);
    ball.setVelocity(oppositeRotation); //MAJ de la vitesse

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
      insideGameOutline(ba, gameOutline);

      for(j = i+1; j < bodyList.size(); j++){
          bb = bodyList.get(j);

          //On vérifie que le corps soit toujours dans la fenetre de jeu
          insideGameOutline(bb, gameOutline);

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
