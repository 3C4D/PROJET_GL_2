package projet.ai_engine;
import java.util.*;
import java.lang.Math;
/**
 * Class which implements Graph structure with useful methods.
 **/
public class Graph {
    public int nodeNumber;
    public ArrayList <LinkedList <Node>> list;
    public ArrayList <Node> listNodes;

    /**Constructor of Graph's class 
     * @param nodeNumber (this is the number of node in our graph)
    **/
    public Graph(int nodeNumber) {
        this.nodeNumber = nodeNumber;
        this.list = new ArrayList<LinkedList<Node>>();
        this.listNodes=new ArrayList<Node>();
        for (int i = 0; i <nodeNumber ; i++) {
            this.list.add(new LinkedList<Node>());
        }
    }


    
    /** Method which add edge betweens two nodes.
     * @param firstNode
     * @param secondNode
     */
    public void addEdge(Node firstNode,Node secondNode){

        //add edge
        this.list.get(firstNode.indice).addFirst(secondNode);

        //add back edge ((for undirected)
        this.list.get(secondNode.indice).addFirst(firstNode);
    }


    /** Method which print the graph.
     */
    public void printGraph(){
        for (int i = 0; i <nodeNumber ; i++) {
            if(this.list.get(i).size()>0) {
                System.out.print("node" + i + " is connected to: ");
                for (int j = 0; j < this.list.get(i).size(); j++) {
                    System.out.print(this.list.get(i).get(j).indice + " ");
                }
                System.out.println();
            }
        }
    }

   
    
    /** Method which create a graph according to a matrix according to this definition : 
    *For every vertices (x,y) the cost from x to y is 0 if matrix[x][y]==0 (there is no node) else he is equal to matrix[x][y] ; 
    * 
     * @param [][]matrix (This matrix can take value i=0 to n where i is the weight, if i=0 then the node wont be connected to others)
     * @param n
     * @param diagonal (True if the node is connected to his diagonal's node)
     * @param symetrical (True if the cost is the same between two nodes in both directions)
     */
    public void matrixToGraph(int [][]matrix,int n,boolean diagonal){
        this.list = new ArrayList<LinkedList<Node>>();

        for (int i = 0; i <this.nodeNumber ; i++) {
            this.list.add(new LinkedList<Node>());
        }
        this.listNodes=new ArrayList<Node>();

        int indice=0;


        // Creation of list nodes
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    this.listNodes.add(new Node(i,j,i+""+j,indice,matrix[i][j]));
                    indice+=1;
                }
            }
        

        indice=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]!=0){
                    //case right
                    if(j+1<n && matrix[i][j+1]!=0) this.addEdge(this.listNodes.get(indice),this.listNodes.get(indice+1));

                    //case down
                    if(i+1<n && matrix[i+1][j]!=0) this.addEdge(this.listNodes.get(indice),this.listNodes.get(indice+n));

                    //case down right
                    if(diagonal && i+1<n && j+1<n && matrix[i+1][j+1]!=0) this.addEdge(this.listNodes.get(indice),this.listNodes.get(indice+n+1));

                    //case down left
                    if(diagonal && i+1<n && j-1>=0 && matrix[i+1][j-1]!=0) this.addEdge(this.listNodes.get(indice),this.listNodes.get(indice+n-1));
            
                
                }
                indice+=1;
            }
        }

    }
   
}
