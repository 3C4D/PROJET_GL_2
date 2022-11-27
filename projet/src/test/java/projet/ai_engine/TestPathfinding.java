package projet.ai_engine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import javax.net.ssl.TrustManager;
import java.util.List;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.*;
import java.lang.Math;
public class TestPathfinding {

    @Test
	public void euclideanDistanceComparator() throws Exception{
        int n=3;
        // we have this matrix
        /* 1 1 1
         * 1 1 1 
         * 1 1 1
         */
        int [][]matrix=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                matrix[i][j]=1; 
            }
        }
        Pathfinding test=new Pathfinding(n*n);
        test.updateGraph(matrix,n,true);
        assertEquals(test.euclideanDistanceComparator(0,1,8),false);
        assertEquals(test.euclideanDistanceComparator(0,8,1),true);

    }
    @Test
	public void TestupdateGraph() throws Exception{
        // we already tested matrixsTograph so we will just notice if the change worked after calling updateGraph with another matrix.
    	int n=5;
        int nodeNumber=n*n;
        Pathfinding test=new Pathfinding(nodeNumber);
        int [][]matrix=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                matrix[i][j]=1;
            }
        }
       
        test.updateGraph(matrix,n,true);
        assert(test.graph.list.get(1*n+1).size()>0);
        // we change the value at position 1 1 to 0.
        matrix[1][1]=0;
        test.updateGraph(matrix,n,true);

        assertEquals(test.graph.list.get(1*n+1).size(),0);

    }

    @Test
	public void Testpathfinding() throws Exception{
    
        LinkedList <int[]>coords;
        int n=3;
        int nodeNumber=n*n;
        Pathfinding test=new Pathfinding(nodeNumber);
        int nombreAleatoire;

        int [][]matrix=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                matrix[i][j]=1; 
            }
        }

        //first test with dijkstra and weighted node
        matrix[1][1]=0;
        matrix[1][2]=3;
        for(int i=0;i<n;i++) System.out.println(Arrays.toString(matrix[i]));
        /* we have this matrix
         * 1 1 1
         * 7 0 3
         * 1 1 1
         */

        // this shortest path from (0,0) to (2,2) is : (0,0),(1,0),(2,0),(2,1),(2,2)
        
        coords=new LinkedList <int[]>();
        test.updateGraph(matrix,n,false);
        //test.graph.printGraph();
        coords=test.pathfinding(0,nodeNumber-1,2);
        System.out.println("Chemin le plus court en (0,0) et (2,2) : ");
        for(int i=0;i<coords.size();i++) System.out.println(Arrays.toString(coords.get(i)));
        // (0,0)
        assert(coords.get(0)[0]==0 && coords.get(0)[1]==0);
     
        //(1,0)
        assert(coords.get(1)[0]==1 && coords.get(1)[1]==0);

        //(2,0)
        assert(coords.get(2)[0]==2 && coords.get(2)[1]==0);

        //(2,1)
        assert(coords.get(3)[0]==2 && coords.get(3)[1]==1);
        //(2,2)
        assert(coords.get(4)[0]==2 && coords.get(4)[1]==2);


        //second test with dijkstra with heuristics without weighted nodes but with nodes weigthed to 0 on a 1000*1000 grid
        coords=new LinkedList <int[]>();
        n=1000;
        nodeNumber=n*n;
        test=new Pathfinding(nodeNumber);
        matrix=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                nombreAleatoire= 0 + (int)(Math.random() * ((4 - 0) + 1));
                if(nombreAleatoire==0){
                    matrix[i][j]=0;

                }
                else{
                    matrix[i][j]=1;
                }
            }
        }
        
        //we make sure, start and arrival exists
        matrix[n-2][n-1]=1;
        matrix[n-1][n-1]=1;
        matrix[0][0]=1;
        matrix[1][0]=1;
        int indice1=0;
        int indice2=0;
        boolean found;
        test.updateGraph(matrix,n,true);
        coords=test.pathfinding(0,nodeNumber-1,2);
        // We generated randomly this grid with blank nodes, there may be no path from the start to the arrival
        //lets verify we have a path
        int size=coords.size();
        if(size>0){
            // now lets verify if this path is correct
            for(int i=0;i<size-1;i++){
                // we verify the two coordonates are neighbors
                assert(Math.abs(coords.get(i)[0]-coords.get(i+1)[0])<=1 && Math.abs(coords.get(i)[1]-coords.get(i+1)[1])<=1);

                // we verify the is an edge betweens those two coordonates
                indice1=coords.get(i)[0]*n+coords.get(i)[1];
                indice2=coords.get(i+1)[0]*n+coords.get(i+1)[1];
                found=false;
                // we are looking for indice2 in indice1 edges
                for(int j=0;j<test.graph.list.get(indice1).size();j++){
                    if(test.graph.list.get(indice1).get(j).indice==indice2){
                        found=true;
                    }
                
            }
            
            assert(found);

        }	
    }
}

    @Test
    public void TestgetNearTo() throws Exception{
        // it is the same as doing test of Testpathfinding()
    }

    @Test
    public void Testgetwayfrom() throws Exception{
        LinkedList <int[]>coords;
        int n=10;
        int nodeNumber=n*n;
        Pathfinding test=new Pathfinding(nodeNumber);
        int [][]matrix=new int[n][n];
        coords=new LinkedList <int[]>();
        int nombreAleatoire;
        int indice1=0;
        int indice2=0;
        boolean found=false;
        matrix=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                nombreAleatoire= 0 + (int)(Math.random() * ((4 - 0) + 1));
                if(nombreAleatoire==0){
                    matrix[i][j]=0;

                }
                else{
                    matrix[i][j]=1;
                }
            }
        }

        matrix[0][9]=1;
        matrix[1][9]=1;
        matrix[0][8]=1;
        for(int i=0;i<n;i++) System.out.println(Arrays.toString(matrix[i]));
        test.updateGraph(matrix,n,false);
        coords=test.getAwayFrom(9);
        int size=coords.size();
        if(size>0){
            // now lets verify if this path is correct
            for(int i=0;i<size-1;i++){
                // we verify the two coordonates are neighbors
                assert(Math.abs(coords.get(i)[0]-coords.get(i+1)[0])<=1 && Math.abs(coords.get(i)[1]-coords.get(i+1)[1])<=1);

                // we verify the is an edge betweens those two coordonates
                indice1=coords.get(i)[0]*n+coords.get(i)[1];
                indice2=coords.get(i+1)[0]*n+coords.get(i+1)[1];
                found=false;
                // we are looking for indice2 in indice1 edges
                for(int j=0;j<test.graph.list.get(indice1).size();j++){
                    if(test.graph.list.get(indice1).get(j).indice==indice2){
                        found=true;
                    }
                
                }
            
            assert(found);

            }	
        System.out.println("Chemin pour s'éloigner de la position (0,9) : ");
        for(int i=0;i<coords.size();i++) System.out.println(Arrays.toString(coords.get(i)));

        }
        


    }


}
