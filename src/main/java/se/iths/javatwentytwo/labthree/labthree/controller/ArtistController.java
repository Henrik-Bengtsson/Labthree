package se.iths.javatwentytwo.labthree.labthree.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;
import se.iths.javatwentytwo.labthree.labthree.model.Model;

public class ArtistController {

    public Model model = new Model();

    public GraphicsContext context;

    @FXML
    public Canvas canvas;
    @FXML
    public Button rectangleButton;
    @FXML
    public Button circleButton;
    @FXML
    public Button triangleButton;
    @FXML
    public Button selectButton;
    @FXML
    public ColorPicker colorPick;
    @FXML
    public Spinner<Integer> sizeSpinner;


    public void initialize(){
        context = canvas.getGraphicsContext2D();

        colorPick.valueProperty().bindBidirectional(model.colorPickProperty());
        sizeSpinner.getValueFactory().valueProperty().bindBidirectional(model.sizeSpinnerProperty());
    }

    public void canvasClicked(MouseEvent mouseEvent) {
        model.setPoint(mouseEvent.getX(), mouseEvent.getY());
        checkActiveButton();
        drawShape(context);
    }

    private void drawShape(GraphicsContext context) {
        for (var shape: model.shapeList) {
            shape.draw(context);
        }
    }

    private void checkActiveButton(){
        if(rectangleButton.isFocused())
            model.shapeList.add(model.createRectangle());
        else if(circleButton.isFocused())
            model.shapeList.add(model.createCircle());
        else if(triangleButton.isFocused())
            model.shapeList.add(model.createTriangle());
    }

}
