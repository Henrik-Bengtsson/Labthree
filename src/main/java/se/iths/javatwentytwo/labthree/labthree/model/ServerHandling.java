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

    StringProperty textMessage = new SimpleStringProperty();
    ObservableList<String> observableChatList = FXCollections.observableArrayList();
    BooleanProperty connected = new SimpleBooleanProperty(false);
    private Socket socket = null;
    private PrintWriter writer = null;
    private BufferedReader read = null;

    public void connectServer(ArtistModel artistModel) {
        Thread thread = new Thread(() -> {
            try {
                setupServer();
                readMessageServer(artistModel);
            } catch (IOException ignored) {
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

    private void readMessageServer(ArtistModel artistModel) throws IOException {
        while (connected.get()) {
            String line = read.readLine();
            Platform.runLater(() -> separateInput(line, artistModel));
        }
    }

    private void separateInput(String line, ArtistModel artistModel) {
        if (line.contains("<rect") || line.contains("<circle") || line.contains("<polyline"))
            artistModel.addSvgToShapeList(line);
        else
            observableChatList.add(line);
    }

    public void disconnectServer() {
        try {
            socket.close();
            read.close();
            writer.close();
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

    public ObservableList<String> getObservableChatList() {
        return observableChatList;
    }

    public void sendMessage() {
        writer.println(getTextMessage());
        setTextMessage("");
    }

    public void sendShape(Shape shape) {
        writer.println(shape.svgFormat());
    }

    public BooleanProperty connectedProperty() {
        return connected;
    }
}
