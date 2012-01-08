/**
 *
 * @author Erwin Alvarez C <ealvarez at dcc.uchile.cl>
 */

public class tarea2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SplayTree tree = new SplayTree();
        int repeat = 10;
        //inserciones consecutivas
        for(int i = 10; i > 0; i --){
            tree.insert(i);
        }
        System.out.println("inserté");
        tree.print(tree.root);
        for(int i = 10; i > 0; i --){
            tree.delete();
        }
        System.out.println("borré");
        tree.print(tree.root);
    }
}
