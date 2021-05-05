package ru.mikushov.ood.shape;

public class Coordinate {
    private int y;
    private int x;

    public Coordinate() {
        this(0, 0);
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }
}

