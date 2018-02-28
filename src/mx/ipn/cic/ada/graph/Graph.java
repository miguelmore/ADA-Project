
package mx.ipn.cic.ada.graph;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel Moreno
 */
public abstract class Graph {
    protected List<Node> V;
    protected List<Edge> E;

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
