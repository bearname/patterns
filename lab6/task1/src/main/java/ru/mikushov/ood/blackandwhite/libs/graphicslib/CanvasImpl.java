package ru.mikushov.ood.blackandwhite.libs.graphicslib;

public class CanvasImpl implements Canvas {
    @Override
    public void moveTo(int x, int y) {
        System.out.println("MoveTo (" + x + ", " + y + ")");
    }

    @Override
    public void lineTo(int x, int y) {
        System.out.println("LineTo (" + x + ", " + y + ")");
    }
}
