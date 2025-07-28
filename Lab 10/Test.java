import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The {@code Test} class reads a dictionary file and populates a custom {@code HashMap}
 * with word-definition pairs. It then compares the performance of various data structures—
 * {@code LinkedList}, {@code TreeSet}, and {@code HashMap}—in terms of add, search, and remove operations.
 * It also prints the maximum number of collisions that occurred in the hash map.
 * 
 * The dictionary file should be in the format: word|definition per line.
 */
public class Test {

    /**
     * The main method that drives the program.
     * It reads the dictionary, populates data structures, and tests performance.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args){
        HashMap<String, String> hashWords = new HashMap<>(50000);
        
        try {
            Scanner read = new Scanner(new File("dictionary.txt"));
            while(read.hasNextLine()){
                String line = read.nextLine();
                String[] tokens = line.split("\\|");
                hashWords.put(tokens[0], tokens[1]);
            }
            read.close();
        } catch(FileNotFoundException e){
            System.out.println("file Not Found");
        }

        System.out.println(hashWords.size());

        TreeSet<String> bstWords = new TreeSet<>();
        LinkedList<String> lWords = new LinkedList<>();
        ArrayList<MapEntry<String, String>> alWords = hashWords.toList();

        System.out.println("Testing the Add method");
        testAdd(alWords, lWords, bstWords, hashWords);

        System.out.println("\nTesting the search method");
        testContains(alWords, lWords, bstWords, hashWords);

        System.out.println("\nTesting the remove method");
        testRemove(alWords, lWords, bstWords, hashWords);

        System.out.println("Number of Collisions: " + hashWords.collisions());
    }

    /**
     * Tests and compares the performance of the add operation across different data structures.
     *
     * @param al list of word-definition entries
     * @param ll linked list to be tested
     * @param bst binary search tree to be tested
     * @param hm hash map to be tested
     */
    public static void testAdd(ArrayList<MapEntry<String, String>> al, 
                               LinkedList<String> ll,  
                               TreeSet<String> bst, 
                               HashMap<String, String> hm) {
        System.out.printf("%-20s\t%-10s\t%-10s\t%-10s\n", "Word", "LL Add", "BST Add", "HashMap put");
        int totalLL = 0, totalBST = 0, totalHash = 0;

        for(int i = 0; i < al.size(); i++){
            MapEntry<String, String> entry = al.get(i);
            ll.add(entry.getKey());
            bst.add(entry.getKey());
            hm.put(entry.getKey(), entry.getValue());

            if(i % (al.size()/20) == 0){
                System.out.printf("%-20s\t%-10d\t%-10d\t%-10d\n", entry.getKey(), LinkedList.aIterations, TreeSet.aIterations, HashMap.putIterations);
            }

            totalLL += LinkedList.aIterations;
            totalBST += TreeSet.aIterations;
            totalHash += HashMap.putIterations;
        }

        System.out.printf("%-20s\t%-10d\t%-10d\t%-10d\n", "Average", totalLL/al.size(), totalBST/al.size(), totalHash/al.size());
    }

    /**
     * Tests and compares the performance of the search operation across different data structures.
     *
     * @param al list of word-definition entries
     * @param ll linked list to be tested
     * @param bst binary search tree to be tested
     * @param hm hash map to be tested
     */
    public static void testContains(ArrayList<MapEntry<String, String>> al, 
                                    LinkedList<String> ll,  
                                    TreeSet<String> bst, 
                                    HashMap<String, String> hm) {
        System.out.printf("%-20s\t%-10s\t%-10s\t%-10s\n", "Word", "LL Search", "BST Search", "HashMap Search");
        int totalLL = 0, totalBST = 0, totalHash = 0;

        for(int i = 0; i < al.size(); i++){
            String word = al.get((int)(Math.random()*al.size())).getKey();
            ll.contains(word);
            bst.contains(word);
            hm.get(word);

            if(i % (al.size()/20) == 0){
                System.out.printf("%-20s\t%-10d\t%-10d\t%-10d\n", word, LinkedList.cIterations, TreeSet.cIterations, HashMap.getIterations);
            }

            totalLL += LinkedList.cIterations;
            totalBST += TreeSet.cIterations;
            totalHash += HashMap.getIterations;
        }

        System.out.printf("%-20s\t%-10d\t%-10d\t%-10d\n", "Average", totalLL/al.size(), totalBST/al.size(), totalHash/al.size());
    }

    /**
     * Tests and compares the performance of the remove operation across different data structures.
     *
     * @param al list of word-definition entries
     * @param ll linked list to be tested
     * @param bst binary search tree to be tested
     * @param hm hash map to be tested
     */
    public static void testRemove(ArrayList<MapEntry<String, String>> al, 
                                  LinkedList<String> ll,  
                                  TreeSet<String> bst, 
                                  HashMap<String, String> hm) {
        System.out.printf("%-20s\t%-10s\t%-10s\t%-10s\n", "Word", "LL remove", "BST remove", "HashMap remove");
        int totalLL = 0, totalBST = 0, totalHash = 0;

        for(int i = 0; i < al.size(); i++){
            String word = al.get((int)(Math.random()*al.size())).getKey();
            ll.remove(word);
            bst.remove(word);
            hm.remove(word);

            if(i % (al.size()/20) == 0){
                System.out.printf("%-20s\t%-10d\t%-10d\t%-10d\n", word, LinkedList.rIterations, TreeSet.rIterations, HashMap.removeIterations);
            }

            totalLL += LinkedList.rIterations;
            totalBST += TreeSet.rIterations;
            totalHash += HashMap.removeIterations;
        }

        System.out.printf("%-20s\t%-10d\t%-10d\t%-10d\n", "Average", totalLL/al.size(), totalBST/al.size(), totalHash/al.size());
    }
    /**
     * Discussion about collisions
     * A high number of collisions means that many entries are ending up in the same bucket, likely due to poor hash function design or high load factor. 
     * This results in slower performance for operations like put, get, and remove since they rely on traversing linked lists (or other structures) in those crowded buckets.
     */
}
