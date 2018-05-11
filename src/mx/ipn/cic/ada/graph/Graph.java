
package mx.ipn.cic.ada.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import mx.ipn.cic.ada.math.Counter;

/**
 *
 * @author Miguel Moreno
 */
public abstract class Graph {
    
    protected List<Node> V;
    protected List<Edge> E;
    
    /**
     * Obtiene un nodo de manera aleatoria
     * @param V conjunto de nodos
     * @return 
     */
    private static Node getRandomNode(List<Node> V){        
        int min = 0;
        int max = V.size()-1;
        int randomNum = new Random().nextInt((max - min) + 1) + min; 
        return V.get(randomNum);
    }
            
    
    
    
    
    private static void validateInput(boolean isDigraph, boolean hasAutocycle, int n, int m) throws Exception{        
        
        // Validamos número de nodos
        if(n<0)
            throw new Exception("Número de nodos no puede ser menor a 0");
        
        // Validamos número máximo de aristas
        if(m<0)
            throw new Exception("Número de aristas no puede ser menor a 0");          
                
        // Calculamos el número maximo de arista
        long maxEdges = 0;
        if(isDigraph){            
            if(hasAutocycle){
                if(n == 0)
                    maxEdges = 0;
                else if (n == 1)
                    maxEdges = 1;
                else
                    maxEdges = Counter.permutation(n, 2, true);
            }                
            else{
                if(n == 0)
                    maxEdges = 0;
                else if (n == 1)
                    maxEdges = 0;
                else
                    maxEdges = Counter.permutation(n, 2, false);
            }           
        }
        else{
            if(hasAutocycle){
                if(n == 0)
                    maxEdges = 0;
                else if (n == 1)
                    maxEdges = 1;
                else
                    maxEdges = Counter.combination(n, 2, true);
            }
            else{
                if(n == 0)
                    maxEdges = 0;
                else if (n == 1)
                    maxEdges = 0;
                else
                    maxEdges = Counter.combination(n, 2, false);
            }
        }
        
        if(m > maxEdges) 
            throw new Exception("No pueden haber mas de "+maxEdges+" aristas");        
    }
    
    public static boolean existsEdge(Edge e, List<Edge> E, boolean isDigraph){
        boolean exists = true;
        List result = null;
        
        if(isDigraph){
            result = E.stream()
                      .filter(edge -> 
                               edge.getSource().getId().equals(e.getSource().getId())
                               && edge.getTarget().getId().equals(e.getTarget().getId())
                      )
                      .collect(Collectors.toList());   
            if(result.isEmpty())
                exists = false;
        }
        else{
            result = E.stream()
                     .filter(edge -> 
                            (edge.getSource().getId().equals(e.getSource().getId())
                            && edge.getTarget().getId().equals(e.getTarget().getId()))
                            || (edge.getSource().getId().equals(e.getTarget().getId())
                            && edge.getTarget().getId().equals(e.getSource().getId()))
                      )
                      .collect(Collectors.toList());        
            if(result.isEmpty())
                exists = false;            
        }
        
        
        return exists;
    }
    
    public static boolean existsNode(Node n, List<Node> V){
        boolean exists = true;
        List result = null;        
        
        result = V.stream()
                 .filter(node -> node.getId().equals(n.getId()))
                 .collect(Collectors.toList());  
        
        if(result.isEmpty())
            exists = false;            
                
        return exists;
    }
    
    /**
     * Generacion de grafo por metodo Erdos-Renyi
     * @param isDigraph tipo de grafo
     * @param hasAutocycle si permite autociclos
     * @param n numero de nodos
     * @param m numero de aristas
     * @param hasEdgeCost si las aristas tendrán costo
     * @return grafo generado
     * @throws Exception 
     */
    public static Graph createByErdosRenyi(boolean isDigraph, boolean hasAutocycle, int n,
            int m, boolean hasEdgeCost) throws Exception{        
        Graph graph = null;
        
        // Validamos los datos de entrada
        validateInput(isDigraph,hasAutocycle,n,m);
        
                
        // Construimos grafos
        if(isDigraph)
            graph = new DIGraph();
        else 
            graph = new UDGraph();
        
        // Generamos n cantidad de nodos
        for(int i=1;i<=n;i++)
            graph.addNode(new Node(String.valueOf(i)));
        
        // Generamos m cantidad de aristas al azar
        int count = 0;
        while(count < m){
            Node n1 = getRandomNode(graph.getV());
            Node n2 = getRandomNode(graph.getV());
            Edge e = new Edge(n1, n2);
            
            // Validamos autociclo
            if(n1.getId().equals(n2.getId())){
                if(!hasAutocycle){
                    continue;
                }
            }
            
            // Validamos que no exista la arista
            if(!existsEdge(e,graph.getE(),isDigraph)){
                
                // Validamos si debe tener un costo
                if(hasEdgeCost){
                    int cost = (int) (Math.random() * Edge.MAX_COST) + 1;
                    e.addObject(Edge.COST, cost);
                }                               
                
                // Agregamos
                graph.addEdge(e);
                count++;
            }
            else{
                // Si es digraph, intentamos con arista contraria
                if(isDigraph){
                    e = new Edge(e.getTarget(), e.getSource());
                    if(!existsEdge(e,graph.getE(),isDigraph)){
                        
                        // Validamos si debe tener un costo
                        if(hasEdgeCost){
                            int cost = (int) (Math.random() * Edge.MAX_COST) + 1;
                            e.addObject(Edge.COST, cost);
                        } 
                        
                        // Agregamos
                        graph.addEdge(e);
                        count++;
                    }
                }
            }  
        }
        
        
        return graph;       
    }
    
