package graph;
import java.util.ArrayList;
/**
 * This class implements general operations on a graph as specified by UndirectedGraphADT.
 * It implements a graph where data is contained in Vertex class instances.
 * Edges between verticies are unweighted and undirected.
 * A graph coloring algorithm determines the chromatic number. 
 * Colors are represented by integers. 
 * The maximum number of vertices and colors must be specified when the graph is instantiated.
 * You may implement the graph in the manner you choose. See instructions and course material for background.
 */
 
 public class UndirectedUnweightedGraph<T> implements UndirectedGraphADT<T> {
   // private class variables here.
   
   private int MAX_VERTICES;
   private int MAX_COLORS;
   private ArrayList<Vertex<T>> graph;
   private boolean[][] edges;
   private int verts;
   private int connects;
   
    

   
   /**
    * Initialize all class variables and data structures. 
   */   
   public UndirectedUnweightedGraph (int maxVertices, int maxColors){
      MAX_VERTICES = maxVertices;
      MAX_COLORS = maxColors; 
      graph = new ArrayList<Vertex<T>>();
      verts = 0;
      connects = 0;
      edges = new boolean[MAX_VERTICES][MAX_VERTICES];
      

   }

   /**
    * Add a vertex containing this data to the graph.
    * Throws Exception if trying to add more than the max number of vertices.
   */
   public void addVertex(T data) throws Exception {
    if(graph.size() == MAX_VERTICES){
     throw new NullPointerException(); 
    }
    Vertex<T> newVert = new Vertex<T>(data);
    graph.add(newVert);
    verts += 1;
   }
   
   /**
    * Return true if the graph contains a vertex with this data, false otherwise.
   */   
   public boolean hasVertex(T data){

    if(graph.size() == 0){
      return false;
    }
    for(int i = 0; i < graph.size(); i++){
      if(graph.get(i).getData().equals(data)){
        return true;
      }
     
    }
    return false;
    }
   /**
    * Add an edge between the vertices that contain these data.
    * Throws Exception if one or both vertices do not exist.
   */   
   public void addEdge(T data1, T data2) throws Exception{
    boolean checkOne = false;
    boolean checkTwo = false;
    int indexOne = 0;
    int indexTwo = 0;

    for(int i = 0; i < graph.size(); i++){
      if(graph.get(i).getData().equals(data1)){
        checkOne = true;
        indexOne = i;
      }
      if(graph.get(i).getData().equals(data2)){
        checkTwo = true;
        indexTwo = i;
      }
    }
    if(checkOne && checkTwo){
      edges[indexOne][indexTwo] = true;
      edges[indexTwo][indexOne] = true; 
      connects += 1;
    }
      else{
      throw new NullPointerException();
    }


   }

   /**
    * Get an ArrayList of the data contained in all vertices adjacent to the vertex that
    * contains the data passed in. Returns an ArrayList of zero length if no adjacencies exist in the graph.
    * Throws Exception if a vertex containing the data passed in does not exist.
   */   
   public ArrayList<T> getAdjacentData(T data) throws Exception{
    ArrayList<T> edgeData = new ArrayList<T>();
    
    int i = 0;
    int index = 0;
    while(i < graph.size()){
      if(graph.get(i).getData().equals(data)){
        index = i;
        i = graph.size();
      }
      i += 1;
    }
    for(int j = 0; j < MAX_VERTICES; j++){
      if(edges[index][j] == true){
        edgeData.add(graph.get(j).getData());
      }
    }
      return edgeData;
   }
   
   /**
    * Returns the total number of vertices in the graph.
   */   
   public int getNumVertices(){
      return verts;
   }

   /**
    * Returns the total number of edges in the graph.
   */   
   public int getNumEdges(){
      return connects;
   }

   /**
    * Returns the minimum number of colors required for this graph as 
    * determined by a graph coloring algorithm.
    * Throws an Exception if more than the maximum number of colors are required
    * to color this graph.
   */   
   public int getChromaticNumber() throws Exception{
    ArrayList<Integer> colors = new ArrayList<Integer>();
    int nodesColored = 0;
    int minColors = 0;
    for(int i = 0; i < MAX_COLORS;i++){
      colors.add(i);
    }

    for(int i = 0; i < graph.size();i++){
      ArrayList<Integer> connectedColors = new ArrayList<Integer>(); 
      for(int j = 0; j < graph.size();j++){
        if(edges[i][j] == true){
          connectedColors.add(graph.get(j).getColor()); 
        }   
      }

      int k = 0;
      while(k < MAX_COLORS){
        if(!connectedColors.contains(k)){
          graph.get(i).setColor(k);
          nodesColored += 1;
          if(k + 1 > minColors){
            minColors = k + 1;
          }
          k = colors.size();
        }
        k++;
      }
      

     }

     if(nodesColored == graph.size()){
      return minColors;
     }
     else{
       throw new Exception();
     }
     
     
   }
    
  
   }
   