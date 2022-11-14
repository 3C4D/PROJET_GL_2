package projet.graphic_engine.GUI;
import projet.graphic_engine.*;

import java.awt.*;
import javax.swing.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class testPPanel {
    @Test
    public void testConstructor() throws Exception {
        PPanel panel = new PPanel();
        assertEquals(0, panel.getComponentCount());
    }

    @Test
    public void testAdd() throws Exception {
        PPanel panel = new PPanel();
        PButton button = new PButton("test");
        panel.add(button);
        assertEquals(1, panel.getComponentCount());
    }

    @Test
    public void testPaint() throws Exception {
        PWindow window = new PWindow("name", 100, 100);
        PContext context = new PContext(100, 100);
        PStage stage = new PStage(100, 100);
        context.changeStage(stage);
        window.addContext(context);

        PPanel panel = new PPanel();
        panel.setBounds(0, 0, 100, 100);

        PButton button = new PButton("test");
        button.setBounds(0, 0, 100, 100);

        panel.add(button);

        stage.getGUI().add(panel);

        while(true) {
            
            context.repaint();

            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
