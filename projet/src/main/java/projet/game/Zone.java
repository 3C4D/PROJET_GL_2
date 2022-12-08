package projet.game;

import projet.physicEngine.common.Transform;
import projet.physicEngine.common.*;
import projet.physicEngine.Body;
import projet.physicEngine.Body.BodyType;
import projet.physicEngine.*;

import projet.graphic_engine.drawable.*;

import java.lang.Math;
import java.awt.Graphics;
import java.awt.Color;

public class Zone extends MyEntity{
  private float maxAngle;
  private float minAngle;
  private float angle;

  /**
  * @param max : l'angle maximum de la zone
  * @param min : l'angle minimal de la zone
  * @param angle : l'angle où se trouve la raquette dans la zone
  */
  public Zone(float max, float min, float angle){
    super(MyEntity.ZONE);
    this.maxAngle = max;
    this.minAngle = min;
    this.angle = angle;
  }

  /**
  * @return l'angle max
  */
  public float getMaxAngle(){
    return this.maxAngle;
  }

  /**
  * @return l'angle min
  */
  public float getMinAngle(){
    return this.minAngle;
  }

  /**
  * @return l'angle courant
  */
  public float getAngle(){
    return this.angle;
  }

  /**
  * @param newAngle la nouvelle valeur de l'angle
  */
  public void setAngle(float newAngle){
    this.angle = newAngle;
  }

  /**
  * @return Le point sur le cercle correspondant à l'angle max
  */
  public Point getMaxPoint(){
    return new Point(MyWorldSPP.TABLE_SIZE/2f * (float)Math.cos(this.maxAngle),MyWorldSPP.TABLE_SIZE/2f * (float)Math.sin(this.maxAngle));
  }

  /**
  * @return Le point sur le cercle correspondant à l'angle min
  */
  public Point getMinPoint(){
    return new Point(MyWorldSPP.TABLE_SIZE/2f * (float)Math.cos(this.minAngle),MyWorldSPP.TABLE_SIZE/2f * (float)Math.sin(this.minAngle));
  }

}
