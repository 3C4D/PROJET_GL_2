package projet.game;
import java.util.*;
import projet.physicEngine.common.*;
import projet.physicEngine.*;
import projet.game.Ball;
import projet.game.PastisRacket;
import java.awt.Color;
import java.lang.Thread;
import java.lang.Math;
import java.time.Clock;
public class AISpp extends PastisRacket implements IConfig{
    float AIracket; // racket's position
    private int levelOfDifficulty; //0 to 100
    int step;
    Point racket;
    int type;
    int side;
    long tempsLancement;
    AISpp(Point position, int type, Color color, Point tableOrigin, Zone zone){
        super(position, type, color, tableOrigin,zone);
        this.side=side;
        long tempsactuel=System.currentTimeMillis();
        this.tempsLancement=tempsactuel;
    }




    /** Method which return if the ball is one the line between pos1 and pos2
     * @param pos1
     * @param pos2
     * @param ball
     * @param centre
     * @return boolean
     */

     public boolean line(Point pos1,Point pos2,Point ball,Point centre){
        if(pos1.getX()>=centre.getX()){
            if(pos1.getY()>=centre.getY()){
                if((ball.getX()>pos1.getX() && ball.getY()>pos2.getY()) || ball.getX()>pos2.getX() && ball.getY()>pos1.getY()){
                    return true;
               }
            }

            else{
            if((ball.getX()>pos1.getX() && ball.getY()<pos2.getY()) || ball.getX()>pos2.getX() && ball.getY()<pos1.getY()){
                return true;
            }
       }

        }

    else{
        if(pos1.getY()>=centre.getY()){
            if((ball.getX()<pos1.getX() && ball.getY()>pos2.getY()) || ball.getX()<pos2.getX() && ball.getY()>pos1.getY()){
                return true;
            }
        }
       else{
       if((ball.getX()<pos1.getX() && ball.getY()<pos2.getY()) || ball.getX()<pos2.getX() && ball.getY()<pos1.getY()){
        return true;
       }
    }
    }
     return false;}

     /**
      * @param ball
      function which move the racket if the ball is going to its zone and the racket is not on the trajectory of the ball
      the AI decide to moveUp or moveDown
      @return String (UP or DOWN or STOP)
      */
     public String pastisRacketDecision(Ball ball){

        if(System.currentTimeMillis()>this.tempsLancement+30){
            this.tempsLancement=System.currentTimeMillis();

        Point p;
        Vector2D v=new Vector2D(ball.getBody().getVelocity().getCoordX(),ball.getBody().getVelocity().getCoordY());
        v.setCoordX(v.getCoordX()*100);
        v.setCoordY(v.getCoordY()*100);

        boolean solution=false;
        p= Point.copy(ball.getBody().getCenter());
        Point pos1=this.getZone().getMinPoint();
        Point pos2=this.getZone().getMaxPoint();
        pos1.setX(pos1.getX()+400);
        pos1.setY(pos1.getY()+400);
        pos2.setX(pos2.getX()+400);
        pos2.setY(pos2.getY()+400);
        float distanceInitial=0;
        float distanceVecteur=0;
        distanceInitial=ball.getBody().getCenter().distance(pos1);
        distanceInitial+=ball.getBody().getCenter().distance(pos2);

        p.setX(p.getX()+v.getCoordX());
        p.setY(p.getY()+v.getCoordY());
        distanceVecteur=p.distance(pos1);
        distanceVecteur+=p.distance(pos2);
        ////System.out.println("la balle : ");
            //System.out.println(p.getX());
            //System.out.println(p.getY());
        if(distanceVecteur<distanceInitial){
            //System.out.println("distanceVecteur:"+distanceVecteur);
            //System.out.println("distanceInitial:"+distanceInitial);
                //System.out.println(p.getX());
        int distanceParcouru=0;
        int iteration=0;
            while(!line(pos1,pos2,p,this.getTableOrigin()) && iteration<100){
                p.setX(p.getX()+v.getCoordX());
                p.setY(p.getY()+v.getCoordY());
                iteration+=1;
            }
            if(iteration<1000){
                //System.out.println("on se deplace");
                if(this.getBody().getCenter().getY()>0){
                    if(this.getBody().getCenter().getX()>p.getX()){
                       // //System.out.println("on descends");
                        this.moveDown();
                        return "DOWN";
                    }
                    else if(this.getBody().getCenter().getX()<p.getX()){
                       // //System.out.println("on monte");
                        this.moveUp();
                        return "UP";
                    }
                }
                else{
                    if(this.getBody().getCenter().getX()>p.getX()){
                        this.moveUp();
                        return "UP";
                    }
                    else if(this.getBody().getCenter().getX()<p.getX()){
                        this.moveDown();
                        return "DOWN";
                    }
                }
            }
                }}
                return "STOP";

            }

}
