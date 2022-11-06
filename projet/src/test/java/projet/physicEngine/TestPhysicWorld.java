package projet.physicEngine;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import projet.physicEngine.common.Point;
import projet.physicEngine.common.Vector2D;
import projet.physicEngine.Body.BodyType;
import projet.physicEngine.*;


public class TestPhysicWorld{

  @Test
  void testListenTo(){
    PhysicWorld pw = new PhysicWorld(1000,1000);

    CircleShape cs = new CircleShape(new Point(0,0), 5f);

    Point[] vertex1 = new Point[4];
    vertex1[0] = new Point(4,2);
    vertex1[1] = new Point(6,2);
    vertex1[2] = new Point(5,8);
    vertex1[3] = new Point(1,5);

    Point[] vertex3 = new Point[4];
    vertex3[0] = new Point(3,1);
    vertex3[1] = new Point(3,5);
    vertex3[2] = new Point(1,2);
    vertex3[3] = new Point(2,1);

    Point[] vertex = new Point[4];
    vertex[0] = new Point(1,2);
    vertex[1] = new Point(3,3);
    vertex[2] = new Point(3,6);
    vertex[3] = new Point(0,5);

    PolygonShape ps = new PolygonShape(vertex, 4);
    PolygonShape ps1 = new PolygonShape(vertex1, 4);
    PolygonShape ps3 = new PolygonShape(vertex3, 4);
    Body fp = new Body(new Point(1111,1111), ps, BodyType.DYNAMIC);
    Body fp1 = new Body(new Point(1111,1111), ps1, BodyType.DYNAMIC);
    Body fp3 = new Body(new Point(1111,1111), ps3, BodyType.DYNAMIC);
    Body fc = new Body(new Point(1111,1111), cs, BodyType.DYNAMIC);
    fp.setVelocity(new Vector2D(new Point(0,0), 1,1));
    fp1.setVelocity(new Vector2D(new Point(0,0), 1,1));
    fp3.setVelocity(new Vector2D(new Point(0,0), 1,1));
    fc.setVelocity(new Vector2D(new Point(0,0), 1,1));

    pw.addBody(fp);
    pw.addBody(fp1);
    pw.addBody(fp3);
    pw.addBody(fc);

    for(int i = 0; i<10; i++){
      pw.launchCollisionListener();
    }


  }
}
