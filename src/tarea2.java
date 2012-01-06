
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


        int[] elements = {3, 7, 6, 5, 1, 8, 2, 9, 4, 6};


        for (int i : elements) {
            b.insertItem(i);
        }
        
        b.moveToTop(6);
        

        System.out.println("olo");
    }
}
