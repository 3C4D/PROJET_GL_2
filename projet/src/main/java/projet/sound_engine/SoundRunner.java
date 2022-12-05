package projet.sound_engine;

/**
  * Thread permettant d'attendre la fin d'un son
*/

public class SoundRunner extends Thread{
  private SoundPlayer sp;

  /**
    * Constructeur d'un thread attendant la fin d'un son
    * @param sp Diffuseur de son qu'il doit attendre
  */
  public SoundRunner(SoundPlayer sp){
    this.sp = sp;
  }

  @Override
  public void run(){
    long current = System.currentTimeMillis();;
    long time = sp.getTime();

    // On attends que la musique se finisse
    while(!sp.isEnded()){
      try{
        Thread.sleep(500);
      }
      catch(Exception e){
        e.printStackTrace();
      }
    }

    // Calcul du temps
    time += (System.currentTimeMillis() - current) * 1000;
    if(time > sp.getTotalTime()){
      time = 0;
    }

    // Mise à jour du temps
    try{
      sp.setStart(time);
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }
}
