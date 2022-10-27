package se.iths.javatwentytwo.labthree.labthree.model.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.iths.javatwentytwo.labthree.labthree.model.Point;

public class Rectangle implements Shape{

    final static String type = "rectangle";
    Point point;
    int size;
    Color color;

    public Rectangle(Point point, int size, Color color) {
        this.point = point;
        this.size = size;
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public Point getPoint() {
        return point;
    }

    public int getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(color);
        context.fillRect(point.getMousePosX(), point.getMousePosY(), getSize(), getSize());
    }
}
