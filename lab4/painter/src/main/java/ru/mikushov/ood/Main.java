package ru.mikushov.ood;

import ru.mikushov.ood.canvas.ConsoleCanvas;
import ru.mikushov.ood.canvas.JavaCanvas;
import ru.mikushov.ood.canvas.MyCanvas;
import ru.mikushov.ood.designer.ConsoleDesigner;
import ru.mikushov.ood.designer.Designer;
import ru.mikushov.ood.pacture.PictureDraft;
import ru.mikushov.ood.pacture.ShapeFactory;
import ru.mikushov.ood.pacture.ShapeFactoryImpl;
import ru.mikushov.ood.painter.Painter;
import ru.mikushov.ood.painter.PainterImpl;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            String outputImage = "output.png";
            String imageFormat = "png";
            int width = 1920;
            int height = 1080;
            if (args.length >= 1) {
                outputImage = args[0];
                final String[] split = outputImage.split("\\.");
                imageFormat = split[split.length - 1];
                if (!imageFormat.equals("jpeg") && !imageFormat.equals("jpg") && !imageFormat.equals("png")) {
                    throw new Exception("Invalid image format. Available png, jpeg, jpg\n" +
                            "Usage java -jar painter.jar <outputImage>");
                }
                if (args.length == 3) {
                    width = Integer.parseInt(args[1]);
                    height = Integer.parseInt(args[2]);
                }
            }

            final ShapeFactory shapeFactory = new ShapeFactoryImpl();
            final Designer consoleDesigner = new ConsoleDesigner(shapeFactory);
            final PictureDraft draft = consoleDesigner.createDraft(System.in);

            final Painter painter = new PainterImpl();
            final MyCanvas consoleCanvas = new ConsoleCanvas();
            final JavaCanvas javaCanvas = new JavaCanvas(width, height);

            painter.drawPicture(draft, consoleCanvas);
            painter.drawPicture(draft, javaCanvas);

            saveImage(javaCanvas, outputImage, imageFormat);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void saveImage(JavaCanvas javaCanvas, String outputImage, String outputFileFormat) throws IOException {
        final BufferedImage bufferedImage = javaCanvas.getBufferedImage();
        File file = new File(outputImage);
        ImageIO.write(bufferedImage, outputFileFormat, file);
    }
}