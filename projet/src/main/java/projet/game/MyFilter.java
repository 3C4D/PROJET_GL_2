package projet.game;

import projet.physicEngine.Filter;

public class MyFilter extends Filter{
  public static byte SEED_CATEGORY = 00000010;
  public static byte BALL_CATEGORY = 00000020;

  public static byte SEED_MASK = BALL_CATEGORY;
  public static byte BALL_MASK = SEED_CATEGORY;
}
