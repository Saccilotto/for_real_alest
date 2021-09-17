
public interface PriorityQueue<K> {
    void add(K value);
    void clear();
    boolean isEmpty();
    K peek(); // return min element
    K remove(); // remove/return min element
    int size();
    boolean contains(K element);
    void remove(K element);
    String toString();
}
