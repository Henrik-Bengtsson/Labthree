package se.iths.javatwentytwo.labthree.labthree.model;

public class Point {

    private double mousePosX;
    private double mousePosY;

    public Point(double mousePosX, double mousePosY) {
        this.mousePosX = mousePosX;
        this.mousePosY = mousePosY;
    }

    public double getMousePosX() {
        return mousePosX;
    }

    public void setMousePosX(double mousePosX) {
        this.mousePosX = mousePosX;
    }

    public double getMousePosY() {
        return mousePosY;
    }

    public void setMousePosY(double mousePosY) {
        this.mousePosY = mousePosY;
    }
}
