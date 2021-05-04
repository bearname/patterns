package ru.mikushov.ood.color.libs.moderngraphiclib;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ModernGraphicsRenderer {
    private final OutputStream outputStream;
    private boolean drawing = false;

    public ModernGraphicsRenderer(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void beginDraw() throws Exception {
        if (drawing) {
            throw new Exception("Drawing has already begun");
        }

        writeln("<draw>");
        drawing = true;
    }

    public void drawLine(final Point start, final Point end, RGBAColor color) throws Exception {
        if (!drawing) {
            throw new Exception("DrawLine is allowed between BeginDraw()/EndDraw() only");
        }

        writeln("   <line fromX=" + start.getX() + " fromY=" + start.getY() + " toX=" + end.getX() + " toY=" + end.getY() + ">");
        writeln("      <color r=\"" + color.getR() + "\" g=\"" + color.getG() + "\" b=\"" + color.getB() + "\" a=\"" + color.getA() + "\" />");
        writeln("   </line>");
    }

    public void endDraw() throws Exception {
        if (!drawing) {
            throw new Exception("Drawing has not been started");
        }
        writeln("<draw>");
        drawing = false;
    }

    private void writeln(final String string) throws IOException {
        outputStream.write((string + "\n").getBytes(StandardCharsets.UTF_8));
    }
}