    /**
     * Generacion de grafo por metodo Gilbert
     * @param isDigraph tipo de grafo
     * @param hasAutocycle si permite autociclos
     * @param n numero de nodos
     * @param p probabilida de tener arista
     * @param hasEdgeCost si las aristas tendrán costo
     * @return grafo generado
     * @throws Exception 
     */
    public static Graph createByGilbert(boolean isDigraph, boolean hasAutocycle, int n,
            float p, boolean hasEdgeCost) throws Exception{        
        Graph graph = null;
        
        // Validamos los datos de entrada
        if(p<0 || p>1)
            throw new Exception("El valor de p debe estar entre 0 y 1");
        
                
        // Construimos grafos
        if(isDigraph)
            graph = new DIGraph();
        else 
            graph = new UDGraph();
        
        // Generamos n cantidad de nodos
        for(int i=1;i<=n;i++)
            graph.addNode(new Node(String.valueOf(i)));
        
        // Generamos aristas
        // Por cada par de nodos
        int i=0,j=0;
        for (Node n1:graph.getV()) {
            j=0;
            for (Node n2:graph.getV()) {
                float random = (float)Math.random();                
                // Si gané volado
                if(random <= p){
                    
                    // Si no permite autociclo, omitimos
                    if(!hasAutocycle)
                        if(n1.getId().equals(n2.getId()))
                            continue;
                    
                    Edge e = new Edge(n1, n2);
                    if(!existsEdge(e,graph.getE(),isDigraph)){
                        
                        // Validamos si debe tener un costo
                        if(hasEdgeCost){
                            int costo = (int) (Math.random() * Edge.MAX_COST) + 1;
                            e.addObject(Edge.COST, costo);
                        }
                        
                        // Agregamos
                        graph.addEdge(e);
                    }
                }
                j++;
            }
            i++;
        }
        
        return graph;
    }
    
    /**
     * Generacion de grafo por metodo Geográfico
     * @param isDigraph tipo de grafo
     * @param hasAutocycle si permite autociclos
     * @param n numero de nodos
     * @param r distancia maxima del nodo vecino  
     * @param hasEdgeCost si las aristas tendrán costo
     * @return grafo generado
     * @throws Exception 
     */
    public static Graph createByGeographic(boolean isDigraph, boolean hasAutocycle, int n,
            float r, boolean hasEdgeCost) throws Exception{
        Graph graph = null;
        
        // Validamos los datos de entrada
        if(r<=0)
            throw new Exception("La distancia r debe ser mayor a 0");
        
                
        // Construimos grafos
        if(isDigraph)
            graph = new DIGraph();
        else 
            graph = new UDGraph();        
        
        // Clase local para agregar coordenadas al nodo
        class NodeGeo extends Node{
            float x; // Coordenada x
            float y; // Coordenada y  
            NodeGeo(String id) {
                super(id);
            }                       
            float calcDistance(NodeGeo ng){
                float dist = (float) Math.pow((ng.x - this.x),2);
                dist += (float) Math.pow((ng.y - this.y),2);
                dist = (float) Math.sqrt(dist);                              
                return dist;
            }
            @Override
            public String toString() {
                return "NodeGeo{" + "x=" + x + ", y=" + y + '}';
            }            
        }
        
        // Generamos n cantidad de nodos
        for(int i=1;i<=n;i++){
            NodeGeo ng = new NodeGeo(String.valueOf(i));
            ng.x = (float) Math.random();
            ng.y = (float) Math.random();            
            graph.addNode(ng);
            //System.out.println(ng);
        }
        
        // Buscamos nodos cercanos para crear arista
        for(int i=0; i<graph.getV().size(); i++){
            NodeGeo ni = (NodeGeo) graph.getV().get(i);
            for(int j=0; j<graph.getV().size(); j++){
                NodeGeo nj = (NodeGeo) graph.getV().get(j);
                // Si es mismo nodo validamos autociclo
                if(ni.getId().equals(nj.getId())){
                    if(hasAutocycle){                            
                        Edge e = new Edge(ni, nj);
                        
                        // Validamos si debe tener un costo
                        if(hasEdgeCost){
                            e.addObject(Edge.COST, 0);
                        }
                        
                        graph.addEdge(e);                           
                    }
                    else{
                        continue;
                    }
                }
                else{
                    // Si distancia cumple, se crea arista
                    if(ni.calcDistance(nj) <= r){                            
                        Edge e = new Edge(ni, nj); 
                        if(!existsEdge(e,graph.getE(),isDigraph)){
                            
                            // Validamos si debe tener un costo
                            if(hasEdgeCost){
                                int costo = (int) (Math.random() * Edge.MAX_COST) + 1;
                                e.addObject(Edge.COST, costo);
                            }
                            
                            graph.addEdge(e);
                        }
                    }
                }
            }
        }
        
        return graph;
    }
    
