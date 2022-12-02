package projet.game;

import projet.physicEngine.*;
import projet.physicEngine.common.*;

import projet.graphic_engine.GUI.*;
import projet.graphic_engine.*;
import java.awt.Color;



import javax.swing.*;
import java.awt.event.*;
import javax.swing.JTextArea;


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
     this.panel = new PPanel();

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

     this.panel.add(pongB);
     this.panel.add(sppB);

     this.window.getContentPane().add(panel);
     this.window.setVisible(true);
   }


   /**
   * Permet de créer le menu du jeu
   */
   public void menu(){
     this.window.setTitle("Menu");
     this.window.remove(panel);
     this.window.removeContext();
     this.pongContext = null;
     this.sppContext = null;
     this.panel = new PPanel();

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

     this.panel.add(pongB);
     this.panel.add(sppB);

     this.window.getContentPane().add(panel);
     this.window.setVisible(true);
   }

   /**
   * Permet de créer une partie de PONG
   */
   public void pong(){
     //On met à jour le titre de la fenetre
     this.window.setTitle("PONG");
     //On empeche que la fenetre soit redimensionnée
     this.window.setResizable(false);
     this.panel.remove(pongB);
     this.panel.remove(sppB);
     this.window.remove(panel);

     this.panel = new PPanel();
     this.menu = new PButton("Retour Menu");
     menu.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          menu();
        }
      });
      this.panel.add(menu);
      this.window.getContentPane().add(panel);

     // On créer le contexte
     this.pongContext = new PContext(WIDTH+50, HEIGHT+50);

     //Création du monde du jeu
     this.pongWorld = new MyWorldPong(WIDTH, HEIGHT);
     // System.out.println(this.pongWorld.getStage());
     this.pongContext.changeStage(this.pongWorld.getStage());
     this.pongContext.setBackground(Color.BLACK);

     //Initialisation du contrôle clavier
     pongKeyboard = new MyKeyboardPong(pongWorld);
     this.window.addKeyListener(pongKeyboard);

     this.window.addContext(pongContext);
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
     this.window.setTitle("SUPER PASTIS PONG");
     this.panel.remove(pongB);
     this.panel.remove(sppB);
     this.window.remove(panel);
     this.panel = new PPanel();

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

     this.panel.add(host);
     this.panel.add(join);

     this.window.getContentPane().add(panel);
     this.window.setVisible(true);
   }

   /**
   * Permet de créer le menu d'hebergement d'une partie SPP
   */
   public void hostMenu(){
     this.window.setTitle("SUPER PASTIS PONG");
     this.panel.remove(host);
     this.panel.remove(join);
     this.window.remove(panel);
     this.panel = new PPanel();

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
     usernameB.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          int players = Integer.parseInt(playersNb.getText());
          sppWorld.setNbPlayers(players);
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

     this.panel.add(usernameL);
     this.panel.add(username);
     this.panel.add(usernameB);
     this.panel.add(playersNbL);
     this.panel.add(playersNb);
     this.panel.add(playersNbB);
     this.panel.add(run);

     this.window.getContentPane().add(panel);
     this.window.setVisible(true);
   }

   /**
   * Permet de créer le menu pour rejoindre une partie SPP
   */
   public void joinMenu(){
     this.window.setTitle("SUPER PASTIS PONG");
     this.panel.remove(host);
     this.panel.remove(join);
     this.window.remove(panel);
     this.panel = new PPanel();
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
           int portt = Integer.parseInt(port.getText());
           // JEAN --> Récupération du port (pas pas du cochon lol)
           boolPort = true;
           if(boolIp){
             run.setEnabled(true);
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

     this.panel.add(ipL);
     this.panel.add(ip);
     this.panel.add(ipB);
     this.panel.add(portL);
     this.panel.add(port);
     this.panel.add(portB);
     this.panel.add(run);

     this.window.getContentPane().add(panel);
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
     this.panel.remove(join);
     this.panel.remove(host);
     this.window.remove(panel);

     // On créer le contexte
     this.sppContext = new PContext(WIDTH+50, HEIGHT+50);


     // System.out.println(this.pongWorld.getStage());
     this.sppContext.changeStage(this.sppWorld.getStage());
     this.sppContext.setBackground(Color.WHITE);

     //Initialisation du contrôle clavier
     sppKeyboard = new MyKeyboardSPP(sppWorld);
     this.window.addKeyListener(sppKeyboard);

     this.window.addContext(sppContext);

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
     while(true) {
       this.pongContext.repaint();
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
     while(true) {
       this.sppWorld.process(DELTA_T);
       this.sppContext.repaint();
       try {
             Thread.sleep((long)DELTA_T);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }

     }
   }
 }
