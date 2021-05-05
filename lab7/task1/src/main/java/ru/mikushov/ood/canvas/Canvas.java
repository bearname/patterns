package ru.mikushov.ood.canvas;

import ru.mikushov.ood.shape.RGBColor;

public interface Canvas {
    void beginFill(RGBColor color);

    void endFill();

    void fillPolygon(int[] xPoints, int[] yPoints, int numberPoints);

    void moveTo(int x, int y);

    void lineTo(int x, int y);

    void drawEllipse(double left, double top, double width, double height);

    void setFillColor(RGBColor color);

    void setLineColor(RGBColor color);

    void setLineDrawingThickness(int thickness);

    boolean getIsNeedFill();

    void setIsNeedFill(boolean isNeedFill);
}
