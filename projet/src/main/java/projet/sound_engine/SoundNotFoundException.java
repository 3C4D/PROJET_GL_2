package projet.sound_engine;

/**
  * Exception levée si le fichier de musique est introuvable
*/
public class SoundNotFoundException extends Exception{
  /**
    * Constructeur de l'exception
    * @param path Mauvais chemin spécifié en construisant un diffuseur
  */
  public SoundNotFoundException(String path){
    super("Le fichier de son est introuvable : " + path);
  }
}
