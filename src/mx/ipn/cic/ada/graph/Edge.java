
package mx.ipn.cic.ada.graph;

import java.util.HashMap;

/**
 *
 * @author Miguel Moreno
 */
public class Edge {
    private Node source;
    private Node target;
    private HashMap data;

    public Edge(Node source, Node target) {
        this.source = source;
        this.target = target;
        this.data = new HashMap<>();
    }
    
    public void addData(String key, String value){
        this.data.put(key, value);
    }
    
    public String getData(String key){
        return (String) this.data.get(key);
    }

    public Node getSource() {
        return source;
    }

    public Node getTarget() {
        return target;
    }   

    @Override
    public String toString() {
        return "(" + source.getId() + "," + target.getId() + ')';
    }
    
    
}
