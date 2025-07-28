import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Arrays;

/**
 * Class Calendar to manage a collection of Event objects.
 */
public class Calendar implements Cloneable{
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
        if (matchCount == 0){
            return null;
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
        if (matchCount == 0){
            return null;
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
                try {
                if (tokens.length != 6 && tokens.length != 7){
                    throw new FormatMismatchException("Event Format Error: (Expected number of items: 6 or 7):");
                }
                if (!tokens[0].equals("Appointment") && !tokens[0].equals("Meeting")) {
                    throw new FormatMismatchException("Event Type Error (Expected: Appointment or Meeting): " + tokens[0]);
                }

                    if (tokens[0].equals("Appointment")) {
                        add(new Appointment(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]));
                    } else if (tokens[0].equals("Meeting")) {
                        add(new Meeting(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], Integer.parseInt(tokens[6])));
                    }
                } catch (FormatMismatchException e) {
                    System.out.println(e.getMessage() + "\nLine: " + line);
                }
            }
            sc.close();
            System.out.println(count + " events read from the file \"" + filename + "\"");
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
        StringBuilder sb = new StringBuilder(String.format("%-15s\t%-20s\t%-20s\t%-10s\t%-8s\t%-20s\t%s\n","Event Type", "Description", "Location", "Date", "Time", "Contact/Host", "Guests"));
        for (int i = 0; i < count; i++) {
            sb.append(events[i]).append("\n");
        }
        return sb.toString();
    }

    public void sort(boolean byLocation) {
        if (byLocation) {
            // insertion sort on events[0..count-1] by location
            for (int i = 1; i < count; i++) {
                Event key = events[i];
                int j = i - 1;
                while (j >= 0 && events[j].getLocation().compareTo(key.getLocation()) > 0) {
                    events[j + 1] = events[j];
                    j--;
                }
                events[j + 1] = key;
            }
        } else {
            // sort by the natural ordering => date/time
            Arrays.sort(events, 0, count);
        }
    }

    public Event[] getMeetings() {
    // First, count how many of the stored events are actually Meetings
        int meetingCount = 0;
        for (int i = 0; i < count; i++) {
            if (events[i] instanceof Meeting) {
                meetingCount++;
            }
        }

        // Create a new array sized exactly for those Meetings
        Event[] meetings = new Event[meetingCount];
        
        // Fill the array with Meeting objects
        int index = 0;
        for (int i = 0; i < count; i++) {
            if (events[i] instanceof Meeting) {
                meetings[index++] = events[i];
            }
        }

        // Return the array of Meetings
        return meetings;
    }

    public Event[] getAppointments() {
        int aCount = 0;
        for (int i = 0; i < count; i++) {
            if (events[i] instanceof Appointment) aCount++;
        }
        if (aCount == 0) return null;
        Event[] result = new Event[aCount];
        int idx = 0;
        for (int i = 0; i < count; i++) {
            if (events[i] instanceof Appointment) {
                result[idx++] = events[i];
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || !(obj instanceof Calendar)) return false;
        Calendar other = (Calendar) obj;
        if (this.count != other.count) return false;
        // Check event by event
        for (int i = 0; i < this.count; i++) {
            if (!this.events[i].equals(other.events[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object clone() {
        try {
            Calendar copy = (Calendar) super.clone();
            copy.events = new Event[this.events.length]; // new array
            // For each event, call the event's clone()
            for (int i = 0; i < this.count; i++) {
                copy.events[i] = (Event) this.events[i].clone(); 
            }
            return copy;
        } catch (CloneNotSupportedException e) {
            // should not happen if we implement Cloneable
            return null;
        }
    }
}
