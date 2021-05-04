package ru.mikushov.ood.app;

import ru.mikushov.ood.blackandwhite.adapter.CanvasObjectAdapter;
import ru.mikushov.ood.blackandwhite.libs.graphicslib.Canvas;
import ru.mikushov.ood.blackandwhite.libs.graphicslib.CanvasImpl;
import ru.mikushov.ood.blackandwhite.libs.moderngraphiclib.ModernGraphicsRenderer;
import ru.mikushov.ood.blackandwhite.libs.shapedrawinglib.CanvasPainter;
import ru.mikushov.ood.blackandwhite.libs.shapedrawinglib.Point;
import ru.mikushov.ood.blackandwhite.libs.shapedrawinglib.Rectangle;
import ru.mikushov.ood.blackandwhite.libs.shapedrawinglib.Triangle;

public class Application {
    public void paintPicture(CanvasPainter painter) {
        final Point point1 = new Point(10, 15);
        final Point point2 = new Point(100, 200);
        final Point point3 = new Point(150, 250);

        Triangle triangle = new Triangle(point1, point2, point3);
        final Point leftTop = new Point(30, 40);

        Rectangle rectangle = new Rectangle(leftTop, 18, 24);

        painter.draw(triangle);
        painter.draw(rectangle);
    }

    public void paintPictureOnCanvas() {
        Canvas simpleCanvas = new CanvasImpl();
        CanvasPainter painter = new CanvasPainter(simpleCanvas);

        paintPicture(painter);
    }

    public void paintPictureOnModernGraphicsRender() {
        try {
            ModernGraphicsRenderer renderer = new ModernGraphicsRenderer(System.out);
            final CanvasObjectAdapter canvasPainterAdapter = new CanvasObjectAdapter(renderer);
            final CanvasPainter painter = new CanvasPainter(canvasPainterAdapter);
            renderer.beginDraw();
            paintPicture(painter);
            renderer.endDraw();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
