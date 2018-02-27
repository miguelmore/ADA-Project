
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
    
    /**
     * Agrega un nodo al grafo
     * @param n nodo
     */
    public abstract void addNode(Node n) throws Exception;
    
    /**
     * Agrega una arista al grafo
     * @param e arista
     */
    public abstract void addEdge(Edge e);
    
    /**
     * Obtiene el grado de un nodo dado
     * @param n nodo
     * @return grado
     */
    public abstract int getDegree(Node n);
}
