/**
 * Class Student that models a student entity, extending the Person class.
 * Represents a student with additional attributes such as a major.
 * 
 * @author: Houria Oudghiri
 * @version: 01
 */
public class Student extends Person {
    // Data member
    private String major;

    /**
     * Default constructor.
     * Initializes the student with default Person attributes and an undeclared major.
     */
    public Student() {
        super(); // Calls the default constructor of the Person class
        major = "Undeclared";
    }

    /**
     * Constructor with parameters.
     * Initializes the student with specific attributes for Person and a major.
     * 
     * @param id Initial value for the student's ID
     * @param name Initial value for the student's name
     * @param address Initial value for the student's address
     * @param phone Initial value for the student's phone number
     * @param email Initial value for the student's email address
     * @param major Initial value for the student's major
     */
    public Student(int id, String name, String address, String phone, String email, String major) {
        super(id, name, address, phone, email); // Calls the parameterized constructor of Person
        this.major = major;
    }

    /**
     * Accessor (getter) for the student's major.
     * 
     * @return The student's current major as a string.
     */
    public String getMajor() {
        return major;
    }

    /**
     * Mutator (setter) for the student's major.
     * 
     * @param m The new major to set for the student.
     */
    public void setMajor(String m) {
        major = m;
    }

    /**
     * Returns a string representation of the Student object.
     * Includes all attributes from the Person class and the student's major.
     * 
     * @return A formatted string containing all attributes of the student.
     */
    @Override
    public String toString() {
        return super.toString() + String.format("Major: %s\n", major);
    }
}
