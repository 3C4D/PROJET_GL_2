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

    int interNum = outlineCollision(body.getShape(), outline);

    if(interNum >= 0){ //Il y a intersection avec un côté
      if(body.getShape().getType() == ShapeType.POLYGON){
        Transform.rotationShape(body.getShape(),null, (float)Math.PI/4);
        body.setVelocity(new Vector2D(((PolygonShape)body.getShape()).getIsobarycenter(), ((PolygonShape)body.getShape()).getVertex(0)));
        body.getVelocity().setCoordX(body.getVelocity().getCoordX() * 0.001f);
        body.getVelocity().setCoordY(body.getVelocity().getCoordY() * 0.001f);
      }else{
        //La balle rebondi
        Vector2D oppositeRotation = body.getVelocity().opposite().vectorRotation((float)Math.PI/4);
        body.setVelocity(oppositeRotation);
      }
      // body.applyImpulse(oppositeRotation);
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
                sp.play(false, 0.7f); // On joue un son
             }
             catch(Exception e){
                e.printStackTrace();
             }

             if(ba.getShape().getType() == ShapeType.POLYGON){
               Transform.rotationShape(ba.getShape(),null, (float)Math.PI/4);
               ba.setVelocity(new Vector2D(((PolygonShape)ba.getShape()).getIsobarycenter(), ((PolygonShape)ba.getShape()).getVertex(0)));
               ba.getVelocity().setCoordX(ba.getVelocity().getCoordX() * 0.001f);
               ba.getVelocity().setCoordY(ba.getVelocity().getCoordY() * 0.001f);
             }else{
                ba.setVelocity(ba.getVelocity().vectorRotation((float)Math.PI/4));
             }

             if(bb.getShape().getType() == ShapeType.POLYGON){
               Transform.rotationShape(bb.getShape(),null, (float)Math.PI/4);
               bb.setVelocity(new Vector2D(((PolygonShape)bb.getShape()).getIsobarycenter(), ((PolygonShape)bb.getShape()).getVertex(0)));
               bb.getVelocity().setCoordX(bb.getVelocity().getCoordX() * 0.001f);
               bb.getVelocity().setCoordY(bb.getVelocity().getCoordY() * 0.001f);
             }else{
               bb.setVelocity(bb.getVelocity().vectorRotation((float)Math.PI/4));
             }
          }
      }
    }
  }

}
