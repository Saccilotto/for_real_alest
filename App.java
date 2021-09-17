import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Scanner;

public class App {  
    public static void main (String args[]) {
        final int OPERATIONS = 30;
        String path = System.getProperty("user.dir") + "/instancias/" + OPERATIONS + ".txt";
        
        BinaryHeapPriorityQueue<Tuple> compras = new BinaryHeapPriorityQueue<Tuple>(new TuplePrecoComparator(), OPERATIONS);
        BinaryHeapPriorityQueue<Tuple> vendas = new BinaryHeapPriorityQueue<Tuple>(new TuplePrecoComparator(), OPERATIONS);
        compras.reverse();
        ChronoLocalDateTime<LocalDate> date0 = LocalDateTime.from(ZonedDateTime.now());
        try {
            int lucroTotal = 0;
            int negocio = 0;
            File instances = new File(path);    
            Scanner readerScan = new Scanner(instances);
            String[] element = new String[3];
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
                heapElement = new Tuple(cont, element[0].charAt(0) , Integer.parseInt(element[1]), Integer.parseInt(element[2]));  
                
                //System.out.println("Tuple: " + heapElement.toString());
                if(heapElement.getLabel() == 'C') {
                    compras.add(heapElement);
                }
                
                if(heapElement.getLabel() == 'V') {
                    vendas.add(heapElement);
                } 

                while((compras.length() > 0 && vendas.length() > 0))  {
                    if(vendas.peek().getPreco() >= compras.peek().getPreco()) {
                        break;
                    }
                    //int diffPreco = vendas.peek().getPreco() - compras.peek().getPreco();
                    int diffQuantidade = compras.peek().getQuantidade() - vendas.peek().getQuantidade(); 
                    if(diffQuantidade >= 0)  {
                        if(diffQuantidade == 0) {
                            lucroTotal += vendas.peek().getQuantidade() * compras.peek().getPreco() - compras.peek().getQuantidade() * vendas.peek().getPreco();
                            negocio += vendas.peek().getQuantidade()  + compras.peek().getQuantidade(); 
                            vendas.poll(); 
                            compras.poll();
                        }else {
                            lucroTotal += vendas.peek().getQuantidade() * compras.peek().getPreco() - compras.peek().getQuantidade() * vendas.peek().getPreco();
                            //vendas.peek().setQuantidade(0);
                            compras.peek().setQuantidade(diffQuantidade);
                            //if(vendas.peek().getQuantidade() <= 0) {
                            negocio += vendas.peek().getQuantidade();
                            vendas.poll();
                        }
                    } else {
                        lucroTotal += vendas.peek().getQuantidade() * compras.peek().getPreco() - compras.peek().getQuantidade() * vendas.peek().getPreco();
                        compras.peek().setQuantidade(Math.abs(diffQuantidade));
                        negocio += vendas.peek().getQuantidade();
                        vendas.poll();
                    }
                } 
                cont++;   
            }
            readerScan.close();
            ChronoLocalDateTime<LocalDate> date1 = LocalDateTime.from(ZonedDateTime.now());
            Duration diff = Duration.between(date1, date0);
            long time  = diff.toSeconds();
            int restantes = compras.length() + vendas.length();            
            System.out.println();
            System.out.println(compras.toString() + "\n");
            System.out.println(vendas.toString());
            System.out.println("\nLucro: " + lucroTotal + "\nAções negociadas: " + negocio  +  "\nCompras restantes: " + compras.length() + "\nvendas restantes: " + vendas.length() + "\nTotal de transacoes" + restantes);
            System.out.println("tempo de execução: " + time);
        }catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
