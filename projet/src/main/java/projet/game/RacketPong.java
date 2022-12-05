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
public class RacketPong extends MyEntity implements IConfig {

  // Le joueur
  PongPlayer player;

  /**
   * Classe définissant l'aspect graphique d'une raquette
   */
  public class RacketPongTexture extends PFixedTexturedDrawable {

    public RacketPongTexture(int x, int y, int width, int height) {
      super(x, y, width, height);
    }

    @Override
    public void paint(Graphics g) {
      g.setColor(Color.WHITE);
      g.fillRect(this.x - this.width / 2, this.y - this.height / 2, this.width, this.height);
    }
  }

  /**
   * Constructeur
   * 
   * @param position sa position initiale
   * @param type     son type pour savoir si c'est la raquette A ou B
   * @param width    la largeur de la raquette
   * @param height   la hauteur de la raquette
   */
  public RacketPong(Point position, int type, float width, float height) {
    super(type);

    // On créer son enveloppe

    Point[] vertex = new Point[4];
    vertex[0] = new Point(0, 0);
    vertex[1] = new Point(width, 0);
    vertex[2] = new Point(width, height);
    vertex[3] = new Point(0, height);

    PolygonShape racketShape = new PolygonShape(vertex, 4);
    Vector2D trans = new Vector2D(new Point(width / 2f, height / 2f), position);
    // On translate à la position initale souhaité
    Transform.translationPolygon(racketShape, trans);

    // On crée son body
    this.body = new Body(position, racketShape, BodyType.DYNAMIC);
    // On donne la valeur de son filtre
    this.body.getFilter().setCategoryBits(MyFilter.RACKET_CATEGORY);
    this.body.getFilter().setMaskBits(MyFilter.RACKET_MASK);

    // On ajoute son aspect graphique
    RacketPongTexture texture = new RacketPongTexture((int) trans.getCoordX(), (int) trans.getCoordY(), (int) width,
        (int) height);
    this.setDrawable(texture);
  }

  /**
   * Constructeur
   * 
   * @param position la position initiale
   * @param type     le type pour savoir si c'est la raquette A ou B
   * @param width    la largeur de la raquette
   * @param height   la hauteur de la raquette
   * @param player   le joueur de pong
   */
  public RacketPong(Point position, int type, float width, float height, PongPlayer player) {
    super(type);

    // On créer son enveloppe

    Point[] vertex = new Point[4];
    vertex[0] = new Point(0, 0);
    vertex[1] = new Point(width, 0);
    vertex[2] = new Point(width, height);
    vertex[3] = new Point(0, height);

    PolygonShape racketShape = new PolygonShape(vertex, 4);
    Vector2D trans = new Vector2D(new Point(width / 2f, height / 2f), position);
    // On translate à la position initale souhaité
    Transform.translationPolygon(racketShape, trans);

    // On crée son body
    this.body = new Body(position, racketShape, BodyType.DYNAMIC);
    // On donne la valeur de son filtre
    this.body.getFilter().setCategoryBits(MyFilter.RACKET_CATEGORY);
    this.body.getFilter().setMaskBits(MyFilter.RACKET_MASK);

    // On ajoute son aspect graphique
    RacketPongTexture texture = new RacketPongTexture((int) trans.getCoordX(), (int) trans.getCoordY(), (int) width,
        (int) height);
    this.setDrawable(texture);

    // On affecte le joueur
    this.player = player;
  }

  /**
   * Permet de commencer un mouvement
   */
  public void start() {
    this.body.setVelocity(new Vector2D(0, RACKET_VELOCITY));

    // Si on est en réseau on envoie les infos
    if (player != null) {
      System.out.println("J'envoie sur le réseau");
      if (MyEntity.RACKET_A == this.getType()) {
        player.sendMessage("MVT RACKET_A 0;" + RACKET_VELOCITY);
      } else {
        player.sendMessage("MVT RACKET_B 0;" + RACKET_VELOCITY);
      }
    }
  }

  /**
   * Permet de faire monter la raquette
   */
  public void moveUp() {
    this.body.setVelocity(new Vector2D(0, -RACKET_VELOCITY));

    // Si on est en réseau on envoie les infos
    if (player != null) {
      System.out.println("J'envoie sur le réseau");
      if (MyEntity.RACKET_A == this.getType()) {
        player.sendMessage("MVT RACKET_A 0;-" + RACKET_VELOCITY);
      } else {
        player.sendMessage("MVT RACKET_B 0;-" + RACKET_VELOCITY);
      }
    }
  }

  /**
   * Permet de faire descendre la raquette
   */
  public void moveDown() {
    this.body.setVelocity(new Vector2D(0, RACKET_VELOCITY));

    // Si on est en réseau on envoie les infos
    if (player != null) {
      System.out.println("J'envoie sur le réseau");
      if (MyEntity.RACKET_A == this.getType()) {
        player.sendMessage("MVT RACKET_A 0;" + RACKET_VELOCITY);
      } else {
        player.sendMessage("MVT RACKET_B 0;" + RACKET_VELOCITY);
      }
    }
  }

  /**
   * Permet d'arrêter la raquette
   */
  public void stop() {
    this.body.setVelocity(new Vector2D(0, 0));

    // Si on est en réseau on envoie les infos
    if (player != null) {
      System.out.println("J'envoie sur le réseau");
      if (MyEntity.RACKET_A == this.getType()) {
        player.sendMessage("MVT RACKET_A 0;0");
      } else {
        player.sendMessage("MVT RACKET_B 0;0");
      }
    }
  }
}
