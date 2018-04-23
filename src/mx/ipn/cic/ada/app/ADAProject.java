
package mx.ipn.cic.ada.app;

import java.util.logging.Level;
import java.util.logging.Logger;
import mx.ipn.cic.ada.graph.DIGraph;
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
         
        
       
        /** Pruebas del Proyecto 1 **/
        //String URL_BASE = "C:\\Users\\SIA Miguel\\Documents\\Segundo\\Diseño y Análisis de Algoritmos\\Proyecto1\\";
        //String URL_BASE = "/home/komodo/Documents/Cic/Semestre 2/Diseño y Análisis  de Algoritmos/Proyecto 1/archivosGV/";
        //proyecto1(URL_BASE);
                
        /** Pruebas del Proyecto 2 **/
        //String URL_BASE = "C:\\Users\\SIA Miguel\\Documents\\Segundo\\Diseño y Análisis de Algoritmos\\Proyecto2\\";
        //String URL_BASE = "/home/komodo/Documents/Cic/Semestre 2/Diseño y Análisis  de Algoritmos/Proyecto 2/archivosGV/";
        //proyecto2(URL_BASE);
        
        /** Pruebas para Examen 1 **/
        //String URL_BASE = "C:\\Users\\SIA Miguel\\Documents\\Segundo\\Diseño y Análisis de Algoritmos\\Examen1\\gv\\";
        //examen1(URL_BASE);
        
        /** Pruebas del Proyecto 3 **/
        //String URL_BASE = "C:\\Users\\SIA Miguel\\Documents\\Segundo\\Diseño y Análisis de Algoritmos\\Proyecto3\\";
        //String URL_BASE = "/home/komodo/Documents/Cic/Semestre 2/Diseño y Análisis  de Algoritmos/Proyecto 3/archivosGV/";
        //proyecto3(URL_BASE);
        
        /** Pruebas del Proyecto 4 **/
        //String URL_BASE = "C:\\Users\\SIA Miguel\\Documents\\Segundo\\Diseño y Análisis de Algoritmos\\Proyecto4\\";
        String URL_BASE = "/home/komodo/Documents/Cic/Semestre 2/Diseño y Análisis  de Algoritmos/Proyecto 4/archivosGV/";
        proyecto4(URL_BASE);
    }
    

    public static void proyecto1(final String URL_BASE){
        /* PROYECTO 1 */
        try {
            int numNodos = 30;            
            // Generamos grafos
            System.out.println("\n\n-- Grafo Erdos Renyi --");
            Graph g1 = Graph.createByErdosRenyi(false, true, numNodos, numNodos*2, false);
            System.out.println(g1);            
            String destFile = URL_BASE + "ErdosRenyi-"+numNodos+".gv";            
            g1.toGraphviz(destFile);
            
            System.out.println("\n\n-- Grafo Gilbert --");
            g1 = Graph.createByGilbert(false, true, numNodos, 0.5f, false);
            System.out.println(g1);            
            destFile = URL_BASE + "Gilbert-"+numNodos+".gv";
            g1.toGraphviz(destFile);
            
            System.out.println("\n\n-- Grafo Geográfico Simple --");
            g1 = Graph.createByGeographic(false, false, numNodos, 0.1f, false);
            System.out.println(g1);
            destFile = URL_BASE + "Geographic-"+numNodos+".gv";
            g1.toGraphviz(destFile);            
            
            System.out.println("\n\n-- Grafo createByBarabasiAlbert --");
            g1 = Graph.createByBarabasiAlbert(false, numNodos, numNodos/10, false);
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
            Graph g = null;
            Graph bfsTree = null;
            Graph dfsTree = null;
            
            // Creamos el grafo aleatorio ERDOS RENYI Pocos Nodos
            System.out.println("Creando Erdos Pocos Nodos...");
            g = Graph.createByErdosRenyi(false, false, 10, 20, false);  
            g.toGraphviz(URL_BASE + "ErdosRenyi1.gv");  
            
            bfsTree = Search.BFS(g, g.getV().get(0));   
            bfsTree.toGraphviz(URL_BASE + "ErdosRenyi1-BFS.gv");  
            
            dfsTree = Search.DFS_R(g, g.getV().get(0));
            dfsTree.toGraphviz(URL_BASE + "ErdosRenyi1-DFS-R.gv");
            
            dfsTree = Search.DFS_I(g, g.getV().get(0));     
            dfsTree.toGraphviz(URL_BASE + "ErdosRenyi1-DFS-I.gv");
            
            // Creamos el grafo aleatorio ERDOS RENYI Muchos Nodos
            System.out.println("Creando Erdos Muchos Nodos...");
            g = Graph.createByErdosRenyi(false, false, 500, 1000, false);  
            g.toGraphviz(URL_BASE + "ErdosRenyi2.gv");  
            
            bfsTree = Search.BFS(g, g.getV().get(0));   
            bfsTree.toGraphviz(URL_BASE + "ErdosRenyi2-BFS.gv");  
            
            dfsTree = Search.DFS_R(g, g.getV().get(0));
            dfsTree.toGraphviz(URL_BASE + "ErdosRenyi2-DFS-R.gv");
            
            dfsTree = Search.DFS_I(g, g.getV().get(0));     
            dfsTree.toGraphviz(URL_BASE + "ErdosRenyi2-DFS-I.gv");

            // Creamos el grafo aleatorio GILBERT Pocos Nodos
            System.out.println("Creando Gilbert Pocos Nodos...");
            g = Graph.createByGilbert(false, false, 10, 0.5f, false);  
            g.toGraphviz(URL_BASE + "Gilbert1.gv");  
            
            bfsTree = Search.BFS(g, g.getV().get(0));   
            bfsTree.toGraphviz(URL_BASE + "Gilbert1-BFS.gv");  
            
            dfsTree = Search.DFS_R(g, g.getV().get(0));
            dfsTree.toGraphviz(URL_BASE + "Gilbert1-DFS-R.gv");
            
            dfsTree = Search.DFS_I(g, g.getV().get(0));     
            dfsTree.toGraphviz(URL_BASE + "Gilbert1-DFS-I.gv");

            // Creamos el grafo aleatorio GILBERT muchos Nodos
            System.out.println("Creando Gilbert Muchos Nodos...");
            g = Graph.createByGilbert(false, false, 500, 0.5f, false);  
            g.toGraphviz(URL_BASE + "Gilbert2.gv");  
            
            bfsTree = Search.BFS(g, g.getV().get(0));   
            bfsTree.toGraphviz(URL_BASE + "Gilbert2-BFS.gv");  
            
            dfsTree = Search.DFS_R(g, g.getV().get(0));
            dfsTree.toGraphviz(URL_BASE + "Gilbert2-DFS-R.gv");
            
            dfsTree = Search.DFS_I(g, g.getV().get(0));     
            dfsTree.toGraphviz(URL_BASE + "Gilbert2-DFS-I.gv");

            // Creamos el grafo aleatorio GEOGRAPHIC pocos nodos
            System.out.println("Creando Geographic Pocos Nodos...");
            g = Graph.createByGeographic(false, false, 10, 0.2f, false);
            g.toGraphviz(URL_BASE + "Geographic1.gv");  
            
            bfsTree = Search.BFS(g, g.getV().get(0));   
            bfsTree.toGraphviz(URL_BASE + "Geographic1-BFS.gv");  
            
            dfsTree = Search.DFS_R(g, g.getV().get(0));
            dfsTree.toGraphviz(URL_BASE + "Geographic1-DFS-R.gv");
            
            dfsTree = Search.DFS_I(g, g.getV().get(0));     
            dfsTree.toGraphviz(URL_BASE + "Geographic1-DFS-I.gv");

            // Creamos el grafo aleatorio GEOGRAPHIC muchos nodos
            System.out.println("Creando Geographic Muchos Nodos...");
            g = Graph.createByGeographic(false, false, 500, 0.2f, false);
            g.toGraphviz(URL_BASE + "Geographic2.gv");  
            
            bfsTree = Search.BFS(g, g.getV().get(0));   
            bfsTree.toGraphviz(URL_BASE + "Geographic2-BFS.gv");  
            
            dfsTree = Search.DFS_R(g, g.getV().get(0));
            dfsTree.toGraphviz(URL_BASE + "Geographic2-DFS-R.gv");
            
            dfsTree = Search.DFS_I(g, g.getV().get(0));     
            dfsTree.toGraphviz(URL_BASE + "Geographic2-DFS-I.gv");


            // Creamos el grafo aleatorio BARABASI pocos nodos
            System.out.println("Creando Barabasi Albert Pocos Nodos...");
            g = Graph.createByBarabasiAlbert(false, 10, 4, false);
            g.toGraphviz(URL_BASE + "BarabasiAlbert1.gv");  
            
            bfsTree = Search.BFS(g, g.getV().get(0));   
            bfsTree.toGraphviz(URL_BASE + "BarabasiAlbert1-BFS.gv");  
            
            dfsTree = Search.DFS_R(g, g.getV().get(0));
            dfsTree.toGraphviz(URL_BASE + "BarabasiAlbert1-DFS-R.gv");
            
            dfsTree = Search.DFS_I(g, g.getV().get(0));     
            dfsTree.toGraphviz(URL_BASE + "BarabasiAlbert1-DFS-I.gv");

            // Creamos el grafo aleatorio BARABASI muchos nodos
            System.out.println("Creando Barabasi Albert Muchos Nodos...");
            g = Graph.createByBarabasiAlbert(false, 500, 50, false);
            g.toGraphviz(URL_BASE + "BarabasiAlbert2.gv");  
            
            bfsTree = Search.BFS(g, g.getV().get(0));   
            bfsTree.toGraphviz(URL_BASE + "BarabasiAlbert2-BFS.gv");  
            
            dfsTree = Search.DFS_R(g, g.getV().get(0));
            dfsTree.toGraphviz(URL_BASE + "BarabasiAlbert2-DFS-R.gv");
            
            dfsTree = Search.DFS_I(g, g.getV().get(0));     
            dfsTree.toGraphviz(URL_BASE + "BarabasiAlbert2-DFS-I.gv");
            
        } catch (Exception ex) {
            Logger.getLogger(ADAProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public static void examen1(final String URL_BASE){
        try {
            int numNodos = 10;            
            // Generamos grafos
            System.out.println("\n\n-- Grafo Generado --");
            Graph g = Graph.createByErdosRenyi(false, false, numNodos, 8, false);
            System.out.println(g);            
            String destFile = URL_BASE + "Examen1-Original.gv";            
            g.toGraphviz(destFile);
            
            Search.DFS_I_FindCycle(g, g.getV().get(0)); 
            
        } catch (Exception ex) {
            Logger.getLogger(ADAProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void proyecto3(final String URL_BASE){
        /* PROYECTO 3 */      
//        try{
//            System.out.println("\nEjemplo revisado en Clase");
//            DIGraph dig = null; 
//
//            // Ejemplo revisado en Clase
//            dig = new DIGraph();
//            for (int i = 1; i <= 8; i++) {
//                dig.addNode(new Node(String.valueOf(i)));
//            }
//            
//            Edge e = new Edge(dig.getNode("1"), dig.getNode("2"));
//            e.addObject(Edge.COST, 9);
//            dig.addEdge(e);
//            
//            e = new Edge(dig.getNode("1"), dig.getNode("6"));
//            e.addObject(Edge.COST, 14);
//            dig.addEdge(e);
//            
//            e = new Edge(dig.getNode("1"), dig.getNode("7"));
//            e.addObject(Edge.COST, 15);
//            dig.addEdge(e);
//            
//            e = new Edge(dig.getNode("2"), dig.getNode("3"));
//            e.addObject(Edge.COST, 24);
//            dig.addEdge(e);
//            
//            e = new Edge(dig.getNode("6"), dig.getNode("3"));
//            e.addObject(Edge.COST, 18);
//            dig.addEdge(e);
//            
//            e = new Edge(dig.getNode("6"), dig.getNode("5"));
//            e.addObject(Edge.COST, 30);
//            dig.addEdge(e);
//            
//            e = new Edge(dig.getNode("6"), dig.getNode("7"));
//            e.addObject(Edge.COST, 5);
//            dig.addEdge(e);
//            
//            e = new Edge(dig.getNode("7"), dig.getNode("5"));
//            e.addObject(Edge.COST, 20);
//            dig.addEdge(e);
//            
//            e = new Edge(dig.getNode("7"), dig.getNode("8"));
//            e.addObject(Edge.COST, 44);
//            dig.addEdge(e);
//            
//            
//            e = new Edge(dig.getNode("5"), dig.getNode("4"));
//            e.addObject(Edge.COST, 11);
//            dig.addEdge(e);
//            
//            e = new Edge(dig.getNode("5"), dig.getNode("8"));
//            e.addObject(Edge.COST, 16);
//            dig.addEdge(e);
//            
//            e = new Edge(dig.getNode("4"), dig.getNode("3"));
//            e.addObject(Edge.COST, 6);
//            dig.addEdge(e);
//            
//            e = new Edge(dig.getNode("4"), dig.getNode("8"));
//            e.addObject(Edge.COST, 6);
//            dig.addEdge(e);
//            
//            e = new Edge(dig.getNode("3"), dig.getNode("5"));
//            e.addObject(Edge.COST, 2);
//            dig.addEdge(e);
//            
//            e = new Edge(dig.getNode("3"), dig.getNode("8"));
//            e.addObject(Edge.COST, 19);
//            dig.addEdge(e);
//            
//            dig.toGraphviz(URL_BASE + "Digraph.gv");     
//            System.out.println(dig); 
//            
//            // Dijkstra
//            DIGraph shortPathGraph = Search.Dijkstra(dig, dig.getNode("1"));
//            shortPathGraph.toGraphviz(URL_BASE + "Dijkstra.gv");     
//            System.out.println(shortPathGraph);                
//            
//        } catch (Exception ex) {
//            Logger.getLogger(ADAProject.class.getName()).log(Level.SEVERE, null, ex);
//        }
                        
        try{
            System.out.println("\nEjemplo Erdos");
            DIGraph dig = (DIGraph) Graph.createByErdosRenyi(true, false, 10, 10, true);
            dig.toGraphviz(URL_BASE + "Digraph.gv");
            System.out.println(dig);            
            // Dijkstra
            DIGraph shortPathGraph = Search.Dijkstra(dig, dig.getNode("1"));
            shortPathGraph.toGraphviz(URL_BASE + "Dijkstra.gv");
            System.out.println(shortPathGraph);                
            
        } catch (Exception ex) {
            Logger.getLogger(ADAProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void proyecto4(final String URL_BASE){
        /* PROYECTO 4 */                            
        try{
            System.out.println("\nEjemplo revisado en Clase");
            DIGraph dig = null;             
            Edge e = null;

            // Ejemplo revisado en Clase de Dijkstra
//            dig = new DIGraph();
//            for (int i = 1; i <= 8; i++) {
//                dig.addNode(new Node(String.valueOf(i)));
//            }
//            
//            e = new Edge(dig.getNode("1"), dig.getNode("2"));
//            e.addObject(Edge.COST, 9);
//            dig.addEdge(e);
//            
//            e = new Edge(dig.getNode("1"), dig.getNode("6"));
//            e.addObject(Edge.COST, 14);
//            dig.addEdge(e);
//            
//            e = new Edge(dig.getNode("1"), dig.getNode("7"));
//            e.addObject(Edge.COST, 15);
//            dig.addEdge(e);
//            
//            e = new Edge(dig.getNode("2"), dig.getNode("3"));
//            e.addObject(Edge.COST, 24);
//            dig.addEdge(e);
//            
//            e = new Edge(dig.getNode("6"), dig.getNode("3"));
//            e.addObject(Edge.COST, 18);
//            dig.addEdge(e);
//            
//            e = new Edge(dig.getNode("6"), dig.getNode("5"));
//            e.addObject(Edge.COST, 30);
//            dig.addEdge(e);
//            
//            e = new Edge(dig.getNode("6"), dig.getNode("7"));
//            e.addObject(Edge.COST, 5);
//            dig.addEdge(e);
//            
//            e = new Edge(dig.getNode("7"), dig.getNode("5"));
//            e.addObject(Edge.COST, 20);
//            dig.addEdge(e);
//            
//            e = new Edge(dig.getNode("7"), dig.getNode("8"));
//            e.addObject(Edge.COST, 44);
//            dig.addEdge(e);            
//                       
//            e = new Edge(dig.getNode("5"), dig.getNode("4"));
//            e.addObject(Edge.COST, 11);
//            dig.addEdge(e);
//            
//            e = new Edge(dig.getNode("5"), dig.getNode("8"));
//            e.addObject(Edge.COST, 16);
//            dig.addEdge(e);
//            
//            e = new Edge(dig.getNode("4"), dig.getNode("3"));
//            e.addObject(Edge.COST, 6);
//            dig.addEdge(e);
//            
//            e = new Edge(dig.getNode("4"), dig.getNode("8"));
//            e.addObject(Edge.COST, 6);
//            dig.addEdge(e);
//            
//            e = new Edge(dig.getNode("3"), dig.getNode("5"));
//            e.addObject(Edge.COST, 2);
//            dig.addEdge(e);
//            
//            e = new Edge(dig.getNode("3"), dig.getNode("8"));
//            e.addObject(Edge.COST, 19);
//            dig.addEdge(e);
            
            
            // Ejemplo revisado en Clase de arbol de expansion minima
            dig = new DIGraph();
            for (int i = 1; i <= 8; i++) {
                dig.addNode(new Node(String.valueOf(i)));
            }
            
            e = new Edge(dig.getNode("1"), dig.getNode("2"));
            e.addObject(Edge.COST, 4);
            dig.addEdge(e);
            
            e = new Edge(dig.getNode("1"), dig.getNode("3"));
            e.addObject(Edge.COST, 6);
            dig.addEdge(e);
            
            e = new Edge(dig.getNode("1"), dig.getNode("4"));
            e.addObject(Edge.COST, 16);
            dig.addEdge(e);
            
            e = new Edge(dig.getNode("2"), dig.getNode("6"));
            e.addObject(Edge.COST, 24);
            dig.addEdge(e);
            
            e = new Edge(dig.getNode("3"), dig.getNode("6"));
            e.addObject(Edge.COST, 23);
            dig.addEdge(e);
            
            e = new Edge(dig.getNode("3"), dig.getNode("5"));
            e.addObject(Edge.COST, 5);
            dig.addEdge(e);
            
            e = new Edge(dig.getNode("3"), dig.getNode("4"));
            e.addObject(Edge.COST, 8);
            dig.addEdge(e);
            
            e = new Edge(dig.getNode("4"), dig.getNode("5"));
            e.addObject(Edge.COST, 10);
            dig.addEdge(e);
            
            e = new Edge(dig.getNode("4"), dig.getNode("8"));
            e.addObject(Edge.COST, 21);
            dig.addEdge(e);
            
            e = new Edge(dig.getNode("5"), dig.getNode("6"));
            e.addObject(Edge.COST, 18);
            dig.addEdge(e);
            
            e = new Edge(dig.getNode("5"), dig.getNode("7"));
            e.addObject(Edge.COST, 11);
            dig.addEdge(e);
            
            e = new Edge(dig.getNode("5"), dig.getNode("8"));
            e.addObject(Edge.COST, 14);
            dig.addEdge(e);
            
            e = new Edge(dig.getNode("6"), dig.getNode("7"));
            e.addObject(Edge.COST, 9);
            dig.addEdge(e);
            
            e = new Edge(dig.getNode("7"), dig.getNode("8"));
            e.addObject(Edge.COST, 7);
            dig.addEdge(e);
                                   
            dig.toGraphviz(URL_BASE + "Test.gv");     
            System.out.println(dig);            
                
            // Kruskal              
            System.out.println("Kruskal");
            UDGraph kruskal = Search.Kruskal(dig);
            System.out.println(kruskal);
            
            
        } catch (Exception ex) {
            Logger.getLogger(ADAProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
