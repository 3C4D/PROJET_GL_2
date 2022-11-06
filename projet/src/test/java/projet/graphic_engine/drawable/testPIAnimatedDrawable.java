package projet.graphic_engine.drawable;
import projet.graphic_engine.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.Test;

public class testPIAnimatedDrawable {
    @Test
    public void testConstructor() throws Exception {
        PIAnimatedDrawable drawable = new PIAnimatedDrawable() {
            @Override
            public void paint(java.awt.Graphics g) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void next(float dt) {
                // TODO Auto-generated method stub
                
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

        PIAnimatedDrawable drawable = new PIAnimatedDrawable() {
            int i = 0;
            float dt = 100/60;
            float current_dt = 0;

            @Override
            public void paint(java.awt.Graphics g) {
                g.setColor(Color.RED);
                g.fillRect(i, i, 100, 100);
                
            }

            @Override
            public void next(float dt) {
                current_dt += dt;
                if (current_dt >= this.dt) {
                    current_dt = current_dt%this.dt;
                    i++;
                }           
            }
        };

        for(int i = 0; i < 1000; i++) {
            drawable.next(1000/60);
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
