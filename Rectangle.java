// File: Rectangle.java
import java.awt.Color;
import java.awt.Graphics;

class Rectangle extends Shape {
    public Rectangle(int x1, int y1, int x2, int y2, Color color, boolean filled) {
        super(x1, y1, x2, y2, color, filled);
    }

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