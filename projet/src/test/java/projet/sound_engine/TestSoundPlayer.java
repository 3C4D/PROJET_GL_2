package projet.sound_engine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TestSoundPlayer{
  @Test
  public void TestOpen() throws Exception{
    var c = new SoundPlayer("sounds/gigachad.wav"); // Fichier ouvert
  }

  @Test
  public void TestOpenBadFormatException(){
    assertThrows(Exception.class, ()->{       // Fichier avec mauvaise extension
      new SoundPlayer("sounds/gigachad.mp3");
    });
  }

  @Test
  public void TestSoundNotFoundException(){
    // Le fichier est introuvable
    assertThrows(SoundNotFoundException.class, ()->{
      new SoundPlayer("sounds/notexists.mp3");
    });
  }

  @Test
  public void TestPlayOneTimeMute() throws Exception{
    var c = new SoundPlayer("sounds/gigachad.wav");

    c.play(false, -100f); // On démarre
    while(!c.isEnded()){  // On attends la fin
      Thread.sleep(500);
    }

    // Le son est fini et il est revenu à 0
    assertEquals(c.isEnded(), true);
    assertEquals(c.getTime() == 0, true);
  }

  @Test
  public void TestPlayOneTimeMaxVolume() throws Exception{
    var c = new SoundPlayer("sounds/gigachad.wav");

    c.play(false, 100f);  // On démarre
    while(!c.isEnded()){  // On attend la fin
      Thread.sleep(500);
    }

    // Le son est fini et il est revenu à 0
    assertEquals(c.isEnded(), true);
    assertEquals(c.getTime() == 0, true);
  }

  @Test
  public void TestPlayOneTimeStopped() throws Exception{
    var c = new SoundPlayer("sounds/gigachad.wav");

    c.play(false, 100f);  // On démarre
    Thread.sleep(1000);   // On attend une seconde

    c.stop();             // On l'arrete

    // Le son est fini et son temps est en dessous du temps total qu'il contient
    assertEquals(c.isEnded(), true);
    assertEquals(c.getTime() < c.getTotalTime(), true);
  }

  @Test
  public void TestPlayOneTimeModifyVolume() throws Exception{
    var c = new SoundPlayer("sounds/gigachad.wav");

    c.play(false, 100f);  // On démarre
    Thread.sleep(1000);   // On attend une seconde

    c.setVolume(0.5f);    // On modifie le volume
  }

  @Test
  public void TestModifyStartPoint() throws Exception{
    var c = new SoundPlayer("sounds/gigachad.wav");

    c.setStart(20_000l);                // On modifie le point de départ du son
    assertEquals(c.getTime(), 20_000l); // On vérifie la modification
  }

  @Test
  public void TestModifyIllegalStartPoint() throws Exception{
    var c = new SoundPlayer("sounds/gigachad.wav");

    // On essaye de modifier le point de départ du son avec une valeur aberrante
    assertThrows(IllegalTimeException.class, ()->{
      c.setStart(20_000_000_000l);
    });
  }

  @Test
  public void TestPlayOneTimeModifyStartPointException() throws Exception{
    var c = new SoundPlayer("sounds/gigachad.wav");

    c.play(false, 100f);  // On démarre
    Thread.sleep(1000);   // On attend une seconde

    // On essaye de modifier le point de départ pendant l'écoute
    assertThrows(SoundPlayingException.class, ()->{
      c.setStart(20_000l);
    });
  }

  @Test
  public void TestPlayLoopMaxVolume() throws Exception{
    var c = new SoundPlayer("sounds/gigachad.wav");

    c.play(true, 1f);                 // On démarre en boucle
    assertEquals(c.isEnded(), false); // On vérifie que le son ai démarré
    Thread.sleep(1000);               // On attends une seconde

    c.stop();                         // On arrete
    assertEquals(c.isEnded(), true);  // On vérifie que le son soit bien arreté
  }
}
