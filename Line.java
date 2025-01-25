import java.awt.Color;
import java.awt.Graphics;

/**
 * The `Line` class represents a line shape that can be drawn on a canvas.
 * It extends the `Shape` abstract class and provides an implementation for the `draw` method.
 */
public class Line extends Shape {

    /**
     * Constructs a new `Line` object with the specified start and end coordinates, and color.
     *
     * @param x     The x-coordinate of the starting point of the line.
     * @param y     The y-coordinate of the starting point of the line.
     * @param x2    The x-coordinate of the ending point of the line.
     * @param y2    The y-coordinate of the ending point of the line.
     * @param color The color of the line.
     */
    public Line(int x, int y, int x2, int y2, Color color) {
        super(x, y, x2, y2, color, false); // Lines cannot be filled, so `filled` is set to false
    }

    /**
     * Draws the line on the canvas using the specified `Graphics` object.
     * This method overrides the `draw` method in the `Shape` class.
     *
     * @param g The `Graphics` object used to draw the line.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(color); // Set the color of the line
        g.drawLine(x1, y1, x2, y2); // Draw the line from (x1, y1) to (x2, y2)
    }
}