package ru.mikushov.ood.canvas;

import ru.mikushov.ood.shape.Color;
import ru.mikushov.ood.shape.Dot;

public interface MyCanvas {
    void setColor(Color color);

    void drawLine(Dot from, Dot to);

    void drawEllipse(double x, double y, double width, double height);
}
