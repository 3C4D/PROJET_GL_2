package projet.graphic_engine;

import projet.graphic_engine.drawable.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class testPStage {

    @Test
    public void testConstructor() throws Exception {
        PStage stage = new PStage(50, 100);

        assertEquals(50, stage.getWidth());
        assertEquals(100, stage.getHeight());
    }

    @Test
    public void testSetSize() throws Exception {
        PStage stage = new PStage(50, 100);
        stage.setSize(10, 10);

        assertEquals(10, stage.getWidth());
        assertEquals(10, stage.getHeight());
    }

    @Test
    public void testAdd() throws Exception {
        PStage stage = new PStage(50, 100);


        stage.add(new PIDrawable() {
            @Override
            public void paint(Graphics g) {
                // TODO Auto-generated method stub

            }
            @Override
            public void setPosition(int x, int y){
              // nothing to do
            }
        });

        assertEquals(1, stage.getListToPaint().size());
    }



}
