package projet.game;

import projet.kernel.PWorld;
import projet.physicEngine.common.*;
import projet.physicEngine.*;

import projet.graphic_engine.*;
import projet.graphic_engine.drawable.*;

import java.awt.Graphics;
import java.awt.Color;
import java.util.Vector;

/**
* Classe définissant le monde de jeu
*/
public class MyWorldSPP extends PWorld implements IConfig {
  public static float TABLE_SIZE = 800;
  public static float RACKET_WIDTH = 50;
  public static float RACKET_HEIGHT = 25;
  public static float BALL_SIZE = 20f;
  private int players_nb;

  /**
  * @param largeur du jeu
  * @param hauteur du jeu
  */
  public MyWorldSPP(float width, float height){
    super(width, height);

    // Création de mon monde physique
    this.physicWorld = new MyPhysicWorldSPP(width, height);

    // Création de la table
    Table table;
    if(HEIGHT > WIDTH){
      table = new Table(new Point(WIDTH/2f,HEIGHT/2f), (float)(WIDTH/2f - 12f));
      TABLE_SIZE = WIDTH-24;
    }else{
      table = new Table(new Point(WIDTH/2f,HEIGHT/2f), (float)(HEIGHT/2f - 12f));
      TABLE_SIZE = HEIGHT-24;
    }
    this.addEntity(table);

    float size_zone = (float)(2 * Math.sin(Math.PI/PLAYERS_NB) * TABLE_SIZE/2);
    RACKET_WIDTH = size_zone/3;
    RACKET_HEIGHT = TABLE_SIZE/10;

    addBall();


    players_nb = 0;
    for(int i = 0; i < PLAYERS_NB; i++){
      addPastisRacket();
    }

  }

  @Override
  public void processPhysic(float dt) {
    int i;
    // On calcule lance l'écouteur de collision
    physicWorld.launchCollisionListener();

    //On calcule le déplacement de chacune des corps des entités
    for(i = 0; i < this.entities.size(); i++){
      entities.get(i).getBody().linearMovement(dt);
    }
  }

  @Override
  public void processGraphic(float dt) {

  for(int i = 0; i < this.entities.size(); i++){
    entities.get(i).getDrawable().setPosition((int)entities.get(i).getBody().getCenter().getX(), (int)entities.get(i).getBody().getCenter().getY());
    this.getStage().add(entities.get(i).getDrawable());
  }


}

  // /**
  // *
  // */
  // public void setRacket

  /**
  * Permet de mettre à jour la liste d'entités du monde
  * @return les entités actualisées
  */
  public void setMyWorld(Vector<MyEntity> entitiesList){
    int i;

    for(i = 0; i < entitiesList.size(); i++){
      this.addEntity(entitiesList.get(i));
    }
  }

  /**
  * Permet d'ajouter dynamiquement des raquette au jeu
  * @return -1 si la partie et complète, le numéro de la raquette sinon
  */
  public int addPastisRacket(){
    int player_num;
    if(players_nb >= PLAYERS_NB){
      return -1; //Les joueurs sont au complet
    }

    player_num = players_nb;
    players_nb++;

    float angle = (float)(2*(player_num)*Math.PI/PLAYERS_NB);
    Point position = new Point((float)((TABLE_SIZE/2f)*Math.cos(angle) + RACKET_WIDTH/2f), (float)((TABLE_SIZE/2f)*Math.sin(angle) + RACKET_HEIGHT/2f));
    this.addEntity(new PastisRacket(position,
                   MyEntity.RACKET,
                   Color.BLUE,
                   new Point(WIDTH/2f,HEIGHT/2f),
                   new Zone((float)Math.PI*2*(player_num+1)/PLAYERS_NB, (float)Math.PI*2*player_num/PLAYERS_NB, angle )));

    return player_num;
  }

  /**
  * Permet d'ajouter dynamiquement des balles
  */
  public void addBall(){
    if(WIDTH < HEIGHT){
      Ball ball = new Ball(new Point(WIDTH/2f,HEIGHT/2f), WIDTH/50f, Color.BLUE);
      ball.getBody().setVelocity(new Vector2D(0.1f, 0f));
      this.addEntity(ball);
    }else{
      Ball ball = new Ball(new Point(WIDTH/2f,HEIGHT/2f), HEIGHT/50f , Color.BLUE);
      ball.getBody().setVelocity(new Vector2D(0.1f, 0f));
      this.addEntity(ball);
    }
  }

  /**
  * @return la liste des raquettes du jeu
  */
  public Vector<PastisRacket> getRackets(){
    int i;
    Vector pastisRackets = new Vector<PastisRacket>();
    for(i = 0; i < this.entities.size(); i++){
      if(this.entities.get(i).getType() == MyEntity.RACKET){
        pastisRackets.add((PastisRacket)this.entities.get(i));
      }
    }
    return pastisRackets;
  }

  /**
  * @return la racketB du jeu
  */
  public Vector<Ball> getBalls(){
    int i;
    Vector pastisBall = new Vector<Ball>();
    for(i = 0; i < this.entities.size(); i++){
      if(this.entities.get(i).getType() == MyEntity.BALL){
        pastisBall.add((PastisRacket)this.entities.get(i));
      }
    }
    return pastisBall;
  }
}
