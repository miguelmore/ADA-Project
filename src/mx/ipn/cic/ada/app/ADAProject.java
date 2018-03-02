
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
        
        /* Grafo no dirigido */
        Graph udgraph = new UDGraph();
        
        try{
            System.out.println("-- Grafo no dirigido --");
            // Agregamos nodos
            Node n1 = new Node("n1");
            Node n2 = new Node("n2");
            udgraph.addNode(n1);
            udgraph.addNode(n2);
            
            // Agregamos aristas
            udgraph.addEdge(new Edge(n1, n2));
            udgraph.addEdge(new Edge(n2, n2));
            
            
            // Calculamos el orden de cada nodo
            for(Node node: udgraph.getV()) {
                String msg = "El orden de "+node.getId();
                msg += " es "+udgraph.getDegree(node);
                System.out.println(msg);
            }
            
            // Generamos archivo GV
            //String destFile = "/home/komodo/Documents/Cic/Semestre 2/Diseño y Análisis  de Algoritmos/Proyecto 1/archivosGV/testUDG.gv";
            String destFile = "C:\\Users\\SIA Miguel\\Documents\\Segundo\\Diseño y Análisis de Algoritmos\\Proyecto1\\testUDG.gv";
            //udgraph.toGraphviz(destFile);
            
            // Imprimimos grafo
            System.out.println(udgraph);
        } 
        catch (Exception ex) {
            Logger.getLogger(ADAProject.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        
        
        /* Grafo dirigido */
        Graph digraph = new DIGraph();
        
        try{
            System.out.println("\n\n-- Grafo dirigido --");
            // Agregamos nodos
            Node n1 = new Node("n1");
            Node n2 = new Node("n2");
            digraph.addNode(n1);
            digraph.addNode(n2);
            
            // Agregamos aristas
            digraph.addEdge(new Edge(n1, n2));
            digraph.addEdge(new Edge(n2, n1));
            digraph.addEdge(new Edge(n2, n2));
            
            
            
            // Calculamos el orden de cada nodo
            for(Node node: digraph.getV()){
                String msg = "El orden de "+node.getId();
                msg += " es "+digraph.getDegree(node);
                System.out.println(msg);
            }
            
            // Generamos archivo GV
            //String destFile = "/home/komodo/Documents/Cic/Semestre 2/Diseño y Análisis  de Algoritmos/Proyecto 1/archivosGV/testDIG.gv";
            String destFile = "C:\\Users\\SIA Miguel\\Documents\\Segundo\\Diseño y Análisis de Algoritmos\\Proyecto1\\testDIG.gv";
            //digraph.toGraphviz(destFile);
            
            // Imprimimos grafo
            System.out.println(digraph);
        } 
        catch (Exception ex) {
            Logger.getLogger(ADAProject.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        
        
        
        try {
            // Generamos grafos
            System.out.println("\n\n-- Grafo Erdos Renyi --");
            Graph g1 = Graph.createByErdosRenyi(false, true, 500, 100);
            System.out.println(g1);
            String destFile = "C:\\Users\\SIA Miguel\\Documents\\Segundo\\Diseño y Análisis de Algoritmos\\Proyecto1\\ErdosRenyi.gv";
            g1.toGraphviz(destFile);
            
            System.out.println("\n\n-- Grafo Gilbert --");
            g1 = Graph.createByGilbert(true, true, 500, 0.1f);
            System.out.println(g1);
            destFile = "C:\\Users\\SIA Miguel\\Documents\\Segundo\\Diseño y Análisis de Algoritmos\\Proyecto1\\Gilbert.gv";
            g1.toGraphviz(destFile);
            
            System.out.println("\n\n-- Grafo Geográfico Simple --");
            g1 = Graph.createByGeographic(true, false, 500, 0.1f);
            System.out.println(g1);
            destFile = "C:\\Users\\SIA Miguel\\Documents\\Segundo\\Diseño y Análisis de Algoritmos\\Proyecto1\\Geographic.gv";
            g1.toGraphviz(destFile);
        } catch (Exception ex) {
            Logger.getLogger(ADAProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
