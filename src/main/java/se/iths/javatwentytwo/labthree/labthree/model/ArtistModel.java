package se.iths.javatwentytwo.labthree.labthree.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import se.iths.javatwentytwo.labthree.labthree.model.shapes.Shape;

import java.util.ArrayList;
import java.util.List;

public class ArtistModel {

    private Point point;

    List<Shape> shapeList = new ArrayList<>();
    List<Shape> undoList = new ArrayList<>();

    ObjectProperty<Color> colorPicker = new SimpleObjectProperty<>(Color.RED);
    ObjectProperty<Integer> sizeSpinner = new SimpleObjectProperty<>(50);

    public List<Shape> getShapeList() {
        return shapeList;
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

    public Shape selectShape(){
        return shapeList.stream()
                .filter(shape -> shape.pointInsideShape(point))
                .findFirst().orElseThrow();
    }

    public void changeShape(){
        var shape = selectShape();
        shapeList.add(shape.changeShape(colorPicker.getValue(), sizeSpinner.getValue()));
        undoList.add(shape);
        shapeList.remove(shape);
    }

    public void undoLastEntry(){
        if(!undoList.isEmpty() && !shapeList.isEmpty()) {
            shapeList.add(undoList.get(undoList.size() - 1));
            undoList.remove(undoList.size() - 1);
        }else if(!shapeList.isEmpty()){
            undoList.add(shapeList.get(shapeList.size() -1));
            shapeList.remove(shapeList.size() - 1);
        }
        System.out.println(shapeList);
        System.out.println(undoList);
    }

    public void redoLastEntry(){
        if(!undoList.isEmpty()){
            shapeList.add(undoList.get(undoList.size() - 1));
            undoList.remove(undoList.size() - 1);
        }
    }
}
