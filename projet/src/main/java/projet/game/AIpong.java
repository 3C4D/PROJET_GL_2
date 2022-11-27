package projet;
import java.util.*;
import projet.physicEngine.common.*;
import projet.physicEngine.*;
import projet.game.Ball;
import java.lang.Math;

public class AIpong{
    int AIracket; // racket's position
    private int levelOfDifficulty; //0 to 100
    int step;
    int windowSize;
    AIpong(int AIracket,int windowSize){
        this.AIracket=AIracket;
        this.windowSize=windowSize;
    }


    
    /** Methode which decide if the racket must go UP or DOWN or STAND according to a Vector of Ball
     * @param balls  (Vector of Ball )
     * @return int (1 for UP, -1 for DOWN, 0 for STAND)
     */
    public int racketDecision(Vector<Ball> balls){
        Point p;
        Vector2D v;
        int distanceMin=-1;
        int indiceMin=-1;
        int distance=0;
        int size=balls.size();
        if (size==0) return 0;
        for(int i=0;i<size;i++){
            distance=0;
            p=balls.get(i).getBody().getCenter();
            v=balls.get(i).getBody().getVelocity();
            while(p.getX()>0 && p.getY()>0 && p.getY()<windowSize){       
                p.setX(p.getX()+v.getCoordX());
                p.setY(p.getY()+v.getCoordY());
                distance+=1;
            }
            if(p.getX()<=0 && (distanceMin==-1 || distance<distanceMin)){
                indiceMin=i;
                distanceMin=distance;
            }
    

        }


        if(indiceMin!=-1){
            if(this.AIracket<balls.get(indiceMin).getBody().getCenter().getY()){
                return 1;

            }
            else if(this.AIracket>balls.get(indiceMin).getBody().getCenter().getY()){
                return -1;
            }
            else{
                return 0;
            }
        }
        else{
            return 0;
        }
    }

    
}