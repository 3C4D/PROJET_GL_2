package projet.graphic_engine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class testPContext {

    @Test
    public void testConstructor() throws Exception {
        
        PContext context = new PContext(50, 100);

        assertEquals(50, context.getWidth());
        assertEquals(100, context.getHeight());
	}


    @Test
    public void testChangeStage() throws Exception {
        PStage stage = new PStage(10, 100);
        PContext context = new PContext(50, 100);
        context.changeStage(stage);

        assertEquals(stage, context.getCurrentStage());
    
    }

    @Test
    public void testContextSizeChanged() throws Exception {
        PStage stage = new PStage(10, 10);
        
        PContext context = new PContext(50, 100);
        context.changeStage(stage);

        context.contextSizeChanged();

        assertEquals(50, stage.getWidth());
        assertEquals(100, stage.getHeight());
    }

    



}
