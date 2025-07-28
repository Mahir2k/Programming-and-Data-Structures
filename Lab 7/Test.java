import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

//class to test ArrayList and LinkedList
public class Test{
    public static void main(String[] args){
        //create lists
        ArrayList<String> animalAL = new ArrayList<>();
        LinkedList<String> animalLL = new LinkedList<>();
        
        //fill lists
        readAnimals(animalAL, animalLL, "animals.txt");
        testContains(animalAL, animalLL);
        testAdd(animalAL, animalLL);
        testRemove(animalAL, animalLL);
    }

    /**
     * read from file and populate lists
     * @param al ArrayList to be populted
     * @param ll LinkedList to be populated
     * @param filename file to read from
     * @throws FileNotFoundException if filename cannot be found
     */
    public static void readAnimals(ArrayList<String> al, LinkedList<String> ll, String filename){
        try{
            Scanner read = new Scanner(new File(filename));
            while(read.hasNextLine()){
                String animal = read.nextLine();
                al.add(animal);
                ll.add(animal);
            }
            read.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    /**
     * test the contains method
     * @param al ArrayList to test on
     * @param ll LinkedList to test on
     */
    public static void testContains(ArrayList<String> al, LinkedList<String> ll){

        System.out.println("Comparing the methods contains(Object)");
        System.out.printf("%-30s\t%-15s\t%-15s\n", "Animal name", "Iterations(AL)", "Iterations(LL)");
        int totalAL = 0, totalLL = 0;
        for(int i = 0; i<20; i++){
            int randomIndex = (int)(Math.random() * al.size());
            String randomAnimal = al.get(randomIndex);
            al.contains(randomAnimal);
            ll.contains(randomAnimal);

            System.out.printf("%-30s\t%-15d\t%-15d\n", randomAnimal, ArrayList.containsIterations, LinkedList.containsIterations);
            totalAL += ArrayList.containsIterations;
            totalLL += LinkedList.containsIterations;
        }
        System.out.printf("%-30s\t%-15d\t%-15d\n", "Average", totalAL/20, totalLL/20);
    }

    /**
     * test the add method
     * @param al ArrayList to test on
     * @param ll LinkedList to test on
     */
    public static void testAdd(ArrayList<String> al, LinkedList<String> ll){

        System.out.println("Comparing the methods add(int, E)");
        System.out.printf("%-30s\t%-15s\t%-15s\n", "Animal name", "Iterations(AL)", "Iterations(LL)");
        int totalAL = 0, totalLL = 0;
        for(int i = 0; i<20; i++){
            int randomIndex = (int)(Math.random() * al.size());
            String randomAnimal = al.get(randomIndex);
            al.add(randomIndex, randomAnimal);
            ll.add(randomIndex, randomAnimal);

            System.out.printf("%-30s\t%-15d\t%-15d\n", randomAnimal, ArrayList.addIterations, LinkedList.addIterations);
            totalAL += ArrayList.addIterations;
            totalLL += LinkedList.addIterations;
        }
        System.out.printf("%-30s\t%-15d\t%-15d\n", "Average", totalAL/20, totalLL/20);
    }

    /**
     * test the remove method
     * @param al ArrayList to test on
     * @param ll LinkedList to test on
     */
    public static void testRemove(ArrayList<String> al, LinkedList<String> ll){

        System.out.println("Comparing the methods remove(Object)");
        System.out.printf("%-30s\t%-15s\t%-15s\n", "Animal name", "Iterations(AL)", "Iterations(LL)");
        int totalAL = 0, totalLL = 0;
        for(int i = 0; i<20; i++){
            int randomIndex = (int)(Math.random() * al.size());
            String randomAnimal = al.get(randomIndex);
            al.remove(randomAnimal);
            ll.remove(randomAnimal);

            System.out.printf("%-30s\t%-15d\t%-15d\n", randomAnimal, ArrayList.removeIterations, LinkedList.removeIterations);
            totalAL += ArrayList.removeIterations;
            totalLL += LinkedList.removeIterations;
        }
        System.out.printf("%-30s\t%-15d\t%-15d\n", "Average", totalAL/20, totalLL/20);
    }

    //the remove method for the LinkedList is much faster. The one for the ArrayList is worst case performance always.
    //The other methods are about equal since they're all O(N), and take half as long as the list on average.
}
