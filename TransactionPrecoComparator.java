import java.util.Comparator;

public class TransactionPrecoComparator implements Comparator<Transaction> {
    public int compare(Transaction s1, Transaction s2) {
        if (s1.getPreco() < s2.getPreco()) {
            return 1;
        }else if (s1.getPreco() == s2.getPreco()) {
            return 0;
        }else {
            return -1;
        }       
    }
}
