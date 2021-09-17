
public class Tuple implements Comparable<Tuple> {

    private Integer id;
    private Integer quantidade; 
    private Integer preco;
    /* TuplePrecoComparator comparePreco = new TuplePrecoComparator();
    Tuple QuantidadeComparator compareQuantidade= new TupleQuantidadeComparator();*/

    public Tuple(Integer id, Integer quantidade, Integer preco) { 
        super();
        this.id = id;
        this.quantidade = quantidade; 
        this.preco = preco; 
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
        return "(" + quantidade + "," + preco + ")";
    }

    @Override
    public int compareTo(Tuple t) {
        return this.getId().compareTo(t.getId());
    }

    public int compareQuantidade(Tuple t) {
        return this.getQuantidade().compareTo(t.getQuantidade());
    }

    public int comparePreco(Tuple t) {
        return this.getPreco().compareTo(t.getPreco());
    }
}
