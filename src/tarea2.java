
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

        int[] k = {1, 3, 7};
        int[][] n = {
            {(int) Math.pow(2, 11), (int) Math.pow(2, 16)},
            {(int) Math.pow(2, 19), (int) Math.pow(2, 24)}
        };


        System.out.println("Instancia Random:");
        for (int[] currentN : n) {
            System.out.println("N = 2^" + (int) (Math.log(currentN[0]) / Math.log(2)));
            for (int i : k) {
                Test binomialQueueTest = new Test(currentN[0], currentN[1], new BinomialQueue());

                long testTime = System.nanoTime();
                long[] results = binomialQueueTest.runRandom(i);
                testTime = System.nanoTime() - testTime;

                System.out.println(Arrays.toString(results));
                System.out.println("Tiempo de ejecucion para k=" + i + ": " + testTime / (int) Math.pow(10, 9));
            }
        }

        System.out.println("Instancia Random:");
        for (int[] currentN : n) {
            System.out.println("N = 2^" + (int) (Math.log(currentN[0]) / Math.log(2)));
            for (int i : k) {
                Test binomialQueueTest = new Test(currentN[0], currentN[1], new BinomialQueue());

                long testTime = System.nanoTime();
                long[] results = binomialQueueTest.runSemiOrdenered(i);
                testTime = System.nanoTime() - testTime;

                System.out.println(Arrays.toString(results));
                System.out.println("Tiempo de ejecucion para k=" + i + ": " + testTime / (int) Math.pow(10, 9));
            }
        }

    }
}
