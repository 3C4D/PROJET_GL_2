package projet.moteur_son;

import javafx.scene.media.AudioClip;
import java.net.URL;

public class DiffuseurBruit{
  private AudioClip bruit;

  /**
    * Exception levée si le fichier de bruit est introuvable
  */
  public class BruitIntrouvableException extends Exception{
    /**
      * Constructeur de l'exception
      * @param chemin Mauvais chemin spécifié en construisant un diffuseur
    */
    public BruitIntrouvableException(String chemin){
      super("Le fichier de bruit est introuvable : " + chemin);
    }
  }

  /**
    * Constructeur de bruit
    * @param chemin Chemin du bruit à partir du dossier resources
  */
  public DiffuseurBruit(String chemin) throws BruitIntrouvableException{
    URL chemin_complet = Thread.currentThread()
      .getContextClassLoader()
      .getResource(chemin);

    // Le chemin est vide, le fichier est introuvable
    if(chemin_complet == null){
      bruit = null;
      throw new BruitIntrouvableException(chemin);
    }
    else{
      bruit = new AudioClip(chemin_complet.toString());
    }
  }

  /**
    * Joue un bruit
  */
  public void jouer(double volume){
    if(bruit != null){
      bruit.setVolume(volume);
      bruit.play();
    }
  }
}
