
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
    public void addEdge(Edge e) {
        this.E.add(e);
    }

    @Override
    public int getDegree(Node n) {
        return 0;
    }

}
