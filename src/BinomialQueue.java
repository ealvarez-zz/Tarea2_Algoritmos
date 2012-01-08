
import java.util.ArrayList;

/**
 *
 * @author Erwin Alvarez C <ealvarez at dcc.uchile.cl>
 */
public class BinomialQueue implements Tarea2Structure {

    private ArrayList<GenericNode> _queue;

    /**
     * Constructors
     */
    public BinomialQueue() {
        this._queue = new ArrayList<GenericNode>();
    }

    public BinomialQueue(int item) {
        GenericNode node = new GenericNode(item);
        this._queue = new ArrayList<GenericNode>();
        this._queue.add(node);
    }

    /**
     * Aumenta el tamaño de la cola en 1 elemento.
     */
    private void growQueue() {
        this._queue.add(null);
    }

    private GenericNode joinNodes(GenericNode p, GenericNode q) {

        if (p == null || q == null) {
            return (q != null) ? q : p;
        }

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
    @Override
    public void insert(int item) {

        GenericNode node = new GenericNode(item);

        for (int i = 0; i <= this._queue.size(); i++) {

            if (this._queue.isEmpty()) {
                growQueue();
                this._queue.set(i, node);
                return;
            }


            if (this._queue.get(i) == null) {
                this._queue.set(i, node);
                return;
            }

            if (i == this._queue.size() - 1) {
                growQueue();
            }


            node = joinNodes(this._queue.get(i), node);
            this._queue.set(i, null);
        }


    }

    public void merge(BinomialQueue remoteQueue) {
        merge(remoteQueue, null, 0);
    }

    private void merge(BinomialQueue remoteQueue, GenericNode carry, int index) {

        if (index >= remoteQueue.length() || index >= this.length()) {

            if (index >= this.length()) {
                this.growQueue();
                merge(remoteQueue, carry, index);
            } else {
                if (carry != null) {
                    if (this.get(index) != null) {
                        carry = joinNodes(this.get(index), carry);
                        this.set(index, null);
                        merge(remoteQueue, carry, index + 1);
                    } else {
                        this.set(index, carry);
                    }
                }
            }
            return;

        }
        if (remoteQueue.get(index) != null && this.get(index) != null) {

            GenericNode nodeHolder = this.get(index);
            this.set(index, carry);
            carry = joinNodes(remoteQueue.get(index), nodeHolder);
        } else {
            if (remoteQueue.get(index) != null) {
                if (carry != null) {
                    carry = joinNodes(remoteQueue.get(index), carry);
                } else {
                    this.set(index, remoteQueue.get(index));
                }
            } else {
                if (carry != null) {
                    carry = joinNodes(this.get(index), carry);
                    this.set(index, null);
                }
            }
        }
        merge(remoteQueue, carry, index + 1);
    }

    public void delete(int item) {

        GenericNode nodeToDelete = find(item);
        nodeToDelete.setValue(-42);
        swapUp(nodeToDelete);
        deleteMin();

    }

    @Override
    public void deleteMin() {

        if (this._queue == null) {
            return;
        }

        int minIndex = 0;
        GenericNode minNode = this.get(minIndex);


        for (int i = 1; i < this.length(); i++) {

            GenericNode currentNode = this.get(i);

            if (minNode == null) {
                minNode = currentNode;
                minIndex = i;
                continue;
            }

            if (currentNode != null && currentNode.getValue() < minNode.getValue()) {
                minNode = currentNode;
                minIndex = i;
            }
        }

        if (minNode == null) {
            return;
        }

        this.set(minIndex, null);

        BinomialQueue minNodeQueue = new BinomialQueue();
        minNodeQueue.setQueue(minNode.getChildren());
        merge(minNodeQueue);
    }

    private void setQueue(ArrayList<GenericNode> queue) {
        for (GenericNode node : queue) {
            if (node != null) {
                node.setParent(null);
            }
        }
        this._queue = queue;
    }

    public ArrayList<GenericNode> getQueue() {
        return this._queue;
    }

    private GenericNode find(int value) {
        return find(value, this._queue);

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

    private void moveToTop(int value) {
        swapUp(find(value));
    }

    private void swapUp(GenericNode node) {

        GenericNode parent = node.getParent();

        if (parent == null) {
            return;
        }

        int valueHolder = node.getValue();

        node.setValue(parent.getValue());
        parent.setValue(valueHolder);
        swapUp(parent);
    }

    public int length() {
        return this._queue.size();
    }

    public GenericNode get(int i) {
        return this._queue.get(i);
    }

    private void set(int i, GenericNode node) {
        this._queue.set(i, node);
    }

    @Override
    public String toString() {

        String toString = "";
        GenericNode node;

        for (int i = 0; i < this._queue.size(); i++) {
            node = this.get(i);
            if (node != null) {
                toString += "Nodo en posicion: " + i + "\n";
                toString += node.toString();
            }
        }



        return toString;
    }
}
