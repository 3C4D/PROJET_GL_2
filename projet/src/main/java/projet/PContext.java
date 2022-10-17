package projet;

import java.awt.*;
import javax.swing.*;

import java.util.LinkedList;


public class PContext extends JComponent {
    
    private JFrame frame;

    private LinkedList<PDrawable> listToDraw;

    private int width;
    private int height;

    public PContext(int width, int height) {
        this.width = width;
        this.height = height;


        this.frame = new JFrame();
        this.frame.setSize(this.width, this.height);

        this.listToDraw = new LinkedList<PDrawable>();


    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void paint(Graphics g) {

        g.setColor(Color.red);
        g.fillRect(0, 0, 100, 100);
        System.out.println("new painting");

        for(int i = 0; i < this.listToDraw.size(); i++) {
            PDrawable toDraw = this.listToDraw.poll();
            //g.drawImage(toDraw.getTexture(), toDraw.getX(), toDraw.getY(), getBackground(), frame)
        }
    }

    public void displaytexture(PDrawable drawable) {
        this.listToDraw.offer(drawable);
    }
    



}