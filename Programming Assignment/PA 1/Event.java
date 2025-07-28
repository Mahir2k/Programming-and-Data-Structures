/**
 * Abstract class representing calendar events
 */
public abstract class Event {
    protected String description;
    protected String location;
    protected String date;
    protected String time;

    /**
     * Constructor initializing event attributes
     * @param description Event description
     * @param location Event location
     * @param date Event date
     * @param time Event time
     */
    public Event(String description, String location, String date, String time) {
        this.description = description;
        this.location = location;
        this.date = date;
        this.time = time;
    }

    /**
     * Getting event description
     * @return Description string
     */
    public String getDescription() { return description; }

    /**
     * Getting event location
     * @return Location string
     */
    public String getLocation() { return location; }

    /**
     * Getting event date
     * @return Date string
     */
    public String getDate() { return date; }

    /**
     * Getting event time
     * @return Time string
     */
    public String getTime() { return time; }

    /**
     * Formatting event details
     * @return Formatted string
     */
    @Override
    public String toString() {
        return String.format("%-20s\t%-20s\t%-8s\t%-5s", description, location, date, time);
    }
}








