package projet.physicEngine;

import projet.physicEngine.common.Transform;
import projet.physicEngine.common.Vector2D;
import projet.physicEngine.common.Point;

import java.io.Serializable;
import java.util.ArrayList;

public class PhysicWorld implements Serializable {
  protected ArrayList<Body> bodyList;
  protected CollisionListener collisionL;
  protected PolygonShape gameOutline;

  /**
  * @param La largeur du monde de jeu
  * @param La hauteur du monde du jeu
  */
  public PhysicWorld(float width, float height){
    bodyList = new ArrayList<Body>();
    collisionL = new CollisionListener(this);
    gameOutline = PolygonShape.createRectShape(width, height, new Point(width/2f, height/2f));
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
  * Permet de supprimer un corps au monde
  * @param le corps à ajouter
  */
  public void removeBody(Body body){
    bodyList.remove(body);
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

  /**
  * @return le contour du jeu
  */
  public PolygonShape getGameOutline(){
    return this.gameOutline;
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
