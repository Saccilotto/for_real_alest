import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {  
    public static void main (String args[]) {
        final int OPERATIONS = 30;
        String path = System.getProperty("user.dir") + "/instancias/" + OPERATIONS + ".txt";
        
        BinaryHeapPriorityQueue<Tuple> comprasPreco = new BinaryHeapPriorityQueue<Tuple>(new TuplePrecoComparator(), OPERATIONS);
        BinaryHeapPriorityQueue<Tuple> vendasPreco = new BinaryHeapPriorityQueue<Tuple>(new TuplePrecoComparator(), OPERATIONS);
        vendasPreco.reverse();
        int lucroTotal = 0;
        int negocio = 0;
        //ChronoLocalDateTime date0 = LocalDateTime.from(ZonedDateTime.now());
        try {
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
                
                System.out.println("Tuple: " + heapElement.toString());
                if(heapElement.getLabel() == 'C') {
                    comprasPreco.add(heapElement);
                }
                
                if(heapElement.getLabel() == 'V') {
                    vendasPreco.add(heapElement);
                } 
                
                while((comprasPreco.length() > 0 || vendasPreco.length() > 0))  {
                    if(comprasPreco.peek() == null|| vendasPreco.peek() == null){
                        break;
                    }
                    int diffPreco = comprasPreco.peek().getPreco() - vendasPreco.peek().getPreco();
                    int diffQuantidade = comprasPreco.peek().getQuantidade() - vendasPreco.peek().getQuantidade(); 
                    if(diffPreco < 0) {
                        if(diffPreco > 0 && diffQuantidade > 0)  {
                            lucroTotal += vendasPreco.peek().getQuantidade() * comprasPreco.peek().getPreco() - comprasPreco.peek().getQuantidade() * vendasPreco.peek().getPreco();
                            vendasPreco.peek().setQuantidade(diffQuantidade);
                            comprasPreco.peek().setQuantidade(diffPreco);
                            comprasPreco.pool();
                            negocio++;
                        } else if (diffPreco > 0 && diffQuantidade == 0) {
                            lucroTotal += vendasPreco.peek().getQuantidade() * comprasPreco.peek().getPreco() - comprasPreco.peek().getQuantidade() * vendasPreco.peek().getPreco();
                            vendasPreco.pool(); 
                            comprasPreco.pool();
                            negocio += 2;
                        }
                    }
                } 
                cont++;   
            }
            readerScan.close();
            System.out.println();
            System.out.println(comprasPreco.toString() + "\n");
            System.out.println(vendasPreco.toString());
            System.out.println("\nLucro: " + lucroTotal + "\nAções negociadas: " + negocio  +  "\nCompras restantes: " + comprasPreco.length() + "\nVendas restantes: " + vendasPreco.length());
        }catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
