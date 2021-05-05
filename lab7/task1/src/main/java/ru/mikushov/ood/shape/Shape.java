package ru.mikushov.ood.shape;

import ru.mikushov.ood.Group;
import ru.mikushov.ood.canvas.Canvas;

public interface Shape {
    Dimension<Double> getFrame();

    void setFrame(Dimension<Double> frame);

    Style getLineStyle();

    void setLineStyle(LineStyle style);

    void setIsNeedFeel(boolean isNeedFeel);

    boolean isNeedFeel();

    Style getFillStyle();

    void setFillStyle(Style style);

    void draw(Canvas canvas);

    Group getGroup();
}
