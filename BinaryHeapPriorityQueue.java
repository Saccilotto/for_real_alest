import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BinaryHeapPriorityQueue<K extends Comparable<K>> implements PriorityQueue<K> {
    private ArrayList<K> eleKs;

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

    private void heapify_down(int i) {
        int left = left(i), right = right(i), smallest = -1;

        if(left <= eleKs.size()-1 && eleKs.get(left).compareTo(eleKs.get(i)) >= 0) {
            smallest = left;
        } else {
            smallest = i;
        }

        if(right <= eleKs.size() - 1 &&  eleKs.get(right).compareTo(eleKs.get(smallest)) >= 0) {
            smallest = right;
        }
        if(smallest != i) {
            swap(i, smallest);
            heapify_down(smallest);
        }
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
        eleKs.set(i,  temp);
    }

    public BinaryHeapPriorityQueue() {
        super();
        eleKs = new ArrayList<K>(30);
    }

    public BinaryHeapPriorityQueue(int capacity) {
        super();
        eleKs = new ArrayList<K>(capacity);
    }

    public BinaryHeapPriorityQueue(Comparator<K> comparator) {
        super();
        eleKs = new ArrayList<K>(30);
        Collections.sort(eleKs, comparator);
    }

    public BinaryHeapPriorityQueue(Comparator<K> comparator, int capacity) {
        super();
        eleKs = new ArrayList<K>(capacity);
        Collections.sort(eleKs, comparator);
    }

    public void add (K valueK) {
        eleKs.add(valueK);
        heapify_up(eleKs.size() - 1);
    }

    @Override
    public void clear() {
        eleKs.clear(); 
    }

    @Override
    public boolean isEmpty() {
        return eleKs.size() == 0;
    }

    @Override
    public K peek() {
        int tamanho = eleKs.size();
        if (eleKs.size() > 0) {
            return eleKs.get(0);
        }
        return tamanho == 0 ? null : eleKs.get(0);
    }

    @Override
    public int size() {
        return eleKs.size();    
    }

    @Override
    public K pool() {
        K raiz = null;
        if (eleKs.size() == 0) {
            throw new IllegalStateException("MinHeap is empty.");
        } else if(eleKs.size() == 1) {
            raiz = eleKs.remove(0);
            return raiz;
        }

        raiz = eleKs.get(0);
        K last = eleKs.remove(eleKs.size() - 1);
        eleKs.set(0, last);
        heapify_down(0);
        return raiz;
    }
    
    @Override
    public String toString (){ 
        return eleKs.toString();
    }

    public int length() {
        return eleKs.size();
    }
}
