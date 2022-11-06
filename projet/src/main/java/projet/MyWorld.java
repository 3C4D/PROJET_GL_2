package projet;

import projet.kernel.PWorld;
import projet.physicEngine.common.*;
import projet.physicEngine.*;

import projet.graphic_engine.*;
import projet.graphic_engine.drawable.*;

import java.awt.Graphics;
import java.awt.Color;

public class MyWorld extends PWorld {
  private final int WIDTH = 500; // Largeur du monde
  private final int HEIGHT = 500; // Hauteur du monde

  public MyWorld(float width, float height){
    super(width, height);

    // Création de mon monde physique
    this.physicWorld = new MyPhysicWorld(width, height);


    //Création de deux boules
    PFixedTexturedDrawable texture1 = new PFixedTexturedDrawable(50, 50, 50, 50) {
      @Override
      public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(this.x-25, this.y-25, 25*2, 25*2);
      }
    };
    texture1.loadTexture("src/test/java/projet/graphic_engine/texture.png");

    PFixedTexturedDrawable texture2 = new PFixedTexturedDrawable(50, 50, 100, 100){
      @Override
      public void paint(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(this.x-50, this.y-50, 50*2, 50*2);
      }
    };;
    texture2.loadTexture("src/main/resources/textures/ball2.png");

    Ball ball1 = new Ball(new Point(50,50), 25f);
    ball1.setDrawable(texture1);
    ball1.getBody().setVelocity(new Vector2D(0.01f, 0.10f));

    Ball ball2 = new Ball(new Point(250,350), 50f);
    ball2.setDrawable(texture2);
    ball2.getBody().setVelocity(new Vector2D(0.05f, -0.15f));


    Seed seed = new Seed(new Point(350,50));
    seed.getBody().setVelocity(new Vector2D(((PolygonShape)seed.getBody().getShape()).getIsobarycenter(), ((PolygonShape)seed.getBody().getShape()).getVertex(0)));
    seed.getBody().getVelocity().setCoordX(seed.getBody().getVelocity().getCoordX() * 0.001f);
    seed.getBody().getVelocity().setCoordY(seed.getBody().getVelocity().getCoordY() * 0.001f);

    //On les ajoute a la liste d'entité
    this.addEntity(ball1);
    this.addEntity(ball2);
    this.addEntity(seed);
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

}
