package ru.mikushov.ood.color.libs.shapedrawinglib;

import ru.mikushov.ood.color.libs.graphicslib.Canvas;

public class CanvasPainter {
    private final Canvas canvas;

    public CanvasPainter(Canvas canvas) {
        this.canvas = canvas;
    }

    public void draw(CanvasDrawable canvasDrawable) {
        canvasDrawable.draw(canvas);
    }
}
