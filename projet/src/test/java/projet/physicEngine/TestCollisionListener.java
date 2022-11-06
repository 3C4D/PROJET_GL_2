package projet.physicEngine;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import projet.physicEngine.common.Point;
import projet.physicEngine.common.Vector2D;
import projet.physicEngine.Body.BodyType;
import projet.physicEngine.*;


public class TestCollisionListener{

  @Test
  void testAreInIntersectionForCircles(){
    CollisionListener cl = new CollisionListener();

    CircleShape cs = new CircleShape(new Point(0,0), 1f);
    // cs2 dans cs
    CircleShape cs2 = new CircleShape(new Point(0,0), 3f);
    // cs3 et cs en intersection
    CircleShape cs3 = new CircleShape(new Point(1,0), 0.5f);
    // cs4 et cs bordure en commun
    CircleShape cs4 = new CircleShape(new Point(2,0), 1f);
    // cs5 et cs aucunes intersections
    CircleShape cs5 = new CircleShape(new Point(10,0), 1f);

    Body f = new Body(cs);
    Body f2 = new Body(cs2);
    Body f3 = new Body(cs3);
    Body f4 = new Body(cs4);
    Body f5 = new Body(cs5);

    assertThat(cl.areInCollision(f, f2)).isNotNull();
    assertThat(cl.areInCollision(f, f3)).isNotNull();
    assertThat(cl.areInCollision(f, f4)).isNotNull();
    assertThat(cl.areInCollision(f, f5)).isNull();

  }

  @Test
  void testAreInIntersectionForCiclePolygon(){
    CollisionListener cl = new CollisionListener();

    CircleShape cs = new CircleShape(new Point(0,0), 5f);
    CircleShape cs2 = new CircleShape(new Point(10,3), 1f);

    Point[] vertex = new Point[4];
    vertex[0] = new Point(1,2);
    vertex[1] = new Point(3,3);
    vertex[2] = new Point(3,6);
    vertex[3] = new Point(0,5);

    Point[] vertex2 = new Point[7];
    vertex2[0] = new Point(1,7);
    vertex2[1] = new Point(5,7);
    vertex2[2] = new Point(6,8);
    vertex2[3] = new Point(6,9);
    vertex2[4] = new Point(5,10);
    vertex2[5] = new Point(2,10);
    vertex2[6] = new Point(1,9);

    Point[] vertex3 = new Point[3];
    vertex3[0] = new Point(8,0);
    vertex3[1] = new Point(12,0);
    vertex3[2] = new Point(8,5);


    PolygonShape ps = new PolygonShape(vertex, 4);
    PolygonShape ps2 = new PolygonShape(vertex2, 7);
    PolygonShape ps3 = PolygonShape.createRectShape(1f, 1f);
    PolygonShape ps4 = new PolygonShape(vertex3, 3);

    Body fc = new Body(cs);
    Body fc2 = new Body(cs2);
    Body fp = new Body(ps);
    Body fp2 = new Body(ps2);
    Body fp3 = new Body(ps3);
    Body fp4 = new Body(ps4);

    //Un sommet de ps dans cs
    assertThat(cl.areInCollision(fc, fp)).isNotNull();
    //Pas d'intersection
    assertThat(cl.areInCollision(fc, fp2)).isNull();
    //Polygone dans cercle
    assertThat(cl.areInCollision(fc, fp3)).isNotNull();

    //Pas de sommets dans le cercle, mais un segment traverse le cercle
    assertThat(cl.areInCollision(fc2, fp4)).isNotNull();

  }

  @Test
  void testAreInIntersectionForPolygons(){
    CollisionListener cl = new CollisionListener();

    Point[] vertex = new Point[4];
    vertex[0] = new Point(4,2);
    vertex[1] = new Point(6,2);
    vertex[2] = new Point(5,8);
    vertex[3] = new Point(1,5);

    Point[] vertex2 = new Point[3];
    vertex2[0] = new Point(6,2);
    vertex2[1] = new Point(8,5);
    vertex2[2] = new Point(5,8);

    Point[] vertex3 = new Point[4];
    vertex3[0] = new Point(3,1);
    vertex3[1] = new Point(3,5);
    vertex3[2] = new Point(1,2);
    vertex3[3] = new Point(2,1);

    Point[] vertex4 = new Point[4];
    vertex4[0] = new Point(3,4);
    vertex4[1] = new Point(5,4);
    vertex4[2] = new Point(5,6);
    vertex4[3] = new Point(3,6);


    PolygonShape ps = new PolygonShape(vertex, 4);
    PolygonShape ps2 = new PolygonShape(vertex2, 3);
    PolygonShape ps3 = new PolygonShape(vertex3, 4);
    PolygonShape ps4 = new PolygonShape(vertex4, 4);

    Body fp = new Body(ps);
    Body fp2 = new Body(ps2);
    Body fp3 = new Body(ps3);
    Body fp4 = new Body(ps4);

    assertThat(cl.areInCollision(fp,fp3)).isNotNull();
    assertThat(cl.areInCollision(fp,fp2)).isNotNull();
    assertThat(cl.areInCollision(fp,fp4)).isNotNull();
    assertThat(cl.areInCollision(fp3,fp2)).isNull();
  }

  @Test
  void testOutlineCollision(){
    CollisionListener cl = new CollisionListener();
    PolygonShape outline = PolygonShape.createRectShape(500, 500, new Point(500f/2f, 500f/2f));

    //Intersection avec côté 1
    Point[] vertex = new Point[3];
    vertex[0] = new Point(200,0);
    vertex[1] = new Point(250,50);
    vertex[2] = new Point(150, 100);

    PolygonShape ps1 = new PolygonShape(vertex, 3);


    //Intersection avec côté 2
    Point[] vertex2 = new Point[4];
    vertex2[0] = new Point(400,100);
    vertex2[1] = new Point(550,100);
    vertex2[2] = new Point(550, 275);
    vertex2[3] = new Point(400, 275);

    PolygonShape ps2 = new PolygonShape(vertex2, 4);

    //Intersection avec côté 3
    CircleShape cs3 = new CircleShape(new Point(200,450), 50);

    //Intersection avec côté 4
    Point[] vertex3 = new Point[4];
    vertex3[0] = new Point(0,50);
    vertex3[1] = new Point(200,50);
    vertex3[2] = new Point(200, 150);
    vertex3[3] = new Point(0, 150);

    PolygonShape ps4 = new PolygonShape(vertex3, 4);
    CircleShape cs4 = new CircleShape(new Point(-10, 400), 50f);

    //Pas d'intersection
    Point[] vertex4 = new Point[4];
    vertex4[0] = new Point(200,200);
    vertex4[1] = new Point(250,200);
    vertex4[2] = new Point(250, 250);
    vertex4[3] = new Point(200, 250);

    PolygonShape ps = new PolygonShape(vertex4, 4);
    CircleShape cs = new CircleShape(new Point(200,200), 15f);


    assertEquals(cl.outlineCollision(ps1, outline), 0);
    assertEquals(cl.outlineCollision(ps2, outline), 1);
    assertEquals(cl.outlineCollision(cs3, outline), 2);
    assertEquals(cl.outlineCollision(ps4, outline), 3);
    assertEquals(cl.outlineCollision(cs4, outline), 3);
    assertEquals(cl.outlineCollision(ps, outline), -1);
    assertEquals(cl.outlineCollision(cs, outline), -1);
  }
}
