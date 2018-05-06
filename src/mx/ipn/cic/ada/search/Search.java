
package mx.ipn.cic.ada.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import mx.ipn.cic.ada.graph.DIGraph;
import mx.ipn.cic.ada.graph.Edge;
import mx.ipn.cic.ada.graph.EdgeComparator;
import mx.ipn.cic.ada.graph.Graph;
import mx.ipn.cic.ada.graph.Node;
import mx.ipn.cic.ada.graph.UDGraph;

/**
 *
 * @author Miguel Moreno
 */
public class Search {      
    
    public static final String DIJK_DIS = "DIJKSTRA_DISTANCE";
    private static final String DIJK_PRE_EDG = "DIJKSTRA_PREVIOUS_EDGE";
    private static final Double INFINITE = new Double(Double.POSITIVE_INFINITY);   
    
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
    
    /**
     * Deep First Search - Recursive
     * @param graph
     * @param s
     * @return
     * @throws Exception 
     */
    public static Graph DFS_R(Graph graph, Node s) throws Exception{
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

    /**
     * Deep First Search - Iterative
     * @param graph
     * @param s
     * @return
     * @throws Exception 
     */
    public static Graph DFS_I(Graph graph, Node s) throws Exception{
        Graph dfsTree = new UDGraph();
               
        // Por cada nodo agregamos una bandera 
        // para saber si ya fue explorado
        final String EXP = "explored";
        graph.getV().forEach((n) -> {
            n.addData(EXP, false);
        });
                   
        // Clase para guardar arista relacionada al nodo por explorar
        class NodeEdge{
            Node node;
            Edge edge;

            public NodeEdge(Node node, Edge edge) {
                this.node = node;
                this.edge = edge;
            }
        }
        
        // Pila en donde almacenaremos los nodos por explorar
        Stack<NodeEdge> stack = new Stack<>();
        NodeEdge node_edge = null;
        
        // Se comienza por explorar el nodo raiz
        node_edge = new NodeEdge(s,null);
        stack.push(node_edge);
        
        // Marcamos el nodo raiz y agregamos al arbol
        s.replaceData(EXP, true);
        dfsTree.getV().add(s);
        
        List<Edge> edges = null;
        
        // Mientras la pila no este vacia
        while(!stack.isEmpty()){
            // Sacamos un nodo de la pila para procesar
            // y marcamos como explorado
            node_edge = stack.pop();
            s = node_edge.node;
            
            if(!(boolean)s.getData(EXP))
            {
                // Se agrega nodo y arista al arbol
                // y se marca el nodo
                dfsTree.getV().add(s);
                dfsTree.getE().add(node_edge.edge);
                s.replaceData(EXP, true);
            }
            
            // Obtenemos arista origen en s
            edges = Graph.getEdgesBySource(s, graph.getE(), graph.isDigraph());
            
            // Por cada arista, buscamos su nodo target
            Node target = null;
            for (Edge e : edges) {               

                if(graph.isDigraph()){
                    target = e.getTarget();
                }
                else{
                    if(e.getSource().getId().equals(s.getId()))
                        target = e.getTarget();
                    else
                        target = e.getSource();
                }
                
                // Si no ha sido explorado, se agrega a pila por explorar
                if(!(boolean)target.getData(EXP))
                {
                    stack.push(new NodeEdge(target,e));
                }
            }
            
        }        
        
        return dfsTree;
    }
    
    /**
     * Modificacion al DFS_I para buscar ciclos en el grafo
     * @param graph
     * @param s
     * @return
     * @throws Exception 
     */
    public static Graph DFS_I_FindCycle(Graph graph, Node s) throws Exception{
        Graph dfsTree = new UDGraph();
               
        // Por cada nodo agregamos una bandera 
        // para saber si ya fue explorado
        final String EXP = "explored";
        graph.getV().forEach((n) -> {
            n.addData(EXP, false);
        });
                   
        // Clase para guardar arista relacionada al nodo por explorar
        class NodeEdge{
            Node parent;
            Node node;
            Edge edge;

            public NodeEdge(Node node, Edge edge,Node parent) {
                this.parent = parent;
                this.node = node;
                this.edge = edge;
            }
            
        }
        
        // Pila en donde almacenaremos los nodos por explorar
        Stack<NodeEdge> stack = new Stack<>();
        NodeEdge node_edge = null;
        
        // Se comienza por explorar el nodo raiz
        node_edge = new NodeEdge(s,null,null);
        stack.push(node_edge);
        
        // Marcamos el nodo raiz y agregamos al arbol
        s.replaceData(EXP, true);
        dfsTree.getV().add(s);
        
        List<Edge> edges = null;
        Node s_parent = null;
        
        // Mientras la pila no este vacia
        while(!stack.isEmpty()){
            // Sacamos un nodo de la pila para procesar
            // y marcamos como explorado
            node_edge = stack.pop();
            s = node_edge.node;
            s_parent = node_edge.parent;
            
            // Si no ha sido explorado
            if(!(boolean)s.getData(EXP))
            {
                // Se agrega nodo y arista al arbol
                // y se marca el nodo
                dfsTree.getV().add(s);
                dfsTree.getE().add(node_edge.edge);
                s.replaceData(EXP, true);
            }
            
            // Obtenemos arista origen en s
            edges = Graph.getEdgesBySource(s, graph.getE(), graph.isDigraph());
            
            // Por cada arista, buscamos su nodo target
            Node target = null;
            for (Edge e : edges) {               

                if(graph.isDigraph()){
                    target = e.getTarget();
                }
                else{
                    if(e.getSource().getId().equals(s.getId()))
                        target = e.getTarget();
                    else
                        target = e.getSource();
                }
                
                // Si no ha sido explorado, se agrega a pila por explorar
                if(!(boolean)target.getData(EXP))
                {
                    stack.push(new NodeEdge(target,e,s));
                }                
                // Si ya fue explorado
                else{
                    // Si el target no es padre de s, hay ciclo
                    if(!target.getId().equals(s_parent.getId())){
                        System.out.println("Hay ciclo en el grafo");
                    }
                }
            }
            
        }        
        
        return dfsTree;
    }
    
    /**
     * Genera el grafo resultante del algoritmo Dijkstra
     * @param g Grafo Dirigido
     * @param source Nodo fuente
     * @return
     * @throws Exception 
     */    
    public static DIGraph Dijkstra(DIGraph g, Node source) throws Exception{
        
        DIGraph shortPathGraph = new DIGraph();        
        List<Node> explored = new ArrayList<>();
        List<Node> notExplored = new ArrayList<>();
        
        // Agregamos todos los nodos a no explorados
        g.getV().forEach(node -> notExplored.add(node));
        
        // Agregamos distancia infinita 
        notExplored.forEach(node -> node.addData(DIJK_DIS, INFINITE.intValue()));
        
        // Se agrega el nodo fuente como explorado
        source.replaceData(DIJK_DIS, 0);
        explored.add(source);
        shortPathGraph.addNode(source);
        
        int disconnectCount = -1;
        int previousSize = explored.size();
        
        // Mientras los conjuntos sean diferentes
        while(!explored.containsAll(notExplored)){
        
            // Validacion para detectar si el grafo está desconectado
            // y evitar ciclo infinito
            if(explored.size() == previousSize){
                disconnectCount++;                
            }
            previousSize = explored.size();
            
            if(disconnectCount>=1)
                break;
            
            // Por cada nodo explorado
            int count = 0;
            List<Node> toExplore = new ArrayList<>();
            for(Node expNode : explored){
                
                // Buscamos nodos con arista origen en Explored
                // Y destino en NotExplored
                List<Edge> edges = Graph.getEdgesBySource(expNode, g.getE(), true);                
                                
                // Filtramos por nodos destino no explorados
                edges = edges.stream().filter(
                            e -> !explored.contains(e.getTarget())
                        ).collect(Collectors.toList());
                
                // Recolectamos nodos por explorar
                // Por cada nodo por explorar, calculamos distancia         
                if(edges == null)
                    System.out.println("Null");
                edges.forEach(e -> {
                    Node target = e.getTarget(); 
                    int d = (int)expNode.getData(DIJK_DIS) + (int)e.getObject(Edge.COST);
                    //System.out.println("Edge: "+e+" d: "+d);
                    if(d<(int)target.getData(DIJK_DIS)){
                        target.replaceData(DIJK_DIS, d);  
                        // Guardamos arista 
                        target.addData(DIJK_PRE_EDG, e);
                    }
                                        
                    toExplore.add(target);                    
                });                                
            }            
            
            // Seleccionamos el nodo con la menor distancia
            // y lo marcamos como explorado
            int dmin = INFINITE.intValue();
            Node nodeMin = null;
            for (Node n : toExplore) {
                if((int)n.getData(DIJK_DIS)<dmin){
                    dmin = (int)n.getData(DIJK_DIS);
                    nodeMin = n;
                }  
            }

            // marcar node min
            if(nodeMin != null){
                Edge edgeSource = (Edge) nodeMin.getData(DIJK_PRE_EDG);
                explored.add(nodeMin);
                //System.out.println("De "+edgeSource.getSource()+" hacia "+nodeMin);
                
                // Agregamos al grafo
                shortPathGraph.addNode(nodeMin);
                shortPathGraph.addEdge(edgeSource);
            }                     
        }        
        
        return shortPathGraph;
    }
    
    /**
     * Calcula el Arbol de Expansión Mínima por método Kruskal
     * @param g Grafo
     * @return MST
     * @throws Exception 
     */
    public static UDGraph Kruskal(Graph g) throws Exception{
        UDGraph kruskal = new UDGraph();
        
        List<Edge> edges = new ArrayList<>();
        g.getE().forEach(e -> edges.add(e));
        
        // Ordenamo las aristas por costo menor
        Collections.sort(edges,new EdgeComparator());
        
        // Por cada nodo se crea un grupo
        List groups = new ArrayList<>();
        g.getV().forEach(n -> {
            List<Node> group = new ArrayList<>();
            group.add(n);
            groups.add(group);
        });       
        
                
        // Por cada arista, validamos si puede agregarse
        for(Edge e : edges){
            Node n1 = e.getSource();
            Node n2 = e.getTarget();
            List<Node> groupN1 = null;
            List<Node> groupN2 = null;
            boolean createCycle = false;
            
            for(int i=0; i<groups.size(); i++){
                List<Node> group = (List<Node>) groups.get(i);
                if(group.contains(n1) && group.contains(n2)){
                    createCycle = true;
                    break;
                }                    
                else if(group.contains(n1))
                    groupN1 = group;
                else if(group.contains(n2))
                    groupN2 = group;                   
            }
            
            // Si la arista no genera ciclo,
            // se combinan los grupos
            if(!createCycle){
                for(Node n:groupN2){
                    groupN1.add(n);
                }
                groups.remove(groupN2);
                groupN2 = null;
                kruskal.getE().add(e);
            }
           
        }
        
        if(groups.size() > 1)
            throw new Exception("Algo salió mal :(");
        
        kruskal.setV((List<Node>)groups.get(0));
                
        return kruskal;
    }
    
    /**
     * Calcula el Arbol de Expansión Mínima por método Kruskal Inverso
     * @param g Grafo
     * @return MST
     * @throws Exception 
     */
    public static UDGraph IKruskal(Graph g) throws Exception{
        UDGraph iKruskal = new UDGraph();        
        
        List<Edge> edges = new ArrayList<>();
        g.getE().forEach(e -> edges.add(e));
        
        // agregamos E y V a iKruskal
        for (Edge e : g.getE()) {
            iKruskal.addEdge(e);
        }
        iKruskal.setV(g.getV());
        
        // Ordenamos las aristas por costo descendente
        Collections.sort(edges, new EdgeComparator());//ordena ascendente
        Collections.reverse(edges);
        
        // Borramos la arista que no desconecte el grafo
        int total_nodes = iKruskal.getV().size();
        for (Edge edge : edges) {
            iKruskal.getE().remove(edge);
            Graph dfs = Search.DFS_I(iKruskal,iKruskal.getV().get(0));
            // si se desconectó, regresamos la arista
            if(dfs.getV().size() < total_nodes){
                iKruskal.addEdge(edge);
            }
        }
                
        return iKruskal;
    }
    
}
