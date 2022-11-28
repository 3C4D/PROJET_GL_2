package projet.graphic_engine.GUI;
import projet.graphic_engine.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.BorderFactory;

import org.junit.jupiter.api.Test;

public class testMenu {
    
    @Test
    public void MainMenu() {

        int WIDTH = 500;
        int HEIGHT = 500;

        PWindow window = new PWindow("testMenu", WIDTH, HEIGHT);
        window.setResizable(false);
        PContext context = new PContext(WIDTH, HEIGHT);
        PStage stage = new PStage(WIDTH, HEIGHT);
        context.changeStage(stage);
        window.addContext(context);

        PLabel title = new PLabel("--- PONG ---");

        PButton playButton = new PButton("Jouer");
        //playButton.setBounds(20, 10, 32, 30);
        //playButton.setBounds(200, 200, 100, 100);
        
        playButton.addActionListener(e -> {
            System.out.println("Play");
        });

        PButton noticeButton = new PButton("Notice");    

        PButton quitButton = new PButton("Quitter");

        PGridLayout layout = new PGridLayout(4, 1);
        
        layout.setVgap((int)(HEIGHT/10));

        stage.getGUI().setBorder(BorderFactory.createEmptyBorder((int)(HEIGHT/3), 0, 0, 0));

        stage.getGUI().setLayout(layout);

        stage.getGUI().add(title);
        stage.getGUI().add(playButton);

        stage.getGUI().add(noticeButton);
        stage.getGUI().add(quitButton);

        while(true) {
            
            context.repaint();

            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void SPPMenu() {

        int WIDTH = 500;
        int HEIGHT = 500;

        PWindow window = new PWindow("testSPPMenu", WIDTH, HEIGHT);
        window.setResizable(false);
        PContext context = new PContext(WIDTH, HEIGHT);
        PStage stage = new PStage(WIDTH, HEIGHT);
        context.changeStage(stage);
        window.addContext(context);


        PLabel title = new PLabel("SUPER PASTIS PONG");

        PButton playButton = new PButton("Rejoindre");
        //playButton.setBounds(20, 10, 32, 30);
        //playButton.setBounds(200, 200, 100, 100);
        
        playButton.addActionListener(e -> {
            System.out.println("Rejoindre");
        });

        PButton noticeButton = new PButton("Héberger");

    

        PButton quitButton = new PButton("Quitter");

        PGridLayout layout = new PGridLayout(4, 1);
        
        layout.setVgap((int)(HEIGHT/10));

        stage.getGUI().setBorder(BorderFactory.createEmptyBorder((int)(HEIGHT/3), 0, 0, 0));

        stage.getGUI().setLayout(layout);

        stage.getGUI().add(title);
        stage.getGUI().add(playButton);

        stage.getGUI().add(noticeButton);
        stage.getGUI().add(quitButton);

        while(true) {
            
            context.repaint();

            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }



}
