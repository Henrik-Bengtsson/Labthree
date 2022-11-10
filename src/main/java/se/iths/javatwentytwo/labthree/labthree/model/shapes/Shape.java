package se.iths.javatwentytwo.labthree.labthree.model.shapes;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;

public abstract class Shape {

    private final SimpleObjectProperty<Point> point = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<Color> color = new SimpleObjectProperty<>();
    private final SimpleDoubleProperty size = new SimpleDoubleProperty();

    public static Shape createShape(ShapeType type, Point point, Color color, double size) {
        return switch (type) {
            case RECT -> new Rectangle(point, color, size);
            case CIRCLE -> new Circle(point, color, size);
            case TRIANGLE -> new Triangle(point, color, size);
        };
    }

    public abstract void draw(GraphicsContext context);

    public abstract String svgFormat();

    public boolean pointInsideShape(Point point) {
        boolean xInside = point.posXProperty().get() >= centerPoint().posXProperty().get() && point.posXProperty().get() <= centerPoint().posXProperty().get() + getSize();
        boolean yInside = point.posYProperty().get() >= centerPoint().posYProperty().get() && point.posYProperty().get() <= centerPoint().posYProperty().get() + getSize();
        return xInside && yInside;
    }

    public ObjectProperty<Point> pointProperty() {
        return point;
    }

    public Point getPoint() {
        return point.get();
    }

    public void setPoint(Point point) {
        this.point.set(point);
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }

    public Color getColor() {
        return color.get();
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public SimpleDoubleProperty sizeProperty() {
        return size;
    }

    public double getSize() {
        return size.get();
    }

    public void setSize(double size) {
        this.size.set(size);
    }

    public Point centerPoint() {
        var centerX = getPoint().posXProperty().get() - getSize() / 2;
        var centerY = getPoint().posYProperty().get() - getSize() / 2;
        return new Point(centerX, centerY);
    }

    public static Shape rectFromSvg(String[] svgString) {
        double x = Double.parseDouble(svgString[1].substring(1, 5));
        double y = Double.parseDouble(svgString[2].substring(1, 5));
        double size = Double.parseDouble(svgString[3].substring(1, 5));
        Color color = Color.valueOf(svgString[5].substring(1, 8));
        return createShape(ShapeType.RECT, new Point(x + size / 2, y + size / 2), color, size);
    }

    public static Shape circleFromSvg(String[] svgString) {
        double x = Double.parseDouble(svgString[1].substring(1, 5));
        double y = Double.parseDouble(svgString[2].substring(1, 5));
        double size = Double.parseDouble(svgString[3].substring(1, 5));
        Color color = Color.valueOf(svgString[4].substring(1, 8));
        return createShape(ShapeType.CIRCLE, new Point(x, y), color, (size * 2));
    }

    public static Shape triangleFromSvg(String[] svgString) {
        double x = Double.parseDouble(svgString[1].substring(1, 5));
        double y = Double.parseDouble(svgString[2].substring(1, 5));
        double size = Double.parseDouble(svgString[2].substring(1, 5));
        Color color = Color.valueOf(svgString[5].substring(1, 8));
        return createShape(ShapeType.TRIANGLE, new Point(x, y), color, size);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shape shape)) return false;
        return Objects.equals(color, shape.color) && Objects.equals(size, shape.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, size);
    }

    @Override
    public String toString() {
        return "Shape{" +
                "point=" + point +
                ", color=" + color +
                ", size=" + size +
                '}';
    }
}
