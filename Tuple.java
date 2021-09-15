
public class Tuple implements Comparable<Tuple>{ 
    private Integer id;
    private Integer value1; 
    private Integer value2; 

    public Tuple(Integer id, Integer value1, Integer value2) { 
        this.id = id;
        this.value1 = value1; 
        this.value2 = value2; 
    }

    public Integer getId(){
        return id;
    }

    public Integer getValue1(){
        return value1;
    }

    public Integer getValue2(){
        return value2;
    }

    @Override
    public String toString() {
        return "(" + value1 + "," + value2 + ")";
    }

    @Override
    public int compareTo(Tuple t) {
        return this.getId().compareTo(t.getId());
    }

    public int compareQuantidade(Tuple t) {
        return this.getValue1().compareTo(t.getValue1());
    }

    public int comparePreco(Tuple t) {
        return this.getValue2().compareTo(t.getValue2());
    }
}
