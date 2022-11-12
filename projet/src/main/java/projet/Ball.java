package projet;

import projet.kernel.PEntity;
import projet.physicEngine.common.Point;
import projet.physicEngine.Body.BodyType;
import projet.physicEngine.*;

public class Ball extends MyEntity{

  public Ball(Point center, float ray){
    super(MyEntity.BALL);
    //On créer son enveloppe
    CircleShape ballShape = new CircleShape(center, ray);
    //On crée son body
    this.body = new Body(center, ballShape, BodyType.DYNAMIC);

    this.body.getFilter().setCategoryBits(MyFilter.BALL_CATEGORY);
    this.body.getFilter().setMaskBits(MyFilter.BALL_MASK);
  }

}
