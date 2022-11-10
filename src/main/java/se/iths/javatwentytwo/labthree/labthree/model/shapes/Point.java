package se.iths.javatwentytwo.labthree.labthree.model.shapes;

import javafx.beans.property.SimpleDoubleProperty;

import java.util.Objects;

public class Point {

    private final SimpleDoubleProperty posX = new SimpleDoubleProperty();
    private final SimpleDoubleProperty posY = new SimpleDoubleProperty();

    public Point(double posX, double posY) {
        this.posX.set(posX);
        this.posY.set(posY);
    }

    public SimpleDoubleProperty posXProperty() {
        return posX;
    }

    public SimpleDoubleProperty posYProperty() {
        return posY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point point)) return false;
        return Objects.equals(posX, point.posX) && Objects.equals(posY, point.posY);
    }

    @Override
    public int hashCode() {
        return Objects.hash(posX, posY);
    }
}
