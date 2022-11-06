package projet.physicEngine;

import projet.physicEngine.common.Transform;
import projet.physicEngine.common.Vector2D;
import projet.physicEngine.common.Point;

import java.util.ArrayList;

public class PhysicWorld{
  private ArrayList<Body> bodyList;
  private CollisionListener collisionL;

  public PhysicWorld(){
    bodyList = new ArrayList<Body>();
    collisionL = new CollisionListener(this);
  }

  public void launchCollisionListener(){
    collisionL.listenTo();
  }


  /**
  * Permet d'ajouter un corps au monde
  * @param le corps à ajouter
  */
  public void addBody(Body body){
    bodyList.add(body);
  }

  /**
  * @return la liste des corps présent dans le monde
  */
  public ArrayList<Body> getBodyList(){
    return this.bodyList;
  }

  /**
  * @return l'ecouteur d'évenement
  */
  public CollisionListener getCollisionListener(){
    return collisionL;
  }

  @Override
  public String toString(){
    int i;
    String str = "";

    for(i=0; i<bodyList.size(); i++){
      str += " ; "+bodyList.get(i).toString();
    }
    return str;
  }
}
