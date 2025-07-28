/**
 * Represents a Pentagon shape, extending the Shape class.
 */
public class Pentagon extends Shape {
    private double side;

    /**
     * Default constructor. Creates a Pentagon with default color and side length 1.0.
     */
    public Pentagon() {
        super();
        side = 1.0;
    }

    /**
     * Parameterized constructor.
     * @param c The color of the Pentagon.
     * @param s The side length of the Pentagon.
     */
    public Pentagon(String c, double s) {
        super(c);
        side = s;
    }

    /**
     * Gets the side length of the Pentagon.
     * @return The side length.
     */
    public double getSide() { return side; }

    /**
     * Sets the side length of the Pentagon.
     * @param s The new side length.
     */
    public void setSide(double s) { side = s; }

    /**
     * Returns a string representation of the Pentagon.
     * @return A formatted string containing Pentagon details.
     */
    @Override
    public String toString() {
        return String.format("%-10s\t%-10s\t%-5.2f\t%-5.2f\t%-5.2f",
                    "Pentagon", super.toString(), side, getArea(), getPerimeter());
    }

    /**
     * Creates and returns a clone of this Pentagon.
     * @return A new Pentagon object with the same color and side length.
     */
    @Override
    public Object clone() {
        return new Pentagon(getColor(), side);
    }

    /**
     * Scales the Pentagon by a given factor.
     * @param f The scaling factor.
     */
    @Override
    public void scale(double f) {
        side *= f;
    }

    /**
     * Calculates and returns the area of the Pentagon.
     * @return The area of the Pentagon.
     */
    @Override
    public double getArea() {
        double p = getPerimeter();
        return 0.25 * Math.sqrt(5 * (5 + 2 * (Math.sqrt(5)))) * side * side;
    }

    /**
     * Calculates and returns the perimeter of the Pentagon.
     * @return The perimeter of the Pentagon.
     */
    @Override
    public double getPerimeter() {
        return 5 * side;
    }
}
