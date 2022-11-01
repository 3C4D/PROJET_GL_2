package projet.physicEngine;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import projet.physicEngine.common.Point;
import projet.physicEngine.*;
import projet.physicEngine.PolygonShape;


public class TestShape{

  /*******  Shape  *******/
  @Test
  void testCreateShape(){
    Shape cs = new CircleShape(new Point(0,0), 0.5f);

    Point[] vertex = new Point[4];
    vertex[0] = new Point(0,0);
    vertex[1] = new Point(3,0);
    vertex[2] = new Point(3,5);
    vertex[3] = new Point(0,5);

    Shape ps = new PolygonShape(vertex, 4);
    Shape ps2 = PolygonShape.createRectShape(2f, 3f);
    Shape ps3 = PolygonShape.createRectShape(2f, 3f, 0.5f);

    assertThat(cs).isNotNull();
    assertThat(ps).isNotNull();
    assertThat(ps2).isNotNull();
    assertThat(ps3).isNotNull();
    assertEquals(cs.getType(), PolygonShape.ShapeType.CIRCLE);
    assertEquals(ps.getType(), PolygonShape.ShapeType.POLYGON);
    assertEquals(ps2.getType(), PolygonShape.ShapeType.POLYGON);
    assertEquals(ps3.getType(), PolygonShape.ShapeType.POLYGON);
  }

  @Test
  void testIsConvex(){
    Point[] vertex = new Point[4];
    vertex[0] = new Point(0,0);
    vertex[1] = new Point(3,0);
    vertex[2] = new Point(3,5);
    vertex[3] = new Point(0,5);

    assertEquals(PolygonShape.isConvex(vertex, 4), true);

    vertex[0] = new Point(0,0);
    vertex[1] = new Point(0,0);
    vertex[2] = new Point(0,0);
    vertex[3] = new Point(0,0);

    assertEquals(PolygonShape.isConvex(vertex, 4), true);

    Point[] vertex2 = new Point[5];
    vertex2[0] = new Point(0,0);
    vertex2[1] = new Point(3,0);
    vertex2[2] = new Point(3,5);
    vertex2[3] = new Point(2,3);
    vertex2[4] = new Point(0,5);

    assertEquals(PolygonShape.isConvex(vertex2, 5), false);
  }

  @Test
  void testIsInside(){
    CircleShape cs = new CircleShape(new Point(0,0),5f);

    Point[] vertex = new Point[4];
    vertex[0] = new Point(0,0);
    vertex[1] = new Point(3,0);
    vertex[2] = new Point(3,5);
    vertex[3] = new Point(0,5);

    PolygonShape ps = new PolygonShape(vertex, 4);

    try{
      assertEquals(ps.isInside(new Point(2, 2)), true);
      assertEquals(ps.isInside(new Point(3, 2)), true);
      assertEquals(ps.isInside(new Point(10, 2)), false);

      assertEquals(cs.isInside(new Point(3, 0)), true);
      assertEquals(cs.isInside(new Point(5, 0)), true);
      assertEquals(cs.isInside(new Point(10, 2)), false);
    }catch (Exception e) {
      e.printStackTrace();
    }

  }

}
