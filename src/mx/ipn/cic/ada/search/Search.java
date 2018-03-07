
package mx.ipn.cic.ada.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import mx.ipn.cic.ada.graph.Edge;
import mx.ipn.cic.ada.graph.Graph;
import mx.ipn.cic.ada.graph.Node;
import mx.ipn.cic.ada.graph.UDGraph;

/**
 *
 * @author Miguel Moreno
 */
public class Search {      
    
    public static Graph BFS(Graph graph, Node s) throws Exception{
        Graph bfsTree = new UDGraph();
        
        // agregamos nodo raiz al arbol BFS
        bfsTree.addNode(s);
        
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
        Layer layer = new Layer();
        layer.nodes.add(s);
        layers.add(layer);
        
        // Mientras el arbol BFS tenga menos nodos que el grafo
        while(bfsTree.getV().size() < graph.getV().size()){
            
            // Por cada nodo de la capa i
            for(Node n: layers.get(i).nodes){
                
                // Obtenemos arista origen n
                List<Edge> edges = Graph.getEdgesFromS(n, graph.getE());
                
                // Por cada arista, agregamos nodo target a nueva capa
                layer = new Layer();
                layers.add(layer);
                for (Edge e : edges) {
                    Node target = e.getTarget();
                    // Si el nodo no existe en BFS, se agrega
                    if(!Graph.existsNode(target, bfsTree.getV())){
                        layer.nodes.add(target);
                        bfsTree.getV().add(target);                                                
                        bfsTree.addEdge(e);
//                        System.out.println("Agrego nodo "+target);
//                        System.out.println("Agrego arista "+e);
                    }
                }
            }           
            i++;
        }
        
        return bfsTree;
    }
}
