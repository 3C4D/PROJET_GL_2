package projet.game;

import projet.physicEngine.*;
import projet.physicEngine.common.*;

import projet.graphic_engine.*;

public class Game implements IConfig{

   private MyWorld world;

   private PWindow window;
   private PContext context;
   private MyKeyboard keyboard;



   public Game(){
     this.window = new PWindow("Pong classique", WIDTH,HEIGHT+20);
     this.window.setResizable(false);

     this.context = new PContext(WIDTH+50, HEIGHT+50);
     this.window.addContext(this.context);

     this.world = new MyWorld(WIDTH, HEIGHT);

     this.context.changeStage(this.world.getStage());

     keyboard = new MyKeyboard(world);
     this.window.addKeyListener(keyboard);
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
