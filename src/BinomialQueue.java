
import java.util.List;

/**
 *
 * @author Erwin Alvarez C <ealvarez at dcc.uchile.cl>
 */
public class BinomialQueue {

    private List<Node> _binomialQueue;

    /**
     * Constructor
     */
    public BinomialQueue() {
        this._binomialQueue = null;
    }

    /**
     * Aumenta el tamaño de la cola en 1 elemento.
     */
    private void growQueue() {
        this._binomialQueue.add(null);
    }

    /**
     * Une dos nodos en uno
     * @param p
     * @param q
     * @return union de ambos nod
     */
    private Node mergeNodes(Node p, Node q) {

        /*
         * Si el valor de p es mayor que q, entonces se inserta el hijo derecho
         * de p en el hijo izquierdo de q. Luego, se setea el hijo derecho de p
         * como q.
         * 
         */
        if (p.getValue() > q.getValue()) {
            q.setLeftChild(p.getRightChild());
            p.setRightChild(q);
            return p;
        }

        /* 
         * Si el valor de q es mayor que el de p, se hace el proceso inverso.
         * 
         */
        p.setLeftChild(q.getRightChild());
        q.setRightChild(p);
        return q;
    }

    /**
     * Inserta un elemento en la cola
     * @param item Elemento a insertar
     */
    public void insertItem(int item) {

        Node newNode = new Node(item);

        for (int i = 0; i <= this._binomialQueue.size(); i++) {

            /*
             * Si llegue al final de la lista y aun no encuentro una
             * posicion libre, hago crecer la cola. 
             */

            if (i == this._binomialQueue.size()) {
                growQueue();
            }

            /*
             * Si encuentro una posicion vacia en la cola, me inserto en ella.
             */

            if (this._binomialQueue.get(i) == null) {
                this._binomialQueue.set(i, newNode);
                return;
            }

            /*
             * Si la posicion ya tiene un elemento, uno los 2 arboles y sigo
             * iterando.
             */

            newNode = mergeNodes(newNode, this._binomialQueue.get(i));
            this._binomialQueue.set(i, null);

        }
    }

    public void deleteItem(int item) {
    }

    public List<Node> getBinomialQueue() {
        return this._binomialQueue;
    }
}
