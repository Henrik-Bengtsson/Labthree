package se.iths.javatwentytwo.labthree.labthree.model;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.net.Socket;

public class ChatModel {

    StringProperty textMessage = new SimpleStringProperty();
    ObservableList<String> observableList = FXCollections.observableArrayList();
    private Socket socket;
    private final PrintWriter writer;
    private final BufferedReader reader;

    public ChatModel(){
        try {
            socket = new Socket("localhost", 8000);
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));

            var thread = new Thread(() -> {
                try {
                    while (true) {
                        String line = reader.readLine();
                        Platform.runLater(()-> observableList.add(line));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.setDaemon(true);
            thread.start();

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

    public void setObservableList(ObservableList<String> observableList) {
        this.observableList = observableList;
    }

    public void sendMessage(){
        writer.println(getTextMessage());
        setTextMessage("");
    }
}
