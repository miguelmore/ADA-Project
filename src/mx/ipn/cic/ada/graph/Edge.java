
package mx.ipn.cic.ada.graph;

import java.util.HashMap;

/**
 *
 * @author Miguel Moreno
 */
public class Edge {
    
    public static final String COST = "COST"; // Key para dato del costo
    public static final int MAX_COST = 50; // Key para valor maximo del costo
    
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
    
    public void addObject(String key, Object value){
        this.data.put(key, value);
    }
    
    public String getData(String key){
        return (String) this.data.get(key);
    }
    
    public Object getObject(String key){
        return this.data.get(key);
    }

    public Node getSource() {
        return source;
    }

    public Node getTarget() {
        return target;
    }   

    @Override
    public String toString() {
        String desc = "(" + source.getId() + "," + target.getId() ;
        
        Object cost = this.getObject(COST);
        
        if(cost != null){
            desc += "-cost:" + (int)cost;
        }
                
        desc += ')';
        return desc;
    }
    
    
}
