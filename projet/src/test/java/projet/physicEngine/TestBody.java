package projet.physicEngine;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import projet.physicEngine.common.Point;
import projet.physicEngine.*;
import projet.physicEngine.Body.*;


public class TestBody{

  @Test
  void testNewBody(){
    CircleShape cs = new CircleShape(new Point(0,0), 3f);

    Point[] vertex = new Point[4];
    vertex[0] = new Point(0,0);
    vertex[1] = new Point(3,0);
    vertex[2] = new Point(3,5);
    vertex[3] = new Point(0,5);

    PolygonShape ps = new PolygonShape(vertex, 4);
    PolygonShape ps2 = PolygonShape.createRectShape(2f, 3f, 0, new Point(1,1.5f));

    Body b1 = new Body(ps);
    Body b2 = new Body(new Point(1,1), ps, BodyType.STATIC);
    Body b3 = new Body(new Point(12,13), ps2, BodyType.DYNAMIC);
    Body b4 = new Body(new Point(1,1), cs, BodyType.STATIC);

    assertEquals(b1.getCenter(), new Point(1.5f, 2.5f));
    assertEquals(b2.getCenter(), new Point(1f, 1f));
    // Centre de b3 devient le barycentre du polygone
    assertEquals(b3.getCenter(), new Point(1f, 1.5f));
    assertEquals(b4.getCenter(), new Point(1f, 1f));

  }
}
