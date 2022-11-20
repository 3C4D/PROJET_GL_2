package projet.game;

import projet.io_engine.KeyboardHandler;
import projet.io_engine.IKeyCode;

import projet.physicEngine.common.Vector2D;

public class MyKeyboard extends KeyboardHandler{
  private MyWorld world;

  public MyKeyboard(MyWorld world){
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
        this.world.getRacketA().moveUp();
      break;

      case IKeyCode.KEY_DOWN:
        this.world.getRacketA().moveDown();
      break;

      case IKeyCode.KEY_Z:
        this.world.getRacketB().moveUp();
      break;

      case IKeyCode.KEY_S:
        this.world.getRacketB().moveDown();
      break;

      default:
      break;
    }

  }

  @Override
  public void actionKeyboardReleased(char letter, int code){
    switch (code) {
      case IKeyCode.KEY_UP:
        this.world.getRacketA().stop();
      break;

      case IKeyCode.KEY_DOWN:
        this.world.getRacketA().stop();
      break;

      case IKeyCode.KEY_Z:
        this.world.getRacketB().stop();
      break;

      case IKeyCode.KEY_S:
        this.world.getRacketB().stop();
      break;

      default:
      break;
    }
  }

}
