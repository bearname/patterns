package ru.mikushov.ood.shape;

import ru.mikushov.ood.canvas.Canvas;

public class Ellipse extends ShapeImpl {
    public Ellipse(Dimension<Double> frame, LineStyle lineStyle, Style fillStyle) {
        super(frame, lineStyle, fillStyle);
    }

    public Ellipse(Dimension<Double> frame, LineStyle lineStyle, Style fillStyle, boolean isNeedFeel) {
        super(frame, lineStyle, fillStyle, isNeedFeel);
    }

    @Override
    public void draw(Canvas canvas) {
        Dimension<Double> frame = getFrame();
        canvas.setFillColor(getFillStyle().getRgbColor());
        canvas.setLineColor(getLineStyle().getRgbColor());
        if (isNeedFeel()) {
            canvas.setIsNeedFill(true);
        }
        canvas.drawEllipse(frame.getLeft(), frame.getTop(), frame.getWidth(), frame.getHeight());
        canvas.setIsNeedFill(false);

    }
}
