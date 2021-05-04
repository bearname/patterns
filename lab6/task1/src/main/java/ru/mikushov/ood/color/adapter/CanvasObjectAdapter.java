package ru.mikushov.ood.color.adapter;

import ru.mikushov.ood.color.libs.graphicslib.Canvas;
import ru.mikushov.ood.color.libs.moderngraphiclib.ModernGraphicsRenderer;
import ru.mikushov.ood.color.libs.moderngraphiclib.Point;
import ru.mikushov.ood.color.libs.moderngraphiclib.RGBAColor;

import java.awt.*;

public class CanvasObjectAdapter implements Canvas {
    private final ModernGraphicsRenderer modernGraphicsRenderer;
    private Point start = new Point(0, 0);
    private RGBAColor color = toRGBA(0);

    public CanvasObjectAdapter(ModernGraphicsRenderer modernGraphicsRenderer) {
        this.modernGraphicsRenderer = modernGraphicsRenderer;
    }

    @Override
    public void setColor(int color) {
        this.color = toRGBA(color);
    }

    private RGBAColor toRGBA(int color) {
        final Color color1 = new Color(color);
       return new RGBAColor(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha());
    }

    @Override
    public void moveTo(int x, int y) {
        start = new Point(x, y);
    }

    @Override
    public void lineTo(int x, int y) {
        try {
            final Point end = new Point(x, y);

            modernGraphicsRenderer.drawLine(start, end, color);
            start = end;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
