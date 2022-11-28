package projet.game;

import java.util.*;
import projet.physicEngine.common.*;
import projet.physicEngine.*;

import java.lang.Math;

public class AIpong extends RacketPong implements IConfig{
    private int levelOfDifficulty; //0 to 10
    int step;
    int windowSize;
    Point racket;
    int type;
    int side;
    AIpong(int windowSize,Point racket,int type,int side,int levelOfDifficulty){
        super(racket,type, MyWorldPong.RACKET_WIDTH, MyWorldPong.RACKET_HEIGHT);
        this.windowSize=windowSize;
        this.side=side;
        this.levelOfDifficulty=levelOfDifficulty;
    }



    /** Methode which make move or stop the racket according to the position of the ball
     * @param ball   (Ball)
     *
     */
    public void racketDecision(Ball ball){

        //temps de réaction:
        Point p;
        int n;
        Vector2D v=new Vector2D(ball.getBody().getVelocity().getCoordX(),ball.getBody().getVelocity().getCoordY());
        boolean solution=false;
        p= Point.copy(ball.getBody().getCenter());
        if(levelOfDifficulty==3
        ||
        (this.side==1 && p.getX()>240 && this.levelOfDifficulty==1)||(this.levelOfDifficulty==1 &&this.side==0 && p.getX()<240)
        ||(this.side==1 && p.getX()>200 && this.levelOfDifficulty==2)||(this.levelOfDifficulty==2 &&this.side==0 && p.getX()<300)
        ){
        if((this.side==0 && v.getCoordX()<0)||(this.side==1 && v.getCoordX()>0)){
            while(((this.side==0 && p.getX()>20)||(this.side==1 && p.getX()<this.windowSize-20)&&this.getBody().getCenter().getX()-p.getX()<20 && p.getX()-this.getBody().getCenter().getX()<20  )){
                if((p.getY()<0 || p.getY()>this.windowSize)){
                    v.setCoordY(v.getCoordY()*-1);
                }
                p.setX(p.getX()+v.getCoordX());
                p.setY(p.getY()+v.getCoordY());
            }
            if(p.getY()>0 && p.getY()<this.windowSize){
                solution=true;
            }

            if(solution){
                n = (int)(Math.random() * 100);
                if(this.getBody().getCenter().getY()<this.windowSize-100 && this.getBody().getCenter().getY()<p.getY()){
                    if(this.levelOfDifficulty!=4){
                        if(n>10){
                            this.moveDown();
                        }
                        else{
                            this.moveUp();
                        }

                    }

                   // this.moveDown();

                }
                else if( this.getBody().getCenter().getY()>100 && this.getBody().getCenter().getY()>p.getY()){
                    if(this.levelOfDifficulty!=4){
                        if(n>10){
                            this.moveUp();
                        }
                        else{
                            this.moveDown();
                        }

                    }
                   // this.moveUp();
                }
                else{
                    this.stop();
                }
            }
            else{
                this.stop();
            }
    }
    else{
        if(levelOfDifficulty==3){
           if( this.getBody().getCenter().getY()<this.windowSize/2){
                    this.moveDown();

                }
            else if(this.getBody().getCenter().getY()>this.windowSize/2)
                this.moveUp();
            }
            else{
                this.stop();
            }
        }

    }}

}
