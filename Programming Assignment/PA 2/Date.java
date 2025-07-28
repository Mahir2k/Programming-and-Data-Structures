/**
 * Represents a Date with a month, day, and year.
 * Provides validation for correct date format and range.
 */
public class Date {
    // Instance variables
    private int month;
    private int day;
    private int year;

    /**
     * Default constructor initializing date to 01/01/2010.
     */
    public Date() {
        this.month = 1;
        this.day   = 1;
        this.year  = 2010;
    }

    /**
     * Constructs a Date object from a given date string.
     * @param dateInput The date string in "MM/DD/YYYY" format.
     * @throws FormatMismatchException If the format or values are invalid.
     */
    public Date(String dateInput) throws FormatMismatchException {
        
        if (!dateInput.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
            throw new FormatMismatchException("Error: Date must be in MM/DD/YYYY format.");
        }

        String[] components = dateInput.split("/");
        try {
            this.month = Integer.parseInt(components[0]);
            this.day   = Integer.parseInt(components[1]);
            this.year  = Integer.parseInt(components[2]);
        } catch (NumberFormatException e) {
            throw new FormatMismatchException("Error: Date must contain only numeric values.");
        }

        // Validate month range
        if (month < 1 || month > 12) {
            throw new FormatMismatchException("Invalid month (expected range: 1-12): " + month);
        }
        // Validate day range
        if (day < 1 || day > 31) {
            throw new FormatMismatchException("Invalid day (expected range: 1-31): " + day);
        }
        // Validate year range
        if (year < 2010 || year > 2030) {
            throw new FormatMismatchException("Invalid year (expected range: 2010-2030): " + year);
        }
    }

    /**
     * Gets the month value.
     * @return Month as an integer.
     */
    public int getMonth() { return month; }

    /**
     * Gets the day value.
     * @return Day as an integer.
     */
    public int getDay() { return day; }

    /**
     * Gets the year value.
     * @return Year as an integer.
     */
    public int getYear() { return year; }

    /**
     * Sets a new month value.
     * @param newMonth The month to set.
     */
    public void setMonth(int newMonth) { this.month = newMonth; }

    /**
     * Sets a new day value.
     * @param newDay The day to set.
     */
    public void setDay(int newDay) { this.day = newDay; }

    /**
     * Sets a new year value.
     * @param newYear The year to set.
     */
    public void setYear(int newYear) { this.year = newYear; }

    /**
     * Returns a formatted string representation of the Date.
     * @return The date formatted as "MM/DD/YYYY".
     */
    @Override
    public String toString() {
        return String.format("%02d/%02d/%4d", month, day, year);
    }
}






