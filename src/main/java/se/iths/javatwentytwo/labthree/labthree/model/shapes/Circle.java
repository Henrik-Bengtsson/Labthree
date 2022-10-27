package se.iths.javatwentytwo.labthree.labthree.model.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.iths.javatwentytwo.labthree.labthree.model.Point;

public class Circle implements Shape{

    final static String type = "circle";
    Point point;
    int size;
    Color color;

    public Circle(Point point, int size, Color color) {
        this.point = point;
        this.size = size;
        this.color = color;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(color);
        context.fillOval(point.getMousePosX(), point.getMousePosY(), getSize(), getSize());
    }
}
