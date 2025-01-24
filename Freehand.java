import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a freehand drawing tool.
 * This class extends the abstract `Shape` class and is used to draw freehand lines on a canvas.
 * It collects points as the user drags the mouse and connects them with lines to create a freehand drawing.
 */
public class Freehand extends Shape {
    private final List<Point> points; // List of points to store the freehand drawing path

    /**
     * Constructor for the Freehand class.
     * Initializes the freehand drawing with a specified color and an empty list of points.
     *
     * @param color The color of the freehand drawing.
     */
    public Freehand(Color color) {
        super(0, 0, 0, 0, color, false); // Call the superclass constructor
        points = new ArrayList<>(); // Initialize the list of points
    }

    /**
     * Adds a point to the freehand drawing path.
     * This method is called as the user drags the mouse to draw.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public void addPoint(int x, int y) {
        points.add(new Point(x, y)); // Add the new point to the list
    }

    /**
     * Draws the freehand path on the specified graphics context.
     * This method connects the points in the `points` list with lines to create the freehand drawing.
     *
     * @param g The Graphics object used to draw the freehand path.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(color); // Set the drawing color
        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i); // Get the current point
            Point p2 = points.get(i + 1); // Get the next point
            g.drawLine(p1.x, p1.y, p2.x, p2.y); // Draw a line between the two points
        }
    }
}