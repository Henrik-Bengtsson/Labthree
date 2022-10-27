package se.iths.javatwentytwo.labthree.labthree;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ArtistMaster extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ArtistMaster.class.getResource("labThree.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 600);
        stage.setTitle("Artist Master");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}