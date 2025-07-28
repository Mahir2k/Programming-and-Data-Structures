/**
 * Class Employee that models an employee entity, extending the Person class.
 * Represents an employee with additional attributes such as position and salary.
 * 
 * @author: Houria Oudghiri
 * @version: 01
 */
public class Employee extends Person {
    // Data members 
    private String position;
    private double salary;

    /**
     * Default constructor.
     * Initializes the employee with default Person attributes, staff position, and zero salary.
     */
    public Employee() {
        super();
        position = "staff";
        salary = 0.0;
    }

    /**
     * Constructor with parameters.
     * Initializes the employee with specific attributes for Person, position, and salary.
     * 
     * @param id Initial value for the employee's ID
     * @param name Initial value for the employee's name
     * @param address Initial value for the employee's address
     * @param phone Initial value for the employee's phone number
     * @param email Initial value for the employee's email address
     * @param position Initial value for the employee's position
     * @param salary Initial value for the employee's salary
     */
    public Employee(int id, String name, String address, String phone, String email, String position, double salary) {
        super(id, name, address, phone, email);
        this.position = position;
        this.salary = salary;
    }

    /**
     * Accessor for the employee's position.
     * @return The employee's current position as a string.
     */
    public String getPosition() { 
        return position;
    }

    /**
     * Accessor for the employee's salary.
     * @return The employee's current salary as a double.
     */
    public double getSalary() { 
        return salary;
    }

    /**
     * Mutator for the employee's position.
     * @param p The new position to set for the employee.
     */
    public void setPosition(String p) { 
        position = p;
    }

    /**
     * Mutator for the employee's salary.
     * @param s The new salary to set for the employee.
     */
    public void setSalary(double s) { 
        salary = s;
    }

    /**
     * Returns a string representation of the Employee object.
     * Includes all attributes from the Person class, the employee's position, and salary.
     * 
     * @return A formatted string containing all attributes of the employee.
     */
    @Override
    public String toString() {
        return super.toString() + String.format("Position: %s\nSalary: $%.2f\n", position, salary);
    }
}
