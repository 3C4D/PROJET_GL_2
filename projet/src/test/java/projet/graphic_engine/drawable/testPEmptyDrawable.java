package projet.graphic_engine.drawable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class testPEmptyDrawable {
    
    @Test
    public void testConstructor() throws Exception {
        PEmptyDrawable drawable = new PEmptyDrawable();

        assertEquals(true, drawable != null);
    }

}
