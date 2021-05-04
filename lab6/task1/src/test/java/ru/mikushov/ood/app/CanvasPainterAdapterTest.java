package ru.mikushov.ood.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mikushov.ood.blackandwhite.adapter.CanvasClassAdapter;
import ru.mikushov.ood.blackandwhite.adapter.CanvasObjectAdapter;
import ru.mikushov.ood.blackandwhite.libs.moderngraphiclib.ModernGraphicsRenderer;
import ru.mikushov.ood.blackandwhite.libs.shapedrawinglib.CanvasPainter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class CanvasPainterAdapterTest {
    private ByteArrayOutputStream output;
    private Application application;

    @BeforeEach
    public void setup() {
        application = new Application();
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @Test
    public void canvasObjectAdapter() {
        try {
            final ModernGraphicsRenderer renderer = new ModernGraphicsRenderer(output);
            final CanvasObjectAdapter canvasAdapter = new CanvasObjectAdapter(renderer);
            final CanvasPainter painter = new CanvasPainter(canvasAdapter);
            application.paintPicture(painter);

            final String expected = "Triangle Point 10 15 Point 100 200 Point 150 250\r\n" +
                    "Rectangle leftTop Point 30 40 width 18 height 24\r\n";

            assertEquals(expected, output.toString());
        } catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
    }

    @Test
    public void canvasClassAdapter() {
        try {
            final CanvasClassAdapter canvasAdapter = new CanvasClassAdapter(output);
            final CanvasPainter painter = new CanvasPainter(canvasAdapter);

            application.paintPicture(painter);

            final String expected = "Triangle Point 10 15 Point 100 200 Point 150 250\r\n" +
                    "Rectangle leftTop Point 30 40 width 18 height 24\r\n";

            assertEquals(expected, output.toString());
        } catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
    }

    @Test
    void canvasObjectAdapter1() {
        try {
            final ModernGraphicsRenderer modernGraphicsRenderer = new ModernGraphicsRenderer(output);
            final CanvasObjectAdapter canvasObjectAdapter = new CanvasObjectAdapter(modernGraphicsRenderer);
            modernGraphicsRenderer.beginDraw();
            canvasObjectAdapter.moveTo(50, 10);
            canvasObjectAdapter.lineTo(100, 100);
            modernGraphicsRenderer.endDraw();

            assertEquals("<draw>\n" +
                    "   <line fromX=50 fromY=10 toX=100 toY=100/>\n" +
                    "<draw>\n", output.toString());
        } catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
    }

    @Test
    void canvasClassAdapter1() {
        try {
            final CanvasClassAdapter canvasObjectAdapter = new CanvasClassAdapter(output);
            canvasObjectAdapter.beginDraw();
            canvasObjectAdapter.moveTo(50, 10);
            canvasObjectAdapter.lineTo(100, 100);
            canvasObjectAdapter.endDraw();

            assertEquals("<draw>\n" +
                    "   <line fromX=50 fromY=10 toX=100 toY=100/>\n" +
                    "<draw>\n", output.toString());
        } catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
    }
}