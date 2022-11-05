package se.iths.javatwentytwo.labthree.labthree.model.shapes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.iths.javatwentytwo.labthree.labthree.model.ArtistModel;
import se.iths.javatwentytwo.labthree.labthree.model.Point;

import java.util.Objects;

public abstract class Shape {
    static ArtistModel artistModel = new ArtistModel();


    private final SimpleObjectProperty<Point> point = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<Color> color = new SimpleObjectProperty<>();
    private final SimpleDoubleProperty size = new SimpleDoubleProperty();

    public static Shape createShape(ShapeType type, Point point, Color color, double size){
        return switch (type) {
            case RECT -> new Rectangle(point, color, size);
            case CIRCLE -> new Circle(point, color, size);
            case TRIANGLE -> new Triangle(point, color, size);
        };
    }

    public abstract void draw(GraphicsContext context);

    public abstract boolean pointInsideShape(Point point);

    public abstract String svgFormat();

    public SimpleObjectProperty<Point> pointProperty() {
        return point;
    }

    public Point getPoint() {
        return point.get();
    }

    public void setPoint(Point point) {
        this.point.set(point);
    }

    public SimpleObjectProperty<Color> colorProperty() {
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

    public Point centerPoint(){
        var centerX = getPoint().getPosX() - getSize() / 2;
        var centerY = getPoint().getPosY() - getSize() / 2;
        return new Point(centerX, centerY);
    }


    public static Shape rectFromSvg(String[] svgString) {
        double x = Double.parseDouble(svgString[1].substring(1,5));
        double y = Double.parseDouble(svgString[2].substring(1,5));
        double size = Double.parseDouble(svgString[3].substring(1,5));
        Color color = Color.valueOf(svgString[5].substring(1, 8));
        return createShape(ShapeType.RECT, new Point(x, y), color, size);
    }

    public static Shape circleFromSvg(String[] svgString) {
        double x = Double.parseDouble(svgString[1].substring(1,5));
        double y = Double.parseDouble(svgString[2].substring(1,5));
        double size = Double.parseDouble(svgString[3].substring(1,5));
        Color color = Color.valueOf(svgString[4].substring(1, 8));
        return createShape(ShapeType.CIRCLE, new Point(x, y), color, (size * 2));
    }

    public static Shape triangleFromSvg(String[] svgString) {
        double x = Double.parseDouble(svgString[1].substring(1,5));
        double y = Double.parseDouble(svgString[2].substring(1,5));
        double size = Double.parseDouble(svgString[2].substring(1,5));
        Color color = Color.valueOf(svgString[5].substring(1, 8));
        return createShape(ShapeType.TRIANGLE, new Point(x, y), color, size);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shape shape)) return false;
        return Objects.equals(point, shape.point) && Objects.equals(color, shape.color) && Objects.equals(size, shape.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, color, size);
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
