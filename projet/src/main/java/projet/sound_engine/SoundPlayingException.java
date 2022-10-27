package projet.sound_engine;

/**
  * Exception levée si le son est joué et que l'on fait une action pendant
*/
public class SoundPlayingException extends Exception{
  /**
    * Constructeur de l'exception
  */
  public SoundPlayingException(){
    super("Le son est en train de jouer");
  }
}
