import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Class Calendar to manage a collection of Event objects.
 */
public class Calendar {
    private Event[] events;
    private int count;

    public Calendar() {
        events = new Event[10];
        count = 0;
    }

    public boolean add(Event event) {
        if (count < events.length) {
            events[count++] = event;
            return true;
        }
        return false;
    }

    public Event find(String description) {
        for (int i = 0; i < count; i++) {
            if (events[i].getDescription().equals(description)) {
                return events[i];
            }
        }
        return null;
    }

    public boolean remove(String description) {
        for (int i = 0; i < count; i++) {
            if (events[i].getDescription().equals(description)) {
                for (int j = i; j < count - 1; j++) {
                    events[j] = events[j + 1];
                }
                events[--count] = null;
                return true;
            }
        }
        return false;
    }

    public Event[] findByDate(String date) {
        int matchCount = 0;
        for (int i = 0; i < count; i++) {
            if (events[i].getDate().toString().equals(date)) {
                matchCount++;
            }
        }

        Event[] result = new Event[matchCount];
        int index = 0;
        for (int i = 0; i < count; i++) {
            if (events[i].getDate().toString().equals(date)) {
                result[index++] = events[i];
            }
        }
        return result;
    }

    public Event[] findByLocation(String location) {
        int matchCount = 0;
        for (int i = 0; i < count; i++) {
            if (events[i].getLocation().equals(location)) {
                matchCount++;
            }
        }

        Event[] result = new Event[matchCount];
        int index = 0;
        for (int i = 0; i < count; i++) {
            if (events[i].getLocation().equals(location)) {
                result[index++] = events[i];
            }
        }
        return result;
    }

    public void sortByLocation() {
        for (int i = 1; i < count; i++) {
            Event current = events[i];
            int j = i - 1;
            while (j >= 0 && current.getLocation().compareTo(events[j].getLocation()) < 0) {
                events[j + 1] = events[j];
                j--;
            }
            events[j + 1] = current;
        }
    }

    /**
     * Reads events from a file and handles exceptions internally.
     * This prevents FileNotFoundException from propagating to CalendarManager.java.
     */
    public void readEvents(String filename) {
        count = 0;
        try {
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] tokens = line.split(",");
                if (!tokens[0].equals("Appointment") && !tokens[0].equals("Meeting")) {
                    System.out.println("Event Type Error: " + tokens[0]);
                    continue;
                }
                try {
                    if (tokens[0].equals("Appointment")) {
                        add(new Appointment(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]));
                    } else if (tokens[0].equals("Meeting")) {
                        add(new Meeting(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], Integer.parseInt(tokens[6])));
                    }
                } catch (Exception e) {
                    System.out.println("Error processing line: " + line);
                }
            }
            sc.close();
            System.out.println(count + " events read from " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    /**
     * Saves events to a file and handles exceptions internally.
     * This prevents FileNotFoundException from propagating to CalendarManager.java.
     */
    public void saveEvents(String filename) {
        try {
            PrintWriter pw = new PrintWriter(filename);
            for (int i = 0; i < count; i++) {
                pw.println(events[i].fileString());
            }
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to file: " + filename);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Events:\n");
        for (int i = 0; i < count; i++) {
            sb.append(events[i]).append("\n");
        }
        return sb.toString();
    }
}
