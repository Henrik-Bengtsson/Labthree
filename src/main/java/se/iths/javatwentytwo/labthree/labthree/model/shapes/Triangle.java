package se.iths.javatwentytwo.labthree.labthree.model.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.iths.javatwentytwo.labthree.labthree.model.Point;

public class Triangle extends Shape{

    public Triangle(Point point, Color color, int size) {
        super.setPoint(point);
        super.setColor(color);
        super.setSize(size);
    }

    @Override
    public String svgFormat() {
        return "<polyline points=\"" + (centerPoint().getPosX() + getSize()/2f) + "," + centerPoint().getPosY() + " " +
                centerPoint().getPosX() + "," + (centerPoint().getPosY() + getSize()) + " " +
                (centerPoint().getPosX() + getSize()) + "," + (centerPoint().getPosY() + getSize()) +
                "\" fill=\"#" + getColor().toString().substring(2) + "\" />";

    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(getColor());
        context.fillPolygon(new double[]{centerPoint().getPosX() + getSize()/2f, centerPoint().getPosX(), centerPoint().getPosX() + getSize()},
                new double[]{centerPoint().getPosY(), centerPoint().getPosY() + getSize(), centerPoint().getPosY() + getSize()}, 3);
    }

    @Override
    public boolean pointInsideShape(Point point) {
        boolean xInside = point.getPosX() >= centerPoint().getPosX() && point.getPosX() <= centerPoint().getPosX() + getSize();
        boolean yInside = point.getPosY() >= centerPoint().getPosY() && point.getPosY() <= centerPoint().getPosY() + getSize();
        return xInside && yInside;
    }
}
