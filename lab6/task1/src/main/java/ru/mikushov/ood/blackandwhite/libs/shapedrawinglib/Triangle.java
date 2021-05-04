package ru.mikushov.ood.blackandwhite.libs.shapedrawinglib;

import ru.mikushov.ood.blackandwhite.libs.graphicslib.Canvas;

public class Triangle implements CanvasDrawable {
    private final Point point1;
    private final Point point2;
    private final Point point3;

    public Triangle(Point point1, Point point2, Point point3) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }

    @Override
    public void draw(Canvas canvas) {
        System.out.println("Triangle " + point1 + " " + point2 + " " + point3);
    }
}
