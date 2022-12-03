package projet.physicEngine;

import java.io.Serializable;
import java.lang.Byte;

/**
* Permet un filtrage fin lors de la gestion de collision
*/
public class Filter implements Serializable {
    public static byte DEFAULT = 0;
    private byte categoryBits;
    private byte maskBits;

    /**
    * Permet d'initialiser un filtre
    * Toutes les valeurs ont une valeur à défaut qui signifie que le filtre
      n'est pas définie, et donc qu'il ne changera rien à la gestion des
      collision pour le corps ayant ce filtre
    */
    public Filter(){
      this.categoryBits = DEFAULT;
      this.maskBits = DEFAULT;
     }

     /**
     * @param la nouvelle catégory correspondant au filtre
     */
    public void setCategoryBits(byte newCategoryBits){
      this.categoryBits = newCategoryBits;
    }

    /**
    * @param le nouveau masque correspondant au filtre
    */
    public void setMaskBits(byte newMaskBits){
      this.maskBits = newMaskBits;
    }

    /**
    * @return la catégory correspondant au filtre
    */
    public byte getCategoryBits(){
      return this.categoryBits;
    }

    /**
    * @return le masque correspondant au filtre
    */
    public byte getMaskBits(){
      return this.maskBits;
    }



}
