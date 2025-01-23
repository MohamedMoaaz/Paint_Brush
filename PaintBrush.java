
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


// PaintBrush class
public class PaintBrush extends JPanel implements MouseMotionListener, MouseListener {

    private int lastX, lastY;
    private Shape currentShape;
    private List<Shape> shapes;
    private String selectedShapeType = "Freehand";
    private Color selectedColor = Color.BLACK;
    private boolean eraserMode = false;
    private boolean fillMode = false;

    public PaintBrush() {
        shapes = new ArrayList<>();
        setBackground(Color.WHITE);
        addMouseMotionListener(this);
        addMouseListener(this);

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
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

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

    @Override
public void mouseDragged(MouseEvent e) {
    if (eraserMode) {
        // Draw a white rectangle at the mouse position
        int eraserSize = 20;
        Graphics g = getGraphics();
        g.setColor(Color.WHITE); // Set color to white
        g.fillRect(e.getX() - eraserSize / 2, e.getY() - eraserSize / 2, eraserSize, eraserSize);
        g.dispose();
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


    @Override
    public void mouseMoved(MouseEvent e) {
        lastX = e.getX();
        lastY = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Not used
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!eraserMode) {
            startShape(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!eraserMode) {
            finalizeShape();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Not used
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Not used
    }

    public void startShape(int x, int y) {
        if (selectedShapeType.equals("Oval")) {
            currentShape = new Oval(x, y, x, y, selectedColor, fillMode);
        } else if (selectedShapeType.equals("Rectangle")) {
            currentShape = new Rectangle(x, y, x, y, selectedColor, fillMode);
        } else if (selectedShapeType.equals("Triangle")) {
            currentShape = new Triangle(x, y, x, y, selectedColor, fillMode);
        } else if (selectedShapeType.equals("Line")) {
            currentShape = new Shape(x, y, x, y, selectedColor, false) {
                @Override
                public void draw(Graphics g) {
                    g.setColor(color);
                    g.drawLine(x1, y1, x2, y2);
                }
            };
        }
    }

    public void finalizeShape() {
        if (currentShape != null) {
            shapes.add(currentShape);
            currentShape = null;
        }
    }

    public static void main(String[] args) {
        new PaintBrush();
    }
}
