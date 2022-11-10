package projet.kernel;

import java.awt.*;
import java.util.Vector;

import projet.graphic_engine.PStage;
import projet.physicEngine.PhysicWorld;

public abstract class PWorld {

    protected PStage stage;
    protected PhysicWorld physicWorld;
    protected Vector<PEntity> entities;

    public PWorld(float width, float height){
        this.stage = new PStage((int)width, (int)height);
        this.physicWorld = new PhysicWorld(width, height);
        this.entities = new Vector<PEntity>();
    }


    /**
    * @param le lapse de temps écoulé depuis le dernier appel à la fonction
    */
    public abstract void processPhysic(float dt);

    /**
    * @param le lapse de temps écoulé depuis le dernier appel à la fonction
    */
    public void processGraphic(float dt) {
        for(PEntity entity : this.entities) {
            if(entity.getAnimatedDrawable() != null) {
                entity.getAnimatedDrawable().next(dt);
            }
        }
    }

    private void paint(Graphics g) {
        this.stage.paint(g);
    }

    /**
    * @param le lapse de temps écoulé depuis le dernier appel à la fonction
    */
    public void process(float dt) {
      processPhysic(dt);
      processGraphic(dt);
    }

    /**
    * Permet d'ajouter une entité au monde
    * @param l'entité à ajouter
    */
    public void addEntity(PEntity entity) {
      this.entities.add(entity);
      this.physicWorld.addBody(entity.getBody());
    }

    /**
    * Permet de supprimer une entité du monde
    * @param l'entité à supprimer
    */
    public void removeEntity(PEntity entity) {
      this.entities.remove(entity);
      this.physicWorld.removeBody(entity.getBody());
    }

    public PStage getStage(){
      return this.stage;
    }

    public PhysicWorld getPhysicWorld(){
      return this.physicWorld;
    }

    public Vector<PEntity> getEntities(){
      return this.entities;
    }
}
