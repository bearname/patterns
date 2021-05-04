package ru.mikushov.ood.color.libs.graphicslib;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CanvasImplTest {
    private ByteArrayOutputStream output;
    private CanvasImpl canvas;

    @BeforeEach
    void setup() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        canvas = new CanvasImpl();
    }

    @Test
    public void setColor() {
        try {
            canvas.setColor(100);
            canvas.moveTo(100, 100);
            canvas.lineTo(200, 200);
            assertEquals("setColor 100\r\n" +
                    "MoveTo (100, 100)\r\n" +
                    "LineTo (200, 200)\r\n", output.toString());
        } catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
    }
}