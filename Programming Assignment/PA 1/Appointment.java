/**
 * Class representing appointment events
 */
public class Appointment extends Event {
    private String contact;

    /**
     * Constructor initializing appointment details
     * @param description Event description
     * @param location Event location
     * @param date Event date
     * @param time Event time
     * @param contact Contact person
     */
    public Appointment(String description, String location, 
                      String date, String time, String contact) {
        super(description, location, date, time);
        this.contact = contact;
    }

    /**
     * Formatting appointment details
     * @return Formatted string
     */
    @Override
    public String toString() {
        return String.format("%-15s\t%s\t%-15s", "Appointment", super.toString(), contact);
    }
}







