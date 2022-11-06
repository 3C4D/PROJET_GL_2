package projet.graphic_engine;


import java.util.ArrayList;


import java.awt.*;
import javax.swing.*;


import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import java.awt.event.ComponentAdapter;

import java.awt.event.ComponentEvent;



public class PWindow extends JFrame {

    private GraphicsDevice device;
    private GraphicsEnvironment environment;


    private ArrayList<PContext> contexts;

    private GridLayout layout;

    private String title;

    public PWindow(String title, int width, int height) {
        this.title = title;

        this.environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.device = this.environment.getDefaultScreenDevice();

        this.layout = new GridLayout(1, 1);
        this.contexts = new ArrayList<PContext>();

        this.setTitle(this.title);
        this.setLayout(this.layout);

        this.setSize(width, height);
        this.setResizable(true);

        var that = this;

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                that.windowSizeChanged();
            }
        });


        this.setVisible(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        

    }

    private void windowSizeChanged() {
        for(int i = 0; i < this.contexts.size(); i++) {
            this.contexts.get(i).setSize((int)(this.getWidth() / this.contexts.size()), this.getHeight());
        }
    }

    public void setFullScreenMode() {
        this.device.setFullScreenWindow(this);
    }

    public void setWindowMode() {
        this.dispose();
        this.setVisible(true);
    }

    public void addContext(PContext context) {
        
        this.contexts.add(context);
        this.windowSizeChanged();

        this.add(context);

        this.layout.setColumns(this.contexts.size());

    }


    
    




}
