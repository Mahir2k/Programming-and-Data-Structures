import java.util.Comparator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
    /**
     * Method to read the notes from a file
     * @param list the sorted list where the Note objects will be added
     * @param filename file from where the note information is read
     */
/**
 * Reads notes from a file and adds them to a sorted list.
 * Each note consists of a date, title, and description.
 * If an invalid date format is encountered, an error message is printed with the line number.
 *
 * @param list     The sorted list where Note objects will be added.
 * @param filename The name of the file containing note entries.
 */
public static void readNotes(SortedList<Note> list, String filename) {
    try (Scanner sc = new Scanner(new File(filename))) {
        int lineNum = 0;
        while (sc.hasNextLine()) {
            lineNum++;
            String dateStr = sc.nextLine().trim();
            if (!sc.hasNextLine()) break;
            String title = sc.nextLine().trim();
            if (!sc.hasNextLine()) break;
            String desc = sc.nextLine().trim();

            try {
                Note n = new Note(dateStr, title, desc);
                list.add(n);
            } catch (FormatMismatchException e) {
                System.out.println("Line " + lineNum + ": " + e.getMessage());
            }
        }
    } catch (FileNotFoundException e) {
        System.out.println("File " + filename + " not found.");
    }
}

/**
 * Reads contacts from a file and adds them to a sorted list.
 * Each contact consists of a first name, last name, phone number, and email address.
 * If an invalid phone or email format is encountered, an error message is printed with the line number.
 *
 * @param list     The sorted list where Contact objects will be added.
 * @param filename The name of the file containing contact entries.
 */
