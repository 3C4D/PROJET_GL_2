package projet;

import projet.physicEngine.*;
import projet.physicEngine.common.*;

import projet.graphic_engine.*;

public class Game {

   private int WIDTH = 500;
   private int HEIGHT = 500;

   private float DELTA_T = 1000f/600f;

   private MyWorld world;

   private PWindow window;
   private PContext context;


   public Game(){
     this.window = new PWindow("Exemple", WIDTH, HEIGHT);
     this.window.setResizable(false);

     this.context = new PContext(WIDTH, HEIGHT);
     this.window.addContext(this.context);

     this.world = new MyWorld(WIDTH, HEIGHT);

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
