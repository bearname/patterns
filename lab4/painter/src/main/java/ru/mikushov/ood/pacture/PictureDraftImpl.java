package ru.mikushov.ood.pacture;

import ru.mikushov.ood.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class PictureDraftImpl implements PictureDraft {
    private final List<Shape> shapes = new ArrayList<>();

    @Override
    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    @Override
    public int size() {
        return shapes.size();
    }

    @Override
    public List<Shape> getShapes() {
        return shapes;
    }
}
