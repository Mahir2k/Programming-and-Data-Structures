import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        HashMapQP<String, String> hashQP = new HashMapQP<>(100000);
        HashMapSC<String, String> hashSC = new HashMapSC<>(100000);
        HashMapLP<String, String> hashLP = new HashMapLP<>(100000);
        readFile(hashSC, hashLP, hashQP, "users.txt");
        System.out.println(hashSC.size() + " users read from the file \"users.txt\"");
        ArrayList<String> list = new ArrayList<>();
        readFile(list, "mailingList.txt");
        System.out.println(list.size() + " email addresses read from the file \"mailingList.txt\"");
        int totalLP = 0, totalQP = 0, totalSC = 0;
        System.out.println("\nTest case 1: the search method get(key) performance");
        System.out.printf("%-30s\t%-10s\t%-10s\t%-10s\n", "Username", "HashMapSC", "HashMapLP", "HashMapQP");
        int count = list.size();
        for (int i = 0; i < count; i++) {
            hashQP.get(list.get(i));
            hashSC.get(list.get(i));
            hashLP.get(list.get(i));
            totalQP += HashMapQP.iterations;
            totalSC += HashMapSC.iterations;
            totalLP += HashMapLP.iterations;
            if(i%5 == 0)
                System.out.printf("%-30s\t%-10d\t%-10d\t%-10d\n", list.get(i), HashMapSC.iterations,  HashMapLP.iterations, HashMapQP.iterations); 
        }
        System.out.printf("%-30s\t%-10d\t%-10d\t%-10d\n", "Average", totalSC/count,  totalLP/count, totalQP/count); 

        // Printing the characterictics of the three hash maps
        System.out.println("\nTest case 2: comparing the characteristics of the three hash tables");
        System.out.printf("%-50s\t%-10s\t%-10s\t%-10s\n", "Characteristic", "HashMapSC", "HashMapLP", "HashMapQP");
        int[] characteristicsSC = hashSC.getCharacteristics();
        int[] characteristicsLP = hashLP.getCharacteristics();
        int[] characteristicsQP = hashQP.getCharacteristics();
        String[] characteristics = {"Capacity", "Size", "Collisions", "Number of buckets/clusters", "Largest bucket/cluster", "Smallest bucket/cluster"};
        for(int i=0; i<characteristicsLP.length; i++){
            System.out.printf("%-50s\t%-10d\t%-10d\t%-10d\n", characteristics[i], characteristicsSC[i], characteristicsLP[i], characteristicsQP[i]);
        }


        // Comparing the number of collisions
        System.out.println("\nTest case 3: testing the number of collisions for different starting capacities");
        System.out.printf("%-10s\t%-10s\t%-10s\t%-10s\n", "Size", "HashMapSC", "HashMapLP", "HashMapQP");
        for (int size = 50000; size <= 500000; size += 50000) {
            hashLP = new HashMapLP<>(size);
            hashQP = new HashMapQP<>(size);
            hashSC = new HashMapSC<>(size);
            readFile(hashSC, hashLP, hashQP, "users.txt");
            System.out.printf("%-10d\t%-10d\t%-10d\t%-10d\n", size, HashMapSC.collisions,  HashMapLP.collisions, HashMapQP.collisions); 
        }

       // Testing external merge sort
       System.out.println("\nTest case 4: testing the external merge sort algorithm");
       try{
            PrintWriter pw = new PrintWriter(new File("data.txt"));
            for(int i=0; i<100; i++){
                pw.println(Math.random());
            }
            pw.close();
       }
       catch(FileNotFoundException e){
        System.out.println("Cannot create the file data.txt");
        System.exit(0);
       }
       ExternalSort.mergeSort("data.txt");

    }

    public static void readFile(HashMapSC<String, String> sc, HashMapLP<String, String> lp, HashMapQP<String, String> qp, String fileName) {
        File file = new File(fileName);
        try {
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()) {
                String[] split = scan.nextLine().split(" ");
                sc.put(split[0], split[1]);
                //System.out.println("pair added to SC");
                lp.put(split[0], split[1]);
                //System.out.println("pair added to LP");
                qp.put(split[0], split[1]);
                //System.out.println("pair added to QP");
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
            
        
    }
    
    public static void readFile(ArrayList<String> list, String filename) {
        File file = new File(filename);
        try {
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()) {
                list.add(scan.nextLine());
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
    }
}
