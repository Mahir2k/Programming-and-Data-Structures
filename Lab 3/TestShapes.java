/**
 * A test class for demonstrating various Shape objects and their operations.
 */
public class TestShapes {
    /**
     * The main method that creates, manipulates, and displays an array of Shape objects.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Shape[] shapes = new Shape[8];

        // Create original shapes
        shapes[0] = new Circle("Black", 2.5);
        shapes[1] = new Triangle("Green", 6.0, 6.0, 6.0);
        shapes[2] = new Rectangle("Red", 5.0, 3.0);
        shapes[3] = new Pentagon("Yellow", 7.0);

        // Clone shapes
        shapes[4] = (Shape) (shapes[0].clone()); // Circle
        shapes[5] = (Shape) (shapes[1].clone()); // Triangle
        shapes[6] = (Shape) (shapes[2].clone()); // Rectangle
        shapes[7] = (Shape) (shapes[3].clone()); // Pentagon

        // Modify cloned shapes
        shapes[4].scale(2.0);
        shapes[5].setColor("Orange");
        ((Rectangle)shapes[6]).setLength(10.0);
        ((Pentagon)shapes[7]).setSide(4.0);
        
        // Display original list
        System.out.println("Original List");
        for (Shape s : shapes) {
            System.out.println(s);
        }

        // Sort shapes by area
        java.util.Arrays.sort(shapes);

        // Display sorted list
        System.out.println("Lists sorted by area");
        for (Shape s : shapes) {
            System.out.println(s);
        }
    }
}
