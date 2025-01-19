import java.awt.Color;
import java.awt.Graphics;
// •⁠  ⁠*Description*: A toolbar for selecting colors, shapes, and brush sizes.
public class ToolBar {
    private Color selectedColor;
    private Shape selectedShape;
    private int selectedSize;
    // •⁠  ⁠*Methods*:
    // •⁠  ⁠*Description*: Selects a color.
    // •⁠  ⁠*Parameters*: color - the color to select
    // •⁠  ⁠*Return*: void
    public void selectColor(Color color) {
        selectedColor = color;
    }
    // •⁠  ⁠*Description*: Selects a shape.
    // •⁠  ⁠*Parameters*: shape - the shape to select
    // •⁠  ⁠*Return*: void
    public void selectShape(Shape shape) {
        selectedShape = shape;
    }
    // •⁠  ⁠*Description*: Selects a brush size.
    // •⁠  ⁠*Parameters*: size - the size to select
    // •⁠  ⁠*Return*: void
    public void selectSize(int size) {
        selectedSize = size;
    }
    // •⁠  ⁠*Description*: Draws the selected shape at the specified location.
    // •⁠  ⁠*Parameters*: x - the x-coordinate of the shape
    // •⁠  ⁠*Parameters*: y - the y-coordinate of the shape
    // •⁠  ⁠*Parameters*: g - the graphics context to draw on
    // •⁠  ⁠*Return*: void
    public void drawShape(int x, int y, Graphics g) {
        if (selectedShape != null) {
            g.setColor(selectedColor);
            selectedShape.draw(g);
        }
    }
}