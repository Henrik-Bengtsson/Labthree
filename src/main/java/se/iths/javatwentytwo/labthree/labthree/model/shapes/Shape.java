package se.iths.javatwentytwo.labthree.labthree.model.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface Shape {

    void setColor(Color color);

    void setSize(int size);

    void draw(GraphicsContext context);
}
