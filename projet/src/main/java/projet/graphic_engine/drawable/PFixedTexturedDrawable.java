package projet.graphic_engine.drawable;

import java.awt.*;

public class PFixedTexturedDrawable implements PIDrawable {

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    private Image img;

    public PFixedTexturedDrawable(int x, int y, int width, int height) {
        this.img = null;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void paint(Graphics g) {
        if (this.img == null) return;

        g.drawImage(this.img, this.x, this.y, this.width, this.height, null);
    }

    public boolean loadTexture(String path) {
        try {
            this.img = Toolkit.getDefaultToolkit().createImage(path);
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public void setTexture(Image img) {
        this.img = img;
    }

    public Image getTexture() {
        return this.img;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
