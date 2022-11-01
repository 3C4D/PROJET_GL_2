package projet.physicEngine;

import projet.physicEngine.common.Point;

/**
* Permet de définir un box indépenamment de sa forme
*/
public class Shape{
  /**
  * Enumération des types possible de box
  */
  public enum ShapeType{
    POLYGON, CIRCLE
  }


  private ShapeType type;

  /**
  * @param le type de la box
  */
  Shape(ShapeType t){
    this.type = t;
  }

  /**
  *
  */
  public ShapeType getType(){
    return this.type;
  }


}
