//javac pruebas.java

import java.util.*;

class pruebas {

    static public long uniforme(long inf, long sup) {
        return (long) (Math.random() * (sup - inf)) + inf;
    }


    /*
     * Los elementos a insertar son escogidos al azar uniformemente del universo, y los a borrar, al
     * azar uniformemente del conjunto ya insertado en la estructura
     */
    static public void Test(int n, long rango_max, PriorityQueue pq) {

        int[] k = {1, 3, 7};
        InstanceFactory instanceGenerator = new InstanceFactory();

        for (int i = 0; i < k.length; i++) {

            pq.reset(3 * k[i] * n);

            //Lleva el registro de los valores insertados para luego escoger al azar los elementos a borrar
//            LinkedList elementos_insertados = new LinkedList();

            System.out.println("N: " + n + "\tK: " + k[i]);

            long ini, fin;

            ini = System.nanoTime();
            int[] instance = instanceGenerator.createRandomInstance((int) rango_max, 3 * k[i] * n);
            int instanceIndex = 0;

            for (int s = 0; s < n; s++) { //Realiza (i^k d^k i^k)^n

                int j;

                for (j = 0; j < k[i]; j++) //Realiza i^k inserciones
                {
//                    long value = uniforme(0, rango_max);
                    pq.add(instance[instanceIndex++]);
//                    elementos_insertados.add(value);
                }

                for (j = 0; j < k[i]; j++) //Realiza d^k eliminaciones
                {
//                    int escogido_eliminar = (int) uniforme(0, elementos_insertados.size());

//                    Long value = (Long) elementos_insertados.remove(escogido_eliminar);
                    pq.removeMin();


                }

                for (j = 0; j < k[i]; j++) //Realiza i^k inserciones
                {
//                    long value = uniforme(0, rango_max);
//                    pq.add(value);
//                    elementos_insertados.add(value);
                    pq.add(instance[instanceIndex++]);
                }
            }

            fin = System.nanoTime();

            System.out.println("(i^k d^k i^k)^n \t" + (fin - ini) + " nanosec");

            ini = System.nanoTime();

            for (int s = 0; s < n; s++) { //Realiza (d^k i^k d^k)^n

                int j;

                for (j = 0; j < k[i]; j++) //Realiza d^k eliminaciones
                {
//                    int escogido_eliminar = (int) uniforme(0, elementos_insertados.size());
//
//                    Long value = (Long) elementos_insertados.remove(escogido_eliminar);
//                    pq.remove(value.longValue());
                    pq.removeMin();

                }

                for (j = 0; j < k[i]; j++) //Realiza i^k inserciones
                {
//                    long value = uniforme(0, rango_max);
//                    pq.add(value);
//                    elementos_insertados.add(value);
                    pq.add(instance[instanceIndex++]);
                }

                for (j = 0; j < k[i]; j++) //Realiza d^k eliminaciones
                {
//                    int escogido_eliminar = (int) uniforme(0, elementos_insertados.size());
//
//                    Long value = (Long) elementos_insertados.remove(escogido_eliminar);
//                    pq.remove(value.longValue());
                    pq.removeMin();

                }
            }

            fin = System.nanoTime();
            System.out.println("(d^k i^k d^k)^n \t" + (fin - ini) + " nanosec");

        }
    }

    static public void Test2(int n, long rango_max, PriorityQueue pq) {

        int[] k = {1, 3, 7};
        InstanceFactory instanceGenerator = new InstanceFactory();

        for (int i = 0; i < k.length; i++) {

            pq.reset(3 * k[i] * n);

            //Lleva el registro de los valores insertados para luego escoger al azar los elementos a borrar
//            LinkedList elementos_insertados = new LinkedList();

            System.out.println("N: " + n + "\tK: " + k[i]);

            long ini, fin;

            ini = System.nanoTime();
            int[] instance = instanceGenerator.createSemiOrdeneredInstance((int) rango_max, 3 * k[i] * n);
            int instanceIndex = 0;

            for (int s = 0; s < n; s++) { //Realiza (i^k d^k i^k)^n

                int j;

                for (j = 0; j < k[i]; j++) //Realiza i^k inserciones
                {
//                    long value = uniforme(0, rango_max);
                    pq.add(instance[instanceIndex++]);
//                    elementos_insertados.add(value);
                }

                for (j = 0; j < k[i]; j++) //Realiza d^k eliminaciones
                {
//                    int escogido_eliminar = (int) uniforme(0, elementos_insertados.size());

//                    Long value = (Long) elementos_insertados.remove(escogido_eliminar);
                    pq.removeMin();


                }

                for (j = 0; j < k[i]; j++) //Realiza i^k inserciones
                {
//                    long value = uniforme(0, rango_max);
//                    pq.add(value);
//                    elementos_insertados.add(value);
                    pq.add(instance[instanceIndex++]);
                }
            }

            fin = System.nanoTime();

            System.out.println("(i^k d^k i^k)^n \t" + (fin - ini) + " nanosec");

            ini = System.nanoTime();

            for (int s = 0; s < n; s++) { //Realiza (d^k i^k d^k)^n

                int j;

                for (j = 0; j < k[i]; j++) //Realiza d^k eliminaciones
                {
//                    int escogido_eliminar = (int) uniforme(0, elementos_insertados.size());
//
//                    Long value = (Long) elementos_insertados.remove(escogido_eliminar);
//                    pq.remove(value.longValue());
                    pq.removeMin();

                }

                for (j = 0; j < k[i]; j++) //Realiza i^k inserciones
                {
//                    long value = uniforme(0, rango_max);
//                    pq.add(value);
//                    elementos_insertados.add(value);
                    pq.add(instance[instanceIndex++]);
                }

                for (j = 0; j < k[i]; j++) //Realiza d^k eliminaciones
                {
//                    int escogido_eliminar = (int) uniforme(0, elementos_insertados.size());
//
//                    Long value = (Long) elementos_insertados.remove(escogido_eliminar);
//                    pq.remove(value.longValue());
                    pq.removeMin();

                }
            }

            fin = System.nanoTime();
            System.out.println("(d^k i^k d^k)^n \t" + (fin - ini) + " nanosec");

        }
    }

    public static void main(String[] args) {


        int n = 2048; // 2^11
        //int n = 524288;	// 2^19

        int max_intervalo = 65535; // 2^16-1


//        System.out.println("======= BinaryHeap ========");
//
//        BinaryHeap bh = new BinaryHeap();
//
//        Test(n, max_intervalo, bh);

        System.out.println("======= FibonacciHeap ========");

        FibonacciHeap fh = new FibonacciHeap();
        n = (int) Math.pow(2, 11);
        max_intervalo = (int) Math.pow(2, 16);

        Test(n, max_intervalo, fh);

        n = (int) Math.pow(2, 19);
        max_intervalo = (int) Math.pow(2, 24);
        Test(n, max_intervalo, fh);
        
        n = (int) Math.pow(2, 11);
        max_intervalo = (int) Math.pow(2, 16);

        Test2(n, max_intervalo, fh);

        n = (int) Math.pow(2, 19);
        max_intervalo = (int) Math.pow(2, 24);
        Test2(n, max_intervalo, fh);
        
        
        
        
        
    }
}
