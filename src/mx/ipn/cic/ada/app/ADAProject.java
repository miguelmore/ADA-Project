
package mx.ipn.cic.ada.app;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.ipn.cic.ada.graph.Edge;
import mx.ipn.cic.ada.graph.Graph;
import mx.ipn.cic.ada.graph.Node;
import mx.ipn.cic.ada.graph.UDGraph;
import mx.ipn.cic.ada.search.Search;

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
         
        
        String URL_BASE = "C:\\Users\\SIA Miguel\\Documents\\Segundo\\Diseño y Análisis de Algoritmos\\Proyecto1\\";
        //String URL_BASE = "/home/komodo/Documents/Cic/Semestre 2/Diseño y Análisis  de Algoritmos//Proyecto 1/archivosGV/";
        
        // Pruebas del Proyecto 1
        //proyecto1(URL_BASE);
                
        // Pruebas del Proyecto 2
        URL_BASE = "C:\\Users\\SIA Miguel\\Documents\\Segundo\\Diseño y Análisis de Algoritmos\\Proyecto2\\";
        proyecto2(URL_BASE);
        
    }
    

    public static void proyecto1(final String URL_BASE){
        /* PROYECTO 1 */
        try {
            int numNodos = 30;            
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
    
    
    public static void proyecto2(final String URL_BASE){
        /* PROYECTO 2 */
        try{
            // Creamos el grafo de ejemplo revisado en clase
            Graph g2 = new UDGraph();
            List<Node> nodes = g2.getV();
            for (int i=1; i<=8; i++) {
                nodes.add(new Node(String.valueOf(i)));
            }
            g2.getE().add(new Edge(nodes.get(1-1), nodes.get(2-1)));
            g2.getE().add(new Edge(nodes.get(1-1), nodes.get(3-1)));
            g2.getE().add(new Edge(nodes.get(2-1), nodes.get(3-1)));
            g2.getE().add(new Edge(nodes.get(2-1), nodes.get(5-1)));
            g2.getE().add(new Edge(nodes.get(2-1), nodes.get(4-1)));
            g2.getE().add(new Edge(nodes.get(3-1), nodes.get(5-1)));
            g2.getE().add(new Edge(nodes.get(3-1), nodes.get(7-1)));
            g2.getE().add(new Edge(nodes.get(3-1), nodes.get(8-1)));
            g2.getE().add(new Edge(nodes.get(4-1), nodes.get(5-1)));
            g2.getE().add(new Edge(nodes.get(5-1), nodes.get(6-1)));
            g2.getE().add(new Edge(nodes.get(7-1), nodes.get(8-1)));
            
            System.out.println("\n\n-- Grafo para BFS --");
            System.out.println(g2);            
            g2.toGraphviz(URL_BASE + "BFS-Original-Ejemplo.gv");  
            
            Graph bfsTree = Search.BFS(g2, nodes.get(0));            
            System.out.println("\n\n-- Arbol BFS --");
            System.out.println(bfsTree);            
            bfsTree.toGraphviz(URL_BASE + "BFS-Tree-Ejemplo.gv");  
            
        } catch (Exception ex) {
            Logger.getLogger(ADAProject.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            // Creamos el grafo aleatorio
            Graph g = Graph.createByErdosRenyi(false, false, 10, 10);  
            System.out.println("\n\n-- Grafo para BFS --");
            System.out.println(g);            
            g.toGraphviz(URL_BASE + "BFS-Original-Aleatorio.gv");  
            
            Graph bfsTree = Search.BFS(g, g.getV().get(0));            
            System.out.println("\n\n-- Arbol BFS --");
            System.out.println(bfsTree);            
            bfsTree.toGraphviz(URL_BASE + "BFS-Tree-Aleatorio.gv");  
            
        } catch (Exception ex) {
            Logger.getLogger(ADAProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
