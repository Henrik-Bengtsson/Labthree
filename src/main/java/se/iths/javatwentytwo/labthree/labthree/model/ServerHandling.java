package se.iths.javatwentytwo.labthree.labthree.model;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import se.iths.javatwentytwo.labthree.labthree.model.shapes.Shape;

import java.io.*;
import java.net.Socket;

public class ServerHandling {
    ArtistModel artistModel = new ArtistModel();
    StringProperty textMessage = new SimpleStringProperty();
    ObservableList<String> observableList = FXCollections.observableArrayList();
    BooleanProperty connected = new SimpleBooleanProperty(false);
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader read;

    public void connectServer() {
        Thread thread = new Thread(() -> {
            try {
                setupServer();
                readMessageServer();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    private void setupServer() throws IOException {
        socket = new Socket("localhost", 8000);
        OutputStream output = socket.getOutputStream();
        writer = new PrintWriter(output, true);
        InputStream input = socket.getInputStream();
        read = new BufferedReader(new InputStreamReader(input));
    }

    private void readMessageServer() throws IOException {
        while(connected.get()) {
            String line = read.readLine();
            Platform.runLater(() -> separateInput(line));
        }
    }

    private void separateInput(String line){
        if(line.contains("<rect") || line.contains("<circle") || line.contains("<polyline"))
            artistModel.addSvgToShapeList(line);
        else
            observableList.add(line);
    }

    public void disconnectServer() {
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTextMessage() {
        return textMessage.get();
    }

    public StringProperty textMessageProperty() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage.set(textMessage);
    }

    public ObservableList<String> getObservableList() {
        return observableList;
    }

    public void sendMessage() {
        writer.println(getTextMessage());
        setTextMessage("");
    }

    public void sendShape(Shape shape){
        writer.println(shape.svgFormat());
    }

    public BooleanProperty connectedProperty() {
        return connected;
    }
}
