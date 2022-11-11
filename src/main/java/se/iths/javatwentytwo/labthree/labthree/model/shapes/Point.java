package se.iths.javatwentytwo.labthree.labthree.model.shapes;

import java.util.Objects;

public class Point {

    private final double posX;
    private final double posY;

    public Point(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point point)) return false;
        return Double.compare(point.posX, posX) == 0 && Double.compare(point.posY, posY) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(posX, posY);
    }
}
