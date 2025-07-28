/**
 * Abstract class Event to represent a general event with essential details.
 * It serves as a base class for specific event types like Meetings and Appointments.
 */
public abstract class Event {
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
     * @param dateInput The date of the event in "MM/DD/YYYY" format.
     * @param timeInput The time of the event in "HH:MM" format.
     * @throws FormatMismatchException If the date or time format is invalid.
     */
    protected Event(String description, 
                    String location, 
                    String dateInput, 
                    String timeInput) 
                    throws FormatMismatchException {
        this.description = description;
        this.location    = location;
        this.date        = new Date(dateInput);  // Exception is thrown if format is wrong
        this.time        = new Time(timeInput);  // Ensures time validity
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
     * @param dateInput A date string in "MM/DD/YYYY" format.
     * @throws FormatMismatchException If the format is incorrect.
     */
    public void setDate(String dateInput) throws FormatMismatchException {
        this.date = new Date(dateInput);
    }

    /**
     * Updates the event time with a new value.
     * @param timeInput A time string in "HH:MM" format.
     * @throws FormatMismatchException If the format is incorrect.
     */
    public void setTime(String timeInput) throws FormatMismatchException {
        this.time = new Time(timeInput);
    }

    /**
     * Generates a structured string representation of the event.
     * @return A formatted string containing event details.
     */
    @Override
    public String toString() {
        return String.format("%-20s %-20s %-10s %-8s", 
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
}






