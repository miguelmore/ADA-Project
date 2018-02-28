
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
    
    public void addData(String key, String value){
        this.data.put(key, value);
    }
    
    public String getData(String key){
        return (String) this.data.get(key);
    }  

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id;
    }
    
    
    
}
