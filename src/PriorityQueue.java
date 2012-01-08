
public interface PriorityQueue {

    public abstract void add(long value);

    public abstract void remove(long value);
    
    public abstract void removeMin();

    public void reset(int capacity);
}
