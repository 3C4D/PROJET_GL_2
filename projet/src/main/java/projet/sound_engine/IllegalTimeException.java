package projet.sound_engine;

/**
  * Exception levée si le point en micro secondes est illegal
*/
public class IllegalTimeException extends Exception{
  /**
    * Constructeur de l'exception
  */
  public IllegalTimeException(){
    super("Le temps indiqué est illégal");
  }
}
