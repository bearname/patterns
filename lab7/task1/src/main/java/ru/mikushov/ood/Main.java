package ru.mikushov.ood;

import ru.mikushov.ood.canvas.JavaCanvas;
import ru.mikushov.ood.shape.*;
import ru.mikushov.ood.slide.GroupImpl;
import ru.mikushov.ood.slide.SlideImpl;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static ru.mikushov.ood.Colors.*;

public class Main {
    public static void main(String[] args) {
        try {
            String outputImage = "output.png";

            if (args.length == 1) {
                outputImage = args[0];
            }

            final LineStyle lineStyle = new LineStyle(10, WHITE_COLOR);

            final FillStyle fillStyle = new FillStyle(RED_COLOR);

            final SlideImpl slide = new SlideImpl(1920, 1080);

            final FillStyle scyStyle = new FillStyle(SCI_COLOR);
            final Rectangle sci = new Rectangle(new Dimension<>(0.0, 0.0, 1920.0, 720.0), new LineStyle(SCI_COLOR), scyStyle, true);
            sci.setFillStyle(scyStyle);
            slide.getShapes().add(sci);

            final Rectangle earth = new Rectangle(new Dimension<>(0.0, 720.0, 1920.0, 360.0), new LineStyle(EARTH_COLOR), new FillStyle(EARTH_COLOR), true);
            slide.getShapes().add(earth);

            final Ellipse sun = new Ellipse(new Dimension<>(1600.0, 100.0, 300.0, 300.0), new LineStyle(SUN_COLOR), new FillStyle(SUN_COLOR), true);
            slide.getShapes().add(sun);

            final Ellipse lake = new Ellipse(new Dimension<>(1600.0, 800.0, 300.0, 150.0), new LineStyle(LAKE_COLOR), new FillStyle(LAKE_COLOR), true);
            slide.getShapes().add(lake);

            {
                final GroupImpl house = new GroupImpl(new Dimension<>(600.0, 800.0, 600.0, 600.0), lineStyle, fillStyle);

                final Rectangle trumpet = new Rectangle(new Dimension<>(900.0, 300.0, 50.0, 200.0), new LineStyle(10, RED_COLOR), new FillStyle(RED_COLOR), true);
                final Triangle roof = new Triangle(new Dimension<>(300.0, 300.0, 800.0, 300.0), new LineStyle(10, ROOF_COLOR), new FillStyle(ROOF_COLOR), true);
                final Rectangle baseHouse = new Rectangle(new Dimension<>(400.0, 600.0, 600.0, 350.0), new LineStyle(10, BROWN_COLOR), new FillStyle(BROWN_COLOR), true);

                house.insertShape(trumpet, 0);
                house.insertShape(baseHouse, 1);
                house.insertShape(roof, 2);

                slide.getShapes().add(house);
            }
            {
                final GroupImpl house = new GroupImpl(new Dimension<>(600.0, 800.0, 600.0, 600.0), lineStyle, fillStyle);

                final Rectangle trumpet = new Rectangle(new Dimension<>(900.0, 300.0, 50.0, 200.0), new LineStyle(10), new FillStyle(RED_COLOR));
                final Triangle roof = new Triangle(new Dimension<>(300.0, 300.0, 800.0, 300.0), new LineStyle(10), new FillStyle(ROOF_COLOR));
                final Rectangle baseHouse = new Rectangle(new Dimension<>(400.0, 600.0, 600.0, 350.0), new LineStyle(10), new FillStyle(BROWN_COLOR));
                house.insertShape(trumpet, 0);
                house.insertShape(baseHouse, 1);
                house.insertShape(roof, 2);
                slide.getShapes().add(house);
                house.setFrame(new Dimension<>(1200.0, 600.0, 200.0, 200.0));
            }

            final JavaCanvas javaCanvas = new JavaCanvas();
            slide.draw(javaCanvas);
            saveImage(outputImage, javaCanvas.getBufferedImage());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private static void saveImage(String outputImage, BufferedImage bufferedImage) throws IOException {
        File file = new File(outputImage);
        final boolean write = ImageIO.write(bufferedImage, getImageExtension(outputImage), file);
        System.out.println((write) ? "success" : "failed");
    }

    private static String getImageExtension(String outputImage) {
        return outputImage.substring(outputImage.lastIndexOf(".") + 1);
    }
}