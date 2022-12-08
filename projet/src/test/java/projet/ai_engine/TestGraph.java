package projet.ai_engine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import javax.net.ssl.TrustManager;
import java.util.List;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.*;
import java.lang.Math;
public class TestGraph {


	@Test
	public void TestaddEdge() throws Exception{

		//Initialisation du graphe 
		Graph graph;

		graph=new Graph(4);
		graph.list = new ArrayList<LinkedList<Node>>();

        for (int i = 0; i <graph.nodeNumber ; i++) {
            graph.list.add(new LinkedList<Node>());
        }
		int indice=0;
		for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                graph.listNodes.add(new Node(i,j,i+""+"j",indice,1));
                indice+=1;
            }}
        

        // we add edges
		graph.addEdge(graph.listNodes.get(0),graph.listNodes.get(1));
		graph.addEdge(graph.listNodes.get(2),graph.listNodes.get(3));
        
        // we verify those edges are in the graph
		assertEquals(graph.list.get(0).get(0).indice,1);
		assertEquals(graph.list.get(1).get(0).indice,0);

		assertEquals(graph.list.get(2).get(0).indice,3);
		assertEquals(graph.list.get(3).get(0).indice,2);
	}

	@Test
	public void TestmatrixToGraph() throws Exception{

		Graph graph;
		int n=2;
		graph=new Graph(n*n);
		int [][]matrix=new int[n][n];
		for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
            	matrix[i][j]=1;
            }
        }

		// For now all points of this matrix is linked with his neighbours 

		matrix[1][1]=0;

		// The node at coordonates (1,1) is now linked with no others node.

		graph.matrixToGraph(matrix, n,true);


		// we check central point is linked with nothing :
		assertEquals(graph.list.get(1*n+1).size(),0);
		int indice=0;
		for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
				if(indice!= 1*n+1){
					assertEquals(graph.list.get(indice).get(0).indice==n*1+1,false);
					assertEquals(graph.list.get(indice).get(1).indice==n*1+1,false);
				}
				indice+=1;
            }
        }
		//graph.printGraph();
		assertEquals(graph.list.get(0).get(0).indice==2,true);
		assertEquals(graph.list.get(0).get(1).indice==1,true);

		assertEquals(graph.list.get(1).get(0).indice==2,true);
		assertEquals(graph.list.get(1).get(1).indice==0,true);

		assertEquals(graph.list.get(2).get(0).indice==1,true);
		assertEquals(graph.list.get(2).get(1).indice==0,true);



	}





	
	
	
	}
	

	





