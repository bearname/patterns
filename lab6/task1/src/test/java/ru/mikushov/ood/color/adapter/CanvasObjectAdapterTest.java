package ru.mikushov.ood.color.adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mikushov.ood.color.libs.moderngraphiclib.ModernGraphicsRenderer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class CanvasObjectAdapterTest {
    private ByteArrayOutputStream output;
    private CanvasObjectAdapter canvasObjectAdapter;
    private ModernGraphicsRenderer modernGraphicsRenderer;

    @BeforeEach
    void setup() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        modernGraphicsRenderer = new ModernGraphicsRenderer(output);
        canvasObjectAdapter = new CanvasObjectAdapter(modernGraphicsRenderer);
    }

    @Test
    void withoutCallBeginDraw() {
        try {
            canvasObjectAdapter.setColor(0);
            canvasObjectAdapter.moveTo(50, 10);
            canvasObjectAdapter.lineTo(100, 100);
            modernGraphicsRenderer.endDraw();

            fail();
        } catch (Exception exception) {
            assertEquals("Drawing has not been started", exception.getMessage());
        }
    }

    @Test
    void multipleCallBeginDraw() {
        try {
            modernGraphicsRenderer.beginDraw();
            modernGraphicsRenderer.beginDraw();
            canvasObjectAdapter.setColor(0);
            canvasObjectAdapter.moveTo(50, 10);
            canvasObjectAdapter.lineTo(100, 100);
            modernGraphicsRenderer.endDraw();

            fail();
        } catch (Exception exception) {
            assertEquals("Drawing has already begun", exception.getMessage());
        }
    }

    @Test
    void callEndDrawWithoutCallBeginDraw() {
        try {
            modernGraphicsRenderer.endDraw();

            fail();
        } catch (Exception exception) {
            assertEquals("Drawing has not been started", exception.getMessage());
        }
    }

    @Test
    void withCallBeginDraw() {
        try {
            modernGraphicsRenderer.beginDraw();
            canvasObjectAdapter.setColor(0);
            canvasObjectAdapter.moveTo(50, 10);
            canvasObjectAdapter.lineTo(100, 100);
            modernGraphicsRenderer.endDraw();

            assertEquals("<draw>\n" +
                    "   <line fromX=50 fromY=10 toX=100 toY=100>\n" +
                    "      <color r=\"0.0\" g=\"0.0\" b=\"0.0\" a=\"255.0\" />\n" +
                    "   </line>\n" +
                    "<draw>\n", output.toString());
        } catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
    }
}