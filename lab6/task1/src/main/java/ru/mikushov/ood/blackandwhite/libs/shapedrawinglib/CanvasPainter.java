package ru.mikushov.ood.blackandwhite.libs.shapedrawinglib;

import ru.mikushov.ood.blackandwhite.libs.graphicslib.Canvas;

public class CanvasPainter {
    private final Canvas canvas;

    public CanvasPainter(Canvas canvas) {
        this.canvas = canvas;
    }

    public void draw(CanvasDrawable canvasDrawable) {
        canvasDrawable.draw(canvas);
    }
}
