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
    public void draw(GraphicsContext context) {
        context.setFill(getColor());
        context.fillPolygon(new double[]{getPoint().getPosX() + getSize()/2f, getPoint().getPosX(), getPoint().getPosX() + getSize()},
                new double[]{getPoint().getPosY(), getPoint().getPosY() + getSize(), getPoint().getPosY() + getSize()}, 3);
    }

    @Override
    public boolean pointInsideShape(Point point) {
        return false;
    }
}
