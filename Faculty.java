/**
 * Class Faculty that models a faculty member entity, extending the Employee class.
 * Represents a faculty member with additional attributes such as rank.
 * 
 * @author: Houria Oudghiri
 * @version: 01
 */
public class Faculty extends Employee {
    // Data member
    private String rank;

    /**
     * Default constructor.
     * Initializes the faculty member with default Employee attributes and a default rank of "Assistant Professor".
     */
    public Faculty() {
        super(); // Calls the default constructor of the Employee class
        rank = "Assistant Professor";
    }

    /**
     * Constructor with parameters.
     * Initializes the faculty member with specific attributes for Employee and a rank.
     * 
     * @param id Initial value for the faculty member's ID
     * @param name Initial value for the faculty member's name
     * @param address Initial value for the faculty member's address
     * @param phone Initial value for the faculty member's phone number
     * @param email Initial value for the faculty member's email address
     * @param position Initial value for the faculty member's position
     * @param salary Initial value for the faculty member's salary
     * @param rank Initial value for the faculty member's rank
     */
    public Faculty(int id, String name, String address, String phone, String email, String position, double salary, String rank) {
        super(id, name, address, phone, email, position, salary); // Calls the parameterized constructor of Employee
        this.rank = rank;
    }

    /**
     * Accessor (getter) for the faculty member's rank.
     * 
     * @return The faculty member's current rank as a string.
     */
    public String getRank() { 
        return rank;
    }

    /**
     * Mutator (setter) for the faculty member's rank.
     * 
     * @param r The new rank to set for the faculty member.
     */
    public void setRank(String r) { 
        rank = r;
    }

    /**
     * Returns a string representation of the Faculty object.
     * Includes all attributes from the Employee class and the faculty member's rank.
     * 
     * @return A formatted string containing all attributes of the faculty member.
     */
    @Override
    public String toString() {
        return super.toString() + String.format("Rank: %s\n", rank);
    }
}
