package ru.mikushov.ood.shape;

import ru.mikushov.ood.canvas.MyCanvas;

public class Ellipse extends Shape {
    private final Dot center;
    private final double horizontalRadius;
    private final double verticalRadius;

    public Ellipse(Color color, Dot center, double horizontalRadius, double verticalRadius) {
        super(color);
        this.center = center;
        this.horizontalRadius = horizontalRadius;
        this.verticalRadius = verticalRadius;
    }

    @Override
    public void draw(MyCanvas canvas) {
        System.out.println("Ellipse");
        canvas.setColor(getColor());
        canvas.drawEllipse(center.getX(), center.getY(), horizontalRadius, verticalRadius);
    }

    public Dot getCenter() {
        return center;
    }

    public double getHorizontalRadius() {
        return horizontalRadius;
    }

    public double getVerticalRadius() {
        return verticalRadius;
    }
}
