/**
 * Represents a Circle shape, extending the Shape class.
 */
public class Circle extends Shape {
    private double side1;

    /**
     * Default constructor. Creates a Circle with default color and radius 1.0.
     */
    public Circle() {
        super();
        side1 = 1.0;
        double pi = Math.PI;
    }

    /**
     * Parameterized constructor.
     * @param c The color of the Circle.
     * @param s1 The radius of the Circle.
     */
    public Circle(String c, double s1) {
        super(c);
        side1 = s1;
    }

    /**
     * Gets the radius of the Circle.
     * @return The radius.
     */
    public double getSide1() { return side1; }

    /**
     * Sets the radius of the Circle.
     * @param s The new radius.
     */
    public void setSide1(double s) { side1 = s; }

    /**
     * Returns a string representation of the Circle.
     * @return A formatted string containing Circle details.
     */
    @Override
    public String toString() {
        return String.format("%-10s\t%-10s\t%-5.2f\t%-5.2f\t%-5.2f",
                    "Circle", super.toString(), side1, getArea(), getPerimeter());
    }

    /**
     * Creates and returns a clone of this Circle.
     * @return A new Circle object with the same color and radius.
     */
    @Override
    public Object clone() {
        return new Circle(getColor(), side1);
    }

    /**
     * Scales the Circle by a given factor.
     * @param f The scaling factor.
     */
    @Override
    public void scale(double f) {
        side1 *= f;
    }

    /**
     * Calculates and returns the area of the Circle.
     * @return The area of the Circle.
     */
    @Override
    public double getArea() {
        return Math.PI * side1 * side1;
    }

    /**
     * Calculates and returns the perimeter of the Circle.
     * @return The perimeter of the Circle.
     */
    @Override
    public double getPerimeter() {
        return 2 * Math.PI * side1;
    }
}
