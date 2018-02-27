
package mx.ipn.cic.ada.graph;

import java.util.HashMap;

/**
 *
 * @author Miguel Moreno
 */
public class Node {
    private int id;
    private HashMap data;

    public Node(int id) {
        this.id = id;
        this.data = new HashMap<>();
    }
    
    public void addData(String key, String value){
        this.data.put(key, value);
    }
    
    public String getData(String key){
        return (String) this.data.get(key);
    }  

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "n" + id;
    }
    
    
    
}
