// File: Rectangle.java
import java.awt.Color;
import java.awt.Graphics;

/**
 * A class representing a rectangle shape.
 * This class extends the abstract `Shape` class and implements the `draw` method
 * to render a rectangle on a graphics context. The rectangle can be either filled or outlined,
 * depending on the `filled` property.
 */
public class Rectangle extends Shape {

    /**
     * Constructor for the Rectangle class.
     *
     * @param x1     The x-coordinate of the first point of the rectangle's bounding box.
     * @param y1     The y-coordinate of the first point of the rectangle's bounding box.
     * @param x2     The x-coordinate of the second point of the rectangle's bounding box.
     * @param y2     The y-coordinate of the second point of the rectangle's bounding box.
     * @param color  The color of the rectangle.
     * @param filled Whether the rectangle is filled (true) or outlined (false).
     */
    public Rectangle(int x1, int y1, int x2, int y2, Color color, boolean filled) {
        super(x1, y1, x2, y2, color, filled);
    }

    /**
     * Draws the rectangle on the specified graphics context.
     * If the rectangle is filled, it uses the `fillRect` method; otherwise, it uses the `drawRect` method.
     *
     * @param g The Graphics object used to draw the rectangle.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if (filled) {
            g.fillRect(Math.min(x1, x2), Math.min(y1, y2),
                       Math.abs(x1 - x2), Math.abs(y1 - y2));
        } else {
            g.drawRect(Math.min(x1, x2), Math.min(y1, y2),
                       Math.abs(x1 - x2), Math.abs(y1 - y2));
        }
    }
}