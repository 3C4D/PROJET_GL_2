package projet;

import projet.physicEngine.common.Transform;
import projet.physicEngine.common.*;
import projet.physicEngine.Body;
import projet.physicEngine.*;
import projet.physicEngine.Body.BodyType;

import projet.graphic_engine.drawable.*;

import java.lang.Math;
import java.awt.Graphics;
import java.awt.Color;

public class Seed extends MyEntity{

  public class SeedTexture extends PFixedTexturedDrawable{

    public SeedTexture(int x, int y, int width, int height){
      super(x,y,width,height);
    }

    @Override
    public void paint(Graphics g){
      g.setColor(Color.GREEN);
      Point[] point = ((PolygonShape)getBody().getShape()).getvertexList();
      int[] pointX = new int[7];
      int[] pointY = new int[7];

      pointX[0] = (int)point[0].getX();
      pointX[1] = (int)point[1].getX();
      pointX[2] = (int)point[2].getX();
      pointX[3] = (int)point[3].getX();
      pointX[4] = (int)point[4].getX();
      pointX[5] = (int)point[5].getX();
      pointX[6] = (int)point[6].getX();

      pointY[0] = (int)point[0].getY();
      pointY[1] = (int)point[1].getY();
      pointY[2] = (int)point[2].getY();
      pointY[3] = (int)point[3].getY();
      pointY[4] = (int)point[4].getY();
      pointY[5] = (int)point[5].getY();
      pointY[6] = (int)point[6].getY();

      g.fillPolygon(pointX, pointY, 7);
    }
  }

  public Seed(Point position){
    super(MyEntity.SEED);

    //On créer son enveloppe
    Point[] vertex = new Point[7];
    vertex[0] = new Point(0, 10);
    vertex[1] = new Point(20, 30);
    vertex[2] = new Point(20, 40);
    vertex[3] = new Point(10, 50);
    vertex[4] = new Point(-10, 50);
    vertex[5] = new Point(-20 , 40);
    vertex[6] = new Point(-20, 30);

    PolygonShape seedShape = new PolygonShape(vertex, 7);
    Transform.rotationPolygon(seedShape, new Point(0,70), (float)Math.PI/3);
    Vector2D trans = new Vector2D(new Point(0,70), position);
    Transform.translationPolygon(seedShape, trans);

    //On crée son body
    this.body = new Body(position, seedShape, BodyType.DYNAMIC);
    this.body.getFilter().setCategoryBits(MyFilter.SEED_CATEGORY);
    this.body.getFilter().setMaskBits(MyFilter.SEED_MASK);

    // On ajoute une fixture
    SeedTexture texture = new SeedTexture((int)position.getX(), (int)position.getY(), 100,100);
    this.setDrawable(texture);
  }
}