public static void readContacts(SortedList<Contact> list, String filename) {
    try (Scanner sc = new Scanner(new File(filename))) {
        int lineNum = 0;
        while (sc.hasNextLine()) {
            lineNum++;
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] tokens = line.split("\\s+");

            if (tokens.length < 4) continue;  // Ensures there are enough tokens for name, phone, and email
            
            String name = tokens[0] + " " + tokens[1];  // Combines first and last name
            String phone = tokens[2];
            String email = tokens[3];

            try {
                Contact c = new Contact(name, phone, email);
                list.add(c);
            } catch (FormatMismatchException ex) {
                System.out.println("Line " + lineNum + ": " + ex.getMessage());
            }
        }
    } catch (FileNotFoundException e) {
        System.out.println("File " + filename + " not found.");
    }
}
    public static void main(String[] args) {
        // creating a list of notes that is sorted using the natural ordering of class Note (by title)
        //////////////////////////////////////////////////////////////////////////////////////////////
		SortedList<Note> notes = new SortedList<>();
		/////////////////////////////////////
        // Testing SortedList for type Note
        ////////////////////////////////////
        System.out.println("Test case 1: reading the notes from the file");
        readNotes(notes, "notes.txt");
        System.out.println(notes.size() + " notes read from the file (sorted by title)\n" + notes);

        try {
            System.out.println("\nTest case 2: Adding a new note to the list");
            Note n = new Note("02/28/2025", "Medicine", "Pick up at the pharmacy");
            notes.add(n);
            System.out.println("Note: (" + n.getTitle() + ", " + n.getDate().toString() + ", " +
                               n.getDescription() + ") added successfully.");
            System.out.println("Updated list of notes\n" + notes);

            System.out.println("\nTest case 3: Finding a note (success)");
            n = new Note("01/01/2010", "Trash", "");
            int index = notes.indexOf(n);
            if (index == -1) {
                System.out.println("Note with title \"Trash\" not found.");
            }
            else {
                n = notes.get(index);
                System.out.println("Note with title \"Trash\" found:\n(" + n.getTitle() + ", " +
                                   n.getDate().toString() + ", " + n.getDescription() + ")");
                System.out.println("\nTest case 4: Removing a note from the list");
                notes.remove(index);
                System.out.println("Note (" + n.getTitle() + ", " + n.getDate().toString() +
                                   ", " + n.getDescription() + ") removed successfully.");
                System.out.println("Updated list of notes\n" + notes);
            }

            System.out.println("\nTest case 5: Finding a note (fail)");
            n = new Note("01/01/2010", "Buy pens", "");
            index = notes.indexOf(n);
            if (index == -1) {
                System.out.println("Note with title \"Buy pens\" not found.");
            }
            else {
                System.out.println("Note with title \"Buy pens\" found.\n" + notes.get(index));
            }

            System.out.println("\nTest case 6: Finding a note and modifying it");
            n = new Note("01/01/2010", "Dinner", "");
            index = notes.indexOf(n);
            if (index == -1) {
                System.out.println("Note with title \"Dinner\" not found.");
            }
            else {
                n = notes.get(index);
                System.out.println("Note with title \"Dinner\" found:\n(" + n.getTitle() + ", " +
                                   n.getDate().toString() + ", " + n.getDescription() + ")");
                n.setDate("04/22/2025");
                System.out.println("Note (" + n.getTitle() + ", " + n.getDate().toString() +
                                   ", " + n.getDescription() + ") updated successfully.");
                System.out.println("Updated list of notes\n" + notes);
            }
        }
        catch (FormatMismatchException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nTest case 7: Sorting the notes by date");
        class ComparatorByDate implements Comparator<Note> {
            public int compare(Note n1, Note n2){
                return n1.getDate().compareTo(n2.getDate());
            }
        }
        notes.setComparator(new ComparatorByDate());
        System.out.println("List of notes sorted by date\n" + notes);

        /////////////////////////////////////////////////////////
        // creating a list of contacts that is sorted by email
        ////////////////////////////////////////////////////////

        class ComparatorByEmail implements Comparator<Contact> {
            @Override
            public int compare(Contact c1, Contact c2) {
                return c1.getEmail().compareTo(c2.getEmail());
            }
        }
        ////////////////////////////////////////
		// Testing SortedList for type Contact
        ///////////////////////////////////////
        SortedList<Contact> contacts = new SortedList<>(new ComparatorByEmail());
        System.out.println("\nTest case 8: reading the contacts from the file");
        readContacts(contacts, "contacts.txt");
        System.out.println(contacts.size() + " contacts read from the file (sorted by email)\n" + contacts);

        System.out.println("\nTest case 9: Adding a new contact to the list");
        Contact c = null;
        try {
            c = new Contact("Albert Floss", "610-222-2434", "afloss@lehigh.edu");
        }
        catch (FormatMismatchException e) {
            System.out.println(e.getMessage());
        }
        if (c != null) {
            contacts.add(c);
            System.out.println("Contact (" + c.getName() + ", " + c.getPhone() + ", " +
                               c.getEmail() + ") added successfully.");
            System.out.println("Updated list of contacts\n" + contacts);
        }

        System.out.println("\nTest case 10: Sorting the contacts by name");
        contacts.setComparator(null);
        System.out.println("List of contacts sorted by name\n" + contacts);

        System.out.println("\nTest case 11: Finding a contact (fail)");
        try {
            c = new Contact("Philip Mensen", "000-000-0000", "aaaa@aaaa.com");
        }
        catch (FormatMismatchException e) {
            System.out.println(e.getMessage());
        }
        int index = -1;
        if (c != null) {
            index = contacts.indexOf(c);
        }
        if (index == -1) {
            System.out.println("Contact \"" + (c == null ? "Philip Mensen" : c.getName()) + "\" not found.");
        }
        else {
            System.out.println("Contact found\n(" + contacts.get(index) + ")");
        }

        System.out.println("\nTest case 12: Finding a contact (success)");
        try {
            c = new Contact("Butt James", "000-000-0000", "aaaa@aaaa.com");
        }
        catch (FormatMismatchException e) {
            System.out.println(e.getMessage());
        }
        if (c != null) {
            index = contacts.indexOf(c);
            if (index == -1) {
                System.out.println("Contact \"" + c.getName() + "\" not found.");
            }
            else {
                c = contacts.get(index);
                System.out.println("Contact found\n(" + c.getName() + ", " + c.getPhone() + ", " + c.getEmail() + ")");
                System.out.println("\nTest case 13: Removing a contact from the list");
                contacts.remove(index);
                System.out.println("Contact(" + c.getName() + ", " + c.getPhone() + ", " +
                                   c.getEmail() + ") removed successfully.");
                System.out.println("Updated list of contacts\n" + contacts);
            }
        }

        System.out.println("\nTest case 14: Finding a contact and modifying it");
        try {
            c = new Contact("Amigon Minna", "000-000-0000", "aaaa@aaaa.com");
        }
        catch (FormatMismatchException e) {
            System.out.println(e.getMessage());
        }
        if (c != null) {
            index = contacts.indexOf(c);
            if (index == -1) {
                System.out.println("Contact with name \"Amigon Minna\" not found.");
            }
            else {
                c = contacts.get(index);
                System.out.println("Contact with name \"Amigon Minna\" found:\n(" + c.getName() + "," +
                                   c.getPhone() + ", " + c.getEmail() +")");
                try {
                    c.setEmail("mamigon@lehigh.edu");
                }
                catch (FormatMismatchException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("Contact (" + c.getName() + "," + c.getPhone() + "," + c.getEmail() + ") updated successfully.");
                System.out.println("Updated list of contacts\n" + contacts);
            }
        }
    }
}
