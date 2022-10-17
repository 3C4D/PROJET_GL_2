package projet;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.table.*;


public class PDrawable {
    private int x;
    private int y;
    private Image img;

    public PDrawable() {

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

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public Image getTexture() {
        return this.img;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }


}
