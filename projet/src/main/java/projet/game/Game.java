package projet.game;

import projet.physicEngine.*;
import projet.physicEngine.common.*;

import projet.graphic_engine.*;

/**
* Classe pour la création et le lancement du jeu
*/
public class Game implements IConfig{

   private MyWorldPong world;

   private PWindow window;
   private PContext context;
   private MyKeyboardPong keyboard;


   /**
   * Créateur d'un jeu
   */
   public Game(){
     //Définition de la fenetre
     this.window = new PWindow("Super Pastis Pong", WIDTH,HEIGHT+20);
     this.window.setResizable(false);

     this.context = new PContext(WIDTH+50, HEIGHT+50);
     this.window.addContext(this.context);

     //Création du monde du jeu
     this.world = new MyWorldPong(WIDTH, HEIGHT);

     this.context.changeStage(this.world.getStage());

     //Initialisation du contrôle clavier
     keyboard = new MyKeyboardPong(world);
     this.window.addKeyListener(keyboard);


     // //Définition de la fenetre
     // this.window = new PWindow("Pong classique", WIDTH,HEIGHT+20);
     // this.window.setResizable(false);
     //
     // this.context = new PContext(WIDTH+50, HEIGHT+50);
     // this.window.addContext(this.context);
     //
     // //Création du monde du jeu
     // this.world = new MyWorld(WIDTH, HEIGHT);
     //
     // this.context.changeStage(this.world.getStage());
     //
     // //Initialisation du contrôle clavier
     // keyboard = new MyKeyboard(world);
     // this.window.addKeyListener(keyboard);
     // this.context.setBackground(Color.BLACK);
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
