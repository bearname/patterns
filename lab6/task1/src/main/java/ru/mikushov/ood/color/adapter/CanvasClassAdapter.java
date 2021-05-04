package ru.mikushov.ood.color.adapter;

import ru.mikushov.ood.color.libs.graphicslib.Canvas;
import ru.mikushov.ood.color.libs.moderngraphiclib.ModernGraphicsRenderer;
import ru.mikushov.ood.color.libs.moderngraphiclib.Point;
import ru.mikushov.ood.color.libs.moderngraphiclib.RGBAColor;

import java.awt.*;
import java.io.OutputStream;

public class CanvasClassAdapter extends ModernGraphicsRenderer implements Canvas {
    private Point start = new Point(0, 0);
    private RGBAColor color = toRGBA(0);

    public CanvasClassAdapter(OutputStream outputStream) {
        super(outputStream);
    }

    @Override
    public void setColor(int rgbColor) {
        this.color = toRGBA(rgbColor);
    }

    @Override
    public void moveTo(int x, int y) {
        start = new Point(x, y);
    }

    @Override
    public void lineTo(int x, int y) {
        try {
            final Point end = new Point(x, y);
            drawLine(start, end, color);
            start = end;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private RGBAColor toRGBA(int color) {
        final Color color1 = new Color(color);
        return new RGBAColor(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha());
    }
}
