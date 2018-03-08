
package mx.ipn.cic.ada.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import mx.ipn.cic.ada.graph.DIGraph;
import mx.ipn.cic.ada.graph.Edge;
import mx.ipn.cic.ada.graph.Graph;
import mx.ipn.cic.ada.graph.Node;
import mx.ipn.cic.ada.graph.UDGraph;

/**
 *
 * @author Miguel Moreno
 */
public class Search {      
    
    
    
    
    /**
     * Breadth First Search
     * @param graph grafo original
     * @param s nodo fuente
     * @return
     * @throws Exception 
     */
    public static Graph BFS(Graph graph, Node s) throws Exception{
        Graph bfsTree = new UDGraph();
               
        // Por cada nodo agregamos una bandera 
        // para saber si ya fue agregado a una capa
        final String DIS = "discovered";
        graph.getV().forEach((n) -> {
            n.addData(DIS, false);
        });
        
        // Marcamos el nodo raiz y agregamos al arbol
        s.replaceData(DIS, true);
        bfsTree.getV().add(s);   
        
        // Creacion árbol BFS
        class Layer{
            List<Node> nodes;
            Layer(){
                nodes = new ArrayList<>();
            }
        }
        List<Layer> layers = new ArrayList<>();
        int i = 0;   
        
        // Creamos Layer 0 con nodo S
        layers.add(new Layer());
        layers.get(i).nodes.add(s);        
        
        // Mientras no se llegue a una capa sin nodos
        while(!layers.get(i).nodes.isEmpty()){
            
            // Si la capa i+1 no existe, se crea
            if(i+1 >= layers.size())
                layers.add(new Layer());
            
            // Por cada nodo de la capa i
            for(Node n: layers.get(i).nodes){
                
                // Obtenemos arista origen n
                List<Edge> edges = Graph.getEdgesBySource(n, graph.getE(), graph.isDigraph());
                
                // Por cada arista, agregamos nodo target a nueva capa
                for (Edge e : edges) {
                    Node target = null;
                    
                    if(graph.isDigraph()){
                        target = e.getTarget();
                    }
                    else{
                        if(e.getSource().getId().equals(n.getId()))
                            target = e.getTarget();
                        else
                            target = e.getSource();
                    }
                    
                    // Si el nodo no ha sido marcado
                    if(!(boolean)target.getData(DIS)){
                        target.replaceData(DIS, true);
                        layers.get(i+1).nodes.add(target);
                        bfsTree.getV().add(target);                                                
                        bfsTree.addEdge(e);
                    }
                }
            }           
            i++; // contador de capa
        }        
        return bfsTree;
    }
    
    public static Graph DFS(Graph graph, Node s) throws Exception{
        Graph dfsTree = new UDGraph();
               
        // Por cada nodo agregamos una bandera 
        // para saber si ya fue explorado
        final String EXP = "explored";
        graph.getV().forEach((n) -> {
            n.addData(EXP, false);
        });
        
        // Marcamos el nodo raiz y agregamos al arbol
        s.replaceData(EXP, true);
        dfsTree.getV().add(s);
        
        // Mandamos a la funcion recursiva que genera el arbol DFS
        Search.recursionDFS(graph, s, dfsTree);
        
        
        return dfsTree;
    }
    
    private static void recursionDFS(Graph graph, Node s, Graph dfsTree) throws Exception{
        final String EXP = "explored";
        
        // Obtenemos arista origen n
        List<Edge> edges = Graph.getEdgesBySource(s, graph.getE(), graph.isDigraph());

        // Por cada arista del nodo s
        for (Edge e : edges) {
            Node target = null;

            if(graph.isDigraph()){
                target = e.getTarget();
            }
            else{
                if(e.getSource().getId().equals(s.getId()))
                    target = e.getTarget();
                else
                    target = e.getSource();
            }

            // Si el nodo no ha sido explorado
            if(!(boolean)target.getData(EXP)){
                target.replaceData(EXP, true); 
                //System.out.println("Marco nodo y mando a recursion "+target);
                dfsTree.getV().add(target);                                                
                dfsTree.addEdge(e);
                //Llamado recursivo
                recursionDFS(graph, target, dfsTree);
            }
        }
    }
}
