package projet.game;

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
public class MyCollisionListenerPong extends CollisionListener{
  public SoundPlayer[] sp = new SoundPlayer[20];
  private MyWorldPong world;
  public int count = 0;

  private SoundPlayer ballWallSound, ballRacketSound;

  /**
  * Son constructeur
  */
  public MyCollisionListenerPong(MyPhysicWorldPong pw, MyWorldPong w){
    try{
      ballWallSound = new SoundPlayer("sounds/balle1.wav");
      ballRacketSound = new SoundPlayer("sounds/balle2.wav");
    }
    catch(Exception e){
      e.printStackTrace();
    }

    this.physicW = pw;
    this.world = w;
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
    Point s1, s2, s3, s4;
    Point p;

    //On prends les 4 sommets de la rackette
    s1 = racket.getVertex(0);
    s2 = racket.getVertex(1);
    s3 = racket.getVertex(2);
    s4 = racket.getVertex(3);

    // Si il y a collision avec une balle
    if(ball.getType() == ShapeType.CIRCLE){

      /* On regarde si le projeté orthogonal sur la droite (s1s4) du centre du
         du cercle est compris entre les deux sommets*/
      p = new Point(s1.getX(), ball.getCenter().getY());
      if(p.distance(ball.getCenter()) <= ball.getRay()){
        return 3;
      }

      /* On regarde si le projeté orthogonal sur la droite (s2s3) du centre du
         du cercle est compris entre les deux sommets*/
      p = new Point(s3.getX(), ball.getCenter().getY());
      if(p.distance(ball.getCenter()) <= ball.getRay()){
        return 1;
      }

      /* On regarde si le projeté orthogonal sur la droite (s1s2) du centre du
         du cercle est compris entre les deux sommets*/
      p = new Point(ball.getCenter().getX(), s2.getY());
      if(p.distance(ball.getCenter()) <= ball.getRay()){
        return 0;
      }

      /* On regarde si le projeté orthogonal sur la droite (s3s4) du centre du
         du cercle est compris entre les deux sommets*/
      p = new Point(ball.getCenter().getX(), s4.getY());
      if(p.distance(ball.getCenter()) <= ball.getRay()){
        return 2;
      }


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

    try{
      ballRacketSound.play(false, .6f);
    }
    catch(Exception e){
      e.printStackTrace();
    }

    //On cherche le numéro du côté de la raquette que la balle touche
    int interNum = racketCollision((CircleShape)ball.getShape(), racket);
    switch (interNum) {
      case 3: //Côté droit
      if(velocity.getCoordY() > 0){ //arrive du bas
        normal = new Vector2D(velocity.getStart(),0,1);
        prodS = velocity.scalarProduct(normal);
        alpha = (float)Math.acos((double) (prodS) / (velocity.norme2()));
        alpha = (float)(Math.PI*2 - alpha);

        theta = (float)(Math.PI - Math.PI/2 - alpha); //Angle de rotation

      }else{ //arrive du haut
        normal = new Vector2D(velocity.getStart(),0,-1);
        prodS = velocity.scalarProduct(normal);
        alpha = (float)Math.acos((double) (prodS) / (velocity.norme2()));

        theta = (float)(Math.PI - Math.PI/2 - alpha); //Angle de rotation
      }
      oppositeRotation = ball.getVelocity().opposite().vectorRotation(2*theta);
      ball.setVelocity(oppositeRotation); // MAJ de la vitesse
      break;

      case 1: // Côté gauche
      if(velocity.getCoordY() > 0){ //arrive du haut
        normal = new Vector2D(velocity.getStart(),0,1);
        prodS = velocity.scalarProduct(normal);
        alpha = (float)Math.acos((double) (prodS) / (velocity.norme2()));

        theta = (float)(Math.PI - Math.PI/2 - alpha); //Angle de rotation

      }else{ //arrive du bas
        normal = new Vector2D(velocity.getStart(),0,-1);
        prodS = velocity.scalarProduct(normal);
        alpha = (float)Math.acos((double) (prodS) / (velocity.norme2()));
        alpha = (float)(Math.PI*2 - alpha);

        theta = (float)(Math.PI - Math.PI/2 - alpha); //Angle de rotation
      }
      oppositeRotation = ball.getVelocity().opposite().vectorRotation(2*theta);
      ball.setVelocity(oppositeRotation); //MAJ de la vitesse
      break;

      default:
      break;
    }

    }

  @Override
  public void insideGameOutline(Body body, PolygonShape outline){
    int interNum = outlineCollision(body.getShape(), outline);
    Vector2D normal, velocity = body.getVelocity(), oppositeRotation;
    float alpha, prodS, theta = 0;

    //Si c'est une raquette
    if(body.getShape().getType() == ShapeType.POLYGON){
      switch(interNum){
        case 0: //Tentative de sortie par le haut
        body.setVelocity(new Vector2D(0,0));
        body.applyImpulse(new Vector2D(0, 10f));
        break;

        case 1: // Tentative de sortir par le bas
        body.setVelocity(new Vector2D(0,0));
        body.applyImpulse(new Vector2D(-1f,0));
        break;

        case 3: // Tentative de sortir par le bas
        body.setVelocity(new Vector2D(0,0));
        body.applyImpulse(new Vector2D(0, -10f));
        break;
      }

      PolygonShape racket = (PolygonShape)(body.getShape());
      if(racket.getVertex(0).getY() <= outline.getVertex(0).getY()){
        body.setVelocity(new Vector2D(0,0));
        body.applyImpulse(new Vector2D(0, 1f));
      }
      if(racket.getVertex(2).getY() >= outline.getVertex(2).getY()){
        body.setVelocity(new Vector2D(0,0));
        body.applyImpulse(new Vector2D(0, -1f));
      }
    }

    //Si c'est une ball
    if(body.getShape().getType() == ShapeType.CIRCLE){

      switch (interNum) {
       case 0: // Côté haut
         try{
           ballWallSound.play(false, .6f);
         }
         catch(Exception e){
           e.printStackTrace();
         }

         if(velocity.getCoordX() > 0){ //arrive de la gauche
           normal = new Vector2D(velocity.getStart(),1,0);
           prodS = velocity.scalarProduct(normal);
           alpha = (float)Math.acos((double) (prodS) / (velocity.norme2()));
           alpha = (float)(Math.PI*2 - alpha);
           theta = (float)(Math.PI - Math.PI/2 - alpha);

         }else{ //arrive de la droite
           normal = new Vector2D(velocity.getStart(),-1,0);
           prodS = velocity.scalarProduct(normal);
           alpha = (float)Math.acos((double) (prodS) / (velocity.norme2()));

           theta = (float)(Math.PI - Math.PI/2 - alpha);
         }
         oppositeRotation = body.getVelocity().opposite().vectorRotation(2*theta);
         body.setVelocity(oppositeRotation);
       break;

       case 2: //Côté bas
         try{
           ballWallSound.play(false, .6f);
         }
         catch(Exception e){
           e.printStackTrace();
         }

         if(velocity.getCoordX() > 0){ //arrive de la gauche
           normal = new Vector2D(velocity.getStart(),1,0);
           prodS = velocity.scalarProduct(normal);
           alpha = (float)Math.acos((double) (prodS) / (velocity.norme2()));

           theta = (float)(Math.PI - Math.PI/2 - alpha);

         }else{ //arrive de la droite
           normal = new Vector2D(velocity.getStart(),-1,0);
           prodS = velocity.scalarProduct(normal);
           alpha = (float)Math.acos((double) (prodS) / (velocity.norme2()));
           alpha = (float)(Math.PI*2 - alpha);

           theta = (float)(Math.PI - Math.PI/2 - alpha);
         }
         oppositeRotation = body.getVelocity().opposite().vectorRotation(2*theta);
         body.setVelocity(oppositeRotation);
       break;

       case 1: //Point pour la raquette A
         if(!world.isNetwork()){
           world.removeBall();
           world.addPointA();
           world.replay();
         }else{
          world.removeBall();
           world.replay(0);
         }
         try{
           new SoundPlayer("sounds/siuu.wav").play(false, .6f);
         }
         catch(Exception e){
           e.printStackTrace();
         }
       break;

       case 3: //Point pour la raquette B
         if(!world.isNetwork()){
           world.removeBall();
           world.addPointB();
           world.replay();
         }else{
           world.removeBall();
           world.replay(1);
         }
         try{
           new SoundPlayer("sounds/siuu.wav").play(false, .6f);
         }
         catch(Exception e){
           e.printStackTrace();
         }
       break;

       default:
       break;
     }
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
      insideGameOutline(ba, gameOutline);

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
