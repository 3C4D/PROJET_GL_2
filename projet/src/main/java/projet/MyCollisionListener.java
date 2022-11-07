package projet;

import projet.physicEngine.CollisionListener;
import projet.physicEngine.Body.BodyType;
import projet.physicEngine.*;
import projet.physicEngine.common.*;
import projet.physicEngine.Shape.ShapeType;

import projet.sound_engine.SoundPlayer;

import java.util.ArrayList;
import java.lang.Math;

public class MyCollisionListener extends CollisionListener{

  SoundPlayer sp;

  public MyCollisionListener(MyPhysicWorld pw){
    this.physicW = pw;

    try{
      sp = new SoundPlayer("sounds/oof.wav");
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }

  @Override
  public void insideGameOutline(Body body, PolygonShape outline){
    int i;
    float impulseCoeff = 0.5f;
    Vector2D velocity = body.getVelocity();
    Vector2D normal;
    float alpha, theta=0, prodS;

    int interNum = outlineCollision(body.getShape(), outline);


    switch (interNum) {
      case 0:
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
      break;

      case 1:
        if(velocity.getCoordY() > 0){ //arrive du bas
          normal = new Vector2D(velocity.getStart(),0,1);
          prodS = velocity.scalarProduct(normal);
          alpha = (float)Math.acos((double) (prodS) / (velocity.norme2()));
          alpha = (float)(Math.PI*2 - alpha);

          theta = (float)(Math.PI - Math.PI/2 - alpha);

        }else{ //arrive du haut
          normal = new Vector2D(velocity.getStart(),0,-1);
          prodS = velocity.scalarProduct(normal);
          alpha = (float)Math.acos((double) (prodS) / (velocity.norme2()));

          theta = (float)(Math.PI - Math.PI/2 - alpha);
        }
      break;

      case 2:
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
      break;

      case 3:
        if(velocity.getCoordY() > 0){ //arrive du haut
          normal = new Vector2D(velocity.getStart(),0,1);
          prodS = velocity.scalarProduct(normal);
          alpha = (float)Math.acos((double) (prodS) / (velocity.norme2()));

          theta = (float)(Math.PI - Math.PI/2 - alpha);

        }else{ //arrive du bas
          normal = new Vector2D(velocity.getStart(),0,-1);
          prodS = velocity.scalarProduct(normal);
          alpha = (float)Math.acos((double) (prodS) / (velocity.norme2()));
          alpha = (float)(Math.PI*2 - alpha);

          theta = (float)(Math.PI - Math.PI/2 - alpha);
        }
      break;

      default:
      break;
    }

    // body.applyImpulse(oppositeRotation);
    if(interNum >= 0){ //Il y a intersection avec un côté
      if(body.getShape().getType() == ShapeType.POLYGON){
        Transform.rotationShape(body.getShape(),null, 2*theta);
        body.setVelocity(new Vector2D(((PolygonShape)body.getShape()).getIsobarycenter(), ((PolygonShape)body.getShape()).getVertex(0)));
        body.getVelocity().setCoordX(body.getVelocity().getCoordX() * 0.001f);
        body.getVelocity().setCoordY(body.getVelocity().getCoordY() * 0.001f);
      }else{
        //La balle rebondi

        Vector2D oppositeRotation = body.getVelocity().opposite().vectorRotation(2*theta);
        body.setVelocity(oppositeRotation);
      }
    }
  }

  @Override
  public void listenTo(){
    int i,j;
    Body ba, bb;
    Point inter;
    Vector2D vectImpuls, invVectImpuls;
    ArrayList<Body> bodyList = this.physicW.getBodyList();
    PolygonShape gameOutline = physicW.getGameOutline();
    float impulseCoeff = 0.5f;
    float alpha,theta, prodS;


    //On regarde l'intersection de nos corps deux à deux
    for(i = 0; i < bodyList.size(); i++){
      ba = bodyList.get(i);
      //On vérifie que le corps soit toujours dans la fenetre de jeu
      insideGameOutline(ba, gameOutline);

      for(j = i+1; j < bodyList.size(); j++){
          bb = bodyList.get(j);
          inter = areInCollision(ba, bb); //On regarde s'il y a collision
          if(inter != null){ //IL existe un point d'intersection
             try{
               System.out.println("collision");
                sp.play(false, 0.7f); // On joue un son
             }
             catch(Exception e){
                e.printStackTrace();
             }

             Vector2D velocityA = ba.getVelocity();
             Vector2D velocityB = bb.getVelocity();

             prodS = velocityA.scalarProduct(velocityB);
             alpha = (float)Math.acos((double) (prodS) / (velocityA.norme2()*velocityB.norme2() ));
             // alpha = (float)(Math.PI*2 - alpha);
             theta = (float)(Math.PI - Math.PI/2 - alpha);

             if(ba.getShape().getType() == ShapeType.POLYGON){
               Transform.rotationShape(ba.getShape(),null, (float)Math.PI/4);
               ba.setVelocity(new Vector2D(((PolygonShape)ba.getShape()).getIsobarycenter(), ((PolygonShape)ba.getShape()).getVertex(0)));
               ba.getVelocity().setCoordX(ba.getVelocity().getCoordX() * 0.001f);
               ba.getVelocity().setCoordY(ba.getVelocity().getCoordY() * 0.001f);
               // ba.applyImpulse(new Vector2D(1000f*ba.getVelocity().getCoordX(), 1000f*ba.getVelocity().getCoordY()));
             }else{
                ba.setVelocity(ba.getVelocity().vectorRotation((float)Math.PI/4));
             }

             if(bb.getShape().getType() == ShapeType.POLYGON){
               Transform.rotationShape(bb.getShape(),null, (float)Math.PI/4);
               bb.setVelocity(new Vector2D(((PolygonShape)bb.getShape()).getIsobarycenter(), ((PolygonShape)bb.getShape()).getVertex(0)));
               bb.getVelocity().setCoordX(bb.getVelocity().getCoordX() * 0.001f);
               bb.getVelocity().setCoordY(bb.getVelocity().getCoordY() * 0.001f);
               bb.applyImpulse(ba.getVelocity().opposite());
             }else{
               bb.setVelocity(bb.getVelocity().vectorRotation((float)Math.PI/4));
             }
          }
      }
    }
  }

}
