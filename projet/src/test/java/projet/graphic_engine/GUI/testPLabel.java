package projet.graphic_engine.GUI;
import projet.graphic_engine.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;



public class testPLabel {
        
    @Test
    public void testConstructor() throws Exception {
        PLabel label = new PLabel("test");
        assertEquals("test", label.getText());
    }

    @Test
    public void testPaint() throws Exception {
        PWindow window = new PWindow("name", 100, 100);
        PContext context = new PContext(100, 100);
        PStage stage = new PStage(100, 100);
        context.changeStage(stage);
        window.addContext(context);

        PLabel label = new PLabel("test");
        label.setBounds(0, 0, 100, 100);

        
    }
}