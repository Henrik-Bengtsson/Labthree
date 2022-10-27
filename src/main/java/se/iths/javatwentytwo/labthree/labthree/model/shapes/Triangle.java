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
        context.fillPolygon(new double[]{getPoint().getMousePosX(), getPoint().getMousePosX(), getPoint().getMousePosX() + getSize()},
                new double[]{getPoint().getMousePosY(), getPoint().getMousePosY() + getSize(), getPoint().getMousePosY() + getSize()}, 3);
    }
}
