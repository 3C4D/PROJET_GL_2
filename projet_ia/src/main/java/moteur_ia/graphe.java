package moteur_ia;

public class graphe {
    public final String name;
    public int x;
    public int y;
    public Noeud[] adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public graphe previous;
    public graphe(String argName,int x,int y) { name = argName; 
                                        this.x=x;
                                        this.y=y;}
    public String toString() { return name; }
    public int compareTo(graphe other)
    {
        return Double.compare(minDistance, other.minDistance);
    }
}
