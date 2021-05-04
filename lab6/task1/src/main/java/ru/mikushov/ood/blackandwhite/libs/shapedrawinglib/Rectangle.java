package ru.mikushov.ood.blackandwhite.libs.shapedrawinglib;

import ru.mikushov.ood.blackandwhite.libs.graphicslib.Canvas;

public class Rectangle implements CanvasDrawable {
    private final Point leftTop;
    private final int width;
    private final int height;

    public Rectangle(Point leftTop, int width, int height) {
        this.leftTop = leftTop;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Canvas canvas) {
        System.out.println("Rectangle leftTop " + leftTop + " width " + width + " height " + height);
    }
}
