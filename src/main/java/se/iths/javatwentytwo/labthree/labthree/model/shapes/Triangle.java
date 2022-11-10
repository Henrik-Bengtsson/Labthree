package se.iths.javatwentytwo.labthree.labthree.model.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends Shape {

    public Triangle(Point point, Color color, double size) {
        super.setPoint(point);
        super.setColor(color);
        super.setSize(size);
    }

    @Override
    public String svgFormat() {
        return "<polyline points=\"" + (centerPoint().posXProperty().get() + getSize() / 2f) + "," + centerPoint().posYProperty().get() + " " +
                centerPoint().posXProperty().get() + "," + (centerPoint().posYProperty().get() + getSize()) + " " +
                (centerPoint().posXProperty().get() + getSize()) + "," + (centerPoint().posYProperty().get() + getSize()) +
                "\" fill=\"#" + getColor().toString().substring(2) + "\" />";
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(getColor());
        context.fillPolygon(new double[]{centerPoint().posXProperty().get() + getSize() / 2f, centerPoint().posXProperty().get(), centerPoint().posXProperty().get() + getSize()},
                new double[]{centerPoint().posYProperty().get(), centerPoint().posYProperty().get() + getSize(), centerPoint().posYProperty().get() + getSize()}, 3);
    }
}
