
package mx.ipn.cic.ada.app;

import java.util.logging.Level;
import java.util.logging.Logger;
import mx.ipn.cic.ada.graph.Edge;
import mx.ipn.cic.ada.graph.Graph;
import mx.ipn.cic.ada.graph.Node;
import mx.ipn.cic.ada.graph.UDGraph;

/**
 *
 * @author Miguel Moreno
 */
public class ADAProject {

    /**
     * Método principal
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /* Grafo no dirigido */
        Graph udgraph = new UDGraph();
        
        try{
            // Agregamos nodos
            Node n1 = new Node(1);
            Node n2 = new Node(2);
            udgraph.addNode(n1);
            udgraph.addNode(n2);
            
            // Agregamos aristas
            udgraph.addEdge(new Edge(n1, n2));
            udgraph.addEdge(new Edge(n2, n2));
            
            // Calculamos el orden de cada nodo
            for (Node node: udgraph.getV()) {
                String msg = "El orden de n"+node.getId();
                msg += " es "+udgraph.getDegree(node);
                System.out.println(msg);
            }
            
            // Imprimimos grafo
            System.out.println(udgraph);
        } 
        catch (Exception ex) {
            Logger.getLogger(ADAProject.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        
    }
    
}
