package projet.game;

import projet.graphic_engine.GUI.*;
import projet.graphic_engine.drawable.PFixedTexturedDrawable;
import projet.graphic_engine.*;
import java.awt.Color;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.JTextArea;
import java.lang.Exception;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Classe pour la création et le lancement du jeu
 */
public class Game implements IConfig {
  private volatile boolean pongEnd = true;
  private volatile boolean sppEnd = true;

  private MyWorldPong pongWorld;
  private MyKeyboardPong pongKeyboard;
  private MyWorldSPP sppWorld;
  private MyKeyboardSPP sppKeyboard;

  private PWindow window;
  public PContext context;

  private PButton pongB;
  private PButton sppB;
  private PButton host;
  private PButton join;
  private PButton run;
  private PButton menu;

  private JButton withoutAI;
  private JButton withoutAIR;
  private JButton withAI1;
  private JButton withAI2;
  private JButton withAI3;
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

  public volatile AtomicBoolean isFinished;

  private boolean boolIp = false, boolPort = false;

  public static float x;
  public static float y;

  // Réseau
  PongServer server;
  PongPlayer player;
  String user, hostIp;
  int hostPort, clientPort;

  /**
   * Créateur d'un jeu
   */
  public Game() {
    this.window = new PWindow("Menu", WIDTH, HEIGHT);
    this.context = new PContext(WIDTH, HEIGHT);
    PStage stage = new PStage(WIDTH, HEIGHT);
    this.x = (float) (Math.random() * (3 - 2) + 1) / 10f;
    this.y = (float) (Math.random() * (3 - 2) + 1) / 10f;
    double sx = (Math.random());
    double sy = (Math.random());
    if (sx > 0.5) {
      this.x *= -1;
    }
    if (sy > 0.5) {
      this.y *= -1;
    }

    pongB = new PButton("PONG");
    pongB.setBackground(new Color(0, 0, 0, 255));
    pongB.setForeground(new Color(255, 255, 255, 255));
    pongB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        pongMenu();
        // isFinished.set(true);
      }
    });

    sppB = new PButton("SUPER PASTIS PONG");
    sppB.setBackground(new Color(0, 0, 0, 255));
    sppB.setForeground(new Color(255, 255, 255, 255));
    sppB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Création du monde du jeu
        sppWorld = new MyWorldSPP(WIDTH, HEIGHT);
        sppMenu();
        // isFinished.set(true);
      }
    });

    // PFixedTexturedDrawable background = new PFixedTexturedDrawable(0, 0, WIDTH, HEIGHT);
    // System.out.println(background.loadTexture("src/main/resources/images/bottle.png"));
    // System.out.println("");
    PGridLayout layout = new PGridLayout(2, 1);

    stage.getGUI().setBorder(BorderFactory.createEmptyBorder((int) (HEIGHT / 1.7), (WIDTH / 12), 0, 100));

    layout.setVgap((int) (HEIGHT / 10));
    stage.getGUI().setLayout(layout);

    stage.getGUI().add(pongB);
    stage.getGUI().add(sppB);

    this.context.changeStage(stage);
    this.window.addContext(this.context);
    this.window.setVisible(true);
    this.window.setFocusable(true);

     this.isFinished = new AtomicBoolean(false);

     // int delay = 1000/25; //milliseconds
     // ActionListener taskPerformer = new ActionListener() {
     //     public void actionPerformed(ActionEvent evt) {
     //       stage.add(background);
     //       context.repaint();
     //     }
     // };
     // new Timer(delay, taskPerformer).start();

  }

  /**
   * Permet de créer le menu du jeu
   */
  public void menu() {
    pongEnd = true;
    sppEnd = true;

    this.window.setTitle("Menu");
    PStage stage = new PStage(WIDTH, HEIGHT);

    pongB = new PButton("PONG");
    pongB.setBackground(new Color(0, 0, 0, 255));
    pongB.setForeground(new Color(255, 255, 255, 255));
    pongB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        pongMenu();
        isFinished.set(true);
      }
    });

    sppB = new PButton("SUPER PASTIS PONG");
    sppB.setBackground(new Color(0, 0, 0, 255));
    sppB.setForeground(new Color(255, 255, 255, 255));
    sppB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Création du monde du jeu
        sppWorld = new MyWorldSPP(WIDTH, HEIGHT);
        sppMenu();

        isFinished.set(true);
      }
    });

    // PFixedTexturedDrawable background = new PFixedTexturedDrawable(0, 0, WIDTH, HEIGHT);
    // background.loadTexture("src/main/resources/images/bottle.png");

    PGridLayout layout = new PGridLayout(2, 1);
    layout.setVgap((int) (HEIGHT / 10));
    stage.getGUI().setBorder(BorderFactory.createEmptyBorder((int) (HEIGHT / 1.7), (WIDTH / 12), 0, 100));

    stage.getGUI().setLayout(layout);

    stage.getGUI().add(pongB);
    stage.getGUI().add(sppB);

    this.context.setBackground(Color.WHITE);
    this.context.changeStage(stage);
    this.window.setVisible(true);

    this.isFinished.set(false);

    // int delay = 1000/25; //milliseconds
    // ActionListener taskPerformer = new ActionListener() {
    //     public void actionPerformed(ActionEvent evt) {
    //       stage.add(background);
    //       context.repaint();
    //     }
    // };
    // new Timer(delay, taskPerformer).start();
  }

  /**
   * Permet de créer le menu du pong
   */
  public void pongMenu() {
    System.out.println("EHOH JE SUIS LA");
    pongEnd = false;
    PStage stage = new PStage(WIDTH, HEIGHT);
    this.window.setTitle("PONG");

    withoutAI = new PButton("Jouer à deux joueurs");
    withoutAI.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        pongIA(-1);
      }
    });

    withoutAIR = new PButton("Jouer à deux joueurs en réseau");
    withoutAIR.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        pongMenuNetwork();
      }
    });

    withAI1 = new PButton("Jouer contre une IA facile");
    withAI1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        pongIA(1);
      }
    });

    withAI2 = new PButton("Jouer contre une IA moyenne");
    withAI2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        pongIA(2);
      }
    });

    withAI3 = new PButton("Jouer contre une IA difficile");
    withAI3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        pongIA(3);
      }
    });

    PGridLayout layout = new PGridLayout(5, 1);
    layout.setVgap((int) (HEIGHT / 10));
    stage.getGUI().setLayout(layout);

    stage.getGUI().add(withoutAI);
    stage.getGUI().add(withAI1);
    stage.getGUI().add(withAI2);
    stage.getGUI().add(withAI3);
    stage.getGUI().add(withoutAIR);

    this.context.changeStage(stage);
    this.window.setVisible(true);
  }

  /**
   * Permet de créer le menu réseau du pong
   */
  public void pongMenuNetwork() {
    sppEnd = false;
    PStage stage = new PStage(WIDTH, HEIGHT);
    this.window.setTitle("PONG");

    host = new PButton("HEBERGER");
    host.setBackground(new Color(0, 0, 0, 255));
    host.setForeground(new Color(255, 255, 255, 255));
    host.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        hostMenuPong();
      }
    });

    join = new PButton("REJOINDRE");
    join.setBackground(new Color(0, 0, 0, 255));
    join.setForeground(new Color(255, 255, 255, 255));
    join.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        joinMenuPong();
      }
    });

    // PFixedTexturedDrawable background = new PFixedTexturedDrawable(0, 0, WIDTH, HEIGHT);
    // System.out.println(background.loadTexture("src/main/resources/images/bottle.png"));

    PGridLayout layout = new PGridLayout(2, 1);
    stage.getGUI().setBorder(BorderFactory.createEmptyBorder((int) (HEIGHT / 1.7), (WIDTH / 12), 0, 100));

    layout.setVgap((int) (HEIGHT / 10));
    stage.getGUI().setLayout(layout);

    stage.getGUI().add(host);
    stage.getGUI().add(join);

    this.context.changeStage(stage);
    this.window.setVisible(true);

    // this.isFinished.set(false);

    // int delay = 1000/25; //milliseconds
    // ActionListener taskPerformer = new ActionListener() {
    //     public void actionPerformed(ActionEvent evt) {
    //       stage.add(background);
    //       context.repaint();
    //     }
    // };
    // new Timer(delay, taskPerformer).start();
   }

  /**
   * Permet de créer le menu d'hebergement d'une partie pong
   */
  public void hostMenuPong() {
    PStage stage = new PStage(WIDTH, HEIGHT);
    this.window.setTitle("PONG");

    this.portL = new PLabel("Port: ");
    this.port = new JTextArea("Port");
    this.portB = new PButton("Valider");
    portB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
        clientPort = Integer.parseInt(port.getText());
          run.setEnabled(true);

        } catch (Exception ex) {
          System.out.println("Dommage");
        }
      }
    });

    run = new PButton("LANCER LA PARTIE");
    run.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Lancement du serveur
        server = new PongServer(clientPort, 2);
        server.start();
        user = "RACKET_A";

        // Connexion de l'hôte
        player = new PongPlayer();
        try {
          player.connect(InetAddress.getLocalHost(), clientPort, "RACKET_A");
          player.startReading();
        } catch (UnknownHostException e1) {
          System.out.println("Connexion de l'hôte impossible !");
        }

        // Attente de connexion des joueurs
        while (server.getClientsConnected() < 2) {
          try {
            Thread.sleep(1000);
            System.out.print(".");
          } catch (InterruptedException e1) {
            e1.printStackTrace();
          }
        }

        // Lancement de la partie de pong
        String message = "MVT BALL "+x+";"+y;
        server.sendMessage(message, "RACKET_A");
        server.sendMessage(message, "RACKET_B");
        pong();
      }
    });
    run.setEnabled(false);

    // PFixedTexturedDrawable background = new PFixedTexturedDrawable(0, 0, WIDTH, HEIGHT);
    // background.loadTexture("src/main/resources/images/context.png");

    PGridLayout layout = new PGridLayout(3, 3);
    layout.setVgap((int) (HEIGHT / 10));
    stage.getGUI().setLayout(layout);

    stage.getGUI().add(portL);
    stage.getGUI().add(port);
    stage.getGUI().add(portB);
    stage.getGUI().add(run);

    this.context.changeStage(stage);
    this.window.setVisible(true);

    // new Thread() {
    //   public void run() {
    //     while (!isFinished.get()) {
    //
    //       stage.add(background);
    //       context.repaint();
    //       try {
    //         Thread.sleep(1000 / 25);
    //       } catch (Exception e) {
    //         e.printStackTrace();
    //       }
    //     }
    //   }
    // }.start();
  }

  /**
   * Permet de créer le menu pour rejoindre une partie pong
   */
  public void joinMenuPong() {
    this.window.setTitle("SUPER PASTIS PONG");
    PStage stage = new PStage(WIDTH, HEIGHT);
    boolIp = false;
    boolPort = false;

    this.ipL = new PLabel("IP: ");
    this.ip = new JTextArea("IP");
    this.ipB = new PButton("Valider");
    ipB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        hostIp = ip.getText();
        boolIp = true;
        if (boolPort == true) {
          run.setEnabled(true);
        }
      }
    });

    this.portL = new PLabel("Port: ");
    this.port = new JTextArea("Port");
    this.portB = new PButton("Valider");
    portB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          hostPort = Integer.parseInt(port.getText());
          boolPort = true;
          if (boolIp) {
            run.setEnabled(true);
          }

        } catch (Exception ex) {
          System.out.println("Dommage");
        }
      }
    });

    run = new PButton("REJOINDRE LA PARTIE");
    run.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        player = new PongPlayer();
        try {
          player.connect(InetAddress.getByName(hostIp), hostPort, "RACKET_B");
          player.startReading();
          user = "RACKET_B";
        } catch (UnknownHostException e1) {
          System.out.println("Connexion impossible !");
        }

        // Lancement de la partie de pong
        pong();
      }
    });
    run.setEnabled(false);

    PGridLayout layout = new PGridLayout(3, 3);
    layout.setVgap((int) (HEIGHT / 10));
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
   * Permet de créer une partie de PONG
   */
  public void pong() {
    pongEnd = false;
    // On met à jour le titre de la fenetre
    this.window.setTitle("PONG");
    // On empeche que la fenetre soit redimensionnée
    this.window.setResizable(false);

    this.menu = new PButton("Retour Menu");
    menu.setBackground(new Color(0, 0, 0, 255));
    menu.setForeground(new Color(255, 255, 255, 255));
    menu.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        menu();
      }
    });

    // Création du monde du jeu
    this.pongWorld = new MyWorldPong(WIDTH, HEIGHT, -2, player);
    this.pongWorld.getStage().getGUI().add(menu);
    this.context.changeStage(this.pongWorld.getStage());
    this.context.setBackground(Color.BLACK);

    // Création des raquettes
    if (user.equals("RACKET_A")) {
      pongWorld.addPongRacket(MyEntity.RACKET_A, player);
      pongWorld.addPongRacket(MyEntity.RACKET_B, null);
    } else {
      pongWorld.addPongRacket(MyEntity.RACKET_A, null);
      pongWorld.addPongRacket(MyEntity.RACKET_B, player);
    }

    // On donne le monde au player
    player.setWorld(pongWorld);

    PGridLayout layout = new PGridLayout(2, 3);
    layout.setVgap((int) (HEIGHT - HEIGHT / 6));
    this.pongWorld.getStage().getGUI().setLayout(layout);

    // Initialisation du contrôle clavier
    pongKeyboard = new MyKeyboardPong(pongWorld, user);
    this.window.addKeyListener(pongKeyboard);

    this.window.setVisible(true);

    // On lance un thread avec le jeus
    new Thread() {
      public void run() {
        launchNetworkPong();
      }
    }.start();
  }

  /**
   * Permet de créer une partie de PONG avec une IA
   *
   * @param le niveau de l'IA
   */
  public void pongIA(int level) {
    pongEnd = false;
    // On met à jour le titre de la fenetre
    this.window.setTitle("PONG");
    // On empeche que la fenetre soit redimensionnée
    this.window.setResizable(false);

    this.menu = new PButton("Retour Menu");
    menu.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        menu();
      }
    });

    // Création du monde du jeu
    this.pongWorld = new MyWorldPong(WIDTH, HEIGHT, level);
    this.pongWorld.getStage().getGUI().add(menu);
    this.context.changeStage(this.pongWorld.getStage());
    this.context.setBackground(Color.BLACK);

    PGridLayout layout = new PGridLayout(2, 3);
    layout.setVgap((int) (HEIGHT - HEIGHT / 6));
    this.pongWorld.getStage().getGUI().setLayout(layout);

    // Initialisation du contrôle clavier
    pongKeyboard = new MyKeyboardPong(pongWorld, user);
    this.window.addKeyListener(pongKeyboard);

    this.window.setVisible(true);

    // On lance un thread avec le jeus
    new Thread() {
      public void run() {
        launchPong();
      }
    }.start();
  }

  /**
   * Permet de créer le menu du SPP
   */
  public void sppMenu() {
    sppEnd = false;
    PStage stage = new PStage(WIDTH, HEIGHT);
    this.window.setTitle("SUPER PASTIS PONG");

    host = new PButton("HEBERGER");
    host.setBackground(new Color(0, 0, 0, 255));
    host.setForeground(new Color(255, 255, 255, 255));
    host.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        hostMenu();
      }
    });

    join = new PButton("REJOINDRE");
    join.setBackground(new Color(0, 0, 0, 255));
    join.setForeground(new Color(255, 255, 255, 255));
    join.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        joinMenu();
      }
    });

    // PFixedTexturedDrawable background = new PFixedTexturedDrawable(0, 0, WIDTH, HEIGHT);
    // System.out.println(background.loadTexture("src/main/resources/images/bottle.png"));

    PGridLayout layout = new PGridLayout(2, 1);
    stage.getGUI().setBorder(BorderFactory.createEmptyBorder((int) (HEIGHT / 1.7), (WIDTH / 12), 0, 100));

    layout.setVgap((int) (HEIGHT / 10));
    stage.getGUI().setLayout(layout);

    stage.getGUI().add(host);
    stage.getGUI().add(join);

    this.context.changeStage(stage);
    this.window.setVisible(true);

    this.isFinished.set(false);

    // new Thread() {
    //   public void run() {
    //     while (!isFinished.get()) {
    //
    //       stage.add(background);
    //
    //       context.repaint();
    //
    //       try {
    //         Thread.sleep(1000 / 25);
    //       } catch (Exception e) {
    //         e.printStackTrace();
    //       }
    //     }
    //   }
    // }.start();
  }

  /**
   * Permet de créer le menu d'hebergement d'une partie SPP
   */
  public void hostMenu() {
    PStage stage = new PStage(WIDTH, HEIGHT);
    this.window.setTitle("SUPER PASTIS PONG");

    this.usernameL = new PLabel("Pseudo : ");
    this.username = new JTextArea("username");
    this.usernameB = new PButton("Valider");
    usernameB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //String user = username.getText();
        // JEAN --> recupère le username
        run.setEnabled(true);
      }
    });

    this.playersNbL = new PLabel("Nombre de joueurs :");
    this.playersNb = new JTextArea("4");
    this.playersNbB = new PButton("Valider");
    playersNbB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          int players = Integer.parseInt(playersNb.getText());
          sppWorld.setNbPlayers(players);
        } catch (Exception ex) {
          System.out.println("Dommage");
        }
      }
    });

    run = new PButton("LANCER LA PARTIE");
    run.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // JEAN --> lancement du serveur

        // Lancement de la partie spp;
        spp();
      }
    });
    run.setEnabled(false);

    PGridLayout layout = new PGridLayout(3, 3);
    layout.setVgap((int) (HEIGHT / 10));
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
  public void joinMenu() {
    this.window.setTitle("SUPER PASTIS PONG");
    PStage stage = new PStage(WIDTH, HEIGHT);
    boolIp = false;
    boolPort = false;

    this.ipL = new PLabel("IP: ");
    this.ip = new JTextArea("IP");
    this.ipB = new PButton("Valider");
    ipB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //String ipp = ip.getText();
        // JEAN --> Récupération (jsp si ça te dit mais t'as compris mdr) de l'IP
        boolIp = true;
        if (boolPort == true) {
          run.setEnabled(true);
        }
      }
    });

    this.portL = new PLabel("Port: ");
    this.port = new JTextArea("Port");
    this.portB = new PButton("Valider");
    portB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          //int portt = Integer.parseInt(port.getText());
          // JEAN --> Récupération du port (pas pas du cochon lol)
          boolPort = true;
          if (boolIp) {
            run.setEnabled(true);
          }

        } catch (Exception ex) {
          System.out.println("Dommage");
        }
      }
    });

    run = new PButton("REJOINDRE LA PARTIE");
    run.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // JEAN --> Lancement du client etc
      }
    });
    run.setEnabled(false);

    PGridLayout layout = new PGridLayout(3, 3);
    layout.setVgap((int) (HEIGHT / 10));
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
  public void spp() {
    // On met à jour le titre de la fenetre
    this.window.setTitle("Super Pastis Pong");
    // On empeche que la fenetre soit redimensionnée
    this.window.setResizable(false);

    // System.out.println(this.pongWorld.getStage());
    this.context.changeStage(this.sppWorld.getStage());
    this.context.setBackground(Color.WHITE);

    // Initialisation du contrôle clavier
    sppKeyboard = new MyKeyboardSPP(sppWorld);
    this.window.addKeyListener(sppKeyboard);

    // On lance un thread avec le jeus
    new Thread() {
      public void run() {
        launchSPP();
      }
    }.start();
  }

  /**
   * Boucle principale du jeu de pong
   */
  public void launchPong() {
    PFixedTexturedDrawable background = new PFixedTexturedDrawable(WIDTH / 3, 0, WIDTH / 3, HEIGHT);
    background.loadTexture("src/main/resources/images/pointille_3.png");

    this.pongWorld.getStage().getGUI().setBorder(BorderFactory.createEmptyBorder(0, WIDTH / 12, 0, 0));
    while (!pongEnd) {
      this.context.repaint();
      this.pongWorld.process(DELTA_T);
      try {
        Thread.sleep((long) DELTA_T);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }
  }

  /**
   * Boucle principale du jeu de pong en réseau
   */
  public void launchNetworkPong() {
    PFixedTexturedDrawable background = new PFixedTexturedDrawable(WIDTH / 3, 0, WIDTH / 3, HEIGHT);
    background.loadTexture("src/main/resources/images/pointille_3.png");

    this.pongWorld.getStage().getGUI().setBorder(BorderFactory.createEmptyBorder(0, WIDTH / 12, 0, 0));
    while (!pongEnd) {
      this.context.repaint();
      this.pongWorld.process(DELTA_T);
      try {
        Thread.sleep((long) DELTA_T);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      player.manageMessage();
      /*if (user.equals("RACKET_A")) {
        player.sendMessage("MVT BALL " + pongWorld.getBall().getBody().getVelocity().getCoordX() + ";" + pongWorld.getBall().getBody().getVelocity().getCoordY());
      }*/
    }
  }

  /**
   * Boucle principale du jeu de SPP
   */
  public void launchSPP() {
    while (!sppEnd) {
      this.sppWorld.process(DELTA_T);
      this.context.repaint();
      try {
        Thread.sleep((long) DELTA_T);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }
  }
}
