// File: Oval.java
import java.awt.Color;
import java.awt.Graphics;

class Oval extends Shape {
    public Oval(int x1, int y1, int x2, int y2, Color color, boolean filled) {
        super(x1, y1, x2, y2, color, filled);
    }

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