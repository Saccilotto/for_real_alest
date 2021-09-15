import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {  
    public static void main (String args[]) {
        final int OPERATIONS = 30;
        String path = System.getProperty("user.dir") + "/instancias/teste.txt";

        MaxHeap compras = new MaxHeap(OPERATIONS);
        MinHeap vendas = new MinHeap(OPERATIONS);
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
                heapElement = new Tuple(cont, Integer.parseInt(element[1]), Integer.parseInt(element[2]));          
                if(element[0] == "C") {
                    compras.add(heapElement);
                }
                if(element[0] == "V") {
                    vendas.add(heapElement);
                } 

                while()) {
                    
                }                
                
                cont++;
            }
            readerScan.close();
        }catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
