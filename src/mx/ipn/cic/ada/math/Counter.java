

package mx.ipn.cic.ada.math;

/**
 *
 * @author Miguel Moreno
 */
public class Counter {
    
    /**
     * Permutación
     * @param n 
     * @param r
     * @param hasRepetition
     * @return 
     */
    public static int permutation(int n, int r, boolean hasRepetition){
        
        int per = 0;
        
        if(hasRepetition){
            per = (int)Math.pow(n,r);
        }
        else{
            per = (int) fact(n) / fact(n-r);
        }
                
        return per;
    }
    
    /**
     * Combinación
     * @param n
     * @param r
     * @param hasRepetition
     * @return 
     */
    public static int combination(int n, int r, boolean hasRepetition){
        
        int com = 0;
        
        if(hasRepetition){
            com = (int) fact(n+r-1) / ( fact(r) * fact(n-1));
        }
        else{
            com = (int) fact(n) / ( fact(r) * fact(n-r));
        }
                
        return com;
    }
    
    /**
     * Factorial
     * @param n
     * @return 
     */
    private static int fact(int n){
        int fact = 1;        
        for (int i = 1; i <= n; i++) {
            fact *= i;            
        }        
        return fact;
    }

}
