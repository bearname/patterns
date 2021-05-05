package ru.mikushov.ood.slide;

import ru.mikushov.ood.Group;
import ru.mikushov.ood.canvas.Canvas;
import ru.mikushov.ood.shape.Dimension;
import ru.mikushov.ood.shape.LineStyle;
import ru.mikushov.ood.shape.Shape;
import ru.mikushov.ood.shape.Style;

import java.util.ArrayList;
import java.util.List;

public class GroupImpl implements Group {

    private final List<Shape> shapes = new ArrayList<>();
    private Dimension<Double> frame = new Dimension<>(0.0, 0.0, 500.0, 400.0);
    private LineStyle lineStyle;
    private Style fillStyle;
    private boolean isNeedFeel = false;

    public GroupImpl(LineStyle lineStyle, Style fillStyle) {
        this.lineStyle = lineStyle;
        this.fillStyle = fillStyle;
    }

    public GroupImpl(Dimension<Double> frame, LineStyle lineStyle, Style fillStyle) {
        this.frame = frame;
        this.lineStyle = lineStyle;
        this.fillStyle = fillStyle;
    }

    public GroupImpl(Dimension<Double> frame, LineStyle lineStyle, Style fillStyle, boolean isNeedFeel) {
        this(frame, lineStyle, fillStyle);
        this.isNeedFeel = isNeedFeel;
    }

    @Override
    public int getShapeCount() {
        return shapes.size();
    }

    @Override
    public Shape getShapeAtIndex(int index) throws Exception {
        if (index < 0 || index >= shapes.size()) {
            throw new Exception("Invalid index. Valid index range [0, " + shapes.size() + "]");
        }
        return shapes.get(index);
    }

    @Override
    public void insertShape(Shape shape, int index) throws Exception {
        if (index < 0 || index > shapes.size()) {
            throw new Exception("Invalid index. Valid index range [0, " + shapes.size() + "]");
        }

        shapes.add(index, shape);
    }

    @Override
    public void removeShapeAtIndex(int index) {
        final Shape shape = shapes.get(index);
        if (shape != null) {
            shapes.remove(index);
        }
    }

    @Override
    public Group getGroup() {
        return this;
    }

    @Override
    public void setFillStyle(Style style) {
        this.fillStyle = style;
    }

    @Override
    public Style getFillStyle() {
        return fillStyle;
    }

    @Override
    public void setLineStyle(LineStyle style) {
        this.lineStyle = style;
    }

    @Override
    public void setIsNeedFeel(boolean isNeedFeel) {
        this.isNeedFeel = isNeedFeel;
    }

    @Override
    public boolean isNeedFeel() {
        return isNeedFeel;
    }

    @Override
    public Style getLineStyle() {
        return lineStyle;
    }

    @Override
    public void setFrame(Dimension<Double> frame) {
        final double scaleX = frame.getWidth() / this.frame.getWidth();
        final double scaleY = frame.getHeight() / this.frame.getHeight();
        final Double oldTop = this.frame.getTop();
        final Double oldLeft = this.frame.getLeft();

        this.frame = frame;

        shapes.forEach(shape -> {
            final Dimension<Double> shapeFrame = shape.getFrame();

            double shiftX = shapeFrame.getLeft() - oldLeft;
            double shiftY = shapeFrame.getTop() - oldTop;

            shapeFrame.setLeft(frame.getLeft() + shiftX * scaleX);
            shapeFrame.setTop(frame.getTop() + shiftY * scaleY);
            shapeFrame.setWidth(shapeFrame.getWidth() * scaleX);
            shapeFrame.setHeight(shapeFrame.getHeight() * scaleY);
        });
    }

    @Override
    public Dimension<Double> getFrame() {
        return frame;
    }

    public void draw(Canvas canvas) {
        shapes.forEach(shape -> shape.draw(canvas));
    }
}
