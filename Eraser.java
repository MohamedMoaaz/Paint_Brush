
import java.awt.Color;
import java.awt.Graphics;

// Eraser class
public class Eraser extends Shape {
    public Eraser() {
        super(0, 0, 0, 0, Color.WHITE, false);
    }

    @Override
    public void draw(Graphics g) {
        // No drawing needed for the eraser itself
    }
}