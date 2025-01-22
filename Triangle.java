// File: Triangle.java
import java.awt.Color;
import java.awt.Graphics;

class Triangle extends Shape {
    public Triangle(int x1, int y1, int x2, int y2, Color color, boolean filled) {
        super(x1, y1, x2, y2, color, filled);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        int[] xPoints = {x1, x2, (x1 + x2) / 2};
        int[] yPoints = {y2, y2, y1};
        if (filled) {
            g.fillPolygon(xPoints, yPoints, 3);
        } else {
            g.drawPolygon(xPoints, yPoints, 3);
        }
    }
}