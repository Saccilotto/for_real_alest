
public interface PriorityQueue<K> {
    void add(K value);
    void clear();
    boolean isEmpty();
    K peek(); 
    int size();
    //boolean contains(K element);
    K pool();
    String toString();
}
