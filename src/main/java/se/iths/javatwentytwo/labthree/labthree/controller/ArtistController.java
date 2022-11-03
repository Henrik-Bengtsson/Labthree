package se.iths.javatwentytwo.labthree.labthree.controller;

import javafx.beans.binding.Bindings;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import se.iths.javatwentytwo.labthree.labthree.model.ArtistModel;
import se.iths.javatwentytwo.labthree.labthree.model.ChatModel;
import se.iths.javatwentytwo.labthree.labthree.model.shapes.ShapeType;

import java.io.File;

public class ArtistController {

    public ArtistModel artistModel = new ArtistModel();
    public ChatModel chatModel = new ChatModel();

    public GraphicsContext context;
    public Stage stage;

    public Button saveButton;
    public Button redoButton;
    public Button undoButton;

    public ToggleButton rectangleButton;
    public ToggleButton circleButton;
    public ToggleButton triangleButton;
    public ToggleButton selectButton;
    public ToggleGroup buttonToggleGroup;

    public ColorPicker colorPicker;
    public Spinner<Integer> sizeSpinner;

    public Canvas canvas;

    public ListView<String> messageChatList;
    public TextField textMessageField;
    public Button sendButtonTextField;
    public Button connectServer;
    public Button disconnectServer;
    public Label messageConnected;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        context = canvas.getGraphicsContext2D();
        colorPicker.valueProperty().bindBidirectional(artistModel.colorPickerProperty());
        sizeSpinner.getValueFactory().valueProperty().bindBidirectional(artistModel.sizeSpinnerProperty());
        sendButtonTextField.disableProperty().bind(chatModel.textMessageProperty().isEmpty());
        messageChatList.setItems(chatModel.getObservableList());
        setDisableProperty();
        setToggleButtonToShapeType();
    }

    private void setDisableProperty() {
        saveButton.disableProperty().bind(Bindings.isEmpty(artistModel.getShapeListProperty()));
        undoButton.disableProperty().bind(Bindings.isEmpty(artistModel.getUndoListProperty()));
        redoButton.disableProperty().bind(Bindings.isEmpty(artistModel.getRedoListProperty()));
        textMessageField.textProperty().bindBidirectional(chatModel.textMessageProperty());
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
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (var shape : artistModel.getShapeListProperty()) {
            shape.draw(context);
        }
    }

    public void buttonSelected() {
        if (!selectButton.isSelected())
            artistModel.createShapeToList((ShapeType) buttonToggleGroup.getSelectedToggle().getUserData());
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
        if (savePath != null)
            artistModel.saveToFile(savePath.toPath());
    }

    public void sendMessageClicked() {
        chatModel.sendMessage();
    }

    public void connectToServer() {
        chatModel.connectServer();
        chatModel.chatHandling();
        messageConnected.textProperty().setValue(connectServer.getText());
    }

    public void disconnectToServer() {
        chatModel.disconnectServer();
        messageConnected.textProperty().setValue(disconnectServer.getText());
    }
}
