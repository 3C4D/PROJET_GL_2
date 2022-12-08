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
public class PastisRacket extends MyEntity implements IConfig{
  private Color color;
  private Zone zone;
  private Point tableOrigin;
  private int num;

  /**
  * Classe définissant l'aspect graphique d'une raquette
  */
  public class PastisRacketTexture extends PFixedTexturedDrawable{

    public PastisRacketTexture(int x, int y, int width, int height){
      super(x,y,width,height);
    }

    @Override
    public void paint(Graphics g){
      g.setColor(color);
      Point[] point = ((PolygonShape)getBody().getShape()).getvertexList();
      int[] pointX = new int[6];
      int[] pointY = new int[6];

      pointX[0] = (int)point[0].getX();
      pointX[1] = (int)point[1].getX();
      pointX[2] = (int)point[2].getX();
      pointX[3] = (int)point[3].getX();
      pointX[4] = (int)point[4].getX();
      pointX[5] = (int)point[5].getX();

      pointY[0] = (int)point[0].getY();
      pointY[1] = (int)point[1].getY();
      pointY[2] = (int)point[2].getY();
      pointY[3] = (int)point[3].getY();
      pointY[4] = (int)point[4].getY();
      pointY[5] = (int)point[5].getY();

      g.fillPolygon(pointX, pointY, 6);
    }
  }


  /**
  * @param position sa position initiale
  * @param type son type pour savoir si c'est la raquette A ou B
  * @param color sa couleur
  * @param tableOrigin l'origine du cercle correspondant à la table
  * @param zone la zone de déplacement de la raquette
  */
  public PastisRacket(Point position, int type, Color color, Point tableOrigin, Zone zone){
    super(type);

    this.tableOrigin = tableOrigin;
    this.color = color;
    this.zone = zone;

    //On créer son enveloppe

    Point[] vertex = new Point[6];
    Transform.rotation(position, tableOrigin, zone.getAngle());
    vertex[0] = new Point(tableOrigin.getX() - MyWorldSPP.RACKET_WIDTH/4f, tableOrigin.getY()-MyWorldSPP.RACKET_HEIGHT/2f);
    vertex[1] = new Point(tableOrigin.getX() + MyWorldSPP.RACKET_WIDTH/4f, tableOrigin.getY()-MyWorldSPP.RACKET_HEIGHT/2f);
    vertex[2] = new Point(tableOrigin.getX() + MyWorldSPP.RACKET_WIDTH/2f, tableOrigin.getY());
    vertex[3] = new Point(tableOrigin.getX() + MyWorldSPP.RACKET_WIDTH/2f, tableOrigin.getY() + MyWorldSPP.RACKET_HEIGHT/2f);
    vertex[4] = new Point(tableOrigin.getX() - MyWorldSPP.RACKET_WIDTH/2f, tableOrigin.getY() + MyWorldSPP.RACKET_HEIGHT/2f);
    vertex[5] = new Point(tableOrigin.getX() - MyWorldSPP.RACKET_WIDTH/2f, tableOrigin.getY());

    PolygonShape racketShape = new PolygonShape(vertex, 6);
    Transform.rotationShape(racketShape, null, (float)Math.PI/2f);
    Transform.translateShape(racketShape, new Vector2D(tableOrigin, new Point(tableOrigin.getX()+MyWorldSPP.TABLE_SIZE/2f-MyWorldSPP.RACKET_HEIGHT/2, tableOrigin.getY())));

    Transform.rotationShape(racketShape, tableOrigin, zone.getAngle());

    Vector2D trans = new Vector2D(position, tableOrigin);

    //On crée son body
    this.body = new Body(position, racketShape, BodyType.DYNAMIC);
    //On donne la valeur de son filtre
    this.body.getFilter().setCategoryBits(MyFilter.RACKET_CATEGORY);
    this.body.getFilter().setMaskBits(MyFilter.RACKET_MASK);


    // On ajoute son aspect graphique
    PastisRacketTexture texture = new PastisRacketTexture((int)position.getX(), (int)position.getY(), (int)MyWorldSPP.RACKET_WIDTH,(int)MyWorldSPP.RACKET_HEIGHT);
    this.setDrawable(texture);
  }

  /**
  * @return une copie de la raquette courante
  */
  public PastisRacket copy(){
    PastisRacket pp = new PastisRacket(this.getBody().getCenter(), this.type, this.color, this.tableOrigin, this.zone);
    pp.num = this.num;
    return pp;
  }

  /**
  * Permet de commencé un mouvement
  */
  public void start(){
  }

  /**
  * Permet de faire monter la raquette
  */
  public void moveUp(){
    float angle = zone.getAngle();
    //On vérifie que la raquette ne sorte pas de sa zone
    if((angle - RACKET_VELOCITY) <= zone.getMaxAngle() && (angle - RACKET_VELOCITY) >= zone.getMinAngle()){
      //On met à jour l'angle
      zone.setAngle(angle - RACKET_VELOCITY);
      //Mise à jour de la position
      Transform.rotation(this.body.getCenter(), tableOrigin, -RACKET_VELOCITY);
      //On rotationne le polygone
      Transform.rotationShape(this.body.getShape(), tableOrigin, -RACKET_VELOCITY);
    }
  }

  /**
  * Permet de faire descendre la raquette
  */
  public void moveDown(){
    float angle = zone.getAngle();
    //On vérifie que la raquette ne sorte pas de sa zone
    if((angle + RACKET_VELOCITY) <= zone.getMaxAngle() && (angle + RACKET_VELOCITY) >= zone.getMinAngle()){
      //On rotationne le polygone
      zone.setAngle(angle + RACKET_VELOCITY);
      //Mise à jour de la position
      Transform.rotation(this.body.getCenter(), tableOrigin, RACKET_VELOCITY);

      Transform.rotationShape(this.body.getShape(), tableOrigin, RACKET_VELOCITY);
    }
  }

  /**
  * Permet d'arrêter la raquette
  */
  public void stop(){
  }

  /**
  * @return la zone de la raquette
  */
  public Zone getZone(){
    return this.zone;
  }

  /**
  * @return le point d'origine de la table où se trouve la raquette
  */
  public Point getTableOrigin(){
    return this.tableOrigin;
  }

  /**
  * @return le numéro(identifiant unique) de la raquette
  */
  public int getId(){
    return this.num;
  }
}
