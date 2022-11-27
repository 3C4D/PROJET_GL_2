package projet.io_engine;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
  * Écouteur permettant de définir des comportements lors de frappes au clavier
*/
public abstract class KeyboardHandler implements KeyListener{
    @Override
    public void keyTyped(KeyEvent event){
        actionKeyboardTyped(event.getKeyChar(), event.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent event){
      actionKeyboardPressed(event.getKeyChar(), event.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent event){
      actionKeyboardReleased(event.getKeyChar(), event.getKeyCode());
    }

    /**
      * Fonction d'écoute d'une touche tapée (pressée puis relachée) imprimable
      * @param letter Caractère imprimable tapée
      * @param code Code de la touche tapée (normalement 0)
    */
    public abstract void actionKeyboardTyped(char letter, int code);

    /**
      * Fonction d'écoute d'une touche pressée
      * @param letter Lettre éventuellement pressée
      * @param code Code de la touche pressée
    */
    public abstract void actionKeyboardPressed(char letter, int code);

    /**
      * Fonction d'écoute d'une touche relachée
      * @param letter Lettre éventuellement relachée
      * @param code Code de la touche relachée
    */
    public abstract void actionKeyboardReleased(char letter, int code);
}
