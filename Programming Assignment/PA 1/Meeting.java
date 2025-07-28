/**
 * Class representing meeting events
 */
public class Meeting extends Event {
    private String host;
    private int guests;

    /**
     * Constructor initializing meeting details
     * @param description Event description
     * @param location Event location
     * @param date Event date
     * @param time Event time
     * @param host Meeting host
     * @param guests Number of guests
     */
    public Meeting(String description, String location,
                  String date, String time, String host, int guests) {
        super(description, location, date, time);
        this.host = host;
        this.guests = guests;
    }

    /**
     * Formatting meeting details
     * @return Formatted string
     */
    @Override
    public String toString() {
        return String.format("%-15s\t%s\t%-15s\t%-5d", "Meeting", super.toString(), host, guests);
    }
}








