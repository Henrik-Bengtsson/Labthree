package se.iths.javatwentytwo.labthree.labthree.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import se.iths.javatwentytwo.labthree.labthree.model.ArtistModel;

public class ArtistController {

    public ArtistModel artistModel = new ArtistModel();

    public GraphicsContext context;

    @FXML
    public Button saveButton;
    @FXML
    public Button redoButton;
    @FXML
    public Button undoButton;
    @FXML
    public ToggleButton rectangleButton;
    @FXML
    public ToggleButton circleButton;
    @FXML
    public ToggleButton triangleButton;
    @FXML
    public ToggleButton selectButton;
    @FXML
    public ColorPicker colorPicker;
    @FXML
    public Spinner<Integer> sizeSpinner;
    @FXML
    public Canvas canvas;


    public void initialize(){
        context = canvas.getGraphicsContext2D();

        colorPicker.valueProperty().bindBidirectional(artistModel.colorPickerProperty());
        sizeSpinner.getValueFactory().valueProperty().bindBidirectional(artistModel.sizeSpinnerProperty());
        rectangleButton.selectedProperty().bindBidirectional(artistModel.rectangleButtonProperty());
        circleButton.selectedProperty().bindBidirectional(artistModel.circleButtonProperty());
        triangleButton.selectedProperty().bindBidirectional(artistModel.triangleButtonProperty());
        selectButton.selectedProperty().bindBidirectional(artistModel.selectButtonProperty());
    }

    public void canvasClicked(MouseEvent mouseEvent) {
        artistModel.setPoint(mouseEvent.getX(), mouseEvent.getY());
        artistModel.buttonSelected();
        drawShape(context);
    }

    private void drawShape(GraphicsContext context) {
        context.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        for (var shape: artistModel.getShapeList()) {
            shape.draw(context);
        }
    }

    public void undoButtonClicked() {
        artistModel.undoLastEntry();
        drawShape(context);
    }

    public void redoButtonClicked() {
        artistModel.redoLastEntry();
        drawShape(context);
    }

    public void saveButtonClicked() {
    }
}