    /**
     * Generacion de grafo por metodo Barabasi Albert
     * @param isDigraph tipo de grafo
     * @param n numero de nodos
     * @param d numero máximo de aristas por nodo     * 
     * @param hasEdgeCost si las aristas tendrán costo
     * @return grafo generado
     * @throws Exception 
     */
    public static Graph createByBarabasiAlbert(boolean isDigraph, int n, int d, boolean hasEdgeCost) throws Exception{
        Graph graph = null;
        
        // Validamos los datos de entrada
        if(d<=0)
            throw new Exception("El número de aristas d debe ser mayor a 0");
        
                
        // Construimos grafo
        if(isDigraph)
            graph = new DIGraph();
        else 
            graph = new UDGraph();  
        
        // Generamos n cantidad de nodos
        for(int i=1;i<=n;i++){
            Node newNode = new Node(String.valueOf(i));
            
            //si es el primer nodo, solo se agrega
            if(i==1){
                graph.addNode(newNode);
            }
            else{
                // Por cada nodo en V vaidamos si
                // se genera una arista de newNode a cada V
                int degV = 0;
                float p = 0;
                float random = 0f;
                for(Node vNode : graph.getV()){
                    degV = graph.getDegree(vNode);
                    p = 1 - (degV / d);
                    random = (float)Math.random();   
                    
                    // Si la probabilidad se cumple agregamos arista
                    if(random <= p){
                       Edge e = new Edge(newNode, vNode);
                       
                       // Validamos si debe tener un costo
                       if(hasEdgeCost){
                           int costo = (int) (Math.random() * Edge.MAX_COST) + 1;
                           e.addObject(Edge.COST, costo);
                       } 
                        
                       graph.addEdge(e);
                       //System.out.println("Arista de "+newNode.getId()+" a "+vNode.getId());
                    }                    
                }
                
                // Una vez evaluado contra todos los nodos V, se agrega
                graph.addNode(newNode);
            }
            
        }
        
        
        return graph;
    }
    
    /**
     * Obtiene aristas con origen en s, varía si es dirigido o no
     * @param s Nodo origen
     * @param E Conjunto de Aristas
     * @return 
     */
    public static List<Edge> getEdgesBySource(Node s, List<Edge> E, boolean isDigraph){
        List<Edge> result = null;
        
        if(isDigraph){
            result = E.stream().filter(e -> 
                                       e.getSource().getId().equals(s.getId()))
                     .collect(Collectors.toList());    
        }
        else{
            result = E.stream().filter(e -> 
                                       e.getSource().getId().equals(s.getId())
                                       || e.getTarget().getId().equals(s.getId()))
                     .collect(Collectors.toList());  
        }      
                
        return result;
    }
    
    protected Graph() {
        this.V = new ArrayList<Node>();
        this.E = new ArrayList<Edge>();
    }

    public List<Node> getV() {
        return V;
    }

    public void setV(List<Node> V) {
        this.V = V;
    }
        
    public List<Edge> getE() {
        return E;
    }      

    public void setE(List<Edge> E) {
        this.E = E;
    }       
    
    public boolean isDigraph(){
        return this instanceof DIGraph;
    }
    
        
    @Override
    public String toString() {
        String g = "Graph{\n" ;
        
        boolean isDigraph = false;
        if(this.isDigraph())
            isDigraph = true;
        
        g+= "\tisDigraph: "+isDigraph+"\n";
        
        g+= "\tV[";
        for (int i=0; i<=this.V.size()-1; i++) {
            if(i == this.V.size()-1)
                g+=this.V.get(i);
            else
                g+=this.V.get(i)+",";
        }
        g+= "]\n";
        
        g+= "\tE[";
        for (int i=0; i<=this.E.size()-1; i++) {
            if(i == this.E.size()-1)
                g+=this.E.get(i);
            else
                g+=this.E.get(i)+",";
        }
        g+= "]\n";        
        g += '}';
        
        return g;
    }
    
    
    
   /**
    * Agrega un nodo al grafo
    * @param n nodo
    * @throws Exception 
    */
    public abstract void addNode(Node n) throws Exception;
    
    /**
     * 
     * @param id
     * @return nodo
     * @throws Exception 
     */
    public abstract Node getNode(String id) throws Exception;;
    
    /**
     * Agrega una arista al grafo
     * @param e arista
     * @throws Exception 
     */
    public abstract void addEdge(Edge e) throws Exception;
    
    /**
     * Obtiene el grado de un nodo dado
     * @param n nodo
     * @return grado
     */
    public abstract int getDegree(Node n);
    
    /**
     * Genera archivo con código Graphviz
     * @param destFile ruta archivo destino
     */
    public abstract void toGraphviz(String destFile) throws Exception;
    
}
