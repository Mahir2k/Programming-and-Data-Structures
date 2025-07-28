/**
 * Represents a specific time instance with hours and minutes.
 * Provides validation to ensure correct time format.
 */
public class Time implements Comparable<Time>{
    // Instance variables
    private int hours;
    private int minutes;

    /**
     * Default constructor that sets time to 00:00.
     */
    public Time() {
        this.hours = 0;
        this.minutes = 0;
    }

    /**
     * Constructs a Time object using a given string input.
     * @param timeInput The time string formatted as "HH:MM".
     * @throws FormatMismatchException If the format or values are invalid.
     */
    public Time(String timeInput) throws FormatMismatchException {
        // Validate format (HH:MM or H:MM)
        if (!timeInput.matches("\\d{1,2}:\\d{1,2}")) {
            throw new FormatMismatchException("Error: Time must be in HH:MM format.");
        }

        String[] values = timeInput.split(":");
        try {
            this.hours   = Integer.parseInt(values[0]);
            this.minutes = Integer.parseInt(values[1]);
        } catch (NumberFormatException e) {
            throw new FormatMismatchException("Error: Time must contain numeric values only.");
        }

        // Validate hour range
        if (hours < 0 || hours > 23) {
            throw new FormatMismatchException("Invalid value for hours (0 to 23): " + hours);
        }
        // Validate minute range
        if (minutes < 0 || minutes > 59) {
            throw new FormatMismatchException("Invalid value for minutes (expected: 0 to 59): " + minutes);
        }
    }

    /**
     * Gets the hour component of the time.
     * @return The hour as an integer.
     */
    public int getHours() {
        return hours;
    }

    /**
     * Gets the minute component of the time.
     * @return The minutes as an integer.
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Sets the hour component of the time.
     * @param newHours The new hour value to set.
     */
    public void setHours(int newHours) {
        this.hours = newHours;
    }

    /**
     * Sets the minute component of the time.
     * @param newMinutes The new minute value to set.
     */
    public void setMinutes(int newMinutes) {
        this.minutes = newMinutes;
    }

    /**
     * Returns a formatted string representation of the time.
     * @return Time formatted as "HH:MM".
     */
    @Override
    public String toString() {
        return String.format("%02d:%02d", hours, minutes);
    }

    @Override
    public int compareTo(Time other) {
        if (this.getHours() != other.getHours()) {
            return this.getHours() - other.getHours();
        }
        return this.getMinutes() - other.getMinutes();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || !(obj instanceof Time)) return false;
        Time other = (Time) obj;
        return this.getHours() == other.getHours() && this.getMinutes() == other.getMinutes();
    }

}






