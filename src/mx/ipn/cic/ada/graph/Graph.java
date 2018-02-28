
package mx.ipn.cic.ada.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import mx.ipn.cic.ada.math.Counter;

/**
 *
 * @author Miguel Moreno
 */
public abstract class Graph {
    
    protected List<Node> V;
    protected List<Edge> E;
    
    /**
     * Obtiene un nodo de manera aleatoria
     * @param V conjunto de nodos
     * @return 
     */
    private static Node getRandomNode(List<Node> V){        
        int min = 0;
        int max = V.size()-1;
        int randomNum = new Random().nextInt((max - min) + 1) + min; 
        return V.get(randomNum);
    }
    
    private static int validateInput(boolean isDigraph, boolean hasAutocycle, int n, int m) throws Exception{        
        
        // Validamos número de nodos
        if(n<0)
            throw new Exception("Número de nodos no puede ser menor a 0");
        
        // Validamos número máximo de aristas
        if(m<0)
            throw new Exception("Número de aristas no puede ser menor a 0");          
                
        // Calculamos el número maximo de arista
        int maxEdges = 0;
        if(isDigraph){            
            if(hasAutocycle){
                if(n == 0)
                    maxEdges = 0;
                else if (n == 1)
                    maxEdges = 1;
                else
                    maxEdges = Counter.permutation(n, 2, true);
            }                
            else{
                if(n == 0)
                    maxEdges = 0;
                else if (n == 1)
                    maxEdges = 0;
                else
                    maxEdges = Counter.permutation(n, 2, false);
            }           
        }
        else{
            if(hasAutocycle){
                if(n == 0)
                    maxEdges = 0;
                else if (n == 1)
                    maxEdges = 1;
                else
                    maxEdges = Counter.combination(n, 2, true);
            }
            else{
                if(n == 0)
                    maxEdges = 0;
                else if (n == 1)
                    maxEdges = 0;
                else
                    maxEdges = Counter.combination(n, 2, false);
            }
        }
        
        if(m > maxEdges) 
            throw new Exception("No pueden haber mas de "+maxEdges+" aristas");
        
        return maxEdges;
    
    }
    
    public static Graph createByErdosRenyi(boolean isDigraph, boolean hasAutocycle, int n, int m) throws Exception{        
        Graph graph = null;
        
        // Validamos los datos de entrada
        int maxEdges = validateInput(isDigraph,hasAutocycle,n,m);
        
        // Construimos grafos
        if(isDigraph)
            graph = new DIGraph();
        else 
            graph = new UDGraph();
        
        // Generamos n cantidad de nodos
        for(int i=1;i<=n;i++)
            graph.addNode(new Node(String.valueOf(i)));
        
        // Generamos m cantidad de aristas
        
        
        return graph;       
    }
    

    protected Graph() {
        this.V = new ArrayList<Node>();
        this.E = new ArrayList<Edge>();
    }

    public List<Node> getV() {
        return V;
    }

    public List<Edge> getE() {
        return E;
    }      

    @Override
    public String toString() {
        String g = "Graph{\n" ;
        
        g+= "\tV[";
        for (int i=0; i<=this.V.size()-1; i++) {
            if(i == this.V.size()-1)
                g+=this.V.get(i);
            else
                g+=this.V.get(i)+",";
        }
        g+= "]\n";
        
        g+= "\tE[";
        for (int i=0; i<=this.E.size()-1; i++) {
            if(i == this.E.size()-1)
                g+=this.E.get(i);
            else
                g+=this.E.get(i)+",";
        }
        g+= "]\n";        
        g += '}';
        
        return g;
    }
    
    
    
   /**
    * Agrega un nodo al grafo
    * @param n nodo
    * @throws Exception 
    */
    public abstract void addNode(Node n) throws Exception;
    
    /**
     * 
     * @param id
     * @return nodo
     * @throws Exception 
     */
    public abstract Node getNode(String id) throws Exception;;
    
    /**
     * Agrega una arista al grafo
     * @param e arista
     * @throws Exception 
     */
    public abstract void addEdge(Edge e) throws Exception;
    
    /**
     * Obtiene el grado de un nodo dado
     * @param n nodo
     * @return grado
     */
    public abstract int getDegree(Node n);
    
    /**
     * Genera archivo con código Graphviz
     * @param destFile ruta archivo destino
     */
    public abstract void toGraphviz(String destFile) throws Exception;
    
}
