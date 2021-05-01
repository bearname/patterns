package ru.mikushov.ood.pacture;

import ru.mikushov.ood.shape.*;

public class ShapeFactoryImpl implements ShapeFactory {
    @Override
    public Shape createShape(String description) throws Exception {
        final String[] s = description.split(" ");
        if (s.length < 2) {
            throw new Exception("Invalid command " + description + "'");
        }

        final Color color = Color.fromString(s[1]);
        switch (s[0]) {
            case "rectangle":
                invalidCommand(s, 6);
                final Dot leftTop = parseDot(s[2], s[3]);
                final Dot rightBottom = parseDot(s[4], s[5]);
                return new Rectangle(color, leftTop, rightBottom);
            case "triangle":
                invalidCommand(s, 8);
                final Dot vertex1 = parseDot(s[2], s[3]);
                final Dot vertex2 = parseDot(s[4], s[5]);
                final Dot vertex3 = parseDot(s[6], s[7]);
                return new Triangle(color, vertex1, vertex2, vertex3);
            case "ellipse":
                invalidCommand(s, 6);
                final Dot center = parseDot(s[2], s[3]);
                final double verticalRadius = parseDouble(s[5]);
                final double horizontalRadius = parseDouble(s[4]);
                return new Ellipse(color, center, horizontalRadius, verticalRadius);
            case "polygon":
                invalidCommand(s, 6);
                final Dot polygonCenter = parseDot(s[2], s[3]);
                return new RegularPolygon(color, polygonCenter, parseDouble(s[4]), parseInt(s[5]));
            default:
                throw new Exception("Unknown command " + description);
        }
    }

    private Dot parseDot(String s2, String s1) {
        return new Dot(parseDouble(s2), parseDouble(s1));
    }

    private int parseInt(String s) {
        return Integer.parseInt(s);
    }

    private double parseDouble(String s2) {
        return Double.parseDouble(s2);
    }

    private void invalidCommand(String[] s, int neededForCommandArguments) throws Exception {
        if (s.length != neededForCommandArguments) {
            throw new Exception("Invalid command");
        }
    }
}
