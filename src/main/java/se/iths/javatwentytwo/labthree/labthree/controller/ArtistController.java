package se.iths.javatwentytwo.labthree.labthree.controller;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import se.iths.javatwentytwo.labthree.labthree.model.ArtistModel;
import se.iths.javatwentytwo.labthree.labthree.model.shapes.ShapeType;

import java.io.File;

public class ArtistController {

    public ArtistModel artistModel = new ArtistModel();

    public GraphicsContext context;
    public Stage stage;

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
    @FXML
    public ToggleGroup buttonToggleGroup;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize(){
        context = canvas.getGraphicsContext2D();
        colorPicker.valueProperty().bindBidirectional(artistModel.colorPickerProperty());
        sizeSpinner.getValueFactory().valueProperty().bindBidirectional(artistModel.sizeSpinnerProperty());
        saveButton.disableProperty().bind(Bindings.isEmpty(artistModel.getShapeListProperty()));
        setToggleButtonToShapeType();
    }

    private void setToggleButtonToShapeType() {
        rectangleButton.setUserData(ShapeType.RECT);
        circleButton.setUserData(ShapeType.CIRCLE);
        triangleButton.setUserData(ShapeType.TRIANGLE);
    }

    public void canvasClicked(MouseEvent mouseEvent) {
        artistModel.setPoint(mouseEvent.getX(), mouseEvent.getY());
        buttonSelected();
        drawShape(context);
    }

    private void drawShape(GraphicsContext context) {
        context.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        for (var shape: artistModel.getShapeListProperty()) {
            shape.draw(context);
        }
    }

    public void buttonSelected(){
        if(!selectButton.isSelected()) {
            artistModel.createShapeToList((ShapeType) buttonToggleGroup.getSelectedToggle().getUserData());
        }
        else
            artistModel.changeShape(colorPicker.getValue(), sizeSpinner.getValue());
    }

    public void undoButtonClicked() {
        artistModel.undoLastCommand();
        drawShape(context);
    }

    public void redoButtonClicked() {
        artistModel.redoLastCommand();
        drawShape(context);
    }

    public void saveButtonClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save location");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SVG", "*.svg"));

        File savePath = fileChooser.showSaveDialog(stage);
        if(savePath != null)
            artistModel.saveToFile(savePath.toPath());
    }
}
