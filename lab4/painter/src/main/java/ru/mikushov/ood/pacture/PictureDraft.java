package ru.mikushov.ood.pacture;

import ru.mikushov.ood.shape.Shape;

import java.util.List;

public interface PictureDraft {
    void addShape(Shape shape);

    int size();

    List<Shape> getShapes();
}
