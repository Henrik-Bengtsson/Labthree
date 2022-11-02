package se.iths.javatwentytwo.labthree.labthree.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import se.iths.javatwentytwo.labthree.labthree.model.shapes.Shape;
import se.iths.javatwentytwo.labthree.labthree.model.shapes.ShapeType;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ArtistModel {

    private Point point;

    List<Shape> shapeList = new ArrayList<>();
    static Deque<Command> undoList = new ArrayDeque<>();

    ObjectProperty<Color> colorPicker = new SimpleObjectProperty<>(Color.RED);
    ObjectProperty<Integer> sizeSpinner = new SimpleObjectProperty<>(50);

    public List<Shape> getShapeList() {
        return shapeList;
    }

    public Deque<Command> getUndoList() {
        return undoList;
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

    public void addShapeToList(ShapeType shapeType){
        Shape shape = Shape.createShape(shapeType, point, colorPicker.getValue(), sizeSpinner.getValue());
        shapeList.add(shape);
        Command undo = ()-> shapeList.remove(shape);
        undoList.push(undo);
    }

    public Shape selectShape(){
        return shapeList.stream()
                .filter(shape -> shape.pointInsideShape(point))
                .findFirst().orElseThrow();
    }

    public void changeShape(Color color, int size){
        Shape shape = selectShape();
        Color oldColor = shape.getColor();
        int oldSize = shape.getSize();
        shape.setColor(color);
        shape.setSize(size);
        Command undo = () -> {shape.setColor(oldColor); shape.setSize(oldSize);};
        undoList.push(undo);
    }

    public void undoLastCommand(){
        Command undoToExecute = undoList.pop();
        undoToExecute.execute();
    }

    public void redoLastEntry(){
    }
}

@FunctionalInterface
interface Command{
    void execute();
}
