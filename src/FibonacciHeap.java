//javac FibonacciHeap.java

import java.util.*;

class Node {

    Node m_child;
    Node m_left;
    Node m_parent;
    Node m_right;
    boolean m_mark;
    double m_key;
    int m_degree;

    public Node(double key) {
        m_right = this;
        m_left = this;
        m_key = key;
    }

    public final double getKey() {
        return m_key;
    }
}

public class FibonacciHeap implements PriorityQueue {

    private Node m_min;
    private int m_n;

    public FibonacciHeap() {
        m_min = null;
        m_n = 0;
    }

    public boolean isEmpty() {
        return m_min == null;
    }

    public void clear() {
        m_min = null;
        m_n = 0;
    }

    /**
     * Decreases the key value for a heap node, given the new value to take on.
     * The structure of the heap may be changed and will not be consolidated.
     *
     * @param x node to decrease the key of
     * @param k new key value for node x
     * @throws IllegalArgumentException Thrown if k is larger than x.key value.
     */
    public void decreaseKey(Node x, double k) {
        if (k > x.m_key) {
            throw new IllegalArgumentException("decreaseKey() got larger key value");
        }

        x.m_key = k;

        Node y = x.m_parent;

        if ((y != null) && (x.m_key < y.m_key)) {
            cut(x, y);
            cascadingCut(y);
        }

        if (x.m_key < m_min.m_key) {
            m_min = x;
        }
    }

    //Metodos de la interfaz PriorityQueue
    @Override
    public void add(long value) {
        insert(new Node(value), value);
    }

    @Override
    public void remove(long value) {
        Node s = search(value);

        if (s != null) {
            delete(s);
        }
    }

    @Override
    public void reset(int capacity) {
        clear();
    }

    /**
     * Deletes a node from the heap given the reference to the node. The trees
     * in the heap will be consolidated, if necessary. This operation may fail
     * to remove the correct element if there are nodes with key value
     * -Infinity.
     *
     * @param x node to remove from heap
     */
    public void delete(Node x) {
        // make x as small as possible
        decreaseKey(x, Double.NEGATIVE_INFINITY);

        // remove the smallest, which decreases n also
        removeMin();
    }

    /**
     * Inserts a new data element into the heap. No heap consolidation is
     * performed at this time, the new node is simply inserted into the root
     * list of this heap.
     *
     * @param node new node to insert into heap
     * @param key  key value associated with data object
     */
    public void insert(Node node, double key) {
        node.m_key = key;

        // concatenate node into min list
        if (m_min != null) {
            node.m_left = m_min;
            node.m_right = m_min.m_right;
            m_min.m_right = node;
            node.m_right.m_left = node;

            if (key < m_min.m_key) {
                m_min = node;
            }
        } else {
            m_min = node;
        }

        m_n++;
    }

    /**
     * Returns the smallest element in the heap. This smallest element is the
     * one with the minimum key value.
     *
     * @return heap node with the smallest key
     */
    public Node min() {
        return m_min;
    }

    /**
     * Removes the smallest element from the heap. This will cause the trees in
     * the heap to be consolidated, if necessary.
     *
     * @return node with the smallest key
     */
    public void removeMin() {
        Node z = m_min;

        if (z != null) {
            int numKids = z.m_degree;
            Node x = z.m_child;
            Node tempRight;

            // for each child of z do...
            while (numKids > 0) {
                tempRight = x.m_right;

                // remove x from child list
                x.m_left.m_right = x.m_right;
                x.m_right.m_left = x.m_left;

                // add x to root list of heap
                x.m_left = m_min;
                x.m_right = m_min.m_right;
                m_min.m_right = x;
                x.m_right.m_left = x;

                // set parent[x] to null
                x.m_parent = null;
                x = tempRight;
                numKids--;
            }

            // remove z from root list of heap
            z.m_left.m_right = z.m_right;
            z.m_right.m_left = z.m_left;

            if (z == z.m_right) {
                m_min = null;
            } else {
                m_min = z.m_right;
                consolidate();
            }

            // decrement size of heap
            m_n--;
        }

//        return z;
    }

    /**
     * Returns the size of the heap which is measured in the number of elements
     * contained in the heap.
     *
     * @return number of elements in the heap
     */
    public int size() {
        return m_n;
    }

    /**
     * Joins two Fibonacci heaps into a new one. No heap consolidation is
     * performed at this time. The two root lists are simply joined together.
     *
     * @param h1 first heap
     * @param h2 second heap
     * @return new heap containing h1 and h2
     */
    public static FibonacciHeap union(FibonacciHeap h1, FibonacciHeap h2) {
        FibonacciHeap h = new FibonacciHeap();

        if ((h1 != null) && (h2 != null)) {
            h.m_min = h1.m_min;

            if (h.m_min != null) {
                if (h2.m_min != null) {
                    h.m_min.m_right.m_left = h2.m_min.m_left;
                    h2.m_min.m_left.m_right = h.m_min.m_right;
                    h.m_min.m_right = h2.m_min;
                    h2.m_min.m_left = h.m_min;

                    if (h2.m_min.m_key < h1.m_min.m_key) {
                        h.m_min = h2.m_min;
                    }
                }
            } else {
                h.m_min = h2.m_min;
            }

            h.m_n = h1.m_n + h2.m_n;
        }

        return h;
    }

