package ru.mikushov.ood.shape;

import ru.mikushov.ood.canvas.Canvas;

public class Triangle extends ShapeImpl {
    public Triangle(Dimension<Double> frame, LineStyle lineStyle, Style fillStyle) {
        super(frame, lineStyle, fillStyle);
    }

    public Triangle(Dimension<Double> frame, LineStyle lineStyle, Style fillStyle, boolean isNeedFeel) {
        super(frame, lineStyle, fillStyle, isNeedFeel);
    }

    @Override
    public void draw(Canvas canvas) {
        final Dimension<Double> frame = getFrame();
        int[] xCoordinate = {(frame.getLeft()).intValue(), (int) (frame.getLeft() + frame.getWidth() / 2.0f), (int) (frame.getLeft() + frame.getWidth())};
        int[] yCoordinate = {(int) (frame.getTop() + frame.getHeight()), frame.getTop().intValue(), (int) (frame.getTop() + frame.getHeight())};

        if (isNeedFeel()) {
            canvas.setFillColor(getFillStyle().getRgbColor());
            canvas.setLineColor(getLineStyle().getRgbColor());
            canvas.setIsNeedFill(true);
            canvas.fillPolygon(xCoordinate, yCoordinate, 3);
            canvas.setIsNeedFill(false);
        }

        canvas.moveTo(xCoordinate[0], yCoordinate[0]);
        canvas.lineTo(xCoordinate[1], yCoordinate[1]);
        canvas.lineTo(xCoordinate[2], yCoordinate[2]);
        canvas.lineTo(xCoordinate[0], yCoordinate[0]);
    }

    private int toInt(Double aDouble) {
        return Integer.parseInt(aDouble.toString());
    }
}
