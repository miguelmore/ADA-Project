
package mx.ipn.cic.ada.app;

import java.util.logging.Level;
import java.util.logging.Logger;
import mx.ipn.cic.ada.graph.DIGraph;
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
                       
        try {
            int numNodos = 500;
            //final String URL_BASE = "C:\\Users\\SIA Miguel\\Documents\\Segundo\\Diseño y Análisis de Algoritmos\\Proyecto1\\";
            final String URL_BASE = "/home/komodo/Documents/Cic/Semestre 2/Diseño y Análisis  de Algoritmos//Proyecto 1/archivosGV/";
            
            // Generamos grafos
            System.out.println("\n\n-- Grafo Erdos Renyi --");
            Graph g1 = Graph.createByErdosRenyi(false, true, numNodos, numNodos/2);
            System.out.println(g1);            
            String destFile = URL_BASE + "ErdosRenyi-"+numNodos+".gv";            
            g1.toGraphviz(destFile);
            
            System.out.println("\n\n-- Grafo Gilbert --");
            g1 = Graph.createByGilbert(false, true, numNodos, 0.5f);
            System.out.println(g1);            
            destFile = URL_BASE + "Gilbert-"+numNodos+".gv";
            g1.toGraphviz(destFile);
            
            System.out.println("\n\n-- Grafo Geográfico Simple --");
            g1 = Graph.createByGeographic(false, false, numNodos, 0.1f);
            System.out.println(g1);
            destFile = URL_BASE + "Geographic-"+numNodos+".gv";
            g1.toGraphviz(destFile);            
            
            System.out.println("\n\n-- Grafo createByBarabasiAlbert --");
            g1 = Graph.createByBarabasiAlbert(false, numNodos, numNodos/10);
            System.out.println(g1);
            destFile = URL_BASE + "BarabasiAlbert-"+numNodos+".gv";
            g1.toGraphviz(destFile);
            
            
        } catch (Exception ex) {
            Logger.getLogger(ADAProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
