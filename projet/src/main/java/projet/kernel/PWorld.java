package projet.kernel;

import java.awt.*;

import projet.graphic_engine.PStage;
import projet.physicEngine.PhysicWorld;

public abstract class PWorld {

    private PStage stage;
    private PhysicWorld physicWorld;

    public PWorld(PStage stage, PhysicWorld physicWorld){
        this.stage = stage;
        this.physicWorld = physicWorld;
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

    private void paint(Graphics g) {
        this.stage.paint(g);
    }

    public void process(Graphics g, float dt) {
        this.processPhysic(dt);
        this.paint(g);
    }   
}
