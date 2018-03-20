
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
         
        
        //String URL_BASE = "C:\\Users\\SIA Miguel\\Documents\\Segundo\\Diseño y Análisis de Algoritmos\\Proyecto1\\";
        //String URL_BASE = "/home/komodo/Documents/Cic/Semestre 2/Diseño y Análisis  de Algoritmos/Proyecto 1/archivosGV/";
        
        // Pruebas del Proyecto 1
        //proyecto1(URL_BASE);
                
        // Pruebas del Proyecto 2
        //URL_BASE = "C:\\Users\\SIA Miguel\\Documents\\Segundo\\Diseño y Análisis de Algoritmos\\Proyecto2\\";
        String URL_BASE = "/home/komodo/Documents/Cic/Semestre 2/Diseño y Análisis  de Algoritmos/Proyecto 2/archivosGV/";
        proyecto2(URL_BASE);
        
    }
    

    public static void proyecto1(final String URL_BASE){
        /* PROYECTO 1 */
        try {
            int numNodos = 30;            
            // Generamos grafos
            System.out.println("\n\n-- Grafo Erdos Renyi --");
            Graph g1 = Graph.createByErdosRenyi(false, true, numNodos, numNodos*2);
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
            // Creamos el grafo aleatorio
            Graph g = Graph.createByErdosRenyi(false, false, 10, 10);  
            System.out.println("\n\n-- Grafo para BFS/DFS --");
            System.out.println(g);            
            g.toGraphviz(URL_BASE + "Original-Aleatorio.gv");  
            
            Graph bfsTree = Search.BFS(g, g.getV().get(0));            
            System.out.println("\n\n-- Arbol BFS --");
            System.out.println(bfsTree);            
            bfsTree.toGraphviz(URL_BASE + "BFS-Tree.gv");  
            
            Graph dfsTree = Search.DFS_R(g, g.getV().get(0));            
            System.out.println("\n\n-- Arbol DFS_R --");
            System.out.println(dfsTree);            
            dfsTree.toGraphviz(URL_BASE + "DFS-R-Tree.gv");
            
            dfsTree = Search.DFS_I(g, g.getV().get(0));            
            System.out.println("\n\n-- Arbol DFS_I --");
            System.out.println(dfsTree);            
            dfsTree.toGraphviz(URL_BASE + "DFS-I-Tree.gv");
            
        } catch (Exception ex) {
            Logger.getLogger(ADAProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
