package projet.spp;

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

  public Zone(float max, float min, float angle){
    super(MyEntity.ZONE);
    this.maxAngle = max;
    this.minAngle = min;
    this.angle = angle;
  }

  public float getMaxAngle(){
    return this.maxAngle;
  }

  public float getMinAngle(){
    return this.minAngle;
  }

  public float getAngle(){
    return this.angle;
  }

  public void setAngle(float newAngle){
    this.angle = newAngle;
  }

}
