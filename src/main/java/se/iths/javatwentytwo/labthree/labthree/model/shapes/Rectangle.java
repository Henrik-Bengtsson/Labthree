package se.iths.javatwentytwo.labthree.labthree.model.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.iths.javatwentytwo.labthree.labthree.model.Point;

public class Rectangle extends Shape{

    public Rectangle(Point point, Color color, int size) {
        super.setPoint(point);
        super.setColor(color);
        super.setSize(size);
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(getColor());
        context.fillRect(getPoint().getPosX(), getPoint().getPosY(), getSize(), getSize());
    }

    @Override
    public boolean pointInsideShape(Point point) {
        boolean xInside = point.getPosX() >= this.getPoint().getPosX() && point.getPosX() <= this.getPoint().getPosX() + getSize();
        boolean yInside = point.getPosY() >= this.getPoint().getPosY() && point.getPosY() <= this.getPoint().getPosY() + getSize();
        return xInside && yInside;
    }
}
