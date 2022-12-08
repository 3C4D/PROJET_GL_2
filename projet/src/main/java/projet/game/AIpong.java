package projet.game;

import java.util.*;
import projet.physicEngine.common.*;
import projet.physicEngine.*;

import java.lang.Math;

public class AIpong extends RacketPong implements IConfig{
    private int levelOfDifficulty; //0 to 10
    int step;
    Point racket;
    int type;
    int side;
    int width;
    int height;
    AIpong(int width,int height,Point racket,int type,int side,int levelOfDifficulty){
        super(racket,type, MyWorldPong.RACKET_WIDTH, MyWorldPong.RACKET_HEIGHT);
        this.width=width;
        this.height=height;
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
            while(((this.side==0 && p.getX()>20)||(this.side==1 && p.getX()<this.width-20)&&this.getBody().getCenter().getX()-p.getX()<20 && p.getX()-this.getBody().getCenter().getX()<20  )){
                if((p.getY()<0 || p.getY()>this.height)){
                    v.setCoordY(v.getCoordY()*-1);
                }
                p.setX(p.getX()+v.getCoordX());
                p.setY(p.getY()+v.getCoordY());
            }
            if(p.getY()>0 && p.getY()<this.height){
                solution=true;
            }

            if(solution){
                if(this.getBody().getCenter().getY()<this.height-100 && this.getBody().getCenter().getY()<p.getY()){
                    if(this.levelOfDifficulty!=4){
                            this.moveDown();


                    }

                   // this.moveDown();

                }
                else if( this.getBody().getCenter().getY()>100 && this.getBody().getCenter().getY()>p.getY()){
                    if(this.levelOfDifficulty!=4){
                            this.moveUp();


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
           if( this.getBody().getCenter().getY()<this.height/2){
                    this.moveDown();

                }
            else if(this.getBody().getCenter().getY()>this.height/2)
                this.moveUp();
            }
            else{
                this.stop();
            }
        }

    }}

}
