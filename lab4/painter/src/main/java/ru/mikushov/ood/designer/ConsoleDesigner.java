package ru.mikushov.ood.designer;

import ru.mikushov.ood.pacture.PictureDraft;
import ru.mikushov.ood.pacture.PictureDraftImpl;
import ru.mikushov.ood.pacture.ShapeFactory;

import java.io.InputStream;
import java.util.Scanner;

public class ConsoleDesigner implements Designer {
    private final ShapeFactory shapeFactory;

    public ConsoleDesigner(ShapeFactory shapeFactory) {
        this.shapeFactory = shapeFactory;
    }

    @Override
    public PictureDraft createDraft(InputStream inputStream) {
        printHelp();

        Scanner scanner = new Scanner(inputStream);
        String command;
        final PictureDraft pictureDraft = new PictureDraftImpl();

        while (true) {
            try {
                System.out.print("> ");
                command = scanner.nextLine();
                if (command.equals("quit")) {
                    break;
                } else if (command.equals("help")) {
                    printHelp();
                } else {
                    pictureDraft.addShape(shapeFactory.createShape(command));
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }

        return pictureDraft;
    }

    private void printHelp() {
        System.out.println("quit: quit" +
                "help: help" +
                "Draw shapes: " +
                "Color: green | red | blue | yellow | pink | black\n" +
                "Create rectangle: rectangle <color> <left top vertex x> <left top vertex y> <right bottom vertex x> <right bottom vertex y>\n" +
                "Create triangle: triangle <color> <vertex1 x> <vertex1 y> <vertex2 x> <vertex2 y> <vertex3 x> <vertex3 y>\n" +
                "Create ellipse: ellipse <color> <center x> <center y> <horizontal radius> <vertical radius>\n" +
                "Create regular polygon: polygon <color> <center x> <center y> <radius> <vertex count>\n");
    }
}
