package ru.mikushov.ood.slide;

import ru.mikushov.ood.canvas.Canvas;
import ru.mikushov.ood.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class SlideImpl implements Slide {
    private double width = 1200;
    private double height = 720;
    private final List<Shape> shapes = new ArrayList<>();

    public SlideImpl() {
    }

    public SlideImpl(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public List<Shape> getShapes() {
        return shapes;
    }

    @Override
    public void draw(Canvas canvas) {
        shapes.forEach(shape -> shape.draw(canvas));
    }
}
