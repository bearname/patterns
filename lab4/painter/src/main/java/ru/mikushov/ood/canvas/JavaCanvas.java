package ru.mikushov.ood.canvas;

import ru.mikushov.ood.shape.Color;
import ru.mikushov.ood.shape.Dot;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

public class JavaCanvas implements MyCanvas {
    private java.awt.Color color = java.awt.Color.RED;

    private final Graphics2D graphics2D;
    private final BufferedImage bufferedImage;

    public JavaCanvas() {
        this(400, 300);
    }

    public JavaCanvas(int width, int height) {
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        graphics2D = bufferedImage.createGraphics();
        graphics2D.setBackground(java.awt.Color.WHITE);
    }

    @Override
    public void setColor(Color color) {
        this.color = toAwtColor(color);
    }

    @Override
    public void drawLine(Dot from, Dot to) {
        graphics2D.setColor(color);
        graphics2D.drawLine((int) from.getX(), (int) from.getY(), (int) to.getX(), (int) to.getY());
        graphics2D.draw(new Line2D.Double(from.getX(), from.getY(), to.getX(), to.getY()));
    }

    @Override
    public void drawEllipse(double x, double y, double width, double height) {
        graphics2D.setColor(java.awt.Color.white);
        graphics2D.draw(new Ellipse2D.Double(x, y, width, height));
    }

    private java.awt.Color toAwtColor(Color color) {
        if (color == Color.Black) {
            return java.awt.Color.BLACK;
        } else if (color == Color.Green) {
            return java.awt.Color.GREEN;
        } else if (color == Color.Red) {
            return java.awt.Color.RED;
        } else if (color == Color.Blue) {
            return java.awt.Color.BLUE;
        } else if (color == Color.Yellow) {
            return java.awt.Color.YELLOW;
        } else if (color == Color.Pink) {
            return java.awt.Color.PINK;
        }
        return java.awt.Color.WHITE;
    }

    public BufferedImage getBufferedImage() {
        graphics2D.dispose();
        return bufferedImage;
    }
}
