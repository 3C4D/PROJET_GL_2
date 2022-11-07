package moteur_ia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import javax.net.ssl.TrustManager;
import java.util.List;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
public class TestDijkstra {



	
	/** 
	 * @throws Exception
	 */
	@Test
	public void testDijkstra() throws Exception {
	
	 Dijkstra test=new Dijkstra();
	 graphe A = new graphe("A",1,1);
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
	 test.graphes.add(new graphe("A",1,1));
	 test.graphes.add(B);
	 test.graphes.add(D);
	 test.graphes.add(F);
	 test.graphes.add(K);
	 test.graphes.add(J);
	 test.graphes.add(M);
	 test.graphes.add(O);
	 test.graphes.add(P);
	 test.graphes.add(R);
	 test.graphes.add(Z);
	 System.out.println(test.graphes);

	 Noeud[] noeud=new Noeud[2];
	 noeud[0]=new Noeud(M, 1);
	 noeud[1]=new Noeud(D, 1);
	 System.out.println(test.graphes.get(0));
	 
	 test.graphes.get(0).adjacencies = noeud;
	 System.out.println(test.graphes.get(0).adjacencies);
	 test.graphes.get(test.graphes.indexOf(B)).adjacencies = new Noeud[]{ new Noeud(D,1) };
	 test.graphes.get(test.graphes.indexOf(D)).adjacencies = new Noeud[]{ new Noeud(B, 1),new Noeud(Z, 1) };
	 test.graphes.get(test.graphes.indexOf(F)).adjacencies = new Noeud[]{ new Noeud(K, 1) };
	 test.graphes.get(test.graphes.indexOf(K)).adjacencies = new Noeud[]{ new Noeud(O, 1) };
	 test.graphes.get(test.graphes.indexOf(J)).adjacencies = new Noeud[]{ new Noeud(K, 1) };
	 test.graphes.get(test.graphes.indexOf(M)).adjacencies = new Noeud[]{ new Noeud(R, 1) };
	 test.graphes.get(test.graphes.indexOf(O)).adjacencies = new Noeud[]{ new Noeud(K, 1) };
	 test.graphes.get(test.graphes.indexOf(P)).adjacencies = new Noeud[]{ new Noeud(Z, 1) };
	 test.graphes.get(test.graphes.indexOf(R)).adjacencies = new Noeud[]{ new Noeud(P, 1) };
	 test.graphes.get(test.graphes.indexOf(Z)).adjacencies = new Noeud[]{ new Noeud(P, 1) };
	 /*graphe graphes[]=new graphe[25];
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
	*/
	 test.plusCourtChemin(test.graphes.get(0)); 
	 /* 
	 System.out.println("Distance to " + Z + ": " + Z.minDistance);
	 List<graphe> path = test.getShortestPathTo(Z);
	 System.out.println("Path: " + path);
	 System.out.println(test.plusCourtCheminCoord(path));
		
*/
	}
	

	




}
