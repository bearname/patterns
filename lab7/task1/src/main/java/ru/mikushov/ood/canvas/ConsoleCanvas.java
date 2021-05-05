package ru.mikushov.ood.canvas;

import ru.mikushov.ood.shape.RGBColor;

public class ConsoleCanvas implements Canvas {
    @Override
    public void beginFill(RGBColor color) {
        System.out.println("BeginFill");
    }

    @Override
    public void endFill() {
        System.out.println("EndFill");

    }

    @Override
    public void fillPolygon(int[] xPoints, int[] yPoints, int numberPoints) {
        System.out.println("fillPolygon xpoints " + xPoints.length + " ypoints " + yPoints.length + " " + numberPoints);
    }

    @Override
    public void moveTo(int x, int y) {
        System.out.println("MoveTo " + x + " " + y);

    }

    @Override
    public void lineTo(int x, int y) {
        System.out.println("LineTo " + x + " " + y);

    }

    @Override
    public void drawEllipse(double left, double top, double width, double height) {
        System.out.println("drawEllipse " + left + " " + top + " " + width + " " + height);
    }

    @Override
    public void setFillColor(RGBColor color) {
    }

    @Override
    public void setLineColor(RGBColor color) {
    }

    @Override
    public void setLineDrawingThickness(int thickness) {

    }

    @Override
    public boolean getIsNeedFill() {
        return false;
    }

    @Override
    public void setIsNeedFill(boolean isNeedFill) {

    }
}
