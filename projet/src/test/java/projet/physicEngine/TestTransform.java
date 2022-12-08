package projet.physicEngine;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.lang.Math;

import projet.physicEngine.common.*;
import projet.physicEngine.*;



public class TestTransform{

  @Test
  public void testCircleRotation(){
    CircleShape cs1 = new CircleShape(new Point(4,5), 2f);
    CircleShape cs2 = new CircleShape(new Point(4,5), 2f);
    CircleShape cs3 = new CircleShape(new Point(4,5), 2f);

    Point c1 = new Point(7, 2);
    Point c2 = new Point(3, 6);
    Point c3 = Point.copy(cs3.getCenter());

    Transform.rotationCircle(cs1, c1, (float)Math.PI/2);
    Transform.rotationCircle(cs2, c2, (float)Math.PI/2);
    Transform.rotationCircle(cs3, c3, (float)Math.PI/2);

    assertEquals(cs1.getCenter(), new Point(10, 5));
    assertEquals(cs2.getCenter(), new Point(2,5));
    assertEquals(cs3.getCenter(), c3);
  }

  @Test
  public void testPolygonRotation(){
    Point[] vertex = new Point[4];
    vertex[0] = new Point(3,3);
    vertex[1] = new Point(4,4);
    vertex[2] = new Point(4,6);
    vertex[3] = new Point(1,5);

    PolygonShape ps = new PolygonShape(vertex, 4);

    Point c1 = new Point(7,2);

    Transform.rotationPolygon(ps, c1, (float)Math.PI/2);

    assertEquals(ps.getVertex(0), new Point(8,6));
    assertEquals(ps.getVertex(1), new Point(9,5));
    assertEquals(ps.getVertex(2), new Point(11,5));
    assertEquals(ps.getVertex(3), new Point(10,8));
  }


}
