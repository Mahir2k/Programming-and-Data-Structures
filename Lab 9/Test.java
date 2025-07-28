import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Test {

    /**
     * The entry point of the program. Reads animal names from a file,
     * adds them to both an ArrayList and a TreeSet, and tests performance
     * of basic operations. It performs tests on both random and sorted data.
     */
    public static void main(String[] args) {
        TreeSet<String> animalBST = new TreeSet<>(); // Binary Search Tree of animal names
        ArrayList<String> animalAL = new ArrayList<>(); // List of animal names

        // Read animal names from a file and store them in the ArrayList
        try {
            Scanner read = new Scanner(new File("animals.txt"));
            while (read.hasNextLine()) {
                String animalName = read.next();
                animalAL.add(animalName);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Add all names from ArrayList into the TreeSet (unsorted order)
        for (String element : animalAL) {
            animalBST.add(element);
        }

        // Test operations on unsorted data
        System.out.println("\nTesting BST methods");
        System.out.printf("%-30s\t%-10s\t%-10s\t%-10s\n", "Animal", "add", "remove", "remove");
        testAddRemoveContains(animalAL, animalBST);



        System.out.println("\nBST properties for random data");
        System.out.println("Height of BST: " + animalBST.height());
        System.out.println("BST is balanced? " + animalBST.isBalanced());

        // Sort the ArrayList and clear the TreeSet
        animalAL.sort(null);
        animalBST.clear();

        // Add sorted names into the TreeSet
        for (String element : animalAL) {
            animalBST.add(element);
        }

        // Test operations on sorted data
        System.out.println("\nTesting BST methods");
        System.out.printf("%-30s\t%-10s\t%-10s\t%-10s\n", "Animal", "add", "remove", "remove");
        testAddRemoveContains(animalAL, animalBST);

        
        System.out.println("\nBST properties for Sorted data");
        System.out.println("Height of the BST: " + animalBST.height());
        System.out.println("BST is balanced? " + animalBST.isBalanced());
    }

    /**
     * Tests the efficiency of add, contains, and remove operations
     * 
     * the list and performing the operations 20 times.
     * 
     * It prints the number of iterations each operation takes per item
     * and also displays the average number of iterations.
     * 
     * @param al the ArrayList of animal names
     * @param ts the TreeSet of animal names to be tested
     */
    public static void testAddRemoveContains(ArrayList<String> al, TreeSet<String> ts) {
        int totalTSAdd = 0;
        int totalTSRemove = 0;
        int totalTSContains = 0;

        // Perform each operation 20 times with random elements
        for (int i = 0; i < 20; i++) {
            int randomIndex = (int) (Math.random() * al.size());
            String randomAnimal = al.get(randomIndex);
            ts.add(randomAnimal);
            ts.contains(randomAnimal);
            ts.remove(randomAnimal);
            totalTSAdd += TreeSet.addIterations;
            totalTSRemove += TreeSet.removeIterations;
            totalTSContains += TreeSet.containsIterations;
            System.out.printf("%-30s\t%-10s\t%-10s\t%-10s\n", randomAnimal, TreeSet.addIterations, TreeSet.containsIterations, TreeSet.removeIterations);
        }

        // Compute and print average iteration counts
        int aveAdd = totalTSAdd / 20;
        int aveCon = totalTSContains / 20;
        int aveRem = totalTSRemove / 20;
        System.out.printf("%-30s\t%-10s\t%-10s\t%-10s\n", "Average", aveAdd, aveCon, aveRem);
    }
}
/**
 * Performance of add, contains, and remove:
 * 
 * With random input, operations average ~9 iterations due to balanced structure. 
 * Sorted input leads to an unbalanced tree, resembling a linked list, increasing iterations.
 */

/**
 * Values of height() and isBalanced():
 * 
 * Random data produced moderate height (16) and a reasonably balanced tree. 
 * Sorted data caused extreme imbalance (height 478), degrading the tree into a skewed list.
 */
