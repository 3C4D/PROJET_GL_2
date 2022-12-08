package projet.ai_engine;
import java.lang.reflect.Array;
import java.util.*;
/**
 * Class which implements pathfindings methods in a 2d game.
 **/
public class Pathfinding {
    public Graph graph;
    
    int visited[];
    int distance[];

    int pathDijkstra[];
    int pathHeuristic[];

    int numberNodes;
    boolean dijkstraComputed;
    boolean dijkstraHeuristicComputed;

    /**Constructor of Pathfinding's class 
     * @param numberNodes (this is the number of node in our graph)
     **/
    Pathfinding(int numberNodes){
        this.graph=new Graph(numberNodes);
        this.visited= new int[numberNodes];
        this.distance=new int [numberNodes];
        this.pathDijkstra=new int [numberNodes];
        this.pathHeuristic=new int [numberNodes];
        this.numberNodes=numberNodes;

        this.dijkstraComputed=false;
        this.dijkstraHeuristicComputed=false;

        for(int i=0;i<numberNodes;i++){
            this.distance[i]=-1;
            this.visited[i]=0;
            this.pathDijkstra[i]=-1;
            this.pathHeuristic[i]=-1;
        }

    }

    /** Method which update the graph with a new 2d grid
     * @param [][]matrix
     * @param n
     * @param diagonal
     */
    public void updateGraph(int [][]matrix,int n,boolean diagonal){
        // Graph reset 
        for(int i=0;i<numberNodes;i++){
            this.distance[i]=-1;
            this.visited[i]=0;
            this.pathDijkstra[i]=-1;
            this.pathHeuristic[i]=-1;
        }
        this.dijkstraComputed=false;
        this.dijkstraHeuristicComputed=false;
        this.graph.matrixToGraph(matrix, n,diagonal);
    }


    
    /** Method which implements dijkstra algorithm
     * @param start
     * @param arrival
     * @return boolean (true if a path exists)
     */
    public boolean dijkstra(int start,int arrival){
        int numberVisited=1;
        int currentNode=start;
        this.visited[currentNode]=1;
        int indice=0;
        if(graph.list.get(start).size()==0 || graph.list.get(arrival).size()==0){
            return false;
        }
        while(indice<this.numberNodes) {
            if(this.graph.listNodes.get(indice).cost==0){ 
                this.visited[indice]=1;
                numberVisited+=1;
            }
            indice+=1;
        }
        distance[currentNode]=0;
        this.pathDijkstra[start]=start;
        boolean noPath=false;
        boolean arrivalFound=false;
        while(numberVisited<numberNodes && noPath==false){
        
            int sizeLinkedList=graph.list.get(currentNode).size();
            for(int j=0;j<sizeLinkedList;j++){
                if(this.distance[graph.list.get(currentNode).get(j).indice]==-1 && currentNode==start){
                    this.pathDijkstra[this.graph.list.get(currentNode).get(j).indice]=currentNode;
                    this.distance[graph.list.get(currentNode).get(j).indice]=0;
                    this.distance[graph.list.get(currentNode).get(j).indice]+=this.graph.list.get(currentNode).get(j).cost;
                
                }
                else if(this.distance[graph.list.get(currentNode).get(j).indice]==-1 && currentNode!=start){
                    this.pathDijkstra[this.graph.list.get(currentNode).get(j).indice]=currentNode;
                    this.distance[this.graph.list.get(currentNode).get(j).indice]=this.distance[currentNode]+this.graph.list.get(currentNode).get(j).cost;
                }

                else if(this.distance[currentNode]+this.graph.list.get(currentNode).get(j).cost<distance[this.graph.list.get(currentNode).get(j).indice]){
                    this.pathDijkstra[this.graph.list.get(currentNode).get(j).indice]=currentNode;
                    this.distance[this.graph.list.get(currentNode).get(j).indice]=this.distance[currentNode]+this.graph.list.get(currentNode).get(j).cost;
                }
            }
            
            int distanceMin=0;
            int indiceMin=0;
            boolean newIndiceMin=false;
            for(int i=0;i<this.numberNodes;i++){
                if(this.visited[i]!=1){
                    if(distance[i]!=-1 && distance[i]!=0){
                        if(distanceMin==0 && i!=currentNode){
                            distanceMin=distance[i];
                            indiceMin=i;
                            newIndiceMin=true;
                        }
                        if(distance[i]<distanceMin){
                            distanceMin=distance[i];
                            indiceMin=i;
                            newIndiceMin=true;
                        }
                    }
                }
            }
            if(this.visited[currentNode]==0){
                numberVisited+=1;
            }
            this.visited[currentNode]=1;
            if(currentNode==indiceMin){
                
            }
            if(!newIndiceMin){
                noPath=true;
            }
            currentNode=indiceMin;
            if(currentNode==arrival){
                arrivalFound=true;
            }
        }
   
        if(noPath==true && !arrivalFound) return false;
        else{    
            for(int i=0;i<numberNodes;i++){
                this.distance[i]=-1;
                this.visited[i]=0;
            }
            this.dijkstraComputed=true;
            return true; }
    }
        
