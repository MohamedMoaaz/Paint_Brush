import java.awt.Graphics;

public class Oval implements Shape {
    private int x, y, radius;
    public Oval(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    @Override
    public void draw(Graphics g) {
        g.drawOval(x, y, radius, radius);
    }
}