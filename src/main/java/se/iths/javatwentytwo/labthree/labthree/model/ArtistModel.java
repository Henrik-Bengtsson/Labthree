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
    static Deque<CommandHandling> undoList = new ArrayDeque<>();
    static Deque<CommandHandling> redoList = new ArrayDeque<>();

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

    public void setPoint(double mousePointX, double mousePointY) {
        this.point = new Point(mousePointX, mousePointY);
    }

    public void addShapeToList(ShapeType shapeType){
        Shape shape = Shape.createShape(shapeType, point, colorPicker.getValue(), sizeSpinner.getValue());
        shapeList.add(shape);
        undoRedoShapeCreated(shape);
    }

    private void undoRedoShapeCreated(Shape shape) {
        CommandHandling commandHandling = new CommandHandling();
        commandHandling.undo = ()-> shapeList.remove(shape);
        commandHandling.redo = ()-> shapeList.add(shape);
        undoList.push(commandHandling);
        redoList.clear();
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
        undoRedoShapeChanged(color, size, shape, oldColor, oldSize);
    }

    private static void undoRedoShapeChanged(Color color, int size, Shape shape, Color oldColor, int oldSize) {
        CommandHandling commandHandling = new CommandHandling();
        commandHandling.undo = () -> {shape.setColor(oldColor); shape.setSize(oldSize);};
        commandHandling.redo = () -> {shape.setColor(color); shape.setSize(size);};
        undoList.push(commandHandling);
        redoList.clear();
    }

    public void undoLastCommand(){
        if(!undoList.isEmpty()) {
            CommandHandling undoToExecute = undoList.pop();
            redoList.push(undoToExecute);
            undoToExecute.undo.execute();
        }
    }

    public void redoLastCommand(){
        if(!redoList.isEmpty()) {
            CommandHandling redoToExecute = redoList.pop();
            undoList.push(redoToExecute);
            redoToExecute.redo.execute();
        }
    }
}

@FunctionalInterface
interface Command{
    void execute();
}

class CommandHandling{
    public Command undo;
    public Command redo;
}
