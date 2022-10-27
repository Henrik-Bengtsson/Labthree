package se.iths.javatwentytwo.labthree.labthree.model.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.iths.javatwentytwo.labthree.labthree.model.Point;

public class Triangle implements Shape{

    final static String type = "triangle";
    Point point;
    int size;
    Color color;

    public Triangle(Point point, int size, Color color) {
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
        context.fillPolygon(new double[]{point.getMousePosX(), point.getMousePosX(), point.getMousePosX() + getSize()},
                new double[]{point.getMousePosY(), point.getMousePosY() + getSize(), point.getMousePosY() + getSize()}, 3);
    }
}
