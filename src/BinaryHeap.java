//javac BinaryHeap2.java

public class BinaryHeap implements PriorityQueue {

    int m_size;
    long[] m_elements;

    public BinaryHeap(int capacity) {
        m_elements = new long[capacity + 1];
        m_size = 0;
    }

    public BinaryHeap() {
        m_elements = new long[1];
        m_size = 0;
    }

    public boolean isEmpty() {
        return m_size == 0;
    }
    
    @Override
    public Node removeMin(){
    
        return null;
    }
    

    public boolean isFull() {
        return m_elements.length == m_size + 1;
    }

    public void clear() {
        m_elements = new long[m_elements.length];
        m_size = 0;
    }

    @Override
    public void reset(int capacity) {
        m_elements = new long[capacity + 1];
        m_size = 0;
    }

    @Override
    public void add(long element) {
        percolateUpMinHeap(element);
    }

    @Override
    public void remove(long element) {
        int index = search(element);

        m_elements[index] = m_elements[m_size--];

        m_elements[m_size + 1] = -1;

        if (m_size != 0) {
            percolateDownMinHeap(index);
        }
    }

    public int search(long element) {
        for (int i = 1; i < m_size + 1; i++) {
            if (element == m_elements[i]) {
                return i;
            }
        }
        return -1;
    }

    public long peek() {
        if (!isEmpty()) {
            return m_elements[1];
        } else {
            return -1;
        }
    }

    public long pop() {
        long result = peek();
        m_elements[1] = m_elements[m_size--];

        m_elements[m_size + 1] = -1;

        if (m_size != 0) {
            percolateDownMinHeap(1);
        }

        return result;
    }

    public void percolateDownMinHeap(final int index) {
        long element = m_elements[index];
        int hole = index;

        while ((hole * 2) <= m_size) {
            int child = hole * 2;

            if (child != m_size && m_elements[child + 1] < m_elements[child]) {
                child++;
            }

            if (m_elements[child] >= element) {
                break;
            }
            m_elements[hole] = m_elements[child];
            hole = child;
        }

        m_elements[hole] = element;
    }

    public void percolateUpMinHeap(int index) {
        int hole = index;
        long element = m_elements[hole];
        while (hole > 1 && element < m_elements[hole / 2]) {
            final int next = hole / 2;
            m_elements[hole] = m_elements[next];
            hole = next;
        }
        m_elements[hole] = element;
    }

    public void percolateUpMinHeap(long element) {
        m_elements[++m_size] = element;
        percolateUpMinHeap(m_size);
    }

    public int size() {
        return m_size;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();

        sb.append("[ ");

        for (int i = 1; i < m_size + 1; i++) {
            if (i != 1) {
                sb.append(", ");
            }
            sb.append(m_elements[i]);
        }

        sb.append(" ]");

        return sb.toString();
    }
}
