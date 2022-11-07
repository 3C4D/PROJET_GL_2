package moteur_ia;

class Noeud
{
    public final graphe target;
    public final double weight;
    public Noeud(graphe argTarget, double argWeight)
    { target = argTarget; 
      weight = argWeight;
}
}