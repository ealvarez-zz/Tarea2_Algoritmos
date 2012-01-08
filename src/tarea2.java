
/**
 *
 * @author Erwin Alvarez C <ealvarez at dcc.uchile.cl>
 */
public class tarea2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here    


        BinomialQueue b = new BinomialQueue();
        BinomialQueue b2 = new BinomialQueue();


        int[] elements = {3, 7, 6, 5, 1, 8, 2, 9, 4, 6};
        int[] elements2 = {6, 2, 8, 5, 3, 1};

        for (int i : elements) {
            b.insertItem(i);
        }

        for (int i : elements2) {
            b2.insertItem(i);
        }


//        System.out.println("Cola Binomial b:");
//        System.out.println(b);
//        b.deleteMin();
//        System.out.println("Luego de borrar el minimo:");
//        System.out.println(b);
//        System.out.println("Luego de borrar '5'");
//        b.deleteItem(5);
        
        System.out.println("Cola Binomial b:");
        System.out.println(b);
        System.out.println("Cola Binomial b2:");
        System.out.println(b2);
        
        
        System.out.println("Merge");
        b.merge(b2);
        System.out.println(b);
        
        
    }
}
