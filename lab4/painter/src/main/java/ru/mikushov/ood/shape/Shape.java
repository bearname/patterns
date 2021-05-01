package ru.mikushov.ood.shape;

import ru.mikushov.ood.canvas.MyCanvas;

public abstract class Shape {
    private final Color color;

    public Shape(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public abstract void draw(MyCanvas canvas);
}
