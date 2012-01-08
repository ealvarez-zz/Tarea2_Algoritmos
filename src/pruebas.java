//javac pruebas.java

import java.util.*;

class pruebas {

    static public long uniforme(long inf, long sup) {
        return (long) (Math.random() * (sup - inf)) + inf;
    }

    static long[] generaSecuenciaDesordenada(int num_elementos_insertar, long rango_max) {
        long[] elementos = new long[num_elementos_insertar];

        //Generamos la secuencia de elementos al azar que seran insertados en la estructura
        for (int u = 0; u < elementos.length; u++) {
            elementos[u] = uniforme(0, rango_max);
        }

        return elementos;
    }

    static long[] generaSecuenciaOrdenada(int num_elementos_insertar, long rango_max) {
        long[] elementos = new long[num_elementos_insertar];

        //Generamos la secuencia de elementos al azar que seran insertados en la estructura
        for (int u = 0; u < elementos.length; u++) {
            elementos[u] = uniforme(0, rango_max);
        }

        return elementos;
    }

    /*
     * Los elementos a insertar son escogidos al azar uniformemente del universo, y los a borrar, al
     * azar uniformemente del conjunto ya insertado en la estructura
     */
    static public void Test1(int n, long rango_max, PriorityQueue pq) {

        int[] k = {1, 3, 7};

        for (int i = 0; i < k.length; i++) {

            pq.reset(3 * k[i] * n);

            //Lleva el registro de los valores insertados para luego escoger al azar los elementos a borrar
            LinkedList elementos_insertados = new LinkedList();

            System.out.println("N: " + n + "\tK: " + k[i]);

            long ini, fin;

            ini = System.nanoTime();

            for (int s = 0; s < n; s++) { //Realiza (i^k d^k i^k)^n

                int j;

                for (j = 0; j < k[i]; j++) //Realiza i^k inserciones
                {
                    long value = uniforme(0, rango_max);
                    pq.add(value);
                    elementos_insertados.add(value);
                }

                for (j = 0; j < k[i]; j++) //Realiza d^k eliminaciones
                {
                    int escogido_eliminar = (int) uniforme(0, elementos_insertados.size());

                    Long value = (Long) elementos_insertados.remove(escogido_eliminar);
                    pq.remove(value.longValue());


                }

                for (j = 0; j < k[i]; j++) //Realiza i^k inserciones
                {
                    long value = uniforme(0, rango_max);
                    pq.add(value);
                    elementos_insertados.add(value);
                }
            }

            fin = System.nanoTime();

            System.out.println("(i^k d^k i^k)^n \t" + (fin - ini) + " msec");

            ini = System.nanoTime();

            for (int s = 0; s < n; s++) { //Realiza (d^k i^k d^k)^n

                int j;

                for (j = 0; j < k[i]; j++) //Realiza d^k eliminaciones
                {
                    int escogido_eliminar = (int) uniforme(0, elementos_insertados.size());

                    Long value = (Long) elementos_insertados.remove(escogido_eliminar);
                    pq.remove(value.longValue());

                }

                for (j = 0; j < k[i]; j++) //Realiza i^k inserciones
                {
                    long value = uniforme(0, rango_max);
                    pq.add(value);
                    elementos_insertados.add(value);
                }

                for (j = 0; j < k[i]; j++) //Realiza d^k eliminaciones
                {
                    int escogido_eliminar = (int) uniforme(0, elementos_insertados.size());

                    Long value = (Long) elementos_insertados.remove(escogido_eliminar);
                    pq.remove(value.longValue());

                }
            }

            fin = System.nanoTime();
            System.out.println("(d^k i^k d^k)^n \t" + (fin - ini) + " msec");

        }


    }

    public static void main(String[] args) {


        int n = 2048; // 2^11
        //int n = 524288;	// 2^19

        int max_intervalo = 65535; // 2^16-1


        System.out.println("======= BinaryHeap ========");

        BinaryHeap bh = new BinaryHeap();

        Test1(n, max_intervalo, bh);

        System.out.println("======= FibonacciHeap ========");

        FibonacciHeap fh = new FibonacciHeap();

        Test1(n, max_intervalo, fh);



    }
}
