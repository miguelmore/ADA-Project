

package mx.ipn.cic.ada.math;

/**
 *
 * @author Miguel Moreno
 */
public class Counter {
    
    /**
     * Permutación reducida a r constante a 2
     * @param n 
     * @param r
     * @param hasRepetition
     * @return 
     */
    public static long permutation(int n, int r, boolean hasRepetition){
        
        long per = 0;
        
        if(hasRepetition){
            per = (long) Math.pow(n,r);
        }
        else{
            //per = (long) fact(n) / fact(n-r);
            per = (long) n*(n-1);
        }
                
        return per;
    }
    
    /**
     * Combinación reducida a r constante a 2
     * @param n
     * @param r
     * @param hasRepetition
     * @return 
     */
    public static long combination(int n, int r, boolean hasRepetition){
        
        long com = 0;
        
        if(hasRepetition){
            //com = fact(n+r-1) / ( fact(r) * fact(n-1));
            com = ((n+1)*n) / r;
        }
        else{
            //com = fact(n) / ( fact(r) * fact(n-r));
            com = (n*(n-1)) / r;
        }
                
        return com;
    }
    
    /**
     * Factorial
     * @param n
     * @return 
     */
    private static long fact(int n){
        long fact = 1;        
        for (int i = 1; i <= n; i++) {
            fact *= i;            
        }        
        return fact;
    }

}
