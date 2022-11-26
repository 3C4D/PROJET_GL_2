package projet.game;

import projet.physicEngine.common.Transform;
import projet.physicEngine.common.*;
import projet.physicEngine.Body;
import projet.physicEngine.Body.BodyType;
import projet.physicEngine.*;

import projet.graphic_engine.drawable.*;

import java.lang.Math;
import java.awt.Graphics;
import java.awt.Color;

/**
* Classe définissant une raquette
*/
public class Racket extends MyEntity implements IConfig{

  /**
  * Classe définissant l'aspect graphique d'une raquette
  */
  public class RacketTexture extends PFixedTexturedDrawable{

    public RacketTexture(int x, int y, int width, int height){
      super(x,y,width,height);
    }

    @Override
    public void paint(Graphics g){
      g.setColor(Color.BLACK);
      g.fillRect(this.x - this.width/2 , this.y - this.height/2, this.width, this.height);
    }
  }

  /**
  *
  * @param sa position initiale
  * @param son type pour savoir si c'est la raquette A ou B
  * @param la largeur de la raquette
  * @param la hauteur de la raquette
  */
  public Racket(Point position, int type, float width, float height){
    super(type);


    //On créer son enveloppe

    Point[] vertex = new Point[4];
    vertex[0] = new Point(0, 0);
    vertex[1] = new Point(width, 0);
    vertex[2] = new Point(width, height);
    vertex[3] = new Point(0, height);

    PolygonShape racketShape = new PolygonShape(vertex, 4);
    Vector2D trans = new Vector2D(new Point(width/2f,height/2f), position);
    // On translate à la position initale souhaité
    Transform.translationPolygon(racketShape, trans);

    //On crée son body
    this.body = new Body(position, racketShape, BodyType.DYNAMIC);
    //On donne la valeur de son filtre
    this.body.getFilter().setCategoryBits(MyFilter.RACKET_CATEGORY);
    this.body.getFilter().setMaskBits(MyFilter.RACKET_MASK);

    // On ajoute son aspect graphique
    RacketTexture texture = new RacketTexture((int)trans.getCoordX(), (int)trans.getCoordY(), (int)width,(int)height);
    this.setDrawable(texture);
  }

  /**
  * Permet de commencé un mouvement
  */
  public void start(){
    this.body.setVelocity(new Vector2D(0,RACKET_VELOCITY));
  }

  /**
  * Permet de faire monter la raquette
  */
  public void moveUp(){
    this.body.setVelocity(new Vector2D(0,-RACKET_VELOCITY));
  }

  /**
  * Permet de faire descendre la raquette
  */
  public void moveDown(){
    this.body.setVelocity(new Vector2D(0,RACKET_VELOCITY));
  }

  /**
  * Permet d'arrêter la raquette
  */
  public void stop(){
    this.body.setVelocity(new Vector2D(0,0));
  }
}
