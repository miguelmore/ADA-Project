
package mx.ipn.cic.ada.graph;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Miguel Moreno
 */
public class UDGraph extends Graph{

    public UDGraph() {
        super();
    }  

    @Override
    public void addNode(Node n) throws Exception {
        // Verificamos que nodo no exista
        List result = this.V.stream()
                      .filter(node -> node.getId() == n.getId())
                      .collect(Collectors.toList());
        
        // Si no existe se agrega
        if(result.isEmpty()){
            this.V.add(n);
        }
        else{
            String msg = "El nodo "+n.getId()+" ya existe";
            throw new Exception(msg);
        }
    }
    
    @Override
    public Node getNode(int id) throws Exception {
        // Buscamos nodo
        List result = this.V.stream()
                      .filter(node -> node.getId() == id)
                      .collect(Collectors.toList());
        
        if(result.isEmpty()){
            String msg = "El nodo "+id+" no existe";
            throw new Exception(msg);
        }
        else{
            return (Node)result.get(0);
        }
    }

    @Override
    public void addEdge(Edge e) throws Exception {
        // Verificamos que la arista no exista
        List result = this.E.stream()
                      .filter(edge -> 
                               (edge.getSource().getId() == e.getSource().getId() 
                               && edge.getTarget().getId() == e.getTarget().getId())
                               || (edge.getSource().getId() == e.getTarget().getId() 
                               && edge.getTarget().getId() == e.getSource().getId()) 
                      )
                      .collect(Collectors.toList());
        
        // Si no existe se agrega
        if(result.isEmpty()){
            this.E.add(e);
        }
        else{
            String msg = "La arista "+e.toString()+" ya existe";
            throw new Exception(msg);
        }
    }

    @Override
    public int getDegree(Node n) {        
        List result = this.E.stream()
                      .filter(edge -> 
                              (edge.getSource().getId() == n.getId()) 
                              || (edge.getTarget().getId() == n.getId())
                      )
                      .collect(Collectors.toList());
        
        return result.size();
    }

    @Override
    public void toGraphviz(String fileDir) {
        
        // Generamos código gv
        
    }

    

}
