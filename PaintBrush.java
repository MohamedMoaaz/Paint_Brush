import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A simple paint application that allows users to draw shapes and freehand
 * drawings on a canvas. This class extends `JPanel` and uses anonymous inner
 * classes to handle mouse events.
 */
public class PaintBrush extends JPanel {

    private Shape currentShape; // The shape currently being drawn
    private ArrayList<Shape> shapes; // List of all shapes drawn on the canvas
    private String selectedShapeType = "Freehand"; // The currently selected shape type
    private Color selectedColor = Color.BLACK; // The currently selected color
    private boolean fillMode = false; // Whether shapes should be filled

    /**
     * Constructor for the PaintBrush class.
     * Initializes the canvas and sets up the UI components (buttons for shapes, colors, etc.).
     */
    public PaintBrush() {
        shapes = new ArrayList<>();
        setBackground(Color.WHITE);

        // Create buttons for shape selection
        JButton freehandButton = new JButton("Freehand");
        freehandButton.addActionListener(e -> {
            selectedShapeType = "Freehand";
        });

        JButton lineButton = new JButton("Line");
        lineButton.addActionListener(e -> {
            selectedShapeType = "Line";
        });

        JButton ovalButton = new JButton("Oval");
        ovalButton.addActionListener(e -> {
            selectedShapeType = "Oval";
        });

        JButton rectangleButton = new JButton("Rectangle");
        rectangleButton.addActionListener(e -> {
            selectedShapeType = "Rectangle";
        });

        JButton triangleButton = new JButton("Triangle");
        triangleButton.addActionListener(e -> {
            selectedShapeType = "Triangle";
        });

        // Create buttons for color selection
        JButton blackButton = new JButton("Black");
        blackButton.addActionListener(e -> {
            selectedColor = Color.BLACK;
        });

        JButton redButton = new JButton("Red");
        redButton.addActionListener(e -> {
            selectedColor = Color.RED;
        });

        JButton blueButton = new JButton("Blue");
        blueButton.addActionListener(e -> {
            selectedColor = Color.BLUE;
        });

        JButton greeButton = new JButton("Green");
        greeButton.addActionListener(e -> {
            selectedColor = Color.GREEN;
        });

        // Create a checkbox for fill mode
        JCheckBox fillCheckBox = new JCheckBox("Fill");
        fillCheckBox.addActionListener(e -> {
            // Update fillMode based on checkbox state
            fillMode = fillCheckBox.isSelected(); 
        });

        // Create an eraser button
        JButton eraserButton = new JButton("Eraser");
        eraserButton.addActionListener(e -> {
            selectedShapeType = "Eraser";
        });

        // Create a button to clear all shapes
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            shapes.clear();
            repaint();
        });

        // Add buttons to a panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(freehandButton);
        buttonPanel.add(lineButton);
        buttonPanel.add(ovalButton);
        buttonPanel.add(rectangleButton);
        buttonPanel.add(triangleButton);
        buttonPanel.add(blackButton);
        buttonPanel.add(redButton);
        buttonPanel.add(blueButton);
        buttonPanel.add(greeButton);
        buttonPanel.add(eraserButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(fillCheckBox);

        // Add button panel to the main frame
        add(buttonPanel, BorderLayout.NORTH);

        // Add mouse listeners using anonymous inner classes
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startShape(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (currentShape != null) {
                    shapes.add(currentShape);
                    currentShape = null;
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                switch (selectedShapeType) {
                    case "Eraser" -> {
                        if (currentShape == null) {
                            currentShape = new Eraser();
                        }
                        ((Eraser) currentShape).addPoint(e.getX(), e.getY());
                    }
                    case "Freehand" -> {
                        if (currentShape == null) {
                            currentShape = new Freehand(selectedColor);
                        }
                        ((Freehand) currentShape).addPoint(e.getX(), e.getY());
                    }
                    default -> {
                        if (currentShape == null) {
                            return;
                        }
                        currentShape.x2 = e.getX();
                        currentShape.y2 = e.getY();
                    }
                }
                repaint();
            }
        });
    }

    /**
     * Overrides the `paintComponent` method to render all shapes on the canvas.
     * This method is called automatically whenever the panel needs to be repainted.
     *
     * @param g The Graphics object used for drawing.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape : shapes) {
            shape.draw(g);
        }
        if (currentShape != null) {
            currentShape.draw(g);
        }
    }

    /**
     * Initializes a new shape based on the selected shape type.
     * This method is called when the mouse is pressed to start drawing a shape.
     *
     * @param x The x-coordinate of the starting point.
     * @param y The y-coordinate of the starting point.
     */
    void startShape(int x, int y) {
        switch (selectedShapeType) {
            case "Oval" -> currentShape = new Oval(x, y, x, y, selectedColor, fillMode);
            case "Rectangle" -> currentShape = new Rectangle(x, y, x, y, selectedColor, fillMode);
            case "Triangle" -> currentShape = new Triangle(x, y, x, y, selectedColor, fillMode);
            case "Line" -> currentShape = new Line(x, y, x, y, selectedColor);
            default -> {
            }
        }
    }

    /**
     * The main method to launch the PaintBrush application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Create the PaintBrush panel
        PaintBrush paintBrush = new PaintBrush();

        // Create the main frame and customize it
        JFrame frame = new JFrame("Simple Paint Brush");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1050, 600); // Set the size of the frame
        frame.setLayout(new BorderLayout()); // Use BorderLayout for the frame

        // Add the PaintBrush panel to the center of the frame
        frame.add(paintBrush, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }
}