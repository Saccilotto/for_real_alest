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
        
        BinaryHeapPriorityQueue<Transaction> compras = new BinaryHeapPriorityQueue<Transaction>(new TransactionPrecoComparator(), OPERATIONS);
        //compras.reverse();
        BinaryHeapPriorityQueue<Transaction> vendas = new BinaryHeapPriorityQueue<Transaction>(new TransactionPrecoComparator(), OPERATIONS);
        ChronoLocalDateTime<LocalDate> date0 = LocalDateTime.from(ZonedDateTime.now());
        try {
            int lucroTotal = 0;
            int negocio = 0;
            File instances = new File(path);    
            Scanner readerScan = new Scanner(instances);
            String[] element = new String[3];
            Transaction heapElement;
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
                heapElement = new Transaction(cont, element[0].charAt(0) , Integer.parseInt(element[1]), Integer.parseInt(element[2]));  
                
                System.out.println("Transacation: " + heapElement.toString());
                if(heapElement.getLabel() == 'C') {
                    compras.add(heapElement);
                }
                
                if(heapElement.getLabel() == 'V') {
                    vendas.add(heapElement);
                } 

                compras.reverse();
                negocio = compras.length() + vendas.length();
                while((compras.length() > 0 && vendas.length() > 0) && (vendas.peek().getPreco() >= compras.peek().getPreco()))  {   
                    //int aux = vendas.peek().getPreco() - compras.peek().getPreco();
                    //int diffPreco = vendas.peek().getPreco() - compras.peek().getPreco();
                    int diffQuantidade = vendas.peek().getQuantidade() - compras.peek().getQuantidade(); 
                    if (! (compras.peek().getPreco()  == compras.peek().getPreco())) {
                        if(diffQuantidade > 0)  {
                            compras.peek().setQuantidade(diffQuantidade);
                            lucroTotal += vendas.peek().getQuantidade() * vendas.peek().getPreco() - compras.peek().getQuantidade() * compras.peek().getPreco();
                            vendas.poll();
                        } else if(diffQuantidade == 0) {
                            lucroTotal += vendas.peek().getQuantidade() * compras.peek().getPreco() - compras.peek().getQuantidade() * vendas.peek().getPreco();
                            vendas.poll(); 
                            compras.poll();
                        } else {
                            vendas.peek().setQuantidade(Math.abs(diffQuantidade));
                            lucroTotal += vendas.peek().getQuantidade() * compras.peek().getPreco() - compras.peek().getQuantidade() * vendas.peek().getPreco();
                            vendas.poll();
                        }
                    } 
                } 
            }  
                cont++;   
            
            readerScan.close();
            ChronoLocalDateTime<LocalDate> date1 = LocalDateTime.from(ZonedDateTime.now());
            Duration diff = Duration.between(date0, date1);
            long time  = diff.toMillis();
            int restantes = compras.length() + vendas.length();  
            negocio = OPERATIONS - restantes;
            System.out.println();
            System.out.println(compras.toString() + "\n");
            System.out.println(vendas.toString());
            System.out.println("\nLucro: " + lucroTotal + "\nAções negociadas: " + negocio  +  "\nCompras restantes: " + compras.length() + "\nvendas restantes: " + vendas.length() + "\nTotal de transacoes: " + restantes);
            System.out.println("tempo de execução: " + time + "ms");
        }catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
