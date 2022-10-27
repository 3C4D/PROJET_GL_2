package projet.sound_engine;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.FloatControl;
import java.net.URL;

/**
  * Classe représentant un diffuseur de son
*/

public class SoundPlayer implements LineListener{
  private Clip sound;
  private boolean ended;
  private long time = 0;
  private long totalTime;

  /**
    * Constructeur d'un diffuseur de son
    * @param chemin Chemin du musique à partir du dossier resources
  */
  public SoundPlayer(String path) throws Exception{
    URL fullPath = Thread.currentThread()
      .getContextClassLoader()
      .getResource(path);

    // Le chemin est vide, le fichier est introuvable
    if(fullPath == null){
      sound = null;
      throw new SoundNotFoundException(path);
    }
    else{
      // Initialisation du diffuseur
      sound = AudioSystem.getClip();
      sound.addLineListener(this);
      sound.open(AudioSystem.getAudioInputStream(fullPath));

      // Temps total en microsecondes (avec une marge)
      totalTime = sound.getMicrosecondLength() - 2_000;
      ended = true;
    }
  }

  @Override
  public void update(LineEvent event) {
    // La musique est terminée
    if(event.getType() == LineEvent.Type.STOP){
      ended = true;
    }
  }

  /**
    * Joue une musique
    * @param boucle Doit etre à true si l'on veut jouer le son en boucle
    * @param volume Volume (0 <= volume <= 1)
  */
  public void play(boolean loop, float volume) throws Exception{

    // Le son contenu dans l'objet n'est pas vide
    if(sound != null){
      // La musique commence
      ended = false;
      time = 0;

      // On attribue le volume du son
      setVolume(volume);

      // On règle le son sur boucle si besoin
      sound.loop((loop)?Clip.LOOP_CONTINUOUSLY:0);

      sound.setMicrosecondPosition(0);      // On commence au début
      sound.start();                        // Le son joue

      // Un Thread s'occupe d'attendre la fin
      new SoundRunner(this).start();
    }
  }

  /**
    * Recommence le son là ou il s'était arreté
  */
  public void resume() throws Exception{
    if(time == 0){  // Le son n'a pas commencé
      throw new SoundNotPlayingException();
    }
    else if(!ended){
      throw new SoundPlayingException();
    }
    else{
      ended = false;
      sound.setMicrosecondPosition(time); // On commence là où le son en était
      sound.start();                      // La musique joue

      // Un Thread s'occupe d'attendre la fin
      new SoundRunner(this).start();
    }
  }

  /**
    * Permet d'avancer le son à une position en micro secondes
  */
  public void setStart(long micros) throws Exception{
    if(micros < 0 || micros > totalTime){
      throw new IllegalTimeException();
    }
    else if(!ended){
      throw new SoundPlayingException();
    }
    else{
      // On met à jour le temps actuel et on joue le son
      time = micros;
      sound.setMicrosecondPosition(micros);
    }
  }

  /**
    * Permet de régler le volume
    * @param volume Volume (0 <= volume <= 1)
  */
  public void setVolume(float volume) throws Exception{
    FloatControl gain;

    volume = (volume < 0)?0:(volume > 1)?1:volume;

    // On modifie le volume de la muique (en décibels)
    gain = (FloatControl)sound.getControl(FloatControl.Type.MASTER_GAIN);
    gain.setValue(20f * (float) Math.log10(volume));
  }

  /**
    * Arrete le son
  */
  public void stop() throws Exception{
    if(ended == true){
      throw new SoundNotPlayingException();
    }
    else{
      sound.stop();
      ended = true;
    }
  }

  /**
    * Renvoie le temps actuel du son en micro secondes
  */
  public long getTime(){return time;}

  /**
    * Renvoie le temps total du son en micro secondes
  */
  public long getTotalTime(){return totalTime;}

  /**
    * Indique si une musique est finie
  */
  public boolean isEnded(){return ended;}
}
