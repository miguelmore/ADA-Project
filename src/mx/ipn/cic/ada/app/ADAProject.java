
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
        String URL_BASE = "C:\\Users\\SIA Miguel\\Documents\\Segundo\\Diseño y Análisis de Algoritmos\\Proyecto4\\";
        //String URL_BASE = "/home/komodo/Documents/Cic/Semestre 2/Diseño y Análisis  de Algoritmos/Proyecto 4/archivosGV/";
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
          
          try{
                DIGraph g = null;
                DIGraph shortPathGraph = null;
                        
                // Creamos el grafo aleatorio ERDOS RENYI Pocos Nodos
                System.out.println("Creando Erdos Pocos Nodos...");
                g = (DIGraph) Graph.createByErdosRenyi(true, false, 10, 20, true);  
                g.toGraphviz(URL_BASE + "ErdosRenyi1.gv");  
                
                shortPathGraph = Search.Dijkstra(g, g.getNode("1"));
                shortPathGraph.toGraphviz(URL_BASE + "ErdosRenyi1-Dijkstra.gv");  

                // Creamos el grafo aleatorio ERDOS RENYI Muchos Nodos
                System.out.println("Creando Erdos Muchos Nodos...");
                g = (DIGraph) Graph.createByErdosRenyi(true, false, 400, 1200, true);  
                g.toGraphviz(URL_BASE + "ErdosRenyi2.gv");  

                shortPathGraph = Search.Dijkstra(g, g.getNode("1"));   
                shortPathGraph.toGraphviz(URL_BASE + "ErdosRenyi2-Dijkstra.gv");  

                // Creamos el grafo aleatorio GILBERT Pocos Nodos
                System.out.println("Creando Gilbert Pocos Nodos...");
                g = (DIGraph) Graph.createByGilbert(true, false, 10, 0.7f, true);  
                g.toGraphviz(URL_BASE + "Gilbert1.gv");  

                shortPathGraph = Search.Dijkstra(g, g.getNode("1"));  
                shortPathGraph.toGraphviz(URL_BASE + "Gilbert1-Dijkstra.gv");

                // Creamos el grafo aleatorio GILBERT muchos Nodos
                System.out.println("Creando Gilbert Muchos Nodos...");
                g = (DIGraph) Graph.createByGilbert(true, false, 500, 0.7f, true);  
                g.toGraphviz(URL_BASE + "Gilbert2.gv");  

                shortPathGraph = Search.Dijkstra(g, g.getNode("1"));  
                shortPathGraph.toGraphviz(URL_BASE + "Gilbert2-Dijkstra.gv");

                // Creamos el grafo aleatorio GEOGRAPHIC pocos nodos
                System.out.println("Creando Geographic Pocos Nodos...");
                g = (DIGraph) Graph.createByGeographic(true, false, 10, 0.4f, true);
                g.toGraphviz(URL_BASE + "Geographic1.gv");  

                shortPathGraph = Search.Dijkstra(g, g.getNode("1"));
                shortPathGraph.toGraphviz(URL_BASE + "Geographic1-Dijkstra.gv");

                // Creamos el grafo aleatorio GEOGRAPHIC muchos nodos
                System.out.println("Creando Geographic Muchos Nodos...");
                g = (DIGraph) Graph.createByGeographic(true, false, 500, 0.4f, true);
                g.toGraphviz(URL_BASE + "Geographic2.gv");  

                shortPathGraph = Search.Dijkstra(g, g.getNode("1"));   
                shortPathGraph.toGraphviz(URL_BASE + "Geographic2-Dijkstra.gv");

                // Creamos el grafo aleatorio BARABASI pocos nodos
                System.out.println("Creando Barabasi Albert Pocos Nodos...");
                g = (DIGraph) Graph.createByBarabasiAlbert(true, 10, 4, true);
                g.toGraphviz(URL_BASE + "BarabasiAlbert1.gv");  

                shortPathGraph = Search.Dijkstra(g, g.getNode("10"));      
                shortPathGraph.toGraphviz(URL_BASE + "BarabasiAlbert1-Dijkstra.gv");

                // Creamos el grafo aleatorio BARABASI muchos nodos
                System.out.println("Creando Barabasi Albert Muchos Nodos...");
                g = (DIGraph) Graph.createByBarabasiAlbert(true, 500, 300, true);
                g.toGraphviz(URL_BASE + "BarabasiAlbert2.gv");  

                shortPathGraph = Search.Dijkstra(g, g.getNode("500"));
                shortPathGraph.toGraphviz(URL_BASE + "BarabasiAlbert2-Dijkstra.gv");
          }
          catch(Exception ex){
              Logger.getLogger(ADAProject.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    
    public static void proyecto4(final String URL_BASE){
        /* PROYECTO 4 */  
                
        try{
                UDGraph g = null;
                UDGraph kruskal = null;
                UDGraph iKruskal = null;
                UDGraph prim = null;
                        
                // Creamos el grafo aleatorio ERDOS RENYI Pocos Nodos
                System.out.println("\nCreando Erdos Pocos Nodos...");
                g = (UDGraph) Graph.createByErdosRenyi(false, false, 10, 20, true);  
                g.toGraphviz(URL_BASE + "ErdosRenyi1.gv");  
                
                kruskal = Search.Kruskal(g);
                kruskal.toGraphviz(URL_BASE + "ErdosRenyi1-Kruskal.gv");  
                
                iKruskal = Search.IKruskal(g);
                iKruskal.toGraphviz(URL_BASE + "ErdosRenyi1-IKruskal.gv");  
                
                prim = Search.Prim(g);
                prim.toGraphviz(URL_BASE + "ErdosRenyi1-Prim.gv");  



                // Creamos el grafo aleatorio ERDOS RENYI Muchos Nodos
                System.out.println("\nCreando Erdos Muchos Nodos...");
                g = (UDGraph) Graph.createByErdosRenyi(false, false, 400, 1200, true);  
                g.toGraphviz(URL_BASE + "ErdosRenyi2.gv");  

                kruskal = Search.Kruskal(g);
                kruskal.toGraphviz(URL_BASE + "ErdosRenyi2-Kruskal.gv");  
                
                iKruskal = Search.IKruskal(g);
                iKruskal.toGraphviz(URL_BASE + "ErdosRenyi2-IKruskal.gv");  
                
                prim = Search.Prim(g);
                prim.toGraphviz(URL_BASE + "ErdosRenyi2-Prim.gv");  



                // Creamos el grafo aleatorio GILBERT Pocos Nodos
                System.out.println("\nCreando Gilbert Pocos Nodos...");
                g = (UDGraph) Graph.createByGilbert(false, false, 10, 0.9f, true);
                g.toGraphviz(URL_BASE + "Gilbert1.gv");  

                kruskal = Search.Kruskal(g);
                kruskal.toGraphviz(URL_BASE + "Gilbert1-Kruskal.gv");
                
                iKruskal = Search.IKruskal(g);
                iKruskal.toGraphviz(URL_BASE + "Gilbert1-IKruskal.gv");
                
                prim = Search.Prim(g);
                prim.toGraphviz(URL_BASE + "Gilbert1-Prim.gv");



                // Creamos el grafo aleatorio GILBERT muchos Nodos
                System.out.println("\nCreando Gilbert Muchos Nodos...");
                g = (UDGraph) Graph.createByGilbert(false, false, 500, 0.7f, true);  
                g.toGraphviz(URL_BASE + "Gilbert2.gv");  

                kruskal = Search.Kruskal(g);
                kruskal.toGraphviz(URL_BASE + "Gilbert2-Kruskal.gv");
                
                iKruskal = Search.IKruskal(g);
                iKruskal.toGraphviz(URL_BASE + "Gilbert2-IKruskal.gv");
                
                prim = Search.Prim(g);
                prim.toGraphviz(URL_BASE + "Gilbert2-Prim.gv");



                // Creamos el grafo aleatorio GEOGRAPHIC pocos nodos
                System.out.println("\nCreando Geographic Pocos Nodos...");
                g = (UDGraph) Graph.createByGeographic(false, false, 10, 0.8f, true);
                g.toGraphviz(URL_BASE + "Geographic1.gv");  

                kruskal = Search.Kruskal(g);
                kruskal.toGraphviz(URL_BASE + "Geographic1-Kruskal.gv");
                
                iKruskal = Search.IKruskal(g);
                iKruskal.toGraphviz(URL_BASE + "Geographic1-IKruskal.gv");
                
                prim = Search.Prim(g);
                prim.toGraphviz(URL_BASE + "Geographic1-Prim.gv");



                // Creamos el grafo aleatorio GEOGRAPHIC muchos nodos
                System.out.println("\nCreando Geographic Muchos Nodos...");
                g = (UDGraph) Graph.createByGeographic(false, false, 300, 0.8f, true);
                g.toGraphviz(URL_BASE + "Geographic2.gv");  

                kruskal = Search.Kruskal(g);
                kruskal.toGraphviz(URL_BASE + "Geographic2-Kruskal.gv");
                
                iKruskal = Search.IKruskal(g);
                iKruskal.toGraphviz(URL_BASE + "Geographic2-IKruskal.gv");
                
                prim = Search.Prim(g);
                prim.toGraphviz(URL_BASE + "Geographic2-Prim.gv");



                // Creamos el grafo aleatorio BARABASI pocos nodos
                System.out.println("\nCreando Barabasi Albert Pocos Nodos...");
                g = (UDGraph) Graph.createByBarabasiAlbert(false, 10, 9, true);
                g.toGraphviz(URL_BASE + "BarabasiAlbert1.gv");  

                kruskal = Search.Kruskal(g);
                kruskal.toGraphviz(URL_BASE + "BarabasiAlbert1-Kruskal.gv");
                
                iKruskal = Search.IKruskal(g);
                iKruskal.toGraphviz(URL_BASE + "BarabasiAlbert1-IKruskal.gv");
                
                prim = Search.Prim(g);
                prim.toGraphviz(URL_BASE + "BarabasiAlbert1-Prim.gv");



                // Creamos el grafo aleatorio BARABASI muchos nodos
                System.out.println("\nCreando Barabasi Albert Muchos Nodos...");
                g = (UDGraph) Graph.createByBarabasiAlbert(false, 100, 100, true);
                g.toGraphviz(URL_BASE + "BarabasiAlbert2.gv");  

                kruskal = Search.Kruskal(g);
                kruskal.toGraphviz(URL_BASE + "BarabasiAlbert2-Kruskal.gv");
                
                iKruskal = Search.IKruskal(g);
                iKruskal.toGraphviz(URL_BASE + "BarabasiAlbert2-IKruskal.gv");
                
                prim = Search.Prim(g);
                prim.toGraphviz(URL_BASE + "BarabasiAlbert2-Prim.gv");


          }
          catch(Exception ex){
              Logger.getLogger(ADAProject.class.getName()).log(Level.SEVERE, null, ex);
          }        
        
    }
}