    /** Method which compute if node1 is closer (or equal) to node3 than node2 according to the euclidean distance
     * @param node1
     * @param node2
     * @param node3
     * @return boolean
     */
    public boolean euclideanDistanceComparator(int node1,int node2,int node3){

        double x=(double)this.graph.listNodes.get(node1).x;
        double y=(double)this.graph.listNodes.get(node1).y;

        double xprime=(double)this.graph.listNodes.get(node2).x;
        double yprime=(double)this.graph.listNodes.get(node2).y;

        double euclideanDistance1;
        double euclideanDistance2;

        euclideanDistance1=Math.sqrt(Math.pow(x-xprime,2)+Math.pow(y-yprime,2));

        xprime=(double)this.graph.listNodes.get(node3).x;
        yprime=(double)this.graph.listNodes.get(node3).y;
        euclideanDistance2=Math.sqrt(Math.pow(x-xprime,2)+Math.pow(y-yprime,2));
    

    

        return euclideanDistance2<=euclideanDistance1;

    }

    
    /** Method which implements path finding algorithm with heuristic, 
     * the heuristic is when we are looking to new nodes we choose the one that is closer(according to euclidean distance) to the arrival
     * also when we reach the arrival node we stop,
     * this algorithm will mostly be used when we have matrix of 150*150 or more.
     * this algorithm works only on an unweighted graph
     * @param start
     * @param arrival
     * @return boolean (true if a path exists)
     */
    public boolean pathFindingWithHeurisitc(int start,int arrival){

        if(graph.list.get(start).size()==0 || graph.list.get(arrival).size()==0 ){
          
            return false;}
        else{
        int numberVisited=1;
        int currentNode=start;
        this.visited[currentNode]=1;
        int indice=0;
        while(indice<this.numberNodes) {
            if(this.graph.listNodes.get(indice).cost==0){ this.visited[indice]=1;
            numberVisited+=1;}
            indice+=1;}
        distance[currentNode]=0;
        this.pathHeuristic[start]=start;
        boolean noPath=false;
        while(numberVisited<numberNodes && currentNode!=arrival && noPath==false){

                int sizeLinkedList=graph.list.get(currentNode).size();
                for(int j=0;j<sizeLinkedList;j++){
                    if(this.distance[graph.list.get(currentNode).get(j).indice]==-1 && currentNode==start){
                        this.pathHeuristic[this.graph.list.get(currentNode).get(j).indice]=currentNode;
                        this.distance[graph.list.get(currentNode).get(j).indice]=0;
                        this.distance[graph.list.get(currentNode).get(j).indice]+=this.graph.list.get(currentNode).get(j).cost;
                    
                    }
                    else if(this.distance[graph.list.get(currentNode).get(j).indice]==-1 && currentNode!=start){
                        this.pathHeuristic[this.graph.list.get(currentNode).get(j).indice]=currentNode;
                        this.distance[this.graph.list.get(currentNode).get(j).indice]=this.distance[currentNode]+this.graph.list.get(currentNode).get(j).cost;
                    }

                    else if(this.distance[currentNode]+this.graph.list.get(currentNode).get(j).cost<distance[this.graph.list.get(currentNode).get(j).indice]){
                        this.pathHeuristic[this.graph.list.get(currentNode).get(j).indice]=currentNode;
                        this.distance[this.graph.list.get(currentNode).get(j).indice]=this.distance[currentNode]+this.graph.list.get(currentNode).get(j).cost;
                    }
                }
                
                int distanceMin=0;
                int indiceMin=0;
                boolean newIndiceMin=false;
                for(int i=0;i<this.numberNodes;i++){
                    if(this.visited[i]!=1){
                    if(distance[i]!=-1 && distance[i]!=0){
                        if(distanceMin==0 && i!=currentNode){
                            distanceMin=distance[i];
                            indiceMin=i;
                        }
                        if(euclideanDistanceComparator(arrival,indiceMin,i)){
                            distanceMin=distance[i];
                            indiceMin=i;
                            newIndiceMin=true;
                        }
                    }
                }}
                if(this.visited[currentNode]==0){
                    numberVisited+=1;
                }
                this.visited[currentNode]=1;
                if(currentNode==indiceMin){
                    
                }
                if(!newIndiceMin){
                    noPath=true;
                }
      
                currentNode=indiceMin;
            }
            if(noPath==true) return false;
            else{
                for(int i=0;i<numberNodes;i++){
                    this.distance[i]=-1;
                    this.visited[i]=0;
                }
                this.dijkstraHeuristicComputed=true;
                return true;
            }
            }
    }




        
    /** Method which return the path from a point start to arrival with a specified algorithm
     * @param start 
     * @param arrival
     * @param algorithm (1 = dijkstra, 2=pathFindingWithHeurisitc)
     * @return LinkedList<int[]> (LinkedList of coordonate)
     **/
    public LinkedList<int[]> pathfinding(int start,int arrival,int algorithm){
        final LinkedList <int[]>coords=new LinkedList <int[]>();
        boolean pathExist=false;
        if(algorithm==1){
            if(dijkstraComputed){pathExist=true;}
            
            else{
                if(this.dijkstra(start, arrival)){
                    pathExist=true;
                }
            }
        }
        else if(algorithm==2){
            if(dijkstraHeuristicComputed){pathExist=true;}
            
            else{
                if(this.pathFindingWithHeurisitc(start, arrival)){
                    pathExist=true;
                }
            }
        }

        if(pathExist){
            int [] coord=new int[2];
            int indice=arrival;
        while(indice!=start){
            coord=new int[2];
            coord[0]=this.graph.listNodes.get(indice).x;
            coord[1]=this.graph.listNodes.get(indice).y;
            coords.addFirst(coord);
            if(algorithm==1) indice=this.pathDijkstra[indice];
            else if(algorithm==2) {indice=this.pathHeuristic[indice];}
        }
        coord=new int[2];
        coord[0]=this.graph.listNodes.get(indice).x;
        coord[1]=this.graph.listNodes.get(indice).y;
        coords.addFirst(coord);
        return coords;

        }
        else{
            return coords;
        
        }

}
    
