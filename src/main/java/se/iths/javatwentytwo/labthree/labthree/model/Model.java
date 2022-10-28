package se.iths.javatwentytwo.labthree.labthree.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import se.iths.javatwentytwo.labthree.labthree.model.shapes.Circle;
import se.iths.javatwentytwo.labthree.labthree.model.shapes.Rectangle;
import se.iths.javatwentytwo.labthree.labthree.model.shapes.Shape;
import se.iths.javatwentytwo.labthree.labthree.model.shapes.Triangle;

public class Model {

    private Point point;

    ObservableList<Shape> observableList = FXCollections.observableArrayList();
    ObjectProperty<Color> colorPicker = new SimpleObjectProperty<>(Color.RED);
    ObjectProperty<Integer> sizeSpinner = new SimpleObjectProperty<>(50);
    BooleanProperty rectangleButton = new SimpleBooleanProperty();
    BooleanProperty circleButton = new SimpleBooleanProperty();
    BooleanProperty triangleButton = new SimpleBooleanProperty();
    BooleanProperty selectButton = new SimpleBooleanProperty();

    public ObservableList<Shape> getObservableList() {
        return observableList;
    }

    public BooleanProperty selectButtonProperty() {
        return selectButton;
    }

    public BooleanProperty triangleButtonProperty() {
        return triangleButton;
    }

    public BooleanProperty circleButtonProperty() {
        return circleButton;
    }

    public BooleanProperty rectangleButtonProperty() {
        return rectangleButton;
    }

    public ObjectProperty<Color> colorPickerProperty() {
        return colorPicker;
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

    public void createShape(){
        if(rectangleButton.getValue())
            observableList.add(createRectangle());
        else if(circleButton.getValue())
            observableList.add(createCircle());
        else if(triangleButton.getValue())
            observableList.add(createTriangle());
    }

    public Rectangle createRectangle(){
        return new Rectangle(getPoint(), colorPicker.getValue(), sizeSpinner.getValue());
    }

    public Circle createCircle(){
        return new Circle(getPoint(), colorPicker.getValue(), sizeSpinner.getValue());
    }

    public Triangle createTriangle(){
        return new Triangle(getPoint(), colorPicker.getValue(), sizeSpinner.getValue());
    }
}
