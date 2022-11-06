package projet.sound_engine;

/**
  * Exception levée si le son n'est pas démarré et qu'on veut le redémarrer
*/
public class SoundNotPlayingException extends Exception{
  /**
    * Constructeur de l'exception
  */
  public SoundNotPlayingException(){
    super("Le son n'a pas démarré, on ne peut pas le redémarrer");
  }
}
