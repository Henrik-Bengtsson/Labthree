package se.iths.javatwentytwo.labthree.labthree.model.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.iths.javatwentytwo.labthree.labthree.model.Point;

public class Circle extends Shape{

    public Circle(Point point, Color color, int size) {
        super.setPoint(point);
        super.setColor(color);
        super.setSize(size);
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(getColor());
        context.fillOval(getPoint().getPosX(), getPoint().getPosY(), getSize(), getSize());
    }

    @Override
    public boolean pointInsideShape(Point point) {
        return false;
    }
}
