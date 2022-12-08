package projet.graphic_engine.drawable;

import java.awt.*;

public class PFixedTexturedDrawable implements PIDrawable {

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    private Image img;

    /**
     * Permet de créer un drawable avec une image fixe
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public PFixedTexturedDrawable(int x, int y, int width, int height) {
        this.img = null;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Permet d'afficher l'image
     * @param g
     */
    public void paint(Graphics g) {
        if (this.img == null) return;

        g.drawImage(this.img, this.x, this.y, this.width, this.height, null);
    }

    /**
     * permet de charger la texture
     * @param path
     * @return true si la texture a été chargée, false sinon
     */
    public boolean loadTexture(String path) {
        try {
            this.img = Toolkit.getDefaultToolkit().createImage(path);
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /** 
     * permet de changer la texture
    */
    public void setTexture(Image img) {
        this.img = img;
    }

    /**
     * permet de récuperer la texture courante
     * @return
     */
    public Image getTexture() {
        return this.img;
    }

    /**
     * permet de changer la position du drawable
     * @param x
     * @param y
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
