package projet;

import projet.physicEngine.CollisionListener;
import projet.physicEngine.Body.BodyType;
import projet.physicEngine.*;
import projet.physicEngine.common.*;

import java.util.ArrayList;

public class MyCollisionListener extends CollisionListener{

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

            // System.out.println("INTERSECTION");
          }

      }
    }
  }
}
