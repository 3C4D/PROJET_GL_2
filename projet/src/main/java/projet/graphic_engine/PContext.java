package projet.graphic_engine;

import java.awt.*;
import javax.swing.*;



import java.awt.event.ComponentAdapter;

import java.awt.event.ComponentEvent;


public class PContext extends JPanel{

    private PStage currentStage;

    /**
     * Permet de créer un contexte
     * @param width
     * @param height
     */
    public PContext(int width, int height) {
        super(true);
        this.setSize(width, height);

        var that = this;

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                that.contextSizeChanged();
            }
        });
    }

    /**
     * Permet de changer la taille du contexte
     * Cette fonction est appelée par la fonction windowSizeChanged de PWindow
     * et ne peut pas être appelée directement
     */
    public void contextSizeChanged() {
        this.currentStage.setSize(this.getWidth(), this.getHeight());
    }

    /**
     * Permet d'afficher le contexte
     * Cette fonction est appelée par la fonction paint de PWindow
     * et ne peut pas être appelée directement
     * @param g
     */
    public void paint(Graphics g) {
        super.paint(g);
        if(this.currentStage != null) {
          this.currentStage.paint(g);          
        }
    }

    /**
     * Permet de changer le stage actuel
     * @param stage
     */
    public void changeStage(PStage stage) {
        this.removeAll();
        this.currentStage = stage;
        this.add(this.currentStage.getGUI());

    }

    /**
     * Permet de récuperer le stage actuel.
     * @return
     */
    public PStage getCurrentStage() {
        return this.currentStage;
    }






}
