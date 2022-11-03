module se.iths.javatwentytwo.labthree.labthree {
    requires javafx.controls;
    requires javafx.fxml;


    opens se.iths.javatwentytwo.labthree.labthree to javafx.fxml;
    exports se.iths.javatwentytwo.labthree.labthree;
    exports se.iths.javatwentytwo.labthree.labthree.controller;
    opens se.iths.javatwentytwo.labthree.labthree.controller to javafx.fxml;
    exports se.iths.javatwentytwo.labthree.labthree.model;
    opens se.iths.javatwentytwo.labthree.labthree.model to javafx.fxml;
    exports se.iths.javatwentytwo.labthree.labthree.model.shapes;
    opens se.iths.javatwentytwo.labthree.labthree.model.shapes to javafx.fxml;
    exports se.iths.javatwentytwo.labthree.labthree.model.command;
    opens se.iths.javatwentytwo.labthree.labthree.model.command to javafx.fxml;
}