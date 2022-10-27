package se.iths.javatwentytwo.labthree.labthree.model;

import se.iths.javatwentytwo.labthree.labthree.model.shapes.Shape;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private Point point;

    public List<Shape> shapeList = new ArrayList<>();

    public Point getPoint() {
        return point;
    }

    public void setPoint(double mousePointX, double mousePointY) {
        this.point = new Point(mousePointX, mousePointY);
    }
}
