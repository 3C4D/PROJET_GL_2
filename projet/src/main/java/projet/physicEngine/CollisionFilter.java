package projet.physicEngine;

public class CollisionFilter{

  /**
  * Permet de déterminer s'il est nécessaire de calculer une collision entre
    deux corps grâce à la valeur de leur masque
  * @param un premier corps
  * @param un deuxième
  * @return renvoye vraie si les corps ba et bb peuvent être en collision
  */
  public static boolean shouldCollide(Body ba, Body bb){
    int i, j;
    Filter fa, fb;

    fa = ba.getFilter();
    fb = bb.getFilter();

    if((fa.getMaskBits() & fb.getCategoryBits()) == fb.getCategoryBits()
    || (fb.getMaskBits() & fa.getCategoryBits()) == fa.getCategoryBits()) {
      return true;
    }
    return false;
  }
}
