package projet.physicEngine.common;

/**
* Permet de créer des points du plan 2D
*/
public class Point{
  private float x;
  private float y;

  public static final int SAM = -1;
  public static final int ALIGNES = 0;
  public static final int SIAM = 1;

  /**
  * @param abscisse x
  * @param ordonnée y
  */
  public Point(float x, float y){
    this.x = x;
    this.y = y;
  }


  /**
  * Permet de calculer le déterminant des vecteurs formé par 3 points
  * @param un premier point
  * @param un deuxième point
  * @return la valeur du déterminant entre deux vecteurs
  */
  public double determinant(Point a, Point b){
    double det;
    det = (a.x - this.x)*(b.y - this.y) - (b.x - this.x)*(a.y - this.y);

    return det;
  }

  /**
  *
  */
  public Point orthoProjection(Point pA, Point pB){
    Vector2D vAB = new Vector2D(pA, pB);
    Vector2D vAC = new Vector2D(pA, this);

    float t = (float)(-vAB.getCoordX()*pA.x + this.x*vAB.getCoordX() -vAB.getCoordY()*pA.y + vAB.getCoordY()*this.y)/vAB.norme2Square();

    return new Point(pA.x + t*vAB.getCoordX(), pA.y + t*vAB.getCoordY());
  }

  /**
  * Permet de connaitre le signe d'un angle formé deux vecteurs
  * @param un premier point
  * @param un deuxième point
  * @return le signe de l'angle entre les points a et b, par rapport au point courant
  */
  public int angleSign(Point a, Point b){
    double determinant;

    determinant = this.determinant(a,b);

    if(determinant>0){ /*Angle plus petit que pi*/
      return SAM;
    }else if(determinant<0){ /*Angle plus grand que pi*/
      return SIAM;
    }else{/*Angle égal à pi*/
      return ALIGNES;
    }
  }


  /**
  * Permet de connaitre la distance entre deux point
  * @param le deuxième point
  * @return la distance entre le point courant et le point a
  */
  public float distance(Point a){
    return (float)Math.sqrt( (this.x - a.x)*(this.x - a.x) + (this.y - a.y)*(this.y - a.y));
  }


  /**
  * @param point à copier
  * @return la copie du point
  */
  public static Point copy(Point a){
    return new Point(a.x, a.y);
  }

  /**
  *
  */
  @Override
  public String toString(){
    return " ("+this.x+" ; "+this.y+")";
  }

  /**
  * @return l'abscisse x du point courant
  */
  public float getX(){
    return this.x;
  }

  /**
  * @return l'ordonnée y du point courant
  */
  public float getY(){
    return this.y;
  }

  /**
  * @param la nouvelle valeur de x
  */
  public void setX(float x){
    this.x = x;
  }

  /**
  * @param la nouvelle valeur de y
  */
  public void setY(float y){
    this.y = y;
  }
}
