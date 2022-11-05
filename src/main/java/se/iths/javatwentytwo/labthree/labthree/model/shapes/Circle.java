package se.iths.javatwentytwo.labthree.labthree.model.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.iths.javatwentytwo.labthree.labthree.model.Point;

public class Circle extends Shape{

    public Circle(Point point, Color color, double size) {
        super.setPoint(point);
        super.setColor(color);
        super.setSize(size);
    }

    @Override
    public String svgFormat() {
        return "<circle cx=\"" + getPoint().getPosX() + "\" cy=\"" + getPoint().getPosY() + "\" r=\"" + (getSize()/2) +
                "\" fill=\"#" + getColor().toString().substring(2) + "\" />";

    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(getColor());
        context.fillOval(centerPoint().getPosX(), centerPoint().getPosY(), getSize(), getSize());
    }

    @Override
    public boolean pointInsideShape(Point point) {
        boolean xInside = point.getPosX() >= centerPoint().getPosX() && point.getPosX() <= centerPoint().getPosX() + getSize();
        boolean yInside = point.getPosY() >= centerPoint().getPosY() && point.getPosY() <= centerPoint().getPosY() + getSize();
        return xInside && yInside;
    }
}
