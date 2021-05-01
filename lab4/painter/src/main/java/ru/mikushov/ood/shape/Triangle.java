package ru.mikushov.ood.shape;

import ru.mikushov.ood.canvas.MyCanvas;

public class Triangle extends Shape {
    private Dot vertex1;
    private Dot vertex2;
    private Dot vertex3;

    public Triangle(Color color, Dot vertex1, Dot vertex2, Dot vertex3) {
        super(color);
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
    }

    @Override
    public void draw(MyCanvas canvas) {
        System.out.println("Triangle");
        canvas.setColor(getColor());

        canvas.drawLine(vertex1, vertex2);
        canvas.drawLine(vertex2, vertex3);
        canvas.drawLine(vertex3, vertex1);
    }

    public Dot getVertex1() {
        return vertex1;
    }

    public void setVertex1(Dot vertex1) {
        this.vertex1 = vertex1;
    }

    public Dot getVertex2() {
        return vertex2;
    }

    public void setVertex2(Dot vertex2) {
        this.vertex2 = vertex2;
    }

    public Dot getVertex3() {
        return vertex3;
    }

    public void setVertex3(Dot vertex3) {
        this.vertex3 = vertex3;
    }
}
