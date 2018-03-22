
package mx.ipn.cic.ada.app;

import java.util.logging.Level;
import java.util.logging.Logger;
import mx.ipn.cic.ada.graph.Graph;
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
        String URL_BASE = "C:\\Users\\SIA Miguel\\Documents\\Segundo\\Diseño y Análisis de Algoritmos\\Proyecto2\\";
        //String URL_BASE = "/home/komodo/Documents/Cic/Semestre 2/Diseño y Análisis  de Algoritmos/Proyecto 2/archivosGV/";
        proyecto2(URL_BASE);
        
        /** Pruebas para Examen 1 **/
        //String URL_BASE = "C:\\Users\\SIA Miguel\\Documents\\Segundo\\Diseño y Análisis de Algoritmos\\Examen1\\gv\\";
        //examen1(URL_BASE);
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
            Graph g = null;
            Graph bfsTree = null;
            Graph dfsTree = null;
            
            // Creamos el grafo aleatorio ERDOS RENYI Pocos Nodos
            System.out.println("Creando Erdos Pocos Nodos...");
            g = Graph.createByErdosRenyi(false, false, 10, 20);  
            g.toGraphviz(URL_BASE + "ErdosRenyi1.gv");  
            
            bfsTree = Search.BFS(g, g.getV().get(0));   
            bfsTree.toGraphviz(URL_BASE + "ErdosRenyi1-BFS.gv");  
            
            dfsTree = Search.DFS_R(g, g.getV().get(0));
            dfsTree.toGraphviz(URL_BASE + "ErdosRenyi1-DFS-R.gv");
            
            dfsTree = Search.DFS_I(g, g.getV().get(0));     
            dfsTree.toGraphviz(URL_BASE + "ErdosRenyi1-DFS-I.gv");
            
            // Creamos el grafo aleatorio ERDOS RENYI Muchos Nodos
            System.out.println("Creando Erdos Muchos Nodos...");
            g = Graph.createByErdosRenyi(false, false, 500, 1000);  
            g.toGraphviz(URL_BASE + "ErdosRenyi2.gv");  
            
            bfsTree = Search.BFS(g, g.getV().get(0));   
            bfsTree.toGraphviz(URL_BASE + "ErdosRenyi2-BFS.gv");  
            
            dfsTree = Search.DFS_R(g, g.getV().get(0));
            dfsTree.toGraphviz(URL_BASE + "ErdosRenyi2-DFS-R.gv");
            
            dfsTree = Search.DFS_I(g, g.getV().get(0));     
            dfsTree.toGraphviz(URL_BASE + "ErdosRenyi2-DFS-I.gv");

            // Creamos el grafo aleatorio GILBERT Pocos Nodos
            System.out.println("Creando Gilbert Pocos Nodos...");
            g = Graph.createByGilbert(false, false, 10, 0.5f);  
            g.toGraphviz(URL_BASE + "Gilbert1.gv");  
            
            bfsTree = Search.BFS(g, g.getV().get(0));   
            bfsTree.toGraphviz(URL_BASE + "Gilbert1-BFS.gv");  
            
            dfsTree = Search.DFS_R(g, g.getV().get(0));
            dfsTree.toGraphviz(URL_BASE + "Gilbert1-DFS-R.gv");
            
            dfsTree = Search.DFS_I(g, g.getV().get(0));     
            dfsTree.toGraphviz(URL_BASE + "Gilbert1-DFS-I.gv");

            // Creamos el grafo aleatorio GILBERT muchos Nodos
            System.out.println("Creando Gilbert Muchos Nodos...");
            g = Graph.createByGilbert(false, false, 500, 0.5f);  
            g.toGraphviz(URL_BASE + "Gilbert2.gv");  
            
            bfsTree = Search.BFS(g, g.getV().get(0));   
            bfsTree.toGraphviz(URL_BASE + "Gilbert2-BFS.gv");  
            
            dfsTree = Search.DFS_R(g, g.getV().get(0));
            dfsTree.toGraphviz(URL_BASE + "Gilbert2-DFS-R.gv");
            
            dfsTree = Search.DFS_I(g, g.getV().get(0));     
            dfsTree.toGraphviz(URL_BASE + "Gilbert2-DFS-I.gv");

            // Creamos el grafo aleatorio GEOGRAPHIC pocos nodos
            System.out.println("Creando Geographic Pocos Nodos...");
            g = Graph.createByGeographic(false, false, 10, 0.2f);
            g.toGraphviz(URL_BASE + "Geographic1.gv");  
            
            bfsTree = Search.BFS(g, g.getV().get(0));   
            bfsTree.toGraphviz(URL_BASE + "Geographic1-BFS.gv");  
            
            dfsTree = Search.DFS_R(g, g.getV().get(0));
            dfsTree.toGraphviz(URL_BASE + "Geographic1-DFS-R.gv");
            
            dfsTree = Search.DFS_I(g, g.getV().get(0));     
            dfsTree.toGraphviz(URL_BASE + "Geographic1-DFS-I.gv");

            // Creamos el grafo aleatorio GEOGRAPHIC muchos nodos
            System.out.println("Creando Geographic Muchos Nodos...");
            g = Graph.createByGeographic(false, false, 500, 0.2f);
            g.toGraphviz(URL_BASE + "Geographic2.gv");  
            
            bfsTree = Search.BFS(g, g.getV().get(0));   
            bfsTree.toGraphviz(URL_BASE + "Geographic2-BFS.gv");  
            
            dfsTree = Search.DFS_R(g, g.getV().get(0));
            dfsTree.toGraphviz(URL_BASE + "Geographic2-DFS-R.gv");
            
            dfsTree = Search.DFS_I(g, g.getV().get(0));     
            dfsTree.toGraphviz(URL_BASE + "Geographic2-DFS-I.gv");


            // Creamos el grafo aleatorio BARABASI pocos nodos
            System.out.println("Creando Barabasi Albert Pocos Nodos...");
            g = Graph.createByBarabasiAlbert(false, 10, 4);
            g.toGraphviz(URL_BASE + "BarabasiAlbert1.gv");  
            
            bfsTree = Search.BFS(g, g.getV().get(0));   
            bfsTree.toGraphviz(URL_BASE + "BarabasiAlbert1-BFS.gv");  
            
            dfsTree = Search.DFS_R(g, g.getV().get(0));
            dfsTree.toGraphviz(URL_BASE + "BarabasiAlbert1-DFS-R.gv");
            
            dfsTree = Search.DFS_I(g, g.getV().get(0));     
            dfsTree.toGraphviz(URL_BASE + "BarabasiAlbert1-DFS-I.gv");

            // Creamos el grafo aleatorio BARABASI muchos nodos
            System.out.println("Creando Barabasi Albert Muchos Nodos...");
            g = Graph.createByBarabasiAlbert(false, 500, 50);
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
            Graph g = Graph.createByErdosRenyi(false, false, numNodos, 8);
            System.out.println(g);            
            String destFile = URL_BASE + "Examen1-Original.gv";            
            g.toGraphviz(destFile);
            
            Graph dfsTree = Search.DFS_I_FindCycle(g, g.getV().get(0));     
            dfsTree.toGraphviz(URL_BASE + "Examen1-DFS-I.gv");
            
        } catch (Exception ex) {
            Logger.getLogger(ADAProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
