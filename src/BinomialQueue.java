
import java.util.ArrayList;
import javax.lang.model.element.Element;

/**
 *
 * @author Erwin Alvarez C <ealvarez at dcc.uchile.cl>
 */
public class BinomialQueue {

    private ArrayList<GenericNode> _binomialQueue;

    /**
     * Constructor
     */
    public BinomialQueue() {
        this._binomialQueue = new ArrayList<GenericNode>();
    }

    public BinomialQueue(int item) {
        GenericNode node = new GenericNode(item);
        this._binomialQueue = new ArrayList<GenericNode>();
        this._binomialQueue.add(node);
    }

    /**
     * Aumenta el tamaño de la cola en 1 elemento.
     */
    private void growQueue() {
        this._binomialQueue.add(null);
    }

    private GenericNode joinNodes(GenericNode p, GenericNode q) {

        if (p.getValue() < q.getValue()) {
            q.setParent(p);
            p.addChild(q);
            return p;
        }

        p.setParent(q);
        q.addChild(p);
        return q;
    }

    /**
     * Inserta un elemento en la cola
     * @param item Elemento a insertar
     */
    public void insertItem(int item) {

        GenericNode node = new GenericNode(item);

        for (int i = 0; i <= this._binomialQueue.size(); i++) {

            if (this._binomialQueue.isEmpty()) {
                growQueue();
                this._binomialQueue.set(i, node);
                return;
            }


            if (this._binomialQueue.get(i) == null) {
                this._binomialQueue.set(i, node);
                return;
            }

            if (i == this._binomialQueue.size() - 1) {
                growQueue();
            }


            node = joinNodes(this._binomialQueue.get(i), node);
            this._binomialQueue.set(i, null);
        }


    }

    public void deleteItem(int item) {
    }

    public ArrayList<GenericNode> getBinomialQueue() {
        return this._binomialQueue;
    }


    private GenericNode find(int value) {
        return find(value, this._binomialQueue);

    }

    private GenericNode find(int value, ArrayList<GenericNode> nodeQueue) {

        if (nodeQueue == null) {
            return null;
        }

        GenericNode element = null;

        for (GenericNode node : nodeQueue) {
            if (node != null) {
                if (node.getValue() == value) {
                    element = node;
                    break;
                } else {
                    if (node.getValue() < value) {
                        element = find(value, node.getChildren());
                    }
                }
            }
        }
        return element;
    }

    public void moveToTop(int value) {
        swapUp(find(value));
    }

    public void swapUp(GenericNode node) {

        GenericNode parent = node.getParent();

        if (parent == null) {
            return;
        }

        int valueHolder = node.getValue();

        node.setValue(parent.getValue());
        parent.setValue(valueHolder);
        swapUp(parent);
    }
}
