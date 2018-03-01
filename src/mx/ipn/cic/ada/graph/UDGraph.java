
package mx.ipn.cic.ada.graph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Miguel Moreno
 */
public class UDGraph extends Graph {

    public UDGraph() {
        super();
    }  

    @Override
    public void addNode(Node n) throws Exception {
        // Verificamos que nodo no exista
        List result = this.V.stream()
                      .filter(node -> node.getId().equals(n.getId()))
                      .collect(Collectors.toList());
        
        // Si no existe se agrega
        if(result.isEmpty()){
            this.V.add(n);
        }
        else{
            String msg = "El nodo "+n+" ya existe";
            throw new Exception(msg);
        }
    }
    
    @Override
    public Node getNode(String id) throws Exception {
        // Buscamos nodo
        List result = this.V.stream()
                      .filter(node -> node.getId().equals(id))
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
                               (edge.getSource().getId().equals(e.getSource().getId())
                               && edge.getTarget().getId().equals(e.getTarget().getId()))
                               || (edge.getSource().getId().equals(e.getTarget().getId())
                               && edge.getTarget().getId().equals(e.getSource().getId()))
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
        int degree = 0;
        List result = this.E.stream()
                      .filter(edge -> 
                              (edge.getSource().getId().equals(n.getId())) 
                              || (edge.getTarget().getId().equals(n.getId()))
                      )
                      .collect(Collectors.toList());
        
        // Sumamos las aristas incidentes
        degree += result.size();
        
        // Si la arista tiene el mismo nodo de de origen a destino 
        // ésa arista contribuye en 2 grados        
        result = this.E.stream()
                 .filter(edge -> 
                             (edge.getSource().getId().equals(n.getId())) 
                             && (edge.getTarget().getId().equals(n.getId()))
                 )
                 .collect(Collectors.toList());
        
        if(result.size()>0)
            degree++;
        
        return degree;
    }

    @Override
    public void toGraphviz(String destFile) throws Exception{
        
        // Generamos código gv
        StringBuilder sb = new StringBuilder("graph{\n");
        for(Edge e : this.E){
            sb.append(e.getSource().getId()+" -- ");
            sb.append(e.getTarget().getId()+"\n");
        }             
        sb.append("}"); 
       
        File file = new File(destFile);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(sb.toString());
        } catch (IOException ex) {
            throw new Exception("No se pudo crear el archivo GV");
        }
        finally{            
            bw.close();
        }
    }

    

}
