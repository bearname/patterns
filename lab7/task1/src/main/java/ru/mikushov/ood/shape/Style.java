package ru.mikushov.ood.shape;

public interface Style {
    boolean isEnabled();

    void enable(boolean enable);

    RGBColor getRgbColor();

    void setRgbColor(RGBColor color);
}
