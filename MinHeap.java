import java.util.ArrayList;
import java.util.List;

public class MinHeap {  
    private List<Tuple> heaplist;

    MinHeap() {
        heaplist = new ArrayList<Tuple>();
    }

    MinHeap(int tam) {
        heaplist = new ArrayList<Tuple>(tam);
    }

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

    private void heapify_up(int i) {
        if(i > 0) {
            int parent = parent(i);
            if(heaplist.get(i).compareTo(heaplist.get(parent)) >= 0){
                swap(i, parent);
                heapify_up(parent);
            }
        }
    }

    private void heapify_down(int i) {
        int left = left(i), right = right(i), smallest = -1;

        if(left <= heaplist.size()-1 && heaplist.get(left).compareTo(heaplist.get(i)) >= 0) {
            smallest = left;
        } else {
            smallest = i;
        }

        if(right <= heaplist.size() - 1 &&  heaplist.get(right).compareTo(heaplist.get(smallest)) >= 0) {
            smallest = right;
        }
        if(smallest != i) {
            swap(i, smallest);
            heapify_down(smallest);
        }
    }

    private void swap(int i, int parent) {
        Tuple temp = heaplist.get(parent);
        heaplist.set(parent, heaplist.get(i));
        heaplist.set(i, temp);
    }

    /**
     * Insere um valor inteiro no heap.
     * @param n o número a ser inserido.
     */
    public void add(Tuple n) {         // equivalente ao método inserts
        heaplist.add(n);
        heapify_up(heaplist.size() - 1);
    }

     /**
     * Método para a remoção e retorno do menor elemento do heap. Caso o heap esteja vazio, um valor null deve ser retornado.
     * @return o menor valor do heap.
     */
    public Tuple poll() {        // equivalente ao método extract_min()
        Tuple raiz = null;
        if (heaplist.size() == 0) {
            throw new IllegalStateException("MinHeap is empty.");
        } else if(heaplist.size() == 1) {
            raiz = heaplist.remove(0);
            return raiz;
        }

        raiz = heaplist.get(0);
        Tuple last = heaplist.remove(heaplist.size() - 1);
        heaplist.set(0, last);
        heapify_down(0);
        return raiz;
    }

    /**
     * Método que acessa e retorna do menor elemento do heap. Caso o heap esteja vazio, um valor null deve ser retornado.
     * @return o menor valor do heap.
     */
    public Tuple peek() {       // equivalente ao método min()
        if (heaplist.size() > 0) {
            return heaplist.get(0);
        }
        else {
            return null;
        }
        // return tamanho == 0 ? null : heap[0];
    }

    /**
     * Retorna o tamanho do heap em número de elementos dentro do heap.
     * @return number of items
     */
    public Integer length() {
        return heaplist.size();
    }

    /**
     * Testa se o heap é vazio.
     * @return true caso o heap esteja vazio, false caso contrário.
     */
    public boolean isEmpty() {
        return heaplist.size() == 0;
    }

    public List<Tuple> getHeap (){ 
        return heaplist;
    }
}
