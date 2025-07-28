/**
 * Models an Appointment event, extending the Event class.
 * Represents an event with an associated contact person.
 */
public class Appointment extends Event {

    /** The contact person associated with the appointment. */
    private String contact;

    /**
     * Default constructor.
     * Calls the superclass constructor and initializes the contact field to "Unknown".
     */
    public Appointment() {
        super();
        this.contact = "Unknown";
    }

    /**
     * Parameterized constructor to create an Appointment with given details.
     * @param description Brief description of the appointment.
     * @param location The place where the appointment is scheduled.
     * @param dateString The date of the appointment in "MM/DD/YYYY" format.
     * @param timeString The time of the appointment in "HH:MM" format.
     * @param contact The person associated with the appointment.
     * @throws FormatMismatchException if the date or time format is incorrect.
     */
    public Appointment(String description,
                       String location,
                       String dateString,
                       String timeString,
                       String contact) throws FormatMismatchException {
        super(description, location, dateString, timeString);
        this.contact = contact;
    }

    /**
     * Retrieves the contact associated with the appointment.
     * @return The name of the contact person.
     */
    public String getContact() {
        return this.contact;
    }

    /**
     * Updates the contact information for the appointment.
     * @param contact The new contact person's name.
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * Generates a formatted string representation of the appointment.
     * @return A string containing appointment details, including the contact person.
     */
    @Override
    public String toString() {
        return String.format("%-15s\t%s\t%-20s", 
            "Appointment", 
            super.toString(), 
            this.contact
        );
    }

    /**
     * Generates a CSV-formatted string representation of the appointment.
     * @return A CSV string for saving to a file, in the format:
     *         "Appointment,description,location,MM/DD/YYYY,HH:MM,contact"
     */
    @Override
    public String fileString() {
        return "Appointment," + super.fileString() + "," + this.contact;
    }

    @Override
    public Object clone() {
        try {
            // Call the constructor, which parses new Date/Time from strings
            Appointment copy = new Appointment(
                this.getDescription(),
                this.getLocation(),
                this.getDate().toString(),  // "MM/DD/YYYY"
                this.getTime().toString(),  // "HH:MM"
                this.getContact()
            );
            return copy;
        }
        catch (FormatMismatchException e) {
            return null;
        }
    }
}
