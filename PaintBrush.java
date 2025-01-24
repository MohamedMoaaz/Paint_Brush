import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A simple paint application that allows users to draw shapes and freehand drawings on a canvas.
 * This class extends `JPanel` and implements `MouseMotionListener` and `MouseListener` to handle
 * user interactions. It supports drawing shapes like lines, ovals, rectangles, triangles, and
 * freehand drawings, as well as an eraser tool and color selection.
 */
public class PaintBrush extends JPanel implements MouseMotionListener, MouseListener {

    @SuppressWarnings("unused")
    private int lastX; // Stores the last mouse coordinates
    @SuppressWarnings("unused")
    private int lastY; // Stores the last mouse coordinates
    private Shape currentShape; // The shape currently being drawn
    private List<Shape> shapes; // List of all shapes drawn on the canvas
    private String selectedShapeType = "Freehand"; // The currently selected shape type
    private Color selectedColor = Color.BLACK; // The currently selected color
    private boolean eraserMode = false; // Whether the eraser tool is active
    private boolean fillMode = false; // Whether shapes should be filled

    /**
     * Constructor for the PaintBrush class.
     * Initializes the canvas, sets up the UI components (buttons for shapes, colors, etc.),
     * and configures mouse listeners for drawing.
     */
    public PaintBrush() {
        shapes = new ArrayList<>();
        setBackground(Color.WHITE);

        // Create buttons for shape selection
        JButton freehandButton = new JButton("Freehand");
        freehandButton.addActionListener(e -> {
            selectedShapeType = "Freehand";
            eraserMode = false;
        });

        JButton lineButton = new JButton("Line");
        lineButton.addActionListener(e -> {
            selectedShapeType = "Line";
            eraserMode = false;
        });

        JButton ovalButton = new JButton("Oval");
        ovalButton.addActionListener(e -> {
            selectedShapeType = "Oval";
            eraserMode = false;
        });

        JButton rectangleButton = new JButton("Rectangle");
        rectangleButton.addActionListener(e -> {
            selectedShapeType = "Rectangle";
            eraserMode = false;
        });

        JButton triangleButton = new JButton("Triangle");
        triangleButton.addActionListener(e -> {
            selectedShapeType = "Triangle";
            eraserMode = false;
        });

        // Create buttons for color selection
        JButton blackButton = new JButton("Black");
        blackButton.addActionListener(e -> {
            selectedColor = Color.BLACK;
            eraserMode = false;
        });

        JButton redButton = new JButton("Red");
        redButton.addActionListener(e -> {
            selectedColor = Color.RED;
            eraserMode = false;
        });

        JButton blueButton = new JButton("Blue");
        blueButton.addActionListener(e -> {
            selectedColor = Color.BLUE;
            eraserMode = false;
        });

        // Create a button to clear all shapes
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            shapes.clear();
            repaint();
        });

        // Create an eraser button
        JButton eraserButton = new JButton("Eraser");
        eraserButton.addActionListener(e -> {
            eraserMode = true;
        });

        // Create a fill button
        JButton fillButton = new JButton("Fill");
        fillButton.addActionListener(e -> {
            fillMode = !fillMode;
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
        buttonPanel.add(fillButton);
        buttonPanel.add(eraserButton);
        buttonPanel.add(clearButton);

        // Add button panel to the main frame
        JFrame frame = new JFrame("Simple Paint Brush");
        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(this, BorderLayout.CENTER);
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        initializeListeners();
    }

    /**
     * Initializes the mouse listeners for the canvas.
     * This method adds the `MouseMotionListener` and `MouseListener` to the panel.
     */
    private void initializeListeners() {
        addMouseMotionListener(this);
        addMouseListener(this);
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
     * Handles mouse drag events for drawing shapes and using the eraser.
     * This method is called when the mouse is dragged across the canvas.
     *
     * @param e The MouseEvent object containing details about the event.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (eraserMode) {
            if (currentShape == null) {
                currentShape = new Eraser();
            }
            ((Eraser) currentShape).addPoint(e.getX(), e.getY());
            repaint();
        } else {
            if (selectedShapeType.equals("Freehand")) {
                if (currentShape == null) {
                    currentShape = new Freehand(selectedColor);
                }
                ((Freehand) currentShape).addPoint(e.getX(), e.getY());
                repaint();
            } else {
                if (currentShape == null) {
                    return;
                }
                currentShape.x2 = e.getX();
                currentShape.y2 = e.getY();
                repaint();
            }
        }
    }

    /**
     * Handles mouse move events.
     * This method is called when the mouse is moved across the canvas.
     *
     * @param e The MouseEvent object containing details about the event.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        lastX = e.getX();
        lastY = e.getY();
    }

    /**
     * Handles mouse click events.
     * This method is called when the mouse is clicked on the canvas.
     *
     * @param e The MouseEvent object containing details about the event.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        // Not used
    }

    /**
     * Handles mouse press events.
     * This method is called when the mouse button is pressed on the canvas.
     *
     * @param e The MouseEvent object containing details about the event.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (!eraserMode) {
            startShape(e.getX(), e.getY());
        }
    }

    /**
     * Handles mouse release events.
     * This method is called when the mouse button is released on the canvas.
     *
     * @param e The MouseEvent object containing details about the event.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (!eraserMode) {
            finalizeShape();
        } else {
            if (currentShape != null) {
                shapes.add(currentShape);
                currentShape = null;
            }
        }
    }

    /**
     * Handles mouse enter events.
     * This method is called when the mouse enters the canvas.
     *
     * @param e The MouseEvent object containing details about the event.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        // Not used
    }

    /**
     * Handles mouse exit events.
     * This method is called when the mouse exits the canvas.
     *
     * @param e The MouseEvent object containing details about the event.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        // Not used
    }

    /**
     * Initializes a new shape based on the selected shape type.
     * This method is called when the mouse is pressed to start drawing a shape.
     *
     * @param x The x-coordinate of the starting point.
     * @param y The y-coordinate of the starting point.
     */
    public void startShape(int x, int y) {
        switch (selectedShapeType) {
            case "Oval" -> currentShape = new Oval(x, y, x, y, selectedColor, fillMode);
            case "Rectangle" -> currentShape = new Rectangle(x, y, x, y, selectedColor, fillMode);
            case "Triangle" -> currentShape = new Triangle(x, y, x, y, selectedColor, fillMode);
            case "Line" -> currentShape = new Shape(x, y, x, y, selectedColor, false) {
                    @Override
                    public void draw(Graphics g) {
                        g.setColor(color);
                        g.drawLine(x1, y1, x2, y2);
                    }
                };
            default -> {
            }
        }
    }

    /**
     * Finalizes the current shape and adds it to the list of shapes.
     * This method is called when the mouse is released after drawing a shape.
     */
    public void finalizeShape() {
        if (currentShape != null) {
            shapes.add(currentShape);
            currentShape = null;
        }
    }

    /**
     * The main method to launch the PaintBrush application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        @SuppressWarnings("unused")
        PaintBrush paintBrush = new PaintBrush();
    }
}