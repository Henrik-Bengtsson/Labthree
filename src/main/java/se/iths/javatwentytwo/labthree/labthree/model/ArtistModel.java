package se.iths.javatwentytwo.labthree.labthree.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import se.iths.javatwentytwo.labthree.labthree.model.shapes.Circle;
import se.iths.javatwentytwo.labthree.labthree.model.shapes.Rectangle;
import se.iths.javatwentytwo.labthree.labthree.model.shapes.Shape;
import se.iths.javatwentytwo.labthree.labthree.model.shapes.Triangle;

import java.util.ArrayList;
import java.util.List;

public class ArtistModel {

    private Point point;

    List<Shape> shapeList = new ArrayList<>();
    List<Shape> undoList = new ArrayList<>();

    ObjectProperty<Color> colorPicker = new SimpleObjectProperty<>(Color.RED);
    ObjectProperty<Integer> sizeSpinner = new SimpleObjectProperty<>(50);
    BooleanProperty rectangleButton = new SimpleBooleanProperty();
    BooleanProperty circleButton = new SimpleBooleanProperty();
    BooleanProperty triangleButton = new SimpleBooleanProperty();
    BooleanProperty selectButton = new SimpleBooleanProperty();

    public List<Shape> getShapeList() {
        return shapeList;
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

    public void buttonSelected(){
        if(rectangleButton.getValue())
            shapeList.add(createRectangle());
        else if(circleButton.getValue())
            shapeList.add(createCircle());
        else if(triangleButton.getValue())
            shapeList.add(createTriangle());
        else if(selectButton.getValue())
            selectShapeAndChange();
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

    public void selectShapeAndChange(){
        var shapeSelected = shapeList.stream()
                .filter(shape -> shape.pointInsideShape(point))
                .findFirst().orElseThrow();
        changeShape(shapeSelected);
    }

    public void changeShape(Shape shape){
        if(shape.getClass() == Rectangle.class)
            shapeList.add(new Rectangle(shape.getPoint(), colorPicker.getValue(), sizeSpinner.getValue()));
        else if(shape.getClass() == Circle.class)
            shapeList.add(new Circle(shape.getPoint(), colorPicker.getValue(), sizeSpinner.getValue()));
        else if (shape.getClass() == Triangle.class)
            shapeList.add(new Triangle(shape.getPoint(), colorPicker.getValue(), sizeSpinner.getValue()));
        undoList.add(shape);
        shapeList.remove(shape);
    }

    public void undoLastEntry(){
        if(!shapeList.isEmpty()) {
            undoList.add(shapeList.get(shapeList.size() - 1));
            shapeList.remove(shapeList.size() - 1);
        }
    }

    public void redoLastEntry(){
        if(!undoList.isEmpty()){
            shapeList.add(undoList.get(undoList.size() - 1));
            undoList.remove(undoList.size() - 1);
        }
    }
}
