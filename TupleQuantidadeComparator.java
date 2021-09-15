import java.util.Comparator;

public class TupleQuantidadeComparator implements Comparator<Tuple> {
    public int compare(Tuple s1, Tuple s2) {
        if (s1.getQuantidade() < s2.getQuantidade()) {
            return 1;
        }else if (s1.getQuantidade() == s2.getQuantidade()) {
            return 0;
        }else {
            return -1;
        }       
    }
}

