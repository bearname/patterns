package ru.mikushov.ood.canvas;

import ru.mikushov.ood.shape.Coordinate;
import ru.mikushov.ood.shape.RGBColor;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import static ru.mikushov.ood.Colors.RED_COLOR;
import static ru.mikushov.ood.Colors.WHITE_COLOR;


public class JavaCanvas implements Canvas {
    private final Graphics2D graphics2D;
    private final BufferedImage bufferedImage;
    private final Coordinate currentCoordinate = new Coordinate(0, 0);
    private RGBColor fillColor = WHITE_COLOR;
    private RGBColor lineColor = RED_COLOR;
    private int thickness = 1;
    private boolean isNeedFill = false;

    public JavaCanvas() {
        this(1920, 1080);
    }

    public JavaCanvas(int width, int height) {
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        graphics2D = bufferedImage.createGraphics();
    }

    @Override
    public void beginFill(RGBColor color) {

    }

    @Override
    public void endFill() {

    }

    @Override
    public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        graphics2D.fillPolygon(xPoints, yPoints, nPoints);
    }

    @Override
    public void moveTo(int x, int y) {
        currentCoordinate.setX(x);
        currentCoordinate.setY(y);
    }

    @Override
    public void lineTo(int x, int y) {
        graphics2D.setColor(toAwtColor(lineColor));
        graphics2D.setStroke(new BasicStroke(thickness));
        graphics2D.drawLine(currentCoordinate.getX(), currentCoordinate.getY(), x, y);
        moveTo(x, y);
    }

    @Override
    public void drawEllipse(double x, double y, double width, double height) {
        graphics2D.setColor(toAwtColor(fillColor));
        graphics2D.setStroke(new BasicStroke(thickness));
        final Ellipse2D.Double ellipse = new Ellipse2D.Double(x, y, width, height);
        graphics2D.draw(ellipse);
        if (isNeedFill) {
            graphics2D.setColor(toAwtColor(fillColor));
            graphics2D.fill(ellipse);
        }
    }

    @Override
    public void setFillColor(RGBColor color) {
        this.fillColor = color;
    }

    @Override
    public void setLineColor(RGBColor color) {
        this.lineColor = color;
    }

    @Override
    public void setLineDrawingThickness(int thickness) {
        this.thickness = thickness;
    }

    @Override
    public boolean getIsNeedFill() {
        return isNeedFill;
    }

    @Override
    public void setIsNeedFill(boolean isNeedFill) {
        this.isNeedFill = isNeedFill;
    }

    public BufferedImage getBufferedImage() {
        graphics2D.dispose();
        return bufferedImage;
    }

    private java.awt.Color toAwtColor(RGBColor color) {
        return new Color(color.getValue());
    }
}
