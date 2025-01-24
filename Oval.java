// File: Oval.java
import java.awt.Color;
import java.awt.Graphics;

/**
 * A class representing an oval shape.
 * This class extends the abstract `Shape` class and implements the `draw` method
 * to render an oval on a graphics context. The oval can be either filled or outlined,
 * depending on the `filled` property.
 */
public class Oval extends Shape {

    /**
     * Constructor for the Oval class.
     *
     * @param x1     The x-coordinate of the first point of the oval's bounding box.
     * @param y1     The y-coordinate of the first point of the oval's bounding box.
     * @param x2     The x-coordinate of the second point of the oval's bounding box.
     * @param y2     The y-coordinate of the second point of the oval's bounding box.
     * @param color  The color of the oval.
     * @param filled Whether the oval is filled (true) or outlined (false).
     */
    public Oval(int x1, int y1, int x2, int y2, Color color, boolean filled) {
        super(x1, y1, x2, y2, color, filled);
    }

    /**
     * Draws the oval on the specified graphics context.
     * If the oval is filled, it uses the `fillOval` method; otherwise, it uses the `drawOval` method.
     *
     * @param g The Graphics object used to draw the oval.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if (filled) {
            g.fillOval(Math.min(x1, x2), Math.min(y1, y2),
                       Math.abs(x1 - x2), Math.abs(y1 - y2));
        } else {
            g.drawOval(Math.min(x1, x2), Math.min(y1, y2),
                       Math.abs(x1 - x2), Math.abs(y1 - y2));
        }
    }
}