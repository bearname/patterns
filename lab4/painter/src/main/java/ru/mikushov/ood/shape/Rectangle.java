package ru.mikushov.ood.shape;

import ru.mikushov.ood.canvas.MyCanvas;

public class Rectangle extends Shape {
    private Dot leftTop;
    private Dot rightBottom;

    public Rectangle(Color color, Dot leftTop, Dot rightBottom) {
        super(color);
        this.leftTop = leftTop;
        this.rightBottom = rightBottom;
    }

    public Dot getLeftTop() {
        return leftTop;
    }

    public Dot getRightBottom() {
        return rightBottom;
    }

    public void setLeftTop(Dot leftTop) {
        this.leftTop = leftTop;
    }

    public void setRightBottom(Dot rightBottom) {
        this.rightBottom = rightBottom;
    }

    @Override
    public void draw(MyCanvas canvas) {
        System.out.println("rectangle");

        canvas.setColor(getColor());
        final Dot rightTop = new Dot(rightBottom.getX(), leftTop.getY());
        final Dot leftBottom = new Dot(leftTop.getX(), rightBottom.getY());

        canvas.drawLine(leftTop, rightTop);
        canvas.drawLine(rightTop, rightBottom);
        canvas.drawLine(rightBottom, leftBottom);
        canvas.drawLine(leftBottom, leftTop);
    }
}
