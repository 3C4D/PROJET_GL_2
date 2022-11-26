package projet.game;

import projet.kernel.PWorld;
import projet.physicEngine.common.*;
import projet.physicEngine.*;

import projet.graphic_engine.*;
import projet.graphic_engine.drawable.*;

import java.awt.Graphics;
import java.awt.Color;

/**
* Classe définissant le monde de jeu
*/
public class MyWorld extends PWorld {
  private final int WIDTH = 500; // Largeur du monde
  private final int HEIGHT = 500; // Hauteur du mond
  private static float BALL_SIZE = 20f;
  private int pointA;
  private int pointB;
  private float x, y;

  /**
  * @param largeur du jeu
  * @param hauteur du jeu
  */
  public MyWorld(float width, float height){
    super(width, height);

    // Création de mon monde physique
    this.physicWorld = new MyPhysicWorld(width, height, this);

    //Création de la balle
    float x = (float)(Math.random() * (3 - 2))/10f;
    float y = (float)(Math.random() * (3 - 2))/10f;
    double sx = (Math.random());
    double sy = (Math.random());
    if(sx > 0.5){
      x *= -1;
    }
    if(sy > 0.5){
      y *= -1;
    }
    this.x = x;
    this.y = y;
    Ball ball = new Ball(new Point(WIDTH/2f,HEIGHT/2f), BALL_SIZE);
    ball.getBody().setVelocity(new Vector2D(x,y));

    // Création de la première raqutte
    Racket racket1 = new Racket(new Point(25,200), MyEntity.RACKET_A);

    // Création de la deuxième raquette
    Racket racket2 = new Racket(new Point(475,200), MyEntity.RACKET_B);

    //On les ajoute a la liste d'entité
    this.addEntity(ball);
    this.addEntity(racket1);
    this.addEntity(racket2);
    pointA = 0;
    pointB = 0;

  }

  @Override
  public void processPhysic(float dt) {
    int i;
    // On calcule lance l'écouteur de collision
    ((MyPhysicWorld)physicWorld).launchCollisionListener();

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
  * @return la racketA du jeu
  */
  public Racket getRacketA(){
    int i;
    for(i = 0; i < this.entities.size(); i++){
      if(this.entities.get(i).getType() == MyEntity.RACKET_A){
        return (Racket)this.entities.get(i);
      }
    }
    return null;
  }

  /**
  * @return la racketB du jeu
  */
  public Racket getRacketB(){
    int i;
    for(i = 0; i < this.entities.size(); i++){
      if(this.entities.get(i).getType() == MyEntity.RACKET_B){
        return (Racket)this.entities.get(i);
      }
    }
    return null;
  }

  /**
  * @return la balle du jeu
  */
  public Ball getBall(){
    int i;
    for(i = 0; i < this.entities.size(); i++){
      if(this.entities.get(i).getType() == MyEntity.BALL){
        return (Ball)this.entities.get(i);
      }
    }
    return null;
  }

  /**
  *
  */
  public void removeBall(){
    this.removeEntity(this.getBall());
  }

  /**
  * @return le nombre de point de la raquette A
  */
  public int getPointA(){
    return this.pointA;
  }

  /**
  * @return le nombre de point de la raquette B
  */
  public int getPointB(){
    return this.pointB;
  }

  /**
  * Ajoute un point à la raquette A
  */
  public void addPointA(){
    this.pointA += 1;
  }

  /**
  * Ajoute un point à la raquette B
  */
  public void addPointB(){
    this.pointB += 1;
  }

  /**
  *
  */
  public void replay(){
    //On recrée une balle que l'on ajoute au jeu
    Ball ball = new Ball(new Point(WIDTH/2f,HEIGHT/2f), BALL_SIZE);
    double sx = (Math.random());
    double sy = (Math.random());
    if(sx > 0.5){
      x *= -1;
    }
    if(sy > 0.5){
      y *= -1;
    }
    if(pointA + pointB > 4){
      x *= 1.3f;
      y *= 1.3f;
    }
    ball.getBody().setVelocity(new Vector2D(x, y));
    this.addEntity(ball);
    System.out.println(this.getEntities());
  }
}
