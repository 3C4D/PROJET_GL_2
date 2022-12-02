package projet.game;

import projet.physicEngine.*;
import projet.physicEngine.common.*;

import projet.graphic_engine.*;
import java.awt.Color;

/**
* Classe pour la création et le lancement du jeu
*/
public class Game implements IConfig{

   private MyWorldPong pongWorld;
   private MyKeyboardPong pongKeyboard;
   private MyWorldSPP sppWorld;
   private MyKeyboardSPP sppKeyboard;

   private PWindow window;
   private PContext context;


   /**
   * Créateur d'un jeu
   */
   public Game(){
     spp();
   }

   /**
   * Permet de créer une partie de PONG
   */
   public void pong(){
     //Définition de la fenetre
     this.window = new PWindow("Pong", WIDTH,HEIGHT+20);
     this.window.setResizable(true);

     this.context = new PContext(WIDTH+50, HEIGHT+50);
     this.window.addContext(this.context);

     //Création du monde du jeu
     this.pongWorld = new MyWorldPong(WIDTH, HEIGHT);

     this.context.changeStage(this.pongWorld.getStage());
     this.context.setBackground(Color.BLACK);

     //Initialisation du contrôle clavier
     pongKeyboard = new MyKeyboardPong(pongWorld);
     this.window.addKeyListener(pongKeyboard);
   }

   /**
   * Permet de créer une partie de Super Pastis Pong
   */
   public void spp(){
     //Définition de la fenetre
     this.window = new PWindow("Super Pastis Pong", WIDTH,HEIGHT+20);
     this.window.setResizable(false);

     this.context = new PContext(WIDTH+50, HEIGHT+50);
     this.window.addContext(this.context);

     //Création du monde du jeu
     this.sppWorld = new MyWorldSPP(WIDTH, HEIGHT);

     this.context.changeStage(this.sppWorld.getStage());

     //Initialisation du contrôle clavier
     sppKeyboard = new MyKeyboardSPP(sppWorld);
     this.window.addKeyListener(sppKeyboard);
   }


   /**
   * Boucle principale du jeu de pong
   */
   public void launchPong(){
     while(true) {
       this.pongWorld.process(DELTA_T);
       this.context.repaint();
       try {
             Thread.sleep((long)DELTA_T);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }

     }
   }


   /**
   * Boucle principale du jeu de SPP
   */
   public void launchSPP(){
     while(true) {
       this.sppWorld.process(DELTA_T);
       this.context.repaint();
       try {
             Thread.sleep((long)DELTA_T);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }

     }
   }
 }
