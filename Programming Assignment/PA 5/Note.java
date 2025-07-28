/**
 * The Note class represents a note with a title, date, and description.
 * It implements Comparable<Note> to allow sorting by title.
 */
public class Note implements Comparable<Note> {
    private String title;
    private Date date;
    private String description;

    /**
     * Constructs a new Note object.
     *
     * @param dateStr The date of the note in "mm/dd/yyyy" format.
     * @param t       The title of the note.
     * @param desc    The description of the note.
     * @throws FormatMismatchException If the date format is invalid.
     */
    public Note(String dateStr, String t, String desc) throws FormatMismatchException {
        date = new Date(dateStr);
        title = t;
        description = desc;
    }

    /**
     * Gets the title of the note.
     *
     * @return The title of the note.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the date of the note.
     *
     * @return The date of the note.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Gets the description of the note.
     *
     * @return The description of the note.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets a new date for the note.
     *
     * @param d The new date in "mm/dd/yyyy" format.
     * @throws FormatMismatchException If the date format is invalid.
     */
    public void setDate(String d) throws FormatMismatchException {
        date = new Date(d);
    }

    /**
     * Sets a new description for the note.
     *
     * @param desc The new description.
     */
    public void setDescription(String desc) {
        description = desc;
    }

    /**
     * Compares this note to another note based on title.
     *
     * @param other The other note to compare to.
     * @return A negative integer, zero, or a positive integer as this note's title
     *         is less than, equal to, or greater than the other note's title.
     */
    @Override
    public int compareTo(Note other) {
        return this.title.compareTo(other.title);
    }

    /**
     * Checks if this note is equal to another object.
     * Two notes are considered equal if they have the same title.
     *
     * @param o The object to compare with.
     * @return true if the titles are the same, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Note) {
            Note n = (Note) o;
            return this.title.equals(n.title);
        }
        return false;
    }

    /**
     * Returns a formatted string representation of the note.
     *
     * @return A string containing the title, date, and description formatted for display.
     */
    @Override
    public String toString() {
        return String.format("%-24s %-12s %-20s", title, date.toString(), description);
    }
}
