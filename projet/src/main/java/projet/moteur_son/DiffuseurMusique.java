package projet.moteur_son;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.net.URL;

public class DiffuseurMusique{
  private MediaPlayer musique;

  /**
    * Exception levée si le fichier de musique est introuvable
  */
  public class MusiqueIntrouvableException extends Exception{
    /**
      * Constructeur de l'exception
      * @param chemin Mauvais chemin spécifié en construisant un diffuseur
    */
    public MusiqueIntrouvableException(String chemin){
      super("Le fichier de musique est introuvable : " + chemin);
    }
  }

  /**
    * Constructeur de musique
    * @param chemin Chemin du musique à partir du dossier resources
    * @throws MusiqueIntrouvableExcepetion si la musique est introuvable
  */
  public DiffuseurMusique(String chemin) throws MusiqueIntrouvableException{
    URL chemin_complet = Thread.currentThread()
      .getContextClassLoader()
      .getResource(chemin);

    // Le chemin est vide, le fichier est introuvable
    if(chemin_complet == null){
      musique = null;
      throw new MusiqueIntrouvableException(chemin);
    }
    else{
      musique = new MediaPlayer(new Media(chemin_complet.toString()));

      musique.setOnEndOfMedia(new Runnable(){
        public void run(){
          musique.seek(Duration.ZERO);
        }
      });
    }
  }

  /**
    * Joue une musique
    * @param boucle Doit etre à true si l'on veut jouer la musique en boucle
    * @param volume Volume (0 <= volume <= 1)
  */
  public void jouer(boolean boucle, double volume){
    if(musique != null){

      // On attribue le volume de la musique
      musique.setVolume(volume);

      if(boucle){
        // La musique revient au début une fois finie
        musique.setOnEndOfMedia(new Runnable(){
          public void run(){
            musique.seek(Duration.ZERO);
          }
        });
      }
      else{
        // La musique ne fait rien une fois finie
        musique.setOnEndOfMedia(new Runnable(){public void run(){}});
      }

      musique.play();
    }
  }

  /**
    * Arrete une musique
  */
  public void arreter(){
    musique.stop();
    musique.seek(Duration.ZERO);
  }
}
