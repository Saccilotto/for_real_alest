
public interface PriorityQueue<K> {
    void add(K value);
    void clear();
    boolean isEmpty();
    K peek(); 
    int size();
    K poll();
    String toString();
}
