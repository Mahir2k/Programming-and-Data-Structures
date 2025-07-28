/**
 * Abstract class representing a geometric shape.
 * Implements Comparable for sorting, Cloneable for object copying, and Scalable for resizing.
 */
public abstract class Shape implements Comparable<Shape>, Cloneable, Scalable {
    private String color;

    /**
     * Default constructor. Initializes the shape with white color.
     */
    protected Shape() {
        color = "White";
    }

    /**
     * Parameterized constructor.
     * @param color The color of the shape
     */
    protected Shape(String color) {
        this.color = color;
    }

    /**
     * Gets the color of the shape.
     * @return The color as a String
     */
    public String getColor() { return color; }

    /**
     * Sets the color of the shape.
     * @param c The new color
     */
    public void setColor(String c) { color = c; }

    /**
     * Returns a string representation of the shape's color.
     * @return Formatted string of the shape's color
     */
    @Override
    public String toString() {
        return String.format("%-10s", color);
    }

    /**
     * Calculates the area of the shape.
     * @return The area as a double
     */
    public abstract double getArea();

    /**
     * Calculates the perimeter of the shape.
     * @return The perimeter as a double
     */
    public abstract double getPerimeter();

    /**
     * Creates and returns a copy of this shape.
     * @return A clone of this instance
     */
    @Override
    public abstract Object clone();

    /**
     * Scales the shape by a given factor.
     * @param f The scaling factor
     */
    public abstract void scale(double f);

    /**
     * Compares this shape with another shape based on their areas.
     * @param s The shape to be compared
     * @return -1, 0, or 1 if this shape's area is less than, equal to, or greater than the other shape's area
     */
    @Override
    public int compareTo(Shape s) {
        double a1 = this.getArea();
        double a2 = s.getArea();
        if (a1 == a2) {
            return 0;
        } else if (a1 > a2) {
            return 1;
        } else {
            return -1;
        }
    }
}
