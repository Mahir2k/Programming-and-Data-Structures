/**
 * Represents a contact with a name, phone number, and email address.
 * Implements Comparable for sorting contacts by name.
 */
public class Contact implements Comparable<Contact> {
    private String name;
    private String phone;
    private String email;

    /**
     * Constructs a Contact object with the given name, phone, and email.
     *
     * @param name  The name of the contact.
     * @param phone The phone number in the format "ddd-ddd-dddd".
     * @param email The email address in a valid format (word@word.(com|org|net|edu)).
     * @throws FormatMismatchException If the phone or email format is invalid.
     */
    public Contact(String name, String phone, String email) throws FormatMismatchException {
        this.name = name;
        setPhone(phone);
        setEmail(email);
    }

    /**
     * Gets the name of the contact.
     *
     * @return The contact's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the phone number of the contact.
     *
     * @return The contact's phone number.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Gets the email address of the contact.
     *
     * @return The contact's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the phone number of the contact.
     *
     * @param phone The new phone number in the format "ddd-ddd-dddd".
     * @throws FormatMismatchException If the phone format is invalid.
     */
    public void setPhone(String phone) throws FormatMismatchException {
        if (!phone.matches("\\d{3}-\\d{3}-\\d{4}")) {
            throw new FormatMismatchException("Invalid phone format (ddd-ddd-dddd): " + phone);
        }
        this.phone = phone;
    }

    /**
     * Sets the email address of the contact.
     *
     * @param email The new email address.
     * @throws FormatMismatchException If the email format is invalid.
     */
    public void setEmail(String email) throws FormatMismatchException {
        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(com|org|net|edu)$")) { //used boldsign.com to understand this
            throw new FormatMismatchException("Invalid email format (word@word.(com|org|net|edu)): " + email);
        }
        this.email = email;
    }

    /**
     * Compares this contact with another contact based on name.
     *
     * @param other The other contact to compare to.
     * @return A negative number if this contact's name is lexicographically
     *         smaller, 0 if they are equal, or a positive number if greater.
     */
    @Override
    public int compareTo(Contact other) {
        return this.name.compareTo(other.name);
    }

    /**
     * Checks if two contacts are equal based on their name.
     *
     * @param o The object to compare.
     * @return True if the object is a Contact and has the same name, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Contact) {
            Contact c = (Contact) o;
            return this.name.equals(c.name);
        }
        return false;
    }

    /**
     * Returns a formatted string representation of the contact.
     *
     * @return A string containing the contact's name, phone, and email formatted in columns.
     */
    @Override
    public String toString() {
        return String.format("%-24s %-15s %-20s", name, phone, email);
    }
}
