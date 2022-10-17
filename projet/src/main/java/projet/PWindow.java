package projet;


import java.util.ArrayList;


import java.awt.*;
import javax.swing.*;


import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;



public class PWindow {

    private GraphicsDevice device;
    private GraphicsEnvironment environment;

    private JFrame frame;
    
    private ArrayList<PContext> contexts;

    private GridLayout layout;


    private String title;
    private int width;
    private int height;

    public PWindow(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;


        this.environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.device = this.environment.getDefaultScreenDevice();

        this.layout = new GridLayout(1, 1);
        this.contexts = new ArrayList<PContext>();

        this.frame = new JFrame(this.title);
        this.frame.setLayout(this.layout);


        this.frame.setSize(this.width, this.height);
        this.frame.setVisible(true);
    }


    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;

        this.frame.setSize(this.width, this.height);
    }

    public void setFullScreenMode() {
        this.device.setFullScreenWindow(this.frame);
    }

    public void setWindowMode() {
        this.frame.dispose();
        this.frame.setVisible(true);
        //this.device.setDisplayMode(new DisplayMode(100, 100,  100, 100));
    }

    public void addContext(PContext context) {


        this.contexts.add(context);
        context.setSize((int)(this.width / this.contexts.size()), this.height);
        this.frame.add(context);

        this.layout.setColumns(this.contexts.size());
    }
}
