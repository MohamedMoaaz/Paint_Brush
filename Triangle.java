// File: Triangle.java
import java.awt.Color;
import java.awt.Graphics;

/**
 * A class representing a triangle shape.
 * This class extends the abstract `Shape` class and implements the `draw` method
 * to render a triangle on a graphics context. The triangle can be either filled or outlined,
 * depending on the `filled` property.
 */
public class Triangle extends Shape {

    /**
     * Constructor for the Triangle class.
     *
     * @param x1     The x-coordinate of the first point of the triangle's bounding box.
     * @param y1     The y-coordinate of the first point of the triangle's bounding box.
     * @param x2     The x-coordinate of the second point of the triangle's bounding box.
     * @param y2     The y-coordinate of the second point of the triangle's bounding box.
     * @param color  The color of the triangle.
     * @param filled Whether the triangle is filled (true) or outlined (false).
     */
    public Triangle(int x1, int y1, int x2, int y2, Color color, boolean filled) {
        super(x1, y1, x2, y2, color, filled);
    }

    /**
     * Draws the triangle on the specified graphics context.
     * The triangle is defined by three points: the base points (x1, y2) and (x2, y2),
     * and the apex point at the midpoint of the base's x-coordinates and the top y-coordinate (y1).
     * If the triangle is filled, it uses the `fillPolygon` method; otherwise, it uses the `drawPolygon` method.
     *
     * @param g The Graphics object used to draw the triangle.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        int[] xPoints = {x1, x2, (x1 + x2) / 2}; // Define x-coordinates of the triangle's vertices
        int[] yPoints = {y2, y2, y1};           // Define y-coordinates of the triangle's vertices
        if (filled) {
            g.fillPolygon(xPoints, yPoints, 3); // Draw a filled triangle
        } else {
            g.drawPolygon(xPoints, yPoints, 3); // Draw an outlined triangle
        }
    }
}