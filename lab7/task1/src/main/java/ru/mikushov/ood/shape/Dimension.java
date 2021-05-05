package ru.mikushov.ood.shape;

public class Dimension<T> {
    private T left;
    private T top;
    private T width;
    private T height;

    public Dimension(T left, T top, T width, T height) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }

    public T getLeft() {
        return left;
    }

    public void setLeft(T left) {
        this.left = left;
    }

    public T getTop() {
        return top;
    }

    public void setTop(T top) {
        this.top = top;
    }

    public T getWidth() {
        return width;
    }

    public void setWidth(T width) {
        this.width = width;
    }

    public T getHeight() {
        return height;
    }

    public void setHeight(T height) {
        this.height = height;
    }
}
