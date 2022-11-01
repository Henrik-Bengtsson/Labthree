package se.iths.javatwentytwo.labthree.labthree.model.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.iths.javatwentytwo.labthree.labthree.model.Point;

import java.util.Objects;

public abstract class Shape {

    private Point point;
    private Color color;
    private int size;

    public static Shape createShape(ShapeType type, Point point, Color color, int size){
        return switch (type) {
            case RECT -> new Rectangle(point, color, size);
            case CIRCLE -> new Circle(point, color, size);
            case TRIANGLE -> new Triangle(point, color, size);
        };
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Point centerPoint(){
        var centerX = getPoint().getPosX() - getSize() / 2;
        var centerY = getPoint().getPosY() - getSize() / 2;
        return new Point(centerX, centerY);
    }

    public abstract void draw(GraphicsContext context);

    public abstract boolean pointInsideShape(Point point);

    public abstract Shape changeShape(Color color, int size);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shape shape)) return false;
        return size == shape.size && Objects.equals(point, shape.point) && Objects.equals(color, shape.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, color, size);
    }
}
