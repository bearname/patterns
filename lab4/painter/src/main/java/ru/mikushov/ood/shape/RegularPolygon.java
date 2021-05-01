package ru.mikushov.ood.shape;

import ru.mikushov.ood.canvas.MyCanvas;

public class RegularPolygon extends Shape {
    private final Dot center;
    private final int vertexCount;
    private final double radius;

    public RegularPolygon(Color color, Dot center, double radius, int vertexCount) {
        super(color);

        this.center = center;
        this.vertexCount = vertexCount;
        this.radius = radius;
    }

    public Dot getCenter() {
        return center;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public void draw(MyCanvas canvas) {
        System.out.println("Regular polygon");
        canvas.setColor(getColor());

        double angles = 2 * Math.PI / this.vertexCount;
        Dot previousVertex = new Dot(this.center.getX() + this.radius * Math.cos(0), this.center.getY());

        for (int i = 1; i <= vertexCount; ++i) {
            Dot tempVertex = new Dot(this.radius * Math.cos(angles * i), this.radius * Math.sin(angles * i));
            Dot vertex = new Dot(this.center.getX() + tempVertex.getX(), this.center.getY() + tempVertex.getY());
            canvas.drawLine(previousVertex, vertex);
            previousVertex = vertex;
        }
    }
}
