package ru.mikushov.ood.color.libs.graphicslib;

public interface Canvas {
    void setColor(int rgbColor);

    void moveTo(int x, int y);

    void lineTo(int x, int y);
}
