
import java.util.Arrays;

/**
 *
 * @author Erwin Alvarez C. <ealvarez at dcc.uchile.cl>
 */
public class testMerge {

    public static void main(String[] args) {


        InstanceFactory instanceGenerator = new InstanceFactory();
        int[] instance;

        int[] n = {9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

        for (int i = 0; i < n.length; i++) {
            n[i] = (int) Math.pow(2, n[i]);
        }

        System.out.println("---> Binomial Queue");
        for (int size : n) {
            BinomialQueue b1 = new BinomialQueue();
            BinomialQueue b2 = new BinomialQueue();
            instance = instanceGenerator.createRandomInstance(size / 2, size - 1);

            for (int element : instance) {
                b1.insert(element);
            }

            instance = instanceGenerator.createRandomInstance(size / 2, size - 1);

            for (int element : instance) {
                b2.insert(element);
            }

            long time = System.nanoTime();
            b1.merge(b2);
            time = System.nanoTime() - time;

            System.out.println("2^" + (int) (Math.log(size) / Math.log(2)) + ": " + time);


        }

        System.out.println("");
        System.out.println("---> Fibonacci Heap");
        for (int size : n) {
            FibonacciHeap f1 = new FibonacciHeap();
            FibonacciHeap f2 = new FibonacciHeap();
            instance = instanceGenerator.createRandomInstance(size / 2, size - 1);

            for (int element : instance) {
                f1.add(element);
            }

            instance = instanceGenerator.createRandomInstance(size / 2, size - 1);

            for (int element : instance) {
                f2.add(element);
            }

            long time = System.nanoTime();
            f1 = FibonacciHeap.union(f1, f2);
            time = System.nanoTime() - time;

            System.out.println("2^" + (int) (Math.log(size) / Math.log(2)) + ": " + time);


        }
    }
}
