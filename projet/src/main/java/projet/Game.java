package projet;

import projet.graphic_engine.*;
import projet.physicEngine.*;

import projet.graphic_engine.*;

public class Game {

   private int WIDTH = 500;
   private int HEIGHT = 500;

   private float DELTA_T = 1000f/60f;

   private MyWorld world;

   private PWindow window;
   private PContext context;


   public Game(){
     this.window = new PWindow("Exemple", WIDTH, HEIGHT);

     this.context = new PContext(WIDTH, HEIGHT);
     this.window.addContext(this.context);

     world = new MyWorld(WIDTH, HEIGHT);
     this.world = world;

     this.context.changeStage(this.world.getStage());
   }


   /**
   * Boucle principale du jeu
   */
   public void launch(){
     while(true) {
       this.world.process(DELTA_T);
       this.context.repaint();
       try {
             Thread.sleep((long)DELTA_T);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }

     }
   }

 }
