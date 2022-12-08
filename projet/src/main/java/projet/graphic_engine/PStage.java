package projet.graphic_engine;




import java.awt.*;
import javax.swing.*;


import projet.graphic_engine.drawable.PIDrawable;
import projet.graphic_engine.GUI.*;

import java.util.LinkedList;




public class PStage {

    private int width;
    private int height;

    private LinkedList<PIDrawable> listToPaint;

    private PPanel GUI;

    /**
     * permet de créer un stage (ou niveau de jeu)
     * @param width
     * @param height
     */
    public PStage(int width, int height) {
        this.width = width;
        this.height = height;
        this.listToPaint = new LinkedList<PIDrawable>();
        this.GUI = new PPanel();
    }

    /**
     * permet d'afficher le stage
     * Cette méthode est appelée par la méthode paint de PContext
     * et ne peut pas être appelée directement
     * @param g
     */
    public void paint(Graphics g) {
      try{
        while(this.listToPaint.size() != 0) {
            this.listToPaint.poll().paint(g);
        }
      }
      catch(Exception e){
        // e.printStackTrace();
      }

      this.GUI.repaint();
    }

    /**
     * permet de changer la taille du stage
     * @param width
     * @param height
     */
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * permet de recuperer la largeur du stage
     * @return width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * permet de recuperer la hauteur du stage
     * @return height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * permet d'ajouter un objet à dessiner sur le stage
     * @param drawable
     */
    public void add(PIDrawable drawable) {
        this.listToPaint.add(drawable);
    }


    /**
     * permet de recuperer le panel du stage
     * @return GUI
     */
    public PPanel getGUI() {
        return this.GUI;
    }


    public LinkedList<PIDrawable> getListToPaint() {
        return this.listToPaint;
    }

}
