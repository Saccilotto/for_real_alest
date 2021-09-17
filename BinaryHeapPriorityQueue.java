import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class BinaryHeapPriorityQueue<K extends Comparable<K>> implements PriorityQueue<K> {
    private ArrayList<K> eleKs;
    private int size;

    private int right(int i) {
        return 2 * i + 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int parent(int i) {
        if (i % 2 == 1) {
            return i / 2;
        }
        return (i - 1) / 2;
    }

    public BinaryHeapPriorityQueue() {
        eleKs = new ArrayList<K>(30);
        size = 0;
    }

    public BinaryHeapPriorityQueue(int capacity) {
        eleKs = new ArrayList<K>(capacity);
        size = 0;
    }

    public BinaryHeapPriorityQueue(Comparator<K> comparator) {
        eleKs = new ArrayList<K>(30));
        Collections.sort(eleKs, comparator);
        size = 0;
    }

    public BinaryHeapPriorityQueue(Comparator<K> comparator ,int capacity) {
        eleKs = new ArrayList<K>(capacity);
        Collections.reverseOrder(eleKs, comparator);
        size = 0;
    }

    public void add (K valueK) {
        eleKs.add(valueK);
        heapify_up(eleKs.size() - 1);
    }

    private void heapify_up(int i) {
        if(i > 0) {
            int parent = parent(i);
            if(eleKs.get(i).compareTo(eleKs.get(parent)) >= 0){
                swap(i, parent);
                heapify_up(parent);
            }
        }
    }

    private void swap(int i, int parent) {
        K temp = eleKs.get(parent);
        eleKs.set(parent, eleKs.get(i));
        eleKs.set(i, temp);
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public K peek() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public K remove() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean contains(K element) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void remove(K element) {
        // TODO Auto-generated method stub
        
    }

}
