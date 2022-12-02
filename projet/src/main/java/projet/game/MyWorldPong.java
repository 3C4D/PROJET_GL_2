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
public class MyWorldPong extends PWorld {
  private int WIDTH = 500; // Largeur du monde
  private int HEIGHT = 500; // Hauteur du mond
  private static float BALL_SIZE = 20f;
  public static float RACKET_WIDTH = 20f , RACKET_HEIGHT = 150f;
  private int pointA;
  private int pointB;
  private int count = 0, pow = 0;
  private float x, y;
  private float maxX, maxY, minX, minY;


  /**
  * @param largeur du jeu
  * @param hauteur du jeu
  */
  public MyWorldPong(float width, float height){
    super(width, height);

    // Création de mon monde physique
    this.physicWorld = new MyPhysicWorldPong(width, height, this);
    System.out.println(physicWorld.getGameOutline());

    WIDTH = (int)width;
    HEIGHT = (int)height;
    //Création de la balle
    maxX = 0.27f;
    maxY = 0.27f;
    minX = -0.27f;
    minY = - 0.27f;
    float x = (float)(Math.random() * (3 - 2)+1)/10f;
    float y = (float)(Math.random() * (3 - 2)+1)/10f;
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

    Ball ball = new Ball(new Point(width/2f,height/2f), BALL_SIZE, Color.WHITE);
    ball.getBody().setVelocity(new Vector2D(x,y));

    // Création de la première raqutte
    RacketPong racket1 = new RacketPong(new Point(RACKET_WIDTH/2f, height/2f), MyEntity.RACKET_A, RACKET_WIDTH, RACKET_HEIGHT);

    // Création de la deuxième raquette
    RacketPong racket2 = new RacketPong(new Point(width - RACKET_WIDTH/2f, height/2f), MyEntity.RACKET_B, RACKET_WIDTH, RACKET_HEIGHT);

    //On les ajoute a la liste d'entité
    this.addEntity(racket1);
    this.addEntity(racket2);
    this.addEntity(ball);
    pointA = 0;
    pointB = 0;
  }

  @Override
  public void processPhysic(float dt) {
    int i;
    // On calcule lance l'écouteur de collision
    ((MyPhysicWorldPong)physicWorld).launchCollisionListener();

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
  public RacketPong getRacketA(){
    int i;
    for(i = 0; i < this.entities.size(); i++){
      if(this.entities.get(i).getType() == MyEntity.RACKET_A){
        return (RacketPong)this.entities.get(i);
      }
    }
    return null;
  }

  /**
  * @return la racketB du jeu
  */
  public RacketPong getRacketB(){
    int i;
    for(i = 0; i < this.entities.size(); i++){
      if(this.entities.get(i).getType() == MyEntity.RACKET_B){
        return (RacketPong)this.entities.get(i);
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
    Ball ball = new Ball(new Point(WIDTH/2f,HEIGHT/2f), BALL_SIZE, Color.WHITE);
    double sx = (Math.random());
    double sy = (Math.random());
    count++;
    if(sx > 0.5){
      x *= -1;
    }
    if(sy > 0.5){
      y *= -1;
    }

    if(count > 4 && x < maxX && y < maxY && x > minX && y > minY){
      x *= 1.3f;
      y *= 1.3f;
      pow ++;
    }
    if(count == 10){
      count = 0;
      x /= (float)(Math.pow(1.3f, pow));
      y /= (float)(Math.pow(1.3f, pow));
      pow = 0;
    }

    ball.getBody().setVelocity(new Vector2D(x, y));
    this.addEntity(ball);
  }
}
