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

public class Table extends MyEntity{

   /**
    * Classe représentant l'aspect graphique d'une balle
    */
    public class TableTexture extends PFixedTexturedDrawable{

      public TableTexture(int x, int y, int width, int height){
        super(x,y,width,height);
      }

      @Override
      public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.drawOval(this.x-this.width, this.y-this.width, this.width*2, this.width*2);
      }
    }

    /**
    * Permet de créer une balle à partir de son centre et de son rayon (taille)
    */
    public Table(Point center, float ray){
      super(MyEntity.TABLE);
      //On créer son enveloppe
      CircleShape tableShape = new CircleShape(center, ray);
      //On crée son body
      this.body = new Body(center, tableShape, BodyType.DYNAMIC);

      this.body.getFilter().setCategoryBits(MyFilter.TABLE_CATEGORY);
      this.body.getFilter().setMaskBits(MyFilter.TABLE_MASK);

      // On ajoute une fixture
      TableTexture texture = new TableTexture((int)center.getX(), (int)center.getY(), (int)ray, (int)ray);
      this.setDrawable(texture);
    }

}
