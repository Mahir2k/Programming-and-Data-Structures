/**
 * Represents a Rectangle shape, extending the Shape class.
 */
public class Rectangle extends Shape {
    private double length, width;

    /**
     * Default constructor. Creates a Rectangle with default color and dimensions 1.0 x 1.0.
     */
    public Rectangle() {
        super();
        length = 1.0;
        width = 1.0;
    }

    /**
     * Parameterized constructor.
     * @param c The color of the Rectangle.
     * @param l The length of the Rectangle.
     * @param w The width of the Rectangle.
     */
    public Rectangle(String c, double l, double w) {
        super(c);
        length = l;
        width = w;
    }

    /**
     * Gets the length of the Rectangle.
     * @return The length.
     */
    public double getLength() { return length; }

    /**
     * Gets the width of the Rectangle.
     * @return The width.
     */
    public double getWidth() { return width; }

    /**
     * Sets the length of the Rectangle.
     * @param l The new length.
     */
    public void setLength(double l) { length = l; }

    /**
     * Sets the width of the Rectangle.
     * @param w The new width.
     */
    public void setWidth(double w) { width = w; }

    /**
     * Returns a string representation of the Rectangle.
     * @return A formatted string containing Rectangle details.
     */
    @Override
    public String toString() {
        return String.format("%-10s\t%s\t%-10.2f\t%-10.2f\t%-10.2f\t%-10.2f",
                    "Rectangle", super.toString(), length, width, getArea(), getPerimeter());
    }

    /**
     * Creates and returns a clone of this Rectangle.
     * @return A new Rectangle object with the same color, length, and width.
     */
    @Override
    public Object clone() {
        return new Rectangle(getColor(), length, width);
    }

    /**
     * Scales the Rectangle by a given factor.
     * @param f The scaling factor.
     */
    @Override
    public void scale(double f) {
        length *= f;
        width *= f;
    }

    /**
     * Calculates and returns the area of the Rectangle.
     * @return The area of the Rectangle.
     */
    @Override
    public double getArea() {
        return length * width;
    }

    /**
     * Calculates and returns the perimeter of the Rectangle.
     * @return The perimeter of the Rectangle.
     */
    @Override
    public double getPerimeter() {
        return (2 * (length + width));
    }
}
