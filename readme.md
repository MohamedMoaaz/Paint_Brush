Simple Paint Brush Application
Overview
This is a simple paint brush application written in Java. It allows users to draw various shapes (freehand, line, oval, rectangle, triangle) and erase parts of the drawing using an eraser tool. The application provides a graphical user interface (GUI) where users can select different shapes, colors, and toggle fill mode for shapes.

Features
Shapes: Draw freehand lines, straight lines, ovals, rectangles, and triangles.

Colors: Choose from black, red, and blue for drawing shapes.

Fill Mode: Toggle between filled and outlined shapes.

Eraser: Erase parts of the drawing by dragging the mouse.

Clear Canvas: Clear the entire canvas with a single button click.

How to Use
Select a Shape: Click on one of the shape buttons (Freehand, Line, Oval, Rectangle, Triangle) to choose the shape you want to draw.

Select a Color: Click on one of the color buttons (Black, Red, Blue) to choose the drawing color.

Toggle Fill Mode: Check the "Fill" checkbox to draw filled shapes. Uncheck it to draw outlined shapes.

Draw: Click and drag the mouse on the canvas to draw the selected shape.

Erase: Click the "Eraser" button and drag the mouse to erase parts of the drawing.

Clear Canvas: Click the "Clear" button to remove all shapes from the canvas.

Classes Overview
Shape: Abstract base class for all shapes. Defines common properties like position, color, and fill status.

Freehand: Represents a freehand drawing tool. Connects points with lines to create a freehand drawing.

Line: Represents a straight line shape.

Oval: Represents an oval shape. Can be filled or outlined.

Rectangle: Represents a rectangle shape. Can be filled or outlined.

Triangle: Represents a triangle shape. Can be filled or outlined.

Eraser: Represents an eraser tool. Draws white squares to simulate erasing.

PaintBrush: Main class that sets up the GUI and handles user interactions.

UML Diagram
Below is a simplified UML diagram representing the class relationships in the application:

+-------------------+       +-------------------+
|     Shape         |       |    PaintBrush     |
|-------------------|       |-------------------|
| - x1, y1, x2, y2  |       | - currentShape    |
| - color           |       | - shapes          |
| - filled          |       | - selectedShapeType|
|-------------------|       | - selectedColor   |
| + draw(Graphics)  |       | - fillMode        |
+-------------------+       |-------------------|
        ^                   | + main(String[])  |
        |                   +-------------------+
        |                           |
        |                           |
+-------+-------+                   |
|               |                   |
|  Freehand     |                   |
|---------------|                   |
| - points      |                   |
|---------------|                   |
| + addPoint()  |                   |
| + draw()      |                   |
+---------------+                   |
                                    |
+-------------------+               |
|     Oval          |               |
|-------------------|               |
| + draw()          |               |
+-------------------+               |
                                    |
+-------------------+               |
|   Rectangle       |               |
|-------------------|               |
| + draw()          |               |
+-------------------+               |
                                    |
+-------------------+               |
|   Triangle        |               |
|-------------------|               |
| + draw()          |               |
+-------------------+               |
                                    |
+-------------------+               |
|     Eraser        |               |
|-------------------|               |
| - points          |               |
|-------------------|               |
| + addPoint()      |               |
| + draw()          |               |
+-------------------+               |
                                    |
+-------------------+               |
|     Line          |               |
|-------------------|               |
| + draw()          |               |
+-------------------+               |
