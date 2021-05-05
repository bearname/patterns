package ru.mikushov.ood;

import ru.mikushov.ood.shape.Shape;

public interface Group extends Shape {
    int getShapeCount();

    Shape getShapeAtIndex(final int index) throws Exception;

    void insertShape(Shape shape, final int index) throws Exception;

    void removeShapeAtIndex(final int index);
}
