package projet.game;

import projet.physicEngine.*;
import projet.physicEngine.common.*;

import projet.graphic_engine.GUI.*;
import projet.graphic_engine.*;
import java.awt.Color;



import javax.swing.*;
import java.awt.event.*;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.lang.Exception;


/**
* Classe pour la création et le lancement du jeu
*/
public class Game implements IConfig{
   private volatile boolean pongEnd = true;
   private volatile boolean sppEnd = true;

   private MyWorldPong pongWorld;
   private MyKeyboardPong pongKeyboard;
   private MyWorldSPP sppWorld;
   private MyKeyboardSPP sppKeyboard;

   private PWindow window;
   private PContext context;
   private PPanel panel;

   private PContext pongContext;
   private PContext sppContext;

   private PWindow windowMenu;
   private PContext contextMenu;

   private PButton pongB;
   private PButton sppB;
   private PButton host;
   private PButton join;
   private PButton run;
   private PButton menu;

   private JButton usernameB;
   private JButton ipB;
   private JButton portB;
   private JButton playersNbB;

   private JTextArea playersNb;
   private JTextArea username;
   private JTextArea ip;
   private JTextArea port;

   private PLabel playersNbL;
   private PLabel usernameL;
   private PLabel ipL;
   private PLabel portL;

   private boolean boolIp = false, boolPort=false;

