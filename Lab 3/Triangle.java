/**
 * Represents a Triangle shape, extending the Shape class.
 */
public class Triangle extends Shape {
    private double side1, side2, side3;

    /**
     * Default constructor. Creates a Triangle with default color and all sides of length 1.0.
     */
    public Triangle() {
        super();
        side1 = side2 = side3 = 1.0;
    }

    /**
     * Parameterized constructor.
     * @param c The color of the Triangle.
     * @param s1 The length of the first side.
     * @param s2 The length of the second side.
     * @param s3 The length of the third side.
     */
    public Triangle(String c, double s1, double s2, double s3) {
        super(c);
        side1 = s1; side2 = s2; side3 = s3;
    }

    /**
     * Gets the length of the first side.
     * @return The length of side1.
     */
    public double getSide1() { return side1; }

    /**
     * Gets the length of the second side.
     * @return The length of side2.
     */
    public double getSide2() { return side2; }

    /**
     * Gets the length of the third side.
     * @return The length of side3.
     */
    public double getSide3() { return side3; }

    /**
     * Sets the length of the first side.
     * @param s The new length for side1.
     */
    public void setSide1(double s) { side1 = s; }

    /**
     * Sets the length of the second side.
     * @param s The new length for side2.
     */
    public void setSide2(double s) { side2 = s; }

    /**
     * Sets the length of the third side.
     * @param s The new length for side3.
     */
    public void setSide3(double s) { side3 = s; }

    /**
     * Returns a string representation of the Triangle.
     * @return A formatted string containing Triangle details.
     */
    @Override
    public String toString() {
        return String.format("%-10s\t%s\t%-5.2f\t%-5.2f\t%-5.2f\t%-5.2f\t%-5.2f",
                    "Triangle", super.toString(), side1, side2, side3, getArea(), getPerimeter());
    }

    /**
     * Creates and returns a clone of this Triangle.
     * @return A new Triangle object with the same color and side lengths.
     */
    @Override
    public Object clone() {
        return new Triangle(getColor(), side1, side2, side3);
    }

    /**
     * Scales the Triangle by a given factor.
     * @param f The scaling factor.
     */
    @Override
    public void scale(double f) {
        side1 *= f;
        side2 *= f;
        side3 *= f;
    }

    /**
     * Calculates and returns the area of the Triangle using Heron's formula.
     * @return The area of the Triangle.
     */
    @Override
    public double getArea() {
        double p = getPerimeter() / 2;
        return Math.sqrt(p * (p-side1) * (p-side2) * (p-side3));
    }

    /**
     * Calculates and returns the perimeter of the Triangle.
     * @return The perimeter of the Triangle.
     */
    @Override
    public double getPerimeter() {
        return side1 + side2 + side3;
    }
}
