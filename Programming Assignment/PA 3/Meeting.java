/**
 * Represents a Meeting event, which is a specialized type of Event.
 * Includes a host and the number of guests attending.
 */
public class Meeting extends Event {

    // Instance variables
    private String host;
    private int guestCount;

    /**
     * Default constructor initializes a Meeting with default values.
     */
    public Meeting() {
        super(); 
        this.host = "Unknown";
        this.guestCount = 0;
    }

    /**
     * Constructs a Meeting event with specified details.
     * @param description Brief description of the meeting.
     * @param location The venue where the meeting will take place.
     * @param dateString The date of the meeting in "MM/DD/YYYY" format.
     * @param timeString The time of the meeting in "HH:MM" format.
     * @param host The person hosting the meeting.
     * @param guestCount Number of attendees at the meeting.
     * @throws FormatMismatchException if the date or time format is invalid.
     */
    public Meeting(String description,
                   String location,
                   String dateString,
                   String timeString,
                   String host,
                   int guestCount) throws FormatMismatchException {
        super(description, location, dateString, timeString);
        this.host = host;
        this.guestCount = guestCount;
    }

    /**
     * Retrieves the host of the meeting.
     * @return The name of the host.
     */
    public String getHost() {
        return this.host;
    }

    /**
     * Retrieves the number of guests attending the meeting.
     * @return The count of guests.
     */
    public int getGuestCount() {
        return this.guestCount;
    }

    /**
     * Updates the host of the meeting.
     * @param host The new host name.
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Updates the number of guests attending the meeting.
     * @param guestCount The updated number of guests.
     */
    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
    }

    /**
     * Generates a formatted string representation of the Meeting.
     * @return A string containing meeting details.
     */
    @Override
    public String toString() {
        return String.format("%-15s\t%s\t%-20s\t%d", 
            "Meeting",
            super.toString(),
            this.host,
            this.guestCount
        );
    }

    /**
     * Generates a formatted string suitable for file storage.
     * Format: "Meeting,description,location,MM/DD/YYYY,HH:MM,host,guestCount"
     * @return A CSV-formatted string representation of the meeting.
     */
    @Override
    public String fileString() {
        return "Meeting," + super.fileString() + "," + this.host + "," + this.guestCount;
    }


    @Override
    public Object clone() {
        try {
            // Reconstruct all fields, calling your constructors again with the same field values.
            // Make sure you create new Date and Time objects (not references to the same ones).
            Meeting copy = new Meeting(
                this.getDescription(), 
                this.getLocation(), 
                this.getDate().toString(),   // or pass year,month,day
                this.getTime().toString(),   // or pass hour,minute
                this.getHost(),
                this.getGuestCount()
            );
            return copy;
        } catch (FormatMismatchException e) {
            // Return null if there's a formatting error in the constructor
            return null;
        }
    }
}







