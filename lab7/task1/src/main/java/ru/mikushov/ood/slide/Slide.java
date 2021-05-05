package ru.mikushov.ood.slide;

import ru.mikushov.ood.shape.Shape;

import java.util.List;

public interface Slide extends Drawable {
    double getWidth();

    double getHeight();

    List<Shape> getShapes();
}
