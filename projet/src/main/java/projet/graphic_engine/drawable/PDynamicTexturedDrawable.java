package projet.graphic_engine.drawable;

import java.util.ArrayList;
import java.awt.*;

public class PDynamicTexturedDrawable implements PIAnimatedDrawable {

    private int x;
    private int y;
    private int width;
    private int height;
    private int imgPtr;
    private float dt;
    private float current_dt;

    private ArrayList<Image> imgs;

    public PDynamicTexturedDrawable(int x, int y, int width, int height, float dt) {
        this.imgs = new ArrayList<Image>();
        this.imgPtr = 0;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.dt = dt;
        this.current_dt = 0;
    }

    public void paint(Graphics g) {
        if (this.imgs.get(this.imgPtr) == null) return;

        g.drawImage(this.imgs.get(this.imgPtr), this.x, this.y, this.width, this.height, null);
    }

    public boolean loadTextures(String[] paths) {
        this.imgs = new ArrayList<Image>();

        try {
            for(int i = 0; i < paths.length; i++) {
                this.imgs.add(Toolkit.getDefaultToolkit().createImage(paths[i]));
            }
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public void setTextures(Image[] imgs) {
        this.imgs = new ArrayList<Image>();
        for(int i = 0; i < imgs.length; i++) {
            this.imgs.add(imgs[i]);
        }
    }

    public Image[] getTextures() {
        Image[] to_send = new Image[this.imgs.size()];

        for(int i = 0; i < this.imgs.size(); i++) {
            to_send[i] = this.imgs.get(i);
        }

        return to_send;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void next(float dt) {
        this.current_dt += dt;
        if(this.current_dt >= this.dt) {
            this.current_dt = this.current_dt%this.dt;
            this.imgPtr = (this.imgPtr + 1) % this.imgs.size();
        }
    }

    public void setPtr(int ptr) {
        this.imgPtr = ptr%this.imgs.size();
    }



}
