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
        return "<circle cx=\"" + getPoint().getPosX() + "\" cy=\"" + getPoint().getPosY() + "\" r=\"" + (getSize() / 2) +
                "\" fill=\"#" + getColor().toString().substring(2) + "\" />";
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(getColor());
        context.fillOval(centerPoint().getPosX(), centerPoint().getPosY(), getSize(), getSize());
    }

    @Override
    public boolean pointInsideShape(Point point) {
        var distance = Math.sqrt(distX(point) + distY(point));
        return distance <= getSize() / 2;
    }

    private double distX(Point point){
        var distX = point.getPosX() - getPoint().getPosX();
        return distX * distX;
    }

    private double distY(Point point){
        var distY = point.getPosY() - getPoint().getPosY();
        return distY * distY;
    }
}
