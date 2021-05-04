package ru.mikushov.ood.color.libs.graphicslib;

public class CanvasImpl implements Canvas {
    @Override
    public void setColor(int rgbColor) {
        System.out.println("setColor " + rgbColor);
    }

    @Override
    public void moveTo(int x, int y) {
        System.out.println("MoveTo (" + x + ", " + y + ")");
    }

    @Override
    public void lineTo(int x, int y) {
        System.out.println("LineTo (" + x + ", " + y + ")");
    }
}
