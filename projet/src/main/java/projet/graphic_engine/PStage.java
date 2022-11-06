package projet.graphic_engine;



import java.awt.*;

import projet.graphic_engine.drawable.PIDrawable;

import java.util.LinkedList;




public class PStage {

    private int width;
    private int height;

    private LinkedList<PIDrawable> listToPaint;

    // TODO
    // Create GUI for the stage. But swing must be invisible
    // private JPanel GUI;


    public PStage(int width, int height) {
        this.width = width;
        this.height = height;
        this.listToPaint = new LinkedList<PIDrawable>();
        //this.GUI = new JPanel();
    }

    public void paint(Graphics g) {
        while(this.listToPaint.size() != 0) {
            System.out.println("Painting");
            this.listToPaint.poll().paint(g);
        }
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void add(PIDrawable drawable) {
        this.listToPaint.add(drawable);
    }
    


    /*
    public JPanel getGUI() {
        return this.GUI;
    }
    */

    public LinkedList<PIDrawable> getListToPaint() {
        return this.listToPaint;
    }

}