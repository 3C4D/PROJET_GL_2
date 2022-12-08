package projet.graphic_engine; 

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class testPWindow {

    @Test
    public void testWindowFullSreenMode() throws Exception {
        
        PWindow window = new PWindow("name", 100, 100);
        window.setResizable(false);
        window.setFullScreenMode();

        assertEquals(false, window.getWidth() == 100);
        assertEquals(false, window.getHeight() == 100);
	}

    @Test
    public void testWindowMode() throws Exception {
        
        PWindow window = new PWindow("name", 100, 100);
        window.setResizable(false);
        window.setWindowMode();

        assertEquals(true, window.getWidth() == 100);
        assertEquals(true, window.getHeight() == 100);
	}



}
