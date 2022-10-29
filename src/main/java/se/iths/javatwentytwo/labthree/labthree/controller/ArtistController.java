package se.iths.javatwentytwo.labthree.labthree.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import se.iths.javatwentytwo.labthree.labthree.model.Model;

public class ArtistController {

    public Model model = new Model();

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

        colorPicker.valueProperty().bindBidirectional(model.colorPickerProperty());
        sizeSpinner.getValueFactory().valueProperty().bindBidirectional(model.sizeSpinnerProperty());
        rectangleButton.selectedProperty().bindBidirectional(model.rectangleButtonProperty());
        circleButton.selectedProperty().bindBidirectional(model.circleButtonProperty());
        triangleButton.selectedProperty().bindBidirectional(model.triangleButtonProperty());
        selectButton.selectedProperty().bindBidirectional(model.selectButtonProperty());
    }

    public void canvasClicked(MouseEvent mouseEvent) {
        model.setPoint(mouseEvent.getX(), mouseEvent.getY());
        model.buttonSelected();
        drawShape(context);
    }

    private void drawShape(GraphicsContext context) {
        context.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        for (var shape: model.getObservableList()) {
            shape.draw(context);
        }
    }
}
