Simple Paint Brush Application

Overview

This project is a simple paintbrush application built using Java Swing. It allows users to draw shapes, select colors, adjust brush sizes, and clear the canvas. The application is designed with an object-oriented approach and utilizes a modular structure for scalability.

UML Class Diagram

1. Class: PaintApp

Description: The main class to initialize the application.

Methods:

main(String[] args): Entry point of the application.

initialize(): Sets up the frame and panel.

2. Class: PaintCanvas

Description: Extends JPanel and serves as the drawing area.

Attributes:

ArrayList<Shape> shapes: Stores all the drawn shapes.

Color currentColor: The current color of the brush.

int brushSize: The size of the brush.

Shape currentShape: The shape currently being drawn.

Methods:

paintComponent(Graphics g): Renders the shapes.

setColor(Color color): Sets the current drawing color.

setBrushSize(int size): Sets the brush size.

addShape(Shape shape): Adds a shape to the canvas.

3. Class: ToolBar

Description: A toolbar for selecting colors, shapes, and brush sizes.

Attributes:

JButton colorPickerButton

JButton clearButton

JComboBox<String> shapeSelector

JSpinner brushSizeSpinner

Methods:

createToolBar(): Creates and initializes toolbar components.

addActionListeners(): Adds listeners for the toolbar events.

4. Interface: Shape

Description: Represents a drawable shape.

Methods:

void draw(Graphics g): Draws the shape.

5. Class: Circle (implements Shape)

Attributes:

int x, y, radius

Color color

Methods:

draw(Graphics g): Draws the circle.

6. Class: Rectangle (implements Shape)

Attributes:

int x, y, width, height

Color color

Methods:

draw(Graphics g): Draws the rectangle.

Relationships

PaintApp contains PaintCanvas and ToolBar.

PaintCanvas uses Shape.

Circle and Rectangle implement Shape.

ToolBar interacts with PaintCanvas to update its state (color, brush size, etc.).

Example Interaction

The user selects a color and brush size from the ToolBar.

The user clicks and drags on the PaintCanvas to draw a Shape.

The PaintCanvas updates the list of shapes and redraws them on the screen.

The ToolBar's clear button clears the PaintCanvas.

Features

Custom Shapes: Draw circles and rectangles.

Color Picker: Choose any color for your shapes.

Adjustable Brush Size: Control the size of your brush.

Clear Canvas: Erase all shapes with a single button.

How to Run

Clone the repository:

git clone <repository-url>

Open the project in your preferred IDE.

Compile and run the PaintApp class.

Future Enhancements

Add more shapes (e.g., lines, polygons).

Implement undo and redo functionality.

Save and load canvas as an image file.

Enhance UI with more styling options.

License

This project is open-source and available under the MIT License.

Acknowledgments

Inspired by basic drawing applications and Java Swing tutorials.