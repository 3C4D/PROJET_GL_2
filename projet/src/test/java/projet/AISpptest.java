package projet.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.*;
import projet.kernel.PEntity;
import projet.physicEngine.common.Point;
import projet.physicEngine.Body.BodyType;
import projet.physicEngine.*;
import java.awt.*;
import java.util.Vector;
import projet.physicEngine.CollisionListener;
import projet.physicEngine.Body.BodyType;
import projet.physicEngine.*;
import projet.physicEngine.common.*;
import projet.physicEngine.Shape.ShapeType;
import projet.game.Ball;
import projet.sound_engine.SoundPlayer;

import java.util.ArrayList;
import java.lang.Math;

public class AISpptest {
    public static float TABLE_SIZE = 800;
    public static float RACKET_WIDTH = 50;
    public static float RACKET_HEIGHT = 25;
    public static float BALL_SIZE = 20f;

    @Test
    public void testUpDown() throws Exception {

        float angle = (float)(2*(3)*Math.PI/8);
        Point position = new Point((float)((TABLE_SIZE/2f)*Math.cos(angle) + RACKET_WIDTH/2f), (float)((TABLE_SIZE/2f)*Math.sin(angle) + RACKET_HEIGHT/2f));
        AISpp test1=new AISpp(position,
                       MyEntity.RACKET,
                       Color.BLUE,
                       new Point(800/2f,800/2f),
                       new Zone((float)Math.PI*2*(3+1)/8, (float)Math.PI*2*3/8, angle ));

        Ball ball = new Ball(new Point(800/2f,800/2f), BALL_SIZE, Color.BLUE);
        Thread.sleep(300);
        ball.getBody().setVelocity(new Vector2D(-2f, -1f));
        // we verify that we move down
        assert(test1.pastisRacketDecision(ball)=="DOWN");

        angle = (float)(2*(5)*Math.PI/8);
        position = new Point((float)((TABLE_SIZE/2f)*Math.cos(angle) + RACKET_WIDTH/2f), (float)((TABLE_SIZE/2f)*Math.sin(angle) + RACKET_HEIGHT/2f));
        test1=new AISpp(position,
                       MyEntity.RACKET,
                       Color.BLUE,
                       new Point(800/2f,800/2f),
                       new Zone((float)Math.PI*2*(5+1)/8, (float)Math.PI*2*5/8, angle ));
        ball = new Ball(new Point(800/2f,800/2f), BALL_SIZE, Color.BLUE);
        Thread.sleep(300);
            // we verify that we move up
        ball.getBody().setVelocity(new Vector2D(0.2f, -2f));
       assert(test1.pastisRacketDecision(ball)=="UP");




    }}
