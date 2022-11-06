package projet.graphic_engine.drawable;
import projet.graphic_engine.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class testPFixedTexturedDrawable {
    @Test
    public void testConstructor() throws Exception {
        PFixedTexturedDrawable drawable = new PFixedTexturedDrawable(0, 0, 20, 20);

        assertEquals(true, drawable != null);
    }

    @Test
    public void testLoadTexture() throws Exception {
        PFixedTexturedDrawable drawable = new PFixedTexturedDrawable(0, 0, 20, 20);
        boolean ok = drawable.loadTexture("src/test/java/projet/graphic_engine/texture.png");

        assertEquals(true, ok);
    }

    @Test
    public void testPaint() throws Exception {
        PWindow window = new PWindow("name", 100, 100);
        PContext context = new PContext(100, 100);
        PStage stage = new PStage(100, 100);
        context.changeStage(stage);
        window.addContext(context);

        PFixedTexturedDrawable drawable = new PFixedTexturedDrawable(0, 0, 200, 200);
        

        assertEquals(true, drawable.loadTexture("src/test/java/projet/graphic_engine/texture.png"));
        
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
