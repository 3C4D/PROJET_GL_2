package projet.graphic_engine.GUI;
import projet.graphic_engine.*;

import java.awt.*;
import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class testPButton {
    
    @Test
    public void testConstructor() throws Exception {
        PButton button = new PButton("test");
        assertEquals("test", button.getText());
    }

    @Test
    public void testPaint() throws Exception {
        PWindow window = new PWindow("name", 100, 100);
        PContext context = new PContext(100, 100);
        PStage stage = new PStage(100, 100);
        context.changeStage(stage);
        window.addContext(context);

        PButton button = new PButton("test");
        button.setBounds(0, 0, 100, 100);


        stage.getGUI().add(button);

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
