package se.iths.javatwentytwo.labthree.labthree.model;

import javafx.beans.property.*;
import javafx.scene.paint.Color;
import se.iths.javatwentytwo.labthree.labthree.model.shapes.Circle;
import se.iths.javatwentytwo.labthree.labthree.model.shapes.Rectangle;
import se.iths.javatwentytwo.labthree.labthree.model.shapes.Shape;
import se.iths.javatwentytwo.labthree.labthree.model.shapes.Triangle;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private Point point;
    public List<Shape> shapeList = new ArrayList<>();

    public ObjectProperty<Color> colorPick = new SimpleObjectProperty<>(Color.RED);
    public ObjectProperty<Integer> sizeSpinner = new SimpleObjectProperty<>(50);
    
    public ObjectProperty<Color> colorPickProperty() {
        return colorPick;
    }

    public ObjectProperty<Integer> sizeSpinnerProperty() {
        return sizeSpinner;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(double mousePointX, double mousePointY) {
        this.point = new Point(mousePointX, mousePointY);
    }

    public Rectangle createRectangle(){
        return new Rectangle(getPoint(), colorPick.getValue(), sizeSpinner.getValue());
    }

    public Circle createCircle(){
        return new Circle(getPoint(), colorPick.getValue(), sizeSpinner.getValue());
    }

    public Triangle createTriangle(){
        return new Triangle(getPoint(), colorPick.getValue(), sizeSpinner.getValue());
    }

}
