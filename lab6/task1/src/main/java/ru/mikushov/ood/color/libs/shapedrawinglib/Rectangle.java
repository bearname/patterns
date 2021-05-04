package ru.mikushov.ood.color.libs.shapedrawinglib;

import ru.mikushov.ood.color.libs.graphicslib.Canvas;

public class Rectangle implements CanvasDrawable {
    private final Point leftTop;
    private final int width;
    private final int height;
    private int rgbColor = 0;

    public Rectangle(Point leftTop, int width, int height) {
        this.leftTop = leftTop;
        this.width = width;
        this.height = height;
    }

    public Rectangle(Point leftTop, int width, int height, int rgbColor) {
        this(leftTop, width, height);
        this.rgbColor = rgbColor;
    }

    @Override
    public void draw(Canvas canvas) {
        System.out.println("Rectangle color " + rgbColor + " leftTop " + leftTop + " width " + width + " height " + height);
    }
}
