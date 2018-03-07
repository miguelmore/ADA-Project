
package mx.ipn.cic.ada.graph;

import java.util.HashMap;

/**
 *
 * @author Miguel Moreno
 */
public class Node {
    private String id;
    private HashMap data;

    public Node(String id) {
        this.id = id;
        this.data = new HashMap<>();
    }
    
    public void addData(String key, Object value){
        this.data.put(key, value);
    }
    
    public void replaceData(String key, Object value){
        this.data.put(key, value);
    }
    
    public Object getData(String key){
        return  this.data.get(key);
    }  

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id;
    }
    
    
    
}
