package projet.game;

import projet.io_engine.KeyboardHandler;
import projet.io_engine.IKeyCode;

import projet.physicEngine.common.Vector2D;

/**
* Classe définissant le comportement du jeu en fonction des évenements clavier
*/
public class MyKeyboardPong extends KeyboardHandler{
  private MyWorldPong world;

  /**
  * @param le monde associé au jeu
  */
  public MyKeyboardPong(MyWorldPong world){
    super();
    this.world = world;
  }

  @Override
  public void actionKeyboardTyped(char letter, int code){
  }

  @Override
  public void actionKeyboardPressed(char letter, int code){
    switch (code) {
      case IKeyCode.KEY_UP:
        this.world.getRacketB().moveUp(); //La raquette monte
      break;

      case IKeyCode.KEY_DOWN:
        this.world.getRacketB().moveDown();  //La raquette descend
      break;

      case IKeyCode.KEY_Z:
        this.world.getRacketA().moveUp(); //La raquette monte
      break;

      case IKeyCode.KEY_S:
        this.world.getRacketA().moveDown(); //La raquette descend
      break;

      default:
      break;
    }

  }

  @Override
  public void actionKeyboardReleased(char letter, int code){
    switch (code) {
      case IKeyCode.KEY_UP:
        this.world.getRacketB().stop(); //La raquette s'arrête
      break;

      case IKeyCode.KEY_DOWN:
        this.world.getRacketB().stop(); //La raquette s'arrête
      break;

      case IKeyCode.KEY_Z:
        this.world.getRacketA().stop(); //La raquette s'arrête
      break;

      case IKeyCode.KEY_S:
        this.world.getRacketA().stop(); //La raquette s'arrête
      break;

      default:
      break;
    }
  }

}
