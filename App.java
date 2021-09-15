import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class App {  
    public static void main (String args[]) {
        final int OPERATIONS = 10000000;
        String path = System.getProperty("user.dir") + "/instancias/" + OPERATIONS + ".txt";

        MaxHeap compras = new MaxHeap(OPERATIONS);
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
                
                //System.out.println("Tuple: " + heapElement.getQuantidade() + heapElement.getPreco());
                if(element[0] == "C") {
                    compras.add(heapElement);
                }
                if(element[0] == "V") {
                    vendas.add(heapElement);
                } 
                
                //System.out.println("Tuple: " + heapElement.getQuantidade() + " " + heapElement.getPreco());
                
                Tuple[] aux = new Tuple[OPERATIONS/4];
                int quantidadeAux = 0;
                int carrier = 0;
                //while(! (compras.isEmpty() || vendas.isEmpty())) {
                System.out.println(compras.peek().toString() + " " + vendas.peek().toString());
                if(! (compras.length() > 5 && vendas.length() > 5)) {
                    if(compras.peek().comparePreco(vendas.peek()) >= 0) {
                        quantidadeAux = compras.peek().getQuantidade();
                        carrier = vendas.peek().getQuantidade();
                        if(quantidadeAux < carrier) {
                            lucroTotal += quantidadeAux * compras.peek().getPreco() - carrier * vendas.peek().getPreco();
                            vendas.peek().setQuantidade(carrier - quantidadeAux);
                            compras.peek().setQuantidade(quantidadeAux - carrier);
                            aux[cont] = compras.poll();
                            negocio++;
                            break;
                        } else if (quantidadeAux == carrier) {
                            lucroTotal += quantidadeAux * compras.peek().getPreco() - carrier * vendas.peek().getPreco();
                            aux[cont] = vendas.poll();
                            aux[cont + 1] = compras.poll();
                            negocio += 2;
                            break;
                        }else {
                            vendas.peek().setQuantidade(quantidadeAux - carrier);
                            compras.peek().setQuantidade(carrier - quantidadeAux);
                            vendas.poll();
                            negocio ++;
                        }
                    }  
                } 
                cont++;   
                //System.out.println("\nLucro: " + lucroTotal + "\nAções negociadas: " + negocio  +  "\nCompras restantes: " + Arrays.toString(compras.getHeap().toArray()) + "\nVendas restantes: " + Arrays.toString(vendas.getHeap().toArray()));
            }
            readerScan.close();
            System.out.println("\nLucro: " + lucroTotal + "\nAções negociadas: " + negocio  +  "\nCompras restantes: " + Arrays.toString(compras.getHeap().toArray()) + "\nVendas restantes: " + Arrays.toString(vendas.getHeap().toArray()));
        }catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
