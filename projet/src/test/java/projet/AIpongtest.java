package projet;

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
    public void testRaquette() throws Exception {
        AIpong aa=new AIpong(4,500);
        Ball ball=new Ball(new Point(8,0),4);
        ball.getBody().getVelocity().setCoordX(-2);
        ball.getBody().getVelocity().setCoordY(1);
        Vector<Ball> balls=new Vector<Ball>();
        balls.add(0,ball);
        balls.size();
        ball=new Ball(new Point(5,2),4);
        ball.getBody().getVelocity().setCoordX(-1);
        ball.getBody().getVelocity().setCoordY(1);
        //balls.add(1,ball);
        
        System.out.println("decision: "+aa.racketDecision(balls));
	}


 
    }

    


