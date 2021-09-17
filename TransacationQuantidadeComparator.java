import java.util.Comparator;

public class TransacationQuantidadeComparator implements Comparator<Transaction> {
    public int compare(Transaction s1, Transaction s2) {
        if (s1.getQuantidade() < s2.getQuantidade()) {
            return 1;
        }else if (s1.getQuantidade() == s2.getQuantidade()) {
            return 0;
        }else {
            return -1;
        }       
    }
}

