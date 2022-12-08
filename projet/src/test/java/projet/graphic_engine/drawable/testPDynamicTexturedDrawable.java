package projet.graphic_engine.drawable;
import projet.graphic_engine.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class testPDynamicTexturedDrawable {
    @Test
    public void testConstructor() throws Exception {
        PDynamicTexturedDrawable drawable = new PDynamicTexturedDrawable(10, 10, 20, 40, 100/60);
        
        assertEquals(true, drawable != null);
    }

    @Test
    public void testLoadTexture() throws Exception {
        PDynamicTexturedDrawable drawable = new PDynamicTexturedDrawable(10, 10, 20, 40, 100/60);
        drawable.loadTextures(new String[]{"src/test/java/projet/graphic_engine/texture.png", "src/test/java/projet/graphic_engine/texture2.png"});
        
        assertEquals(true, drawable.getTextures() != null);
    }

    @Test
    public void testPaint() throws Exception {

        int w = 400;
        int h = 400;

        PWindow window = new PWindow("name", w, h);
        PContext context = new PContext(w, h);
        PStage stage = new PStage(w, h);
        context.changeStage(stage);
        window.addContext(context);

        PDynamicTexturedDrawable drawable = new PDynamicTexturedDrawable(0, 0, w, h, 100/60);
        assertEquals(true, drawable.loadTextures(new String[]{"src/test/java/projet/graphic_engine/texture.png", "src/test/java/projet/graphic_engine/texture2.png"}));
        
        for(int i = 0; i < 1000; i++) {

            if(i%10==0) drawable.next(1000/60);
            
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
