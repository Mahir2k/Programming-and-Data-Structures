import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * A test class demonstrating how to store and manage pairs of data 
 * (like states and their capitals, or tree names and heights), 
 * with operations such as search, sort, and user interaction.
 */
public class Test {

    /**
     * Searches a list of Pair objects for the first Pair whose 'first' element matches the given key.
     *
     * @param <E1> the type of the 'first' element in the Pair
     * @param <E2> the type of the 'second' element in the Pair
     * @param list the list of Pair objects to search
     * @param key  the 'first' value we are looking for in each Pair
     * @return the index of the first matching Pair in the list, or -1 if no match is found
     */
    public static <E1, E2> int search(ArrayList<Pair<E1, E2>> list, E1 key) {
        for(int i = 0; i < list.size(); i++) {
            Pair<E1, E2> pair = list.get(i);
            if (pair.getFirst().equals(key)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * The main method that determines whether we are dealing with "states" or "trees"
     * based on the command-line argument, and then calls the appropriate method
     * (processStates or processTrees).
     *
     * @param args command-line arguments (should be either 'states' or 'trees')
     */
    public static void main(String[] args){
        if (args.length == 0) {
            System.out.println("The program needs a command-line argument (states or trees)");
            System.exit(0);
        }
        String type = args[0];
        if (!type.equals("states") && !type.equals("trees")) {
            System.out.println("The argument can be 'states' or 'trees' only");
            System.exit(0);
        }

        if (type.equals("states")) {
            processStates();
        }
        else {
            processTrees();
        }
    }

    /**
     * Processes an ArrayList of states (each state is stored as a Pair<name, capital>).
     * 1. Reads state-capital data from 'states.txt' 
     * 2. Allows user interaction for various operations: 
     *    - view all states
     *    - search for a specific state
     *    - sort by state name
     *    - sort by capital
     *    - exit
     */
    public static void processStates() {
        ArrayList<Pair<String, String>> states = new ArrayList<>();
        readStates(states, "states.txt");

        Scanner keyboard = new Scanner(System.in);
        int operation = 0;
        do {
            System.out.println("Select an operation:\n1: view\n2: search\n3: sort by name\n4: sort by capital\n5: exit");
            operation = Integer.parseInt(keyboard.nextLine());
            switch(operation) {
                case 1: // view
                    print(states);
                    break;
                case 2: // search
                    System.out.println("Enter a state:");
                    String state = keyboard.nextLine();
                    int index = search(states, state);
                    if (index == -1) {
                        System.out.println("State " + state + " not found");
                    }
                    else {
                        System.out.println("State found: " + states.get(index));
                    }
                    break;
                case 3: // sort by name
                    states.sort(new ComparatorByFirst<String, String>());
                    print(states);
                    break;
                case 4: // sort by capital
                    states.sort(new ComparatorBySecond<String, String>());
                    print(states);
                    break;
                case 5: // exit
                    System.out.println("Thank you!");
                    break;
                default:
                    System.out.println("Operation must be 1 to 5");
            }
        } while(operation != 5);
    }

    /**
     * Reads a list of states from a file, where each line contains "stateName|capitalName".
     * Populates the given ArrayList with Pair objects (state, capital).
     *
     * @param list     the list to populate with state-capital pairs
     * @param filename the name of the file containing the states data
     */
    public static void readStates(ArrayList<Pair<String, String>> list, String filename){
        try {
            Scanner read = new Scanner(new File(filename));
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] tokens = line.split("\\|");
                String name = tokens[0];
                String capital = tokens[1];
                Pair<String, String> state = new Pair<>(name, capital);
                list.add(state);
            }
            read.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Prints each element of an ArrayList on a new line.
     *
     * @param <E>  the type of elements in the list
     * @param list the ArrayList to print
     */
    public static <E> void print(ArrayList<E> list){
        for (E element : list) {
            System.out.println(element);
        }
    }

    /**
     * Processes an ArrayList of trees (each tree is stored as a Pair<treeName, height>).
     * 1. Reads tree data from 'trees.txt' 
     * 2. Allows user interaction for various operations:
     *    - view all trees
     *    - search for a specific tree
     *    - sort by name
     *    - sort by height
     *    - exit
     */
    public static void processTrees() {
        ArrayList<Pair<String, Integer>> trees = new ArrayList<>();
        readTrees(trees, "trees.txt");

        Scanner keyboard = new Scanner(System.in);
        int operation = 0;
        do {
            System.out.println("Select an operation:\n1: view\n2: search\n3: sort by name\n4: sort by height\n5: exit");
            operation = Integer.parseInt(keyboard.nextLine());
            switch(operation) {
                case 1: // view
                    print(trees);
                    break;
                case 2: // search
                    System.out.println("Enter a tree:");
                    String tree = keyboard.nextLine();
                    int index = search(trees, tree);
                    if (index == -1) {
                        System.out.println("Tree " + tree + " not found");
                    }
                    else {
                        System.out.println("Tree found: " + trees.get(index));
                    }
                    break;
                case 3: // sort by name
                    trees.sort(new ComparatorByFirst<String, Integer>());
                    print(trees);
                    break;
                case 4: // sort by height
                    trees.sort(new ComparatorBySecond<String, Integer>());
                    print(trees);
                    break;
                case 5: // exit
                    System.out.println("Thank you!");
                    break;
                default:
                    System.out.println("Operation must be 1 to 5");
            }
        } while(operation != 5);
    }

    /**
     * Reads a list of trees from a file, where each line contains "treeName|height".
     * Populates the given ArrayList with Pair objects (treeName, height).
     *
     * @param list     the list to populate with tree pairs
     * @param filename the name of the file containing the trees data
     */
    public static void readTrees(ArrayList<Pair<String, Integer>> list, String filename){
        try {
            Scanner read = new Scanner(new File(filename));
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] tokens = line.split("\\|");
                String name = tokens[0];
                int height = Integer.parseInt(tokens[1]);
                Pair<String, Integer> tree = new Pair<>(name, height);
                list.add(tree);
            }
            read.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
