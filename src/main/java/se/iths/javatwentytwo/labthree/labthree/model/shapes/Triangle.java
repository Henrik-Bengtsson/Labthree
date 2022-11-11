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
        return "<polyline points=\"" + (centerPoint().getPosX() + getSize() / 2f) + "," + centerPoint().getPosY() + " " +
                centerPoint().getPosX() + "," + (centerPoint().getPosY() + getSize()) + " " +
                (centerPoint().getPosX() + getSize()) + "," + (centerPoint().getPosY() + getSize()) +
                "\" fill=\"#" + getColor().toString().substring(2) + "\" />";
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(getColor());
        context.fillPolygon(new double[]{centerPoint().getPosX() + getSize() / 2f, centerPoint().getPosX(), centerPoint().getPosX() + getSize()},
                new double[]{centerPoint().getPosY(), centerPoint().getPosY() + getSize(), centerPoint().getPosY() + getSize()}, 3);
    }

    @Override
    public boolean pointInsideShape(Point point) {

        double x1 = centerPoint().getPosX() + getSize() / 2f;
        double x2 = centerPoint().getPosX();
        double x3 = centerPoint().getPosX() + getSize();
        double y1 = centerPoint().getPosY();
        double y2 = centerPoint().getPosY() + getSize();
        double y3 = centerPoint().getPosY() + getSize();

        double areaOrig = Math.abs((x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1));

        double area1 = Math.abs( (x1- point.getPosX())*(y2- point.getPosY()) - (x2- point.getPosX())*(y1- point.getPosY()));
        double area2 = Math.abs( (x2- point.getPosX())*(y3- point.getPosY()) - (x3- point.getPosX())*(y2- point.getPosY()));
        double area3 = Math.abs( (x3- point.getPosX())*(y1- point.getPosY()) - (x1- point.getPosX())*(y3- point.getPosY()));

        return area1 + area2 + area3 == areaOrig;
    }
}
