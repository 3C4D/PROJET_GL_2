package projet;

import projet.kernel.PWorld;
import projet.physicEngine.common.Point;

public class MyWorld extends PWorld {
  private final int WIDTH = 500; // Largeur du monde
  private final int HEIGHT = 500; // Hauteur du monde


  public MyWorld(float width, float height){
    super(width, height);

    //Création de deux boules
    Ball ball1 = new Ball(new Point(50,50), 25f);
    Ball ball2 = new Ball(new Point(250,350), 50f);

    //On les ajoute a la liste d'entité
    this.addEntity(ball1);
    this.addEntity(ball2);

  }

  @Override
  public void processPhysic(float dt) {
    int i;
    //On calcule le déplacement de chacune des corps des entités
    for(i = 0; i < this.entities.size(); i++){
      entities.get(i).getBody().linearMovement(dt);
    }

    // On calcule lance l'écouteur de collision
    physicWorld.launchCollisionListener();

  }


}
