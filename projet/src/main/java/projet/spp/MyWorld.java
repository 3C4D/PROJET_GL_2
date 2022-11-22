package projet.spp;

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
public class MyWorld extends PWorld implements IConfig {
  /**
  * @param largeur du jeu
  * @param hauteur du jeu
  */
  public MyWorld(float width, float height){
    super(width, height);

    // Création de mon monde physique
    this.physicWorld = new MyPhysicWorld(width, height);

    // Création de la table
    Table table = new Table(new Point(TABLE_SIZE/2f,TABLE_SIZE/2f), (float)(TABLE_SIZE/2f-25.0f));
    this.addEntity(table);


    //Pour chaque joueur on créer une rackette et on l'ajoute au monde
    float angle;
    Point position;
    for(int i = 0; i < PLAYERS_NB; i++){
      angle = (float)(2*(i)*Math.PI/PLAYERS_NB);
      System.out.println("ANGLE "+i+" est "+angle);
      position = new Point((float)((TABLE_SIZE/2f)*Math.cos(angle) + RACKET_WIDTH/2f), (float)((TABLE_SIZE/2f)*Math.sin(angle) + RACKET_HEIGHT/2f));
      this.addEntity(new PastisRacket(position,
                     MyEntity.RACKET,
                     Color.YELLOW,
                     new Point(TABLE_SIZE/2f,TABLE_SIZE/2f),
                     new Zone((float)Math.PI*2*(i+1)/PLAYERS_NB, (float)Math.PI*2*i/PLAYERS_NB, angle )));
      System.out.println("cc");
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
