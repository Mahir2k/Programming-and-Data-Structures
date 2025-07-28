/**
 * Class managing calendar events
 */
public class Calendar {
    private Event[] events;
    private int count;

    /**
     * Constructor initializing calendar
     */
    public Calendar() {
        events = new Event[10];
        count = 0;
    }

    /**
     * Adding event to calendar
     * @param event Event to add
     * @return true if added successfully
     */
    public boolean add(Event event) {
        if(count == 10) return false;
        events[count++] = event;
        return true;
    }

    /**
     * Finding event by description
     * @param description Event description
     * @return Found event or null
     */
    public Event find(String description) {
        for(int i = 0; i < count; i++) {
            if(events[i].getDescription().equals(description)) {
                return events[i];
            }
        }
        return null;
    }

    /**
     * Removing event by description
     * @param description Event description
     * @return true if found and removed
     */
    public boolean remove(String description) {
        for(int i = 0; i < count; i++) {
            if(events[i].getDescription().equals(description)) {
                for(int j = i; j < count-1; j++) {
                    events[j] = events[j+1];
                }
                events[--count] = null;
                return true;
            }
        }
        return false;
    }

    /**
     * Finding events by date
     * @param date Date to search
     * @return Array of events or null
     */
    public Event[] findByDate(String date) {
        int matchCount = 0;
        for(int i = 0; i < count; i++) {
            if(events[i].getDate().equals(date)) matchCount++;
        }
        
        if(matchCount == 0) return null;
        
        Event[] matches = new Event[matchCount];
        int index = 0;
        for(int i = 0; i < count; i++) {
            if(events[i].getDate().equals(date)) {
                matches[index++] = events[i];
            }
        }
        return matches;
    }

    /**
     * Finding events by location
     * @param location Location to search
     * @return Array of events or null
     */
    public Event[] findByLocation(String location) {
        int matchCount = 0;
        for(int i = 0; i < count; i++) {
            if(events[i].getLocation().equals(location)) matchCount++;
        }
        
        if(matchCount == 0) return null;
        
        Event[] matches = new Event[matchCount];
        int index = 0;
        for(int i = 0; i < count; i++) {
            if(events[i].getLocation().equals(location)) {
                matches[index++] = events[i];
            }
        }
        return matches;
    }

    /**
     * Sorting events by location using insertion sort
     */
    public void sortByLocation() {
        for(int i = 1; i < count; i++) {
            int j = i;
            Event current = events[j];
            while(j > 0 && events[j-1].getLocation().compareTo(current.getLocation()) > 0) {
                events[j] = events[j-1];
                j--;
            }
            events[j] = current;
        }
    }

    /**
     * Returning string representation of calendar
     * @return Formatted string
     */
    @Override
    public String toString() {
        String result = "";
        for(int i = 0; i < count; i++) {
            result += events[i].toString() + "\n";
        }
        return result;
    }
}