    /** Method to get to a point 
     * @param start
     * @param arrival
     * @param method (0 for dijkstra 1 for pathFindingWithHeurisitc)
     * @return LinkedList<int[]> (LinkedList of coordonate)
     */
    public LinkedList<int[]> getTo(int start,int arrival,int method){
        return this.pathfinding(start,arrival,method);
    }



    
    /** Method to get as far as we can from our position 
     * @param start
     * @return LinkedList<int[]> (LinkedList of coordonate)
     */
    
    public LinkedList<int[]> getAwayFrom(int start){
        final LinkedList <int[]>coords=new LinkedList <int[]>();
        int [] coord=new int[2];
        // We define an arrival 
        int arrival;
        if(start+1<this.numberNodes) arrival=start+1;
        else{ arrival=start-1;}
        if(dijkstraComputed || this.dijkstra(start,arrival)){
            int indiceMin=0;
            for(int i=0;i<this.numberNodes;i++){
                if(this.graph.list.get(i).size()>0){
                    if(!this.euclideanDistanceComparator(start, indiceMin, i)&& i!=indiceMin){
                        indiceMin=i;
                    }
                }
            }
            int indice=indiceMin;
        
            while(indice!=start){
                coord=new int[2];
                coord[0]=this.graph.listNodes.get(indice).x;
                coord[1]=this.graph.listNodes.get(indice).y;
                Arrays.toString(coord);
                coords.addFirst(coord);
                indice=this.pathDijkstra[indice];
            
            }
            coord=new int[2];
            coord[0]=this.graph.listNodes.get(indice).x;
            coord[1]=this.graph.listNodes.get(indice).y;
            coords.addFirst(coord);
            return coords;
        }
        else{
            return coords;
        
        }

    }
    }
