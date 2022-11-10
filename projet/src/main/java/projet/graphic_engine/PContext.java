package projet.graphic_engine;

import java.awt.*;
import javax.swing.*;



import java.awt.event.ComponentAdapter;

import java.awt.event.ComponentEvent;


public class PContext extends JPanel{

    private PStage currentStage;

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

    public void contextSizeChanged() {
        this.currentStage.setSize(this.getWidth(), this.getHeight());
    }

    public void paint(Graphics g) {
        super.paint(g);
        if(this.currentStage != null) {
          this.currentStage.paint(g);          
        }
    }

    public void changeStage(PStage stage) {
        this.removeAll();
        this.currentStage = stage;
        //this.add(this.currentStage.getGUI());

    }

    public PStage getCurrentStage() {
        return this.currentStage;
    }






}
