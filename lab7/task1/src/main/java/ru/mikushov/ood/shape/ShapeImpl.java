package ru.mikushov.ood.shape;

import ru.mikushov.ood.Group;

public abstract class ShapeImpl implements Shape {
    private Dimension<Double> frame;

    private LineStyle lineStyle;
    private Style fillStyle;
    private boolean isNeedFeel = false;

    public ShapeImpl(Dimension<Double> frame, LineStyle lineStyle, Style fillStyle) {
        this.frame = frame;
        this.lineStyle = lineStyle;
        this.fillStyle = fillStyle;
    }

    public ShapeImpl(Dimension<Double> frame, LineStyle lineStyle, Style fillStyle, boolean isNeedFeel) {
        this(frame, lineStyle, fillStyle);
        this.isNeedFeel = isNeedFeel;
    }

    @Override
    public Dimension<Double> getFrame() {
        return frame;
    }

    @Override
    public void setFrame(Dimension<Double> frame) {
        this.frame = frame;
    }

    @Override
    public LineStyle getLineStyle() {
        return lineStyle;
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
    public Style getFillStyle() {
        return fillStyle;
    }

    @Override
    public void setFillStyle(Style style) {
        this.fillStyle = style;
    }

    @Override
    public Group getGroup() {
        return null;
    }
}
