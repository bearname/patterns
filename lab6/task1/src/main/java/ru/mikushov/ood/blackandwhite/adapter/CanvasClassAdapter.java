package ru.mikushov.ood.blackandwhite.adapter;

import ru.mikushov.ood.blackandwhite.libs.graphicslib.Canvas;
import ru.mikushov.ood.blackandwhite.libs.moderngraphiclib.ModernGraphicsRenderer;
import ru.mikushov.ood.blackandwhite.libs.moderngraphiclib.Point;

import java.io.OutputStream;

public class CanvasClassAdapter extends ModernGraphicsRenderer implements Canvas {
    private Point start = new Point(0, 0);

    public CanvasClassAdapter(OutputStream outputStream) {
        super(outputStream);
    }

    @Override
    public void moveTo(int x, int y) {
        start = new Point(x, y);
    }

    @Override
    public void lineTo(int x, int y) {
        try {
            final Point end = new Point(x, y);
            drawLine(start, end);
            start = end;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
