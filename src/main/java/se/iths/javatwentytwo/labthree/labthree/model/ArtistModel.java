package se.iths.javatwentytwo.labthree.labthree.model;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import se.iths.javatwentytwo.labthree.labthree.model.command.CommandHandling;
import se.iths.javatwentytwo.labthree.labthree.model.shapes.Shape;
import se.iths.javatwentytwo.labthree.labthree.model.shapes.ShapeType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ArtistModel {

    private Point point;

    ObservableList<CommandHandling> undoList = FXCollections.observableArrayList();
    ObservableList<CommandHandling> redoList = FXCollections.observableArrayList();
    ObjectProperty<Color> colorPicker = new SimpleObjectProperty<>(Color.RED);
    ObjectProperty<Integer> sizeSpinner = new SimpleObjectProperty<>(50);
    ObservableList<Shape> shapeList = FXCollections.observableArrayList(ArtistModel::observableArray);

    private static Observable[] observableArray(Shape shape){
        return new Observable[]{shape.colorProperty(), shape.sizeProperty(), shape.pointProperty()};
    }

    public ObservableList<CommandHandling> getUndoListProperty() {
        return undoList;
    }

    public ObservableList<CommandHandling> getRedoListProperty() {
        return redoList;
    }

    public ObservableList <Shape> getShapeListProperty() {
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

    public void createShapeToList(ShapeType shapeType) {
        Shape shape = Shape.createShape(shapeType, point, colorPicker.getValue(), sizeSpinner.getValue());
        shapeList.add(shape);
        undoRedoShapeCreateCommand(shape);
    }

    public void addSvgToShapeList(String line){
        Shape shape = fromSvgToShape(line);
        shapeList.add(shape);
        undoRedoShapeCreateCommand(shape);
    }

    public Shape fromSvgToShape(String line) {
        Pattern pattern = Pattern.compile("[=,]");
        String[] svgString = pattern.split(line);

        if(line.contains("rect"))
            return Shape.rectFromSvg(svgString);
        else if(line.contains("circle"))
            return Shape.circleFromSvg(svgString);
        else if(line.contains("polyline"))
            return Shape.triangleFromSvg(svgString);
        return null;
    }

    private void undoRedoShapeCreateCommand(Shape shape) {
        CommandHandling commandHandling = new CommandHandling();
        commandHandling.undo = () -> shapeList.remove(shape);
        commandHandling.redo = () -> shapeList.add(shape);
        undoList.add(commandHandling);
        redoList.clear();
    }

    public Shape selectShape() {
        return shapeList.stream()
                .filter(shape -> shape.pointInsideShape(point))
                .findFirst().orElseThrow();
    }

    public void changeShape(Color color, double size) {
        Shape shape = selectShape();
        Color oldColor = shape.getColor();
        double oldSize = shape.getSize();
        shape.setColor(color);
        shape.setSize(size);
        undoRedoShapeChangedCommand(color, size, shape, oldColor, oldSize);
    }

    private void undoRedoShapeChangedCommand(Color color, double size, Shape shape, Color oldColor, double oldSize) {
        CommandHandling commandHandling = new CommandHandling();
        commandHandling.undo = () -> {
            shape.setColor(oldColor);
            shape.setSize(oldSize);
        };
        commandHandling.redo = () -> {
            shape.setColor(color);
            shape.setSize(size);
        };
        undoList.add(commandHandling);
        redoList.clear();
    }

    public void undoLastCommand() {
        if (!undoList.isEmpty()) {
            CommandHandling undoToExecute = undoList.remove(undoList.size() - 1);
            redoList.add(undoToExecute);
            undoToExecute.undo.execute();
        }
    }

    public void redoLastCommand() {
        if (!redoList.isEmpty()) {
            CommandHandling redoToExecute = redoList.remove(redoList.size() - 1);
            undoList.add(redoToExecute);
            redoToExecute.redo.execute();
        }
    }

    public void saveToFile(Path savePath) {
        List<String> svgList = new ArrayList<>();

        svgBuilder(svgList);

        try {
            Files.write(savePath, svgList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void svgBuilder(List<String> svgList) {
        svgList.add("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
        svgList.add("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">");
        svgList.add("<svg width=\"820.0\" height=\"541.0\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">");
        shapeList.forEach(shape -> svgList.add(shape.svgFormat()));
        svgList.add("</svg>");
    }
}
