package ru.mikushov.ood.blackandwhite.adapter;

import ru.mikushov.ood.blackandwhite.libs.graphicslib.Canvas;
import ru.mikushov.ood.blackandwhite.libs.moderngraphiclib.ModernGraphicsRenderer;
import ru.mikushov.ood.blackandwhite.libs.moderngraphiclib.Point;

public class CanvasObjectAdapter implements Canvas {
    private final ModernGraphicsRenderer modernGraphicsRenderer;
    private Point start = new Point(0, 0);

    public CanvasObjectAdapter(ModernGraphicsRenderer modernGraphicsRenderer) {
        this.modernGraphicsRenderer = modernGraphicsRenderer;
    }

    @Override
    public void moveTo(int x, int y) {
        start = new Point(x, y);
    }

    @Override
    public void lineTo(int x, int y) {
        try {
            final Point end = new Point(x, y);
            modernGraphicsRenderer.drawLine(start, end);
            start = end;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
