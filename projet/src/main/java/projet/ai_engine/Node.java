package projet.ai_engine;
/**
 * Class which implements node structure.
 **/
public class Node {
    public final int x;
    public final int y;
    public final String name;
    public final int indice;
    public final int cost;
     /**Constructor of Graph's class 
     * @param x (this is first coordonate)
     * @param y (this is second coordonate)
     * @param name (this is the name of this node)
     * @param indice (this is the indice of this node)
     * @param cost (this is the cost of this node)
    **/
    Node(int x,int y,String name,int indice,int cost){
        this.x=x;
        this.y=y;
        this.indice=indice;
        this.name=name;
        this.cost=cost;
    }
  

}
