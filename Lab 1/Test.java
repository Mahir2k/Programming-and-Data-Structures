/**
 * Test class to demonstrate polymorphic behavior with an array of Person objects.
 * The program creates an array of Person objects (Student, Employee, Faculty),
 * prints the original list, sorts it based on name or ID, and prints the sorted list.
 * 
 * @author: Houria Oudghiri 
 * @version 01
 */
public class Test {

    /**
     * Main method that initializes an array of Person objects, prints the original list,
     * sorts the list based on name, and prints the sorted list.
     * 
     * @param args Command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        // Create an array of Person objects
        Person[] people = new Person[3];
        people[0] = new Student(123456789, "Paul Leister", "972 4th Street, Allentown", "610-331-7177", "pleister@gmail.com", "CSE");
        people[1] = new Employee(334422110, "Beth Down", "234 Main Street, Philadelphia", "484-222-4433", "bdown@gmail.com", "System Administrator", 75000.00);
        people[2] = new Faculty(222222222, "Mark Jones", "21 Orchid Street, Bethlehem", "610-333-2211", "mjones@gmail.com", "Faculty", 100000.00, "Associate Professor");

        // Print original list
        System.out.println("Original list");
        printArray(people);

        // Sort and print sorted list
        System.out.println("Sorted List");
        sortArray(people, true); // Sort by name
        printArray(people);
    }

    /**
     * Prints all elements in the given array of Person objects.
     * 
     * @param list Array of Person objects to be printed.
     */
    public static void printArray(Person[] list) {
        for (Person p : list) {
            System.out.println(p.toString());
        }
    }

    /**
     * Sorts an array of Person objects either by name or by ID using selection sort.
     * 
     * @param list Array of Person objects to be sorted.
     * @param nameOrId If true, sorts by name in alphabetical order; if false, sorts by ID in ascending order.
     */
    public static void sortArray(Person[] list, boolean nameOrId) {
        for (int i = 0; i < list.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < list.length; j++) {
                if (nameOrId) {
                    /**
                     * Sort by name:
                     * Compare names alphabetically and update minIndex if a "smaller" name is found.
                     */
                    String namej = list[j].getName();
                    String namein = list[minIndex].getName();
                    if (namej.compareTo(namein) < 0)
                        minIndex = j;
                } else {
                    /**
                     * Sort by ID:
                     * Compare IDs numerically and update minIndex if a smaller ID is found.
                     */
                    if (list[j].getID() < list[minIndex].getID()) {
                        minIndex = j;
                    }
                }
            }
            /**
             * Swap elements:
             * Exchange the current element with the smallest found element to sort the array.
             */
            Person temp = list[i];
            list[i] = list[minIndex];
            list[minIndex] = temp;
        }
    }

}
