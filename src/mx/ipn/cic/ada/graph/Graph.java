
package mx.ipn.cic.ada.graph;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel Moreno
 */
public abstract class Graph {
    private List V;
    private List E;

    protected Graph() {
        this.V = new ArrayList<Node>();
        this.E = new ArrayList<Edge>();
    }
    
    public abstract void addNode();
    public abstract void addEdge();
}
