package projet.game;

import projet.io_engine.KeyboardHandler;
import projet.io_engine.IKeyCode;

/**
 * Classe définissant le comportement du jeu en fonction des évenements clavier
 */
public class MyKeyboardPong extends KeyboardHandler {
  private MyWorldPong world;
  private String username;

  /**
   * @param le monde associé au jeu
   */
  public MyKeyboardPong(MyWorldPong world, String username) {
    super();
    this.world = world;
    this.username = username;
  }

  @Override
  public void actionKeyboardTyped(char letter, int code) {
  }

  @Override
  public void actionKeyboardPressed(char letter, int code) {
    if (username != null) {
      if (username.equals("RACKET_A")) {
        switch (code) {
          case IKeyCode.KEY_UP:
            this.world.getRacketA().moveUp(); // La raquette monte
            break;

          case IKeyCode.KEY_DOWN:
            this.world.getRacketA().moveDown(); // La raquette descend
            break;
        }
      } else if (username.equals("RACKET_B")) {
        switch (code) {
          case IKeyCode.KEY_UP:
            this.world.getRacketB().moveUp(); // La raquette monte
            break;

          case IKeyCode.KEY_DOWN:
            this.world.getRacketB().moveDown(); // La raquette descend
            break;
        }
      }
    } else {
      switch (code) {
        case IKeyCode.KEY_UP:
          this.world.getRacketB().moveUp(); // La raquette monte
          break;

        case IKeyCode.KEY_DOWN:
          this.world.getRacketB().moveDown(); // La raquette descend
          break;

        case IKeyCode.KEY_Z:
          this.world.getRacketA().moveUp(); // La raquette monte
          break;

        case IKeyCode.KEY_S:
          this.world.getRacketA().moveDown(); // La raquette descend
          break;

        default:
          break;
      }
    }
  }

  @Override
  public void actionKeyboardReleased(char letter, int code) {
    if (username != null) {
      if (username.equals("RACKET_A")) {
        switch (code) {
          case IKeyCode.KEY_UP:
            this.world.getRacketA().stop(); // La raquette s'arrête
            break;

          case IKeyCode.KEY_DOWN:
            this.world.getRacketA().stop(); // La raquette s'arrête
            break;
        }
      } else if (username.equals("RACKET_B")) {
        switch (code) {
          case IKeyCode.KEY_UP:
            this.world.getRacketB().stop(); // La raquette s'arrête
            break;

          case IKeyCode.KEY_DOWN:
            this.world.getRacketB().stop(); // La raquette s'arrête
            break;
        }
      }
    } else {
      switch (code) {
        case IKeyCode.KEY_UP:
          this.world.getRacketB().stop(); // La raquette s'arrête
          break;

        case IKeyCode.KEY_DOWN:
          this.world.getRacketB().stop(); // La raquette s'arrête
          break;

        case IKeyCode.KEY_Z:
          this.world.getRacketA().stop(); // La raquette s'arrête
          break;

        case IKeyCode.KEY_S:
          this.world.getRacketA().stop(); // La raquette s'arrête
          break;

        default:
          break;
      }
    }
  }
}
