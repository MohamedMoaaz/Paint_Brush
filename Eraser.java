import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 * A class representing an eraser tool.
 * This class extends the abstract `Shape` class and is used to simulate erasing by drawing
 * white squares over existing shapes. The eraser collects points as it is dragged across
 * the canvas and draws white squares at those points to "erase" content.
 */
public class Eraser extends Shape {
    private final ArrayList<Point> points; // Store points for eraser strokes

    /**
     * Constructor for the Eraser class.
     * Initializes the eraser with a default white color and an empty list of points.
     */
    public Eraser() {
        super(0, 0, 0, 0, Color.WHITE, false); // Use white color for erasing
        points = new ArrayList<>();
    }

    /**
     * Adds a point to the list of eraser strokes.
     * This method is called when the eraser is dragged across the canvas.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public void addPoint(int x, int y) {
        points.add(new Point(x, y));
    }

    /**
     * Draws the eraser strokes on the specified graphics context.
     * This method draws white squares at each point in the `points` list to simulate erasing.
     *
     * @param g The Graphics object used to draw the eraser strokes.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(color); // Set color to white
        int eraserSize = 20; // Size of the eraser square
        for (Point p : points) {
            g.fillRect(p.x - eraserSize / 2, p.y - eraserSize / 2, eraserSize, eraserSize);
        }
    }
}