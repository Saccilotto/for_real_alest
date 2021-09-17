import java.util.Comparator;

public class TuplePrecoComparator implements Comparator<Tuple> {
    public int compare(Tuple s1, Tuple s2) {
        if (s1.getPreco() != s2.getPreco()) {
            return s1.getPreco() - s2.getPreco();
        }else {
            return s1.getPreco() - s2.getPreco();
        }
    }
}
