package ru.mikushov.ood.shape;

import ru.mikushov.ood.canvas.Canvas;

public class Rectangle extends ShapeImpl {
    public Rectangle(Dimension<Double> frame, LineStyle lineStyle, Style fillStyle) {
        super(frame, lineStyle, fillStyle);
    }

    public Rectangle(Dimension<Double> frame, LineStyle lineStyle, Style fillStyle, boolean isNeedFeel) {
        super(frame, lineStyle, fillStyle, isNeedFeel);
    }

    @Override
    public void draw(Canvas canvas) {
        final Dimension<Double> frame = getFrame();

        int[] xPoints = {(frame.getLeft()).intValue(), (int) (frame.getLeft() + frame.getWidth()), (int) (frame.getLeft() + frame.getWidth()), (frame.getLeft()).intValue()};
        int[] yPoints = {frame.getTop().intValue(), frame.getTop().intValue(), (int) (frame.getTop() + frame.getHeight()), (int) (frame.getTop() + frame.getHeight())};

        if (isNeedFeel()) {
            canvas.setFillColor(getFillStyle().getRgbColor());
            canvas.setLineColor(getLineStyle().getRgbColor());
            canvas.setIsNeedFill(true);
            canvas.fillPolygon(xPoints, yPoints, 4);
            canvas.setIsNeedFill(false);
        }

        canvas.setLineDrawingThickness(getLineStyle().getLineDrawingThickness());
        canvas.moveTo(xPoints[0], yPoints[0]);
        canvas.lineTo(xPoints[1], yPoints[1]);
        canvas.lineTo(xPoints[2], yPoints[2]);
        canvas.lineTo(xPoints[3], yPoints[3]);
        canvas.lineTo(xPoints[0], yPoints[0]);
    }
}
