package ru.mikushov.ood.shape;


import static ru.mikushov.ood.Colors.RED_COLOR;

public class FillStyle implements Style {
    private boolean isEnabled = false;
    private RGBColor color = RED_COLOR;

    public FillStyle() {
    }

    public FillStyle(RGBColor color) {
        this.color = color;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public void enable(boolean enable) {
        this.isEnabled = enable;
    }

    @Override
    public RGBColor getRgbColor() {
        return color;
    }

    @Override
    public void setRgbColor(RGBColor color) {
        this.color = color;
    }
}
