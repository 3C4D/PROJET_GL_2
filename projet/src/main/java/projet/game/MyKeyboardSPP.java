package projet.game;

import projet.io_engine.KeyboardHandler;
import projet.io_engine.IKeyCode;

import projet.physicEngine.common.Vector2D;

/**
* Classe définissant le comportement du jeu en fonction des évenements clavier
*/
public class MyKeyboardSPP extends KeyboardHandler{
  private MyWorldSPP world;

  /**
  * @param le monde associé au jeu
  */
  public MyKeyboardSPP(MyWorldSPP world){
    super();
    this.world = world;
  }

  @Override
  public void actionKeyboardTyped(char letter, int code){
  }

  @Override
  public void actionKeyboardPressed(char letter, int code){
    switch (code) {
      case IKeyCode.KEY_RIGHT:
        this.world.getRackets().get(0).moveDown();  //La raquette descend
      break;

      case IKeyCode.KEY_LEFT:
        this.world.getRackets().get(0).moveUp(); //La raquette monte
      break;

      case IKeyCode.KEY_Z:
        this.world.getRackets().get(1).moveUp(); //La raquette monte
      break;

      case IKeyCode.KEY_S:
        this.world.getRackets().get(1).moveDown(); //La raquette descend
      break;

      default:
      break;
    }

  }

  @Override
  public void actionKeyboardReleased(char letter, int code){
    switch (code) {
      case IKeyCode.KEY_UP:
        this.world.getRackets().get(0).stop(); //La raquette s'arrête
      break;

      case IKeyCode.KEY_DOWN:
        this.world.getRackets().get(0).stop(); //La raquette s'arrête
      break;

      case IKeyCode.KEY_Z:
        this.world.getRackets().get(1).stop(); //La raquette s'arrête
      break;

      case IKeyCode.KEY_S:
        this.world.getRackets().get(1).stop(); //La raquette s'arrête
      break;

      default:
      break;
    }
  }

}
