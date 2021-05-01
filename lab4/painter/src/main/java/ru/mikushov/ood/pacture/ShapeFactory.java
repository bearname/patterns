package ru.mikushov.ood.pacture;

import ru.mikushov.ood.shape.Shape;

public interface ShapeFactory {
    Shape createShape(final String description) throws Exception;
}
