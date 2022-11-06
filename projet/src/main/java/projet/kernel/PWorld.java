package projet.kernel;

import java.awt.*;
import java.util.Vector;

import projet.graphic_engine.PStage;
import projet.physicEngine.PhysicWorld;

public abstract class PWorld {

    private PStage stage;
    private PhysicWorld physicWorld;

    private Vector<PEntity> entities;

    public PWorld(PStage stage, PhysicWorld physicWorld){
        this.stage = stage;
        this.physicWorld = physicWorld;
        this.entities = new Vector<PEntity>();
    }

    public PStage getStage(){
        return this.stage;
    }

    public PhysicWorld getPhysicWorld(){
        return this.physicWorld;
    }


    public void processPhysic(float dt) {
        // TODO
    }

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

    public void process(float dt) {
        this.processPhysic(dt);
    }  
    
    
    public void addEntity(PEntity entity) {
        this.entities.add(entity);
    }

    public void removeEntity(PEntity entity) {
        this.entities.remove(entity);
    }
}
