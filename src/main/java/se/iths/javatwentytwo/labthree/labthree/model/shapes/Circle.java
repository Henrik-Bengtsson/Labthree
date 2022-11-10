package se.iths.javatwentytwo.labthree.labthree.model.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape {

    public Circle(Point point, Color color, double size) {
        super.setPoint(point);
        super.setColor(color);
        super.setSize(size);
    }

    @Override
    public String svgFormat() {
        return "<circle cx=\"" + getPoint().posXProperty().get() + "\" cy=\"" + getPoint().posYProperty().get() + "\" r=\"" + (getSize() / 2) +
                "\" fill=\"#" + getColor().toString().substring(2) + "\" />";
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(getColor());
        context.fillOval(centerPoint().posXProperty().get(), centerPoint().posYProperty().get(), getSize(), getSize());
    }
}
