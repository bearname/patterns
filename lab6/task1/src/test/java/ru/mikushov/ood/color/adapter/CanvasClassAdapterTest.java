package ru.mikushov.ood.color.adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mikushov.ood.color.libs.moderngraphiclib.Point;
import ru.mikushov.ood.color.libs.moderngraphiclib.RGBAColor;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class CanvasClassAdapterTest {
    private ByteArrayOutputStream output;
    private CanvasClassAdapter canvasClassAdapter;

    @BeforeEach
    void setup() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        canvasClassAdapter = new CanvasClassAdapter(output);
    }

    @Test
    void withoutCallBeginDraw() {
        try {
            canvasClassAdapter.setColor(0);
            canvasClassAdapter.moveTo(50, 10);
            canvasClassAdapter.lineTo(100, 100);
            canvasClassAdapter.endDraw();

            fail();
        } catch (Exception exception) {
            assertEquals("Drawing has not been started", exception.getMessage());
        }
    }

    @Test
    void drawLineWithoutCallBeginDraw() {
        try {
            canvasClassAdapter.drawLine(new Point(0, 0), new Point(100, 100), new RGBAColor(100, 100, 100, 1));
            fail();
        } catch (Exception exception) {
            assertEquals("DrawLine is allowed between BeginDraw()/EndDraw() only", exception.getMessage());
        }
    }

    @Test
    void multipleCallBeginDraw() {
        try {
            canvasClassAdapter.beginDraw();
            canvasClassAdapter.beginDraw();
            canvasClassAdapter.setColor(0);
            canvasClassAdapter.moveTo(50, 10);
            canvasClassAdapter.lineTo(100, 100);
            canvasClassAdapter.endDraw();

            fail();
        } catch (Exception exception) {
            assertEquals("Drawing has already begun", exception.getMessage());
        }
    }

    @Test
    void callEndDrawWithoutCallBeginDraw() {
        try {
            canvasClassAdapter.endDraw();

            fail();
        } catch (Exception exception) {
            assertEquals("Drawing has not been started", exception.getMessage());
        }
    }

    @Test
    void withCallBeginDraw() {
        try {
            canvasClassAdapter.beginDraw();
            canvasClassAdapter.setColor(0);
            canvasClassAdapter.moveTo(50, 10);
            canvasClassAdapter.lineTo(100, 100);
            canvasClassAdapter.endDraw();

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