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

public class AIpongtest {

    @Test
    public void testUpDown() throws Exception {
        AIpong test1 = new AIpong(500,500,new Point(20f,250),1,0,3);
        Ball ball=new Ball(new Point(100,100),4,Color.blue);
        ball.getBody().getVelocity().setCoordX(-2);
        ball.getBody().getVelocity().setCoordY(-1);
        // it is stopeed
        assert(test1.getBody().getVelocity().getCoordY()==0);
        test1.racketDecision(ball);
        //it moves up
        assert(test1.getBody().getVelocity().getCoordY()<=0);

        ball.getBody().getVelocity().setCoordX(-2);
        ball.getBody().getVelocity().setCoordY(-1);

         //it moves down

         AIpong test2 = new AIpong(500,500,new Point(20f,250),1,0,3);
         ball=new Ball(new Point(250,250),4,Color.blue);
         ball.getBody().getVelocity().setCoordX(-2);
         ball.getBody().getVelocity().setCoordY(3);
         test2.racketDecision(ball);

            assert(test2.getBody().getVelocity().getCoordY()>=0);

	}



    }
