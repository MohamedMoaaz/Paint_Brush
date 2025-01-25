// File: Shape.java
import java.awt.Color;
import java.awt.Graphics;

/**
 * Abstract class representing a geometric shape.
 * This class serves as the base class for specific shapes like Oval, Rectangle, Triangle, etc.
 * It defines common properties such as position, color, and fill status, and requires
 * subclasses to implement the `draw` method to render the shape.
 */
public abstract class Shape {
    protected int x1, y1, x2, y2; // Coordinates of the shape's bounding box
    protected Color color; // Color of the shape
    protected boolean filled; // Whether the shape is filled or outlined

    /**
     * Constructor for the Shape class.
     *
     * @param x1     The x-coordinate of the first point of the shape.
     * @param y1     The y-coordinate of the first point of the shape.
     * @param x2     The x-coordinate of the second point of the shape.
     * @param y2     The y-coordinate of the second point of the shape.
     * @param color  The color of the shape.
     * @param filled Whether the shape is filled (true) or outlined (false).
     */
    public Shape(int x1, int y1, int x2, int y2, Color color, boolean filled) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.filled = filled;
    }

    /**
     * Abstract method to draw the shape on a graphics context.
     * Subclasses must implement this method to define how the shape is rendered.
     *
     * @param g The Graphics object used to draw the shape.
     */
    public abstract void draw(Graphics g);
}