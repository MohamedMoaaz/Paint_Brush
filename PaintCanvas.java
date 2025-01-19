import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

public class PaintCanvas extends JPanel {
    private ArrayList<Shape> shapes;
    private Color currentColor;
    private int brushSize;
    private Shape currentShape;
    // •⁠  ⁠*Description*: Constructs a new PaintCanvas.
    // •⁠  ⁠*Parameters*: width - the width of the canvas
    // •⁠  ⁠*Parameters*: height - the height of the canvas
    // •⁠  ⁠*Return*: void
    public PaintCanvas(int width, int height) {
        shapes = new ArrayList<>();
        currentColor = Color.BLACK;
        brushSize = 1;
        currentShape = null;
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (currentShape != null) {
                    shapes.add(currentShape);
                    repaint();
                }
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (currentShape != null) {
                    int x = e.getX();
                    int y = e.getY();
                    currentShape = new Oval(x, y, brushSize);
                    repaint();
                }
            }
        });
    }
    // •⁠  ⁠*Description*: Sets the current color of the brush.
    // •⁠  ⁠*Parameters*: color - the color to set
    // •⁠  ⁠*Return*: void
    public void setColor(Color color) {
        currentColor = color;
    }
    // •⁠  ⁠*Description*: Sets the size of the brush.
    // •⁠  ⁠*Parameters*: size - the size to set
    // •⁠  ⁠*Return*: void
    public void setBrushSize(int size) {
        brushSize = size;
    }
    // •⁠  ⁠*Description*: Sets the current shape to draw.
    // •⁠  ⁠*Parameters*: shape - the shape to set
    // •⁠  ⁠*Return*: void
    public void setShape(Shape shape) {
        currentShape = shape;
    }
    // •⁠  ⁠*Description*: Paints the canvas.
    // •⁠  ⁠*Parameters*: g - the graphics context to paint
    // •⁠  ⁠*Return*: void
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape : shapes) {
            g.setColor(currentColor);
            shape.draw(g);
        }
        if (currentShape != null) {
            g.setColor(currentColor);
            currentShape.draw(g);
        }
    }
}