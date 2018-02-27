
package mx.ipn.cic.ada.app;

import java.util.logging.Level;
import java.util.logging.Logger;
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
            udgraph.addNode(new Node(1));
            udgraph.addNode(new Node(2));
        } 
        catch (Exception ex) {
            Logger.getLogger(ADAProject.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        
    }
    
}
