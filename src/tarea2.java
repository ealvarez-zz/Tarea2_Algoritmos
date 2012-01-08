
import java.util.Arrays;

/**
 *
 * @author Erwin Alvarez C <ealvarez at dcc.uchile.cl>
 */
public class tarea2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        Test binomialQueueTest = new Test((int) Math.pow(2, 19), (int) Math.pow(2, 24), new BinomialQueue());

        long testTime = System.nanoTime();
        long[] results = binomialQueueTest.runRandom(7);
        testTime = System.nanoTime() - testTime;


        System.out.println(Arrays.toString(results));
        System.out.println("Tiempo de ejecucion: " + testTime / (int) Math.pow(10, 9));


    }
}
