
public class Transaction implements Comparable<Transaction> {
    private Integer id;
    private char label;
    private Integer quantidade; 
    private Integer preco;

    public Transaction(Integer id, char label, Integer quantidade, Integer preco) { 
        super();
        this.id = id;
        this.label = label;
        this.quantidade = quantidade; 
        this.preco = preco; 
    }

    public char getLabel(){
        return label;
    }

    public Integer getId(){
        return id;
    }

    public Integer getQuantidade(){
        return quantidade;
    }

    public Integer getPreco(){
        return preco;
    }

    public void setQuantidade(Integer q){
        this.preco = q;

    }

    public void setPreco(Integer p){
        this.preco = p;
    }

    @Override
    public String toString() {
        return "(" + id +"," + label + "," + quantidade + "," + preco +  ")";
    }

    @Override
    public int compareTo(Transaction t) {
        return this.getId().compareTo(t.getId());
    }
}
