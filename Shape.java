// File: Shape.java
import java.awt.Color;
import java.awt.Graphics;

// Abstract class for shapes
abstract class Shape {
    protected int x1, y1, x2, y2;
    protected Color color; // Color of the shape
    protected boolean filled; // Whether the shape is filled

    public Shape(int x1, int y1, int x2, int y2, Color color, boolean filled) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.filled = filled;
    }

    public abstract void draw(Graphics g);
}
