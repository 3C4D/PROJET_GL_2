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

public class Racket extends MyEntity implements IConfig{

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

  public Racket(Point position, int type){
    super(type);

    float width = 20f;
    float height = 150f;

    //On créer son enveloppe
    // PolygonShape racketShape = PolygonShape.createRectShape(25,50, position);
    // Transform.rotationPolygon(racketShape, position, (float)Math.PI/3);

    Point[] vertex = new Point[4];
    vertex[0] = new Point(0, 0);
    vertex[1] = new Point(width, 0);
    vertex[2] = new Point(width, height);
    vertex[3] = new Point(0, height);

    PolygonShape racketShape = new PolygonShape(vertex, 4);
    Vector2D trans = new Vector2D(new Point(width/2f,height/2f), position);
    Transform.translationPolygon(racketShape, trans);
    // PolygonShape seedShape = PolygonShape.createRectShape(25,50,position);
    // Transform.rotationShape(racketShape, position, (float)(2*Math.PI));

    //On crée son body
    this.body = new Body(position, racketShape, BodyType.DYNAMIC);
    this.body.getFilter().setCategoryBits(MyFilter.RACKET_CATEGORY);
    this.body.getFilter().setMaskBits(MyFilter.RACKET_MASK);

    // On ajoute une fixture
    RacketTexture texture = new RacketTexture((int)trans.getCoordX(), (int)trans.getCoordY(), (int)width,(int)height);
    this.setDrawable(texture);
  }

  public void start(){
    this.body.setVelocity(new Vector2D(0,RACKET_VELOCITY));
  }

  public void moveUp(){
    this.body.setVelocity(new Vector2D(0,-RACKET_VELOCITY));
  }

  public void moveDown(){
    this.body.setVelocity(new Vector2D(0,RACKET_VELOCITY));
  }

  public void stop(){
    this.body.setVelocity(new Vector2D(0,0));
  }
}
