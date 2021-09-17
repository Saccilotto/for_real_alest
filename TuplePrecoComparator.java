import java.util.Comparator;

public class TuplePrecoComparator implements Comparator<Tuple> {
    public int compare(Tuple s1, Tuple s2) {
        if (s1.getPreco() < s2.getPreco()) {
            return 1;
        }else if (s1.getPreco() == s2.getPreco()) {
            return 0;
        }else {
            return -1;
        }       
    }
}