    /**
     * Performs a cascading cut operation. This cuts y from its parent and then
     * does the same for its parent, and so on up the tree.
     *
     * @param y node to perform cascading cut on
     */
    protected void cascadingCut(Node y) {
        Node z = y.m_parent;

        // if there's a parent...
        if (z != null) {
            // if y is unmarked, set it marked
            if (!y.m_mark) {
                y.m_mark = true;
            } else {
                // it's marked, cut it from parent
                cut(y, z);

                // cut its parent as well
                cascadingCut(z);
            }
        }
    }

    /**
     * Consolidates the trees in the heap by joining trees of equal degree
     * until there are no more trees of equal degree in the root list.
     */
    protected void consolidate() {
        int arraySize = m_n + 1;
        Node[] array = new Node[arraySize];

        // Initialize degree array
        for (int i = 0; i < arraySize; i++) {
            array[i] = null;
        }

        // Find the number of root nodes.
        int numRoots = 0;
        Node x = m_min;

        if (x != null) {
            numRoots++;
            x = x.m_right;

            while (x != m_min) {
                numRoots++;
                x = x.m_right;
            }
        }

        // For each node in root list do...
        while (numRoots > 0) {
            // Access this node's degree..
            int d = x.m_degree;
            Node next = x.m_right;

            // ..and see if there's another of the same degree.
            while (array[d] != null) {
                // There is, make one of the nodes a child of the other.
                Node y = array[d];

                // Do this based on the key value.
                if (x.m_key > y.m_key) {
                    Node temp = y;
                    y = x;
                    x = temp;
                }

                // Node y disappears from root list.
                link(y, x);

                // We've handled this degree, go to next one.
                array[d] = null;
                d++;
            }

            // Save this node for later when we might encounter another
            // of the same degree.
            array[d] = x;

            // Move forward through list.
            x = next;
            numRoots--;
        }

        // Set min to null (effectively losing the root list) and
        // reconstruct the root list from the array entries in array[].
        m_min = null;

        for (int i = 0; i < arraySize; i++) {
            if (array[i] != null) {
                // We've got a live one, add it to root list.
                if (m_min != null) {
                    // First remove node from root list.
                    array[i].m_left.m_right = array[i].m_right;
                    array[i].m_right.m_left = array[i].m_left;

                    // Now add to root list, again.
                    array[i].m_left = m_min;
                    array[i].m_right = m_min.m_right;
                    m_min.m_right = array[i];
                    array[i].m_right.m_left = array[i];

                    // Check if this is a new min.
                    if (array[i].m_key < m_min.m_key) {
                        m_min = array[i];
                    }
                } else {
                    m_min = array[i];
                }
            }
        }
    }

    /**
     * The reverse of the link operation: removes x from the child list of y.
     * This method assumes that min is non-null.
     *
     * @param x child of y to be removed from y's child list
     * @param y parent of x about to lose a child
     */
    protected void cut(Node x, Node y) {
        // remove x from childlist of y and decrement degree[y]
        x.m_left.m_right = x.m_right;
        x.m_right.m_left = x.m_left;
        y.m_degree--;

        // reset y.child if necessary
        if (y.m_child == x) {
            y.m_child = x.m_right;
        }

        if (y.m_degree == 0) {
            y.m_child = null;
        }

        // add x to root list of heap
        x.m_left = m_min;
        x.m_right = m_min.m_right;
        m_min.m_right = x;
        x.m_right.m_left = x;

        // set parent[x] to nil
        x.m_parent = null;

        // set mark[x] to false
        x.m_mark = false;
    }

    /**
     * Make node y a child of node x.
     *
     * @param y node to become child
     * @param x node to become parent
     */
    protected void link(Node y, Node x) {
        // remove y from root list of heap
        y.m_left.m_right = y.m_right;
        y.m_right.m_left = y.m_left;

        // make y a child of x
        y.m_parent = x;

        if (x.m_child == null) {
            x.m_child = y;
            y.m_right = y;
            y.m_left = y;
        } else {
            y.m_left = x.m_child;
            y.m_right = x.m_child.m_right;
            x.m_child.m_right = y;
            y.m_right.m_left = y;
        }

        // increase degree[x]
        x.m_degree++;

        // set mark[y] false
        y.m_mark = false;
    }

    public Node search(long value) {

        if (m_min == null) {
            return null;
        }
        Stack stack = new Stack();
        stack.push(m_min);

        while (!stack.empty()) {
            Node curr = (Node) stack.pop();

            if (curr.m_key == value) {
                return curr;
            }

            if (curr.m_child != null) {
                stack.push(curr.m_child);
            }

            Node start = curr;
            curr = curr.m_right;

            while (curr != start) {

                if (curr.m_key == value) {
                    return curr;
                }

                if (curr.m_child != null) {
                    stack.push(curr.m_child);
                }

                curr = curr.m_right;
            }
        }

        return null;

    }
}
