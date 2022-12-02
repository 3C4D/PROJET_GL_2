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

    /**
     * Permet de créer une texture animée
     * @param x
     * @param y
     * @param width
     * @param height
     * @param dt
     */
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

    /**
     * Permet d'afficher l'image
     * @param g
     */
    public void paint(Graphics g) {
        if (this.imgs.get(this.imgPtr) == null) return;

        g.drawImage(this.imgs.get(this.imgPtr), this.x, this.y, this.width, this.height, null);
    }

    /**
     * permet de charger la texture
     * @param path
     * @return true si la texture a été chargée, false sinon
     */
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

    /** 
     * permet de changer la texture
     */
    public void setTextures(Image[] imgs) {
        this.imgs = new ArrayList<Image>();
        for(int i = 0; i < imgs.length; i++) {
            this.imgs.add(imgs[i]);
        }
    }

    /**
     * Permet de récuperer la texture courante
     * @return
     */
    public Image[] getTextures() {
        Image[] to_send = new Image[this.imgs.size()];

        for(int i = 0; i < this.imgs.size(); i++) {
            to_send[i] = this.imgs.get(i);
        }

        return to_send;
    }

    /**
     * Permet de changer la position de la texture
     * @param x
     * @param y
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Permet d'afficher la l'image de texture suivante
     */
    public void next(float dt) {
        this.current_dt += dt;
        if(this.current_dt >= this.dt) {
            this.current_dt = this.current_dt%this.dt;
            this.imgPtr = (this.imgPtr + 1) % this.imgs.size();
        }
    }

    /**
     * Permet d'afficher la texture choisie
     * @param ptr
     */
    public void setPtr(int ptr) {
        this.imgPtr = ptr%this.imgs.size();
    }



}
