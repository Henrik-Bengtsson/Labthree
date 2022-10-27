package se.iths.javatwentytwo.labthree.labthree.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;
import se.iths.javatwentytwo.labthree.labthree.model.Model;
import se.iths.javatwentytwo.labthree.labthree.model.shapes.Rectangle;

public class ArtistController {

    public Model model = new Model();

    public GraphicsContext context;

    @FXML
    public Canvas canvas;
    @FXML
    public Button rectangleButton;
    @FXML
    public ColorPicker colorPick;
    @FXML
    public Spinner sizeSpinner;


    public void initialize(){
        context = canvas.getGraphicsContext2D();
    }

    public void canvasClicked(MouseEvent mouseEvent) {
        model.setPoint(mouseEvent.getX(), mouseEvent.getY());
        checkShapeButton(context);
        drawShape(context);
    }

    private void drawShape(GraphicsContext context) {
        for (var shape: model.shapeList) {
            shape.draw(context);
        }
    }

    private void checkShapeButton(GraphicsContext context){
        if(rectangleButton.isFocused())
            model.shapeList.add(createRectangle());
    }

    private Rectangle createRectangle(){
        return new Rectangle(model.getPoint(), (Integer) sizeSpinner.getValue(), colorPick.getValue());
    }
}
