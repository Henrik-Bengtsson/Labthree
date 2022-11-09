package se.iths.javatwentytwo.labthree.labthree.model.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.iths.javatwentytwo.labthree.labthree.model.Point;

public class Triangle extends Shape{

    public Triangle(Point point, Color color, double size) {
        super.setPoint(point);
        super.setColor(color);
        super.setSize(size);
    }

    @Override
    public String svgFormat() {
        return "<polyline points=\"" + (getPoint().posXProperty().get() + getSize()/2f) + "," + getPoint().posYProperty().get() + " " +
                getPoint().posXProperty().get() + "," + (getPoint().posYProperty().get() + getSize()) + " " +
                (getPoint().posXProperty().get() + getSize()) + "," + (getPoint().posYProperty().get() + getSize()) +
                "\" fill=\"#" + getColor().toString().substring(2) + "\" />";
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(getColor());
        context.fillPolygon(new double[]{getPoint().posXProperty().get() + getSize()/2f, getPoint().posXProperty().get(), getPoint().posXProperty().get() + getSize()},
                new double[]{getPoint().posYProperty().get(), getPoint().posYProperty().get() + getSize(), getPoint().posYProperty().get() + getSize()}, 3);
    }
}
