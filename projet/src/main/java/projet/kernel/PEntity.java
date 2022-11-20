package projet.kernel;

import projet.graphic_engine.drawable.*;
import projet.physicEngine.*;

import java.awt.*;


public class PEntity {

    protected PIDrawable drawable;
    protected PIAnimatedDrawable animatedDrawable;
    protected Body body;
    protected int type;

    public PEntity(int type){
      this.type = type;
    }

    public PEntity(){
      this.type = 0;
    }

    public int getType(){
      return this.type;
    }

    public PEntity(Body body, PIDrawable drawable) {
        this.body = body;
        this.drawable = drawable;
        this.animatedDrawable = null;
    }

    public PEntity(Body body, PIAnimatedDrawable animatedDrawable) {
        this.body = body;
        this.animatedDrawable = animatedDrawable;
        this.drawable = null;
    }

    public void paint(Graphics g) {
        if(this.drawable != null) {
            this.drawable.paint(g);
        }
        else if(this.animatedDrawable != null) {
            this.animatedDrawable.paint(g);
        }
    }

    public Body getBody() {
        return this.body;
    }

    public PIDrawable getDrawable() {
        return this.drawable;
    }

    public PIAnimatedDrawable getAnimatedDrawable() {
        return this.animatedDrawable;
    }

    public void setDrawable(PIDrawable drawable) {
        this.animatedDrawable = null;
        this.drawable = drawable;
    }

    public void setAnimatedDrawable(PIAnimatedDrawable animatedDrawable) {
        this.drawable = null;
        this.animatedDrawable = animatedDrawable;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public void process(float dt) {
        if(this.animatedDrawable != null) {
            this.animatedDrawable.next(dt);
        }

        // TODO
    }

}
