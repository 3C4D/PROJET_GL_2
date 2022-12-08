package projet.graphic_engine.drawable;
import projet.graphic_engine.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.Test;

public class testPIDrawable {
    @Test
    public void testConstructor() throws Exception {
        PIDrawable drawable = new PIDrawable() {
            @Override
            public void paint(java.awt.Graphics g) {
                // nothing to do

            }

            @Override
            public void setPosition(int x, int y){
              // nothing to do
            }
        };

        assertEquals(true, drawable != null);
    }

    @Test
    public void testVisual() throws Exception {
        PWindow window = new PWindow("name", 100, 100);
        PContext context = new PContext(100, 100);
        PStage stage = new PStage(100, 100);
        context.changeStage(stage);
        window.addContext(context);

        PIDrawable drawable = new PIDrawable() {
            @Override
            public void paint(java.awt.Graphics g) {
                g.setColor(Color.RED);
                g.fillRect(0, 0, 100, 100);

            }

            @Override
            public void setPosition(int x, int y){
              // nothing to do
            }
        };

        for(int i = 0; i < 1000; i++) {

            stage.add(drawable);
            context.repaint();

            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
