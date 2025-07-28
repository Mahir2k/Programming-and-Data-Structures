/**
 * Abstract class Event to represent a general event with essential details.
 * It serves as a base class for specific event types like Meetings and Appointments.
 */
public abstract class Event implements Comparable<Event>, Cloneable{
    // Instance variables
    private String description;
    private String location;
    private Date date;
    private Time time;

    /**
     * Default constructor initializing an event with default values.
     */
    protected Event() {
        this.description = "Not Specified";
        this.location    = "Unknown";
        this.date        = new Date();  // Defaults to 01/01/2010
        this.time        = new Time();  // Defaults to 00:00
    }

    /**
     * Parameterized constructor that initializes an Event object.
     * @param description Short summary of the event.
     * @param location The venue where the event will take place.
     * @param date The date of the event in "MM/DD/YYYY" format.
     * @param time The time of the event in "HH:MM" format.
     * @throws FormatMismatchException If the date or time format is invalid.
     */
    protected Event(String description, 
                    String location, 
                    String date, 
                    String time) 
                    throws FormatMismatchException {
        this.description = description;
        this.location    = location;
        this.date        = new Date(date);  // Exception is thrown if format is wrong
        this.time        = new Time(time);  // Ensures time validity
    }

    /**
     * Retrieves the event description.
     * @return A string representing the event's description.
     */
    public String getDescription() { return description; }
    
    /**
     * Retrieves the event location.
     * @return A string representing the event's location.
     */
    public String getLocation() { return location; }
    
    /**
     * Retrieves the event date.
     * @return The event date as a Date object.
     */
    public Date getDate() { return date; }
    
    /**
     * Retrieves the event time.
     * @return The event time as a Time object.
     */
    public Time getTime() { return time; }

    /**
     * Updates the event description.
     * @param description The new description for the event.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Updates the event location.
     * @param location The new location of the event.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Updates the event date with a new value.
     * @param date A date string in "MM/DD/YYYY" format.
     * @throws FormatMismatchException If the format is incorrect.
     */
    public void setDate(String d) throws FormatMismatchException {
        this.date = new Date(d);
    }

    /**
     * Updates the event time with a new value.
     * @param time A time string in "HH:MM" format.
     * @throws FormatMismatchException If the format is incorrect.
     */
    public void setTime(String t) throws FormatMismatchException {
        this.time = new Time(t);
    }

    /**
     * Generates a structured string representation of the event.
     * @return A formatted string containing event details.
     */
    @Override
    public String toString() {
        return String.format("%-20s\t%-20s\t%-10s\t%-8s", 
            description,
            location,
            date.toString(),
            time.toString()
        );
    }

    /**
     * Formats the event data into a CSV-compatible string.
     * @return A comma-separated string containing the event details.
     */
    public String fileString() {
        return String.join(",",
            description, 
            location, 
            date.toString(),  
            time.toString()
        );
    }

    @Override
    public int compareTo(Event other) {
        int d = this.date.compareTo(other.date);
        if (d != 0) {
            return d;
        }
        return this.time.compareTo(other.time);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || !(obj instanceof Event)) return false;
        Event other = (Event) obj;
        return this.description.equals(other.description)
            && this.location.equals(other.location)
            && this.date.equals(other.date)
            && this.time.equals(other.time);
    }

    @Override
    public abstract Object clone();
}






