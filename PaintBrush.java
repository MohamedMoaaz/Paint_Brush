// File: PaintBrush.java

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class PaintBrush extends JPanel implements MouseMotionListener {

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

        JPanel buttonPanel = createButtonPanel();

        JFrame frame = new JFrame("Simple Paint Brush");
        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(this, BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();

        JButton freehandButton = new JButton("Freehand");
        freehandButton.addActionListener(e -> {
            selectedShapeType = "Freehand";
            eraserMode = false;
        });
        buttonPanel.add(freehandButton);

        JButton lineButton = new JButton("Line");
        lineButton.addActionListener(e -> {
            selectedShapeType = "Line";
            eraserMode = false;
        });
        buttonPanel.add(lineButton);

        JButton ovalButton = new JButton("Oval");
        ovalButton.addActionListener(e -> {
            selectedShapeType = "Oval";
            eraserMode = false;
        });
        buttonPanel.add(ovalButton);

        JButton rectangleButton = new JButton("Rectangle");
        rectangleButton.addActionListener(e -> {
            selectedShapeType = "Rectangle";
            eraserMode = false;
        });
        buttonPanel.add(rectangleButton);

        JButton triangleButton = new JButton("Triangle");
        triangleButton.addActionListener(e -> {
            selectedShapeType = "Triangle";
            eraserMode = false;
        });
        buttonPanel.add(triangleButton);

        JButton blackButton = new JButton("Black");
        blackButton.addActionListener(e -> {
            selectedColor = Color.BLACK;
            eraserMode = false;
        });
        buttonPanel.add(blackButton);

        JButton redButton = new JButton("Red");
        redButton.addActionListener(e -> {
            selectedColor = Color.RED;
            eraserMode = false;
        });
        buttonPanel.add(redButton);

        JButton blueButton = new JButton("Blue");
        blueButton.addActionListener(e -> {
            selectedColor = Color.BLUE;
            eraserMode = false;
        });
        buttonPanel.add(blueButton);

        JButton fillButton = new JButton("Fill");
        fillButton.addActionListener(e -> fillMode = !fillMode);
        buttonPanel.add(fillButton);

        JButton eraserButton = new JButton("Eraser");
        eraserButton.addActionListener(e -> eraserMode = true);
        buttonPanel.add(eraserButton);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            shapes.clear();
            repaint();
        });
        buttonPanel.add(clearButton);

        return buttonPanel;
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
            Graphics g = getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(e.getX() - 10, e.getY() - 10, 20, 20);
            g.dispose();
        } else {
            if (selectedShapeType.equals("Freehand")) {
                Graphics g = getGraphics();
                g.setColor(selectedColor);
                g.fillOval(e.getX() - 2, e.getY() - 2, 4, 4);
                g.dispose();
            } else if (selectedShapeType.equals("Line")) {
                // Draw straight line
                if (currentShape == null) {
                    currentShape = new Shape(lastX, lastY, e.getX(), e.getY(), selectedColor, false) {
                        @Override
                        public void draw(Graphics g) {
                            g.setColor(color);
                            g.drawLine(x1, y1, x2, y2);
                        }
                    };
                } else {
                    currentShape.x2 = e.getX();
                    currentShape.y2 = e.getY();
                }
                repaint();
            } else {
                if (currentShape == null) {
                    return; // No shape selected
                }
                currentShape.x2 = e.getX(); // Update the end coordinates of the shape
                currentShape.y2 = e.getY();
                repaint(); // Trigger a repaint to draw the shape
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        lastX = e.getX();
        lastY = e.getY();
    }

    public void finalizeShape() {
        if (currentShape != null) {
            shapes.add(currentShape);
            currentShape = null;
        }
    }

    public void startShape(int x, int y) {
        if (selectedShapeType.equals("Oval")) {
            currentShape = new Oval(x, y, x, y, selectedColor, fillMode);
        } else if (selectedShapeType.equals("Rectangle")) {
            currentShape = new Rectangle(x, y, x, y, selectedColor, fillMode);
        } else if (selectedShapeType.equals("Triangle")) {
            currentShape = new Triangle(x, y, x, y, selectedColor, fillMode);
        }
    }

    public static void main(String[] args) {
        PaintBrush paintBrush = new PaintBrush();
        paintBrush.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!paintBrush.eraserMode) {
                    paintBrush.startShape(e.getX(), e.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (!paintBrush.eraserMode) {
                    paintBrush.finalizeShape();
                }
            }
        });
    }
}
