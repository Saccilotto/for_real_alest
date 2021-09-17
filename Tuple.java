
public class Tuple implements Comparable<Tuple> {
    private Integer id;
    private char label;
    private Integer quantidade; 
    private Integer preco;
    /* TuplePrecoComparator comparePreco = new TuplePrecoComparator();
    TupleQuantidadeComparator compareQuantidade = new TupleQuantidadeComparator();  */

    public Tuple(Integer id, char label, Integer quantidade, Integer preco) { 
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
    public int compareTo(Tuple t) {
        return this.getId().compareTo(t.getId());
    }

/*     public int compareQuantidade(Tuple t) {
        return compareQuantidade.compare(this, t);
    }

    public int comparePreco(Tuple t) {
        return comparePreco.compare(this, t);
    } */
}
