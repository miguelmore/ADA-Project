

package mx.ipn.cic.ada.graph;

import java.util.Comparator;

/**
 *
 * @author Miguel Moreno
 */
public class EdgeComparator implements Comparator<Edge>{
    
    @Override
    public int compare(Edge a, Edge b)
    {
        int res = 0;
        int costA = (int) a.getObject(Edge.COST);
        int costB = (int) b.getObject(Edge.COST);
        
        if(costA > costB)
            res = 1;
        else if(costA < costB)
            res = -1;            
        
        return res;
    }

}