   /**
   * Créateur d'un jeu
   */
   public Game(){
     this.window = new PWindow("Menu", WIDTH, HEIGHT);
     this.context = new PContext(WIDTH, HEIGHT);
     PStage stage = new PStage(WIDTH, HEIGHT);

     pongB = new PButton("PONG");
     pongB.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
         pong();
       }
     });

     sppB = new PButton("SUPER PASTIS PONG");
     sppB.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
         //Création du monde du jeu
         sppWorld = new MyWorldSPP(WIDTH, HEIGHT);
         sppMenu();
       }
     });

     PGridLayout layout = new PGridLayout(2,1);
     layout.setVgap((int)(HEIGHT/10));
     stage.getGUI().setLayout(layout);

     stage.getGUI().add(pongB);
     stage.getGUI().add(sppB);

     this.context.changeStage(stage);
     this.window.addContext(this.context);
     this.window.setVisible(true);
     this.window.setFocusable(true);
   }


   /**
   * Permet de créer le menu du jeu
   */
   public void menu(){
     pongEnd = true;
     sppEnd = true;
     this.window.setTitle("Menu");
     PStage stage = new PStage(WIDTH, HEIGHT);

     pongB = new PButton("PONG");
     pongB.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
         pong();
       }
     });

     sppB = new PButton("SUPER PASTIS PONG");
     sppB.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
         //Création du monde du jeu
         sppWorld = new MyWorldSPP(WIDTH, HEIGHT);
         sppMenu();
       }
     });


     PGridLayout layout = new PGridLayout(2,1);
     layout.setVgap((int)(HEIGHT/10));
     stage.getGUI().setLayout(layout);

     stage.getGUI().add(pongB);
     stage.getGUI().add(sppB);

     this.context.setBackground(Color.WHITE);
     this.context.changeStage(stage);
     this.window.setVisible(true);
   }

   /**
   * Permet de créer une partie de PONG
   */
   public void pong(){
     pongEnd = false;
     //On met à jour le titre de la fenetre
     this.window.setTitle("PONG");
     //On empeche que la fenetre soit redimensionnée
     this.window.setResizable(false);

     this.menu = new PButton("Retour Menu");
     menu.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          menu();
        }
      });

     //Création du monde du jeu
     this.pongWorld = new MyWorldPong(WIDTH, HEIGHT);
     this.pongWorld.getStage().getGUI().add(menu);
     this.context.changeStage(this.pongWorld.getStage());
     this.context.setBackground(Color.BLACK);


     PGridLayout layout = new PGridLayout(2,3);
     layout.setVgap((int)(HEIGHT - HEIGHT/6));
     this.pongWorld.getStage().getGUI().setLayout(layout);

     //Initialisation du contrôle clavier
     pongKeyboard = new MyKeyboardPong(pongWorld);
     this.window.addKeyListener(pongKeyboard);

     this.window.setVisible(true);

     //On lance un thread avec le jeus
     new Thread(){
       public void run(){
         launchPong();
       }
     }.start();
   }

   /**
   * Permet de créer le menu du SPP
   */
   public void sppMenu(){
     sppEnd = false;
     PStage stage = new PStage(WIDTH, HEIGHT);
     this.window.setTitle("SUPER PASTIS PONG");

     host = new PButton("HEBERGER");
     host.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
         hostMenu();
       }
     });

      join = new PButton("REJOINDRE");
      join.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
         joinMenu();
       }
     });

     PGridLayout layout = new PGridLayout(2,1);
     layout.setVgap((int)(HEIGHT/10));
     stage.getGUI().setLayout(layout);

     stage.getGUI().add(host);
     stage.getGUI().add(join);

     this.context.changeStage(stage);
     this.window.setVisible(true);
   }

   /**
   * Permet de créer le menu d'hebergement d'une partie SPP
   */
   public void hostMenu(){
     PStage stage = new PStage(WIDTH, HEIGHT);
     this.window.setTitle("SUPER PASTIS PONG");

     this.usernameL = new PLabel("Pseudo : ");
     this.username = new JTextArea("username");
     this.usernameB = new PButton("Valider");
     usernameB.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          String user = username.getText();
          // JEAN --> recupère le username
          run.setEnabled(true);
        }
      });

     this.playersNbL = new PLabel("Nombre de joueurs :");
     this.playersNb = new JTextArea("4");
     this.playersNbB = new PButton("Valider");
     playersNbB.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          try{
            int players = Integer.parseInt(playersNb.getText());
            sppWorld.setNbPlayers(players);
          }catch(Exception ex){
            System.out.println("Dommage");
          }
        }
      });


    run = new PButton("LANCER LA PARTIE");
    run.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
         // JEAN --> lancement du serveur

         //Lancement de la partie spp;
         spp();
       }
     });
     run.setEnabled(false);

     PGridLayout layout = new PGridLayout(3,3);
     layout.setVgap((int)(HEIGHT/10));
     stage.getGUI().setLayout(layout);

     stage.getGUI().add(usernameL);
     stage.getGUI().add(username);
     stage.getGUI().add(usernameB);
     stage.getGUI().add(playersNbL);
     stage.getGUI().add(playersNb);
     stage.getGUI().add(playersNbB);
     stage.getGUI().add(run);

     this.context.changeStage(stage);
     this.window.setVisible(true);
   }

   /**
   * Permet de créer le menu pour rejoindre une partie SPP
   */
   public void joinMenu(){
     this.window.setTitle("SUPER PASTIS PONG");
     PStage stage = new PStage(WIDTH, HEIGHT);
     boolIp = false;
     boolPort = false;

     this.ipL = new PLabel("IP: ");
     this.ip = new JTextArea("IP");
     this.ipB = new PButton("Valider");
     ipB.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          String ipp = ip.getText();
          // JEAN --> Récupération (jsp si ça te dit mais t'as compris mdr) de l'IP
          boolIp = true;
          if(boolPort == true){
            run.setEnabled(true);
          }
        }
      });

      this.portL = new PLabel("Port: ");
      this.port = new JTextArea("Port");
      this.portB = new PButton("Valider");
      portB.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
           try{
             int portt = Integer.parseInt(port.getText());
             // JEAN --> Récupération du port (pas pas du cochon lol)
             boolPort = true;
             if(boolIp){
               run.setEnabled(true);
             }

           }catch (Exception ex) {
             System.out.println("Dommage");
           }
         }
       });


     run = new PButton("REJOINDRE LA PARTIE");
     run.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
         // JEAN --> Lancement du client etc
       }
     });
     run.setEnabled(false);

     PGridLayout layout = new PGridLayout(3,3);
     layout.setVgap((int)(HEIGHT/10));
     stage.getGUI().setLayout(layout);

     stage.getGUI().add(ipL);
     stage.getGUI().add(ip);
     stage.getGUI().add(ipB);
     stage.getGUI().add(portL);
     stage.getGUI().add(port);
     stage.getGUI().add(portB);
     stage.getGUI().add(run);

     this.context.changeStage(stage);
     this.window.setVisible(true);
   }

   /**
   * Permet de créer une partie de Super Pastis Pong
   */
   public void spp(){
     //On met à jour le titre de la fenetre
     this.window.setTitle("Super Pastis Pong");
     //On empeche que la fenetre soit redimensionnée
     this.window.setResizable(false);

     // System.out.println(this.pongWorld.getStage());
     this.context.changeStage(this.sppWorld.getStage());
     this.context.setBackground(Color.WHITE);

     //Initialisation du contrôle clavier
     sppKeyboard = new MyKeyboardSPP(sppWorld);
     this.window.addKeyListener(sppKeyboard);

     //On lance un thread avec le jeus
     new Thread(){
       public void run(){
         launchSPP();
       }
     }.start();
   }


   /**
   * Boucle principale du jeu de pong
   */
   public void launchPong(){
     while(!pongEnd) {
       this.context.repaint();
       this.pongWorld.process(DELTA_T);
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
     while(!sppEnd) {
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
