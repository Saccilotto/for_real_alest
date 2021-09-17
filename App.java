import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {  
    public static void main (String args[]) {
        final int OPERATIONS = 30;
        String path = System.getProperty("user.dir") + "/instancias/" + OPERATIONS + ".txt";

        BinaryHeapPriorityQueue<Tuple> comprasPreco = new BinaryHeapPriorityQueue<Tuple>(new TuplePrecoComparator() ,OPERATIONS);
        BinaryHeapPriorityQueue<Tuple> vendasQuantidade = new BinaryHeapPriorityQueue<Tuple>(new TupleQuantidadeComparator() ,OPERATIONS);

        MinHeap vendas = new MinHeap(OPERATIONS);
        
        int lucroTotal = 0;
        int negocio = 0;
        //ChronoLocalDateTime date0 = LocalDateTime.from(ZonedDateTime.now());
        try {
            File instances = new File(path);    
            Scanner readerScan = new Scanner(instances);
            String[] element = {null,null,null};
            Tuple heapElement;
            int cont = 0;
            while(readerScan.hasNextLine()) {
                String data = readerScan.nextLine();
                if(data.equals("q")) {
                    break;
                }

                element = data.split(" ");

                if(element.length == 1) {
                    data = readerScan.nextLine();
                    element = data.split(" ");
                }
                //System.out.println("Read: " + element[0] + element[1] + element[2]);
 
                heapElement = new Tuple(cont, Integer.parseInt(element[1]), Integer.parseInt(element[2]));  
                
                //System.out.println("Tuple: " + heapElement.getQuantidade() +  " "  + heapElement.getPreco());
                if(element[0] == "C") {
                    comprasPreco.add(heapElement);
                    //System.out.println(compras.toString());
                }
                if(element[0] == "V") {
                    vendasQuantidade.add(heapElement);
                    //System.out.println(vendas.toString());
                } 

                int quantidadeAux = 0;
                int carrier = 0;
                while(! (comprasPreco.length() > 0 || vendasQuantidade.length() > 0)  && !(comprasPreco.peek() == null && vendasQuantidade.peek() == null)) {
                    if(comprasPreco.peek().comparePreco(vendasQuantidade.peek()) >= 0) {
                        quantidadeAux = vendasQuantidade.peek().getQuantidade();
                        carrier = vendasQuantidade.peek().getQuantidade();
                        if(quantidadeAux < carrier) {
                            lucroTotal += quantidadeAux * comprasPreco.peek().getPreco() - carrier * vendas.peek().getPreco();
                            vendas.peek().setQuantidade(carrier - quantidadeAux);
                            comprasPreco.peek().setQuantidade(quantidadeAux - carrier);
                            comprasPreco.pool();
                            negocio++;
                        } else if (quantidadeAux == carrier) {
                            lucroTotal += quantidadeAux * comprasPreco.peek().getPreco() - carrier * vendas.peek().getPreco();
                            vendas.poll(); 
                            comprasPreco.pool();
                            negocio += 2;
                        }
                    }
                } 
                cont++;   
                //System.out.println("\nLucro: " + lucroTotal + "\nAções negociadas: " + negocio  +  "\nCompras restantes: " + Arrays.toString(compras.getHeap().toArray()) + "\nVendas restantes: " + Arrays.toString(vendas.getHeap().toArray()));
            }
            readerScan.close();
            System.out.println("\nLucro: " + lucroTotal + "\nAções negociadas: " + negocio  +  "\nCompras restantes: " + comprasPreco.toString()+ "\nVendas restantes: " + vendasQuantidade.toString());
        }catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
