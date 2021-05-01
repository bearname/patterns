package ru.mikushov.ood.canvas;

import ru.mikushov.ood.shape.Color;
import ru.mikushov.ood.shape.Dot;

public class ConsoleCanvas implements MyCanvas {
    private Color color = Color.Black;

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void drawLine(Dot from, Dot to) {
        System.out.println("Draw " + color + " line from " + from + " to " + to);
    }

    @Override
    public void drawEllipse(double x, double y, double width, double height) {
        System.out.println("Draw " + color + " ellipse left " + x + " top " + y + " width " + width + " height " + height);
    }
}
