import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
// •⁠  ⁠*Description*: Entry point of the application.
public class PaintApp {
    // •⁠  ⁠*Description*: Entry point of the application.
    // •⁠  ⁠*Parameters*: args - the command-line arguments
    // •⁠  ⁠*Return*: void
    public static void main(String[] args) {
        initialize();
    }
    // •⁠  ⁠*Description*: Sets up the frame and panel.
    // •⁠  ⁠*Return*: void
    private static void initialize() {
        JFrame frame = new JFrame("Paint App");
        PaintCanvas canvas = new PaintCanvas(800, 600);
        ToolBar toolbar = new ToolBar();
        toolbar.selectColor(Color.BLACK);
        toolbar.selectShape(new Oval(0, 0, 0));
        toolbar.selectSize(1);
        frame.add(canvas, BorderLayout.CENTER);
        frame.add(toolbar, BorderLayout.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}