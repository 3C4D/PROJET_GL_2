package moteur_ia;

import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
public class Dijkstra
{
    List <graphe> graphes= new ArrayList<graphe>();
    Dijkstra(){
       this.graphes=graphes;
    }

    public void plusCourtChemin(graphe source)
    {
        graphe test=source;
        source.minDistance = 0.;
        PriorityQueue<graphe> grapheQueue = new PriorityQueue<graphe>();
        System.out.println(source.adjacencies);
        grapheQueue.add(test);
        System.out.println(source);
      /*   
        while (!grapheQueue.isEmpty()) {
            graphe graph = grapheQueue.poll();

           
            for (Noeud noeud : graph.adjacencies)
            {
                graphe v = noeud.target;
                double weight = noeud.weight;
                double distanceThroughgraph = graph.minDistance + weight;
                if (distanceThroughgraph < v.minDistance) {
                    grapheQueue.remove(v);

                    v.minDistance = distanceThroughgraph ;
                    v.previous = graph;
                    grapheQueue.add(v);
                }
            }
        }*/
    }
    public ArrayList<ArrayList> plusCourtCheminCoord(List<graphe> path){
        ArrayList <ArrayList> coords=new ArrayList<ArrayList>();;
        ArrayList <Integer> coord;
        for (graphe graphe : path) {
            coord=new ArrayList<Integer>();
            coord.add(graphe.x);
            coord.add(graphe.y);
            coords.add(coord);
          }
        return coords;
    }

    public List<graphe> getShortestPathTo(graphe target)
    {
        List<graphe> path = new ArrayList<graphe>();
        for (graphe graphe = target; graphe != null; graphe = graphe.previous)
            path.add(graphe);

        Collections.reverse(path);
        return path;
    }

    /*public static void main(String[] args)
    {
     
        graphe A = new graphe("A",1,1);
        System.out.println(A.x);
        graphe B = new graphe("B",1,1);
        graphe D = new graphe("D",1,1);
        graphe F = new graphe("F",1,1);
        graphe K = new graphe("K",1,1);
        graphe J = new graphe("J",1,1);
        graphe M = new graphe("M",1,1);
        graphe O = new graphe("O",1,1);
        graphe P = new graphe("P",1,1);
        graphe R = new graphe("R",1,1);
        graphe Z = new graphe("Z",1,1);

        
        Noeud[] noeud=new Noeud[2];
        noeud[0]=new Noeud(M, 1);
        noeud[1]=new Noeud(D, 1);
        A.adjacencies = noeud;
        B.adjacencies = new Noeud[]{ new Noeud(D,1) };
        D.adjacencies = new Noeud[]{ new Noeud(B, 1),new Noeud(Z, 1) };
        F.adjacencies = new Noeud[]{ new Noeud(K, 1) };
        K.adjacencies = new Noeud[]{ new Noeud(O, 1) };
        J.adjacencies = new Noeud[]{ new Noeud(K, 1) };
        M.adjacencies = new Noeud[]{ new Noeud(R, 1) };
        O.adjacencies = new Noeud[]{ new Noeud(K, 1) };
        P.adjacencies = new Noeud[]{ new Noeud(Z, 1) };
        R.adjacencies = new Noeud[]{ new Noeud(P, 1) };
        Z.adjacencies = new Noeud[]{ new Noeud(P, 1) };
        graphe graphes[]=new graphe[25];
        int indice=0;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                graphes[indice]=new graphe(i+""+j,i,j);
                if((j-1>=0 || j+1<=4) && (i>=0 || i+1<=4)){
                    noeud[0]=new Noeud(M, 1);
                    noeud[1]=new Noeud(D, 1);

                }

                indice+=1;
            
            }
        }
        for(int h=0;h<25;h++){
            System.out.println(graphes[h].name);
        }
       
        plusCourtChemin(A); 
        System.out.println("Distance to " + Z + ": " + Z.minDistance);
        List<graphe> path = getShortestPathTo(Z);
        System.out.println("Path: " + path);
        System.out.println(plusCourtCheminCoord(path));
    }*/
}