package se.iths.javatwentytwo.labthree.labthree.model.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.iths.javatwentytwo.labthree.labthree.model.Point;

public class Rectangle extends Shape{

    public Rectangle(Point point, Color color, double size) {
        super.setPoint(point);
        super.setColor(color);
        super.setSize(size);
    }

    @Override
    public String svgFormat() {
        return "<rect x=\"" + getPoint().posXProperty().get() + "\" y=\"" + getPoint().posYProperty().get() + "\" width=\"" + getSize() +
                "\" height=\"" + getSize() + "\" fill=\"#" + getColor().toString().substring(2) + "\" />";
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(getColor());
        context.fillRect(centerPoint().posXProperty().get(), centerPoint().posYProperty().get(), getSize(), getSize());
    }
}
