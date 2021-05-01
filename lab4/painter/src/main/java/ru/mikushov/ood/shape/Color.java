package ru.mikushov.ood.shape;

public enum Color {
    Green,
    Red,
    Blue,
    Yellow,
    Pink,
    Black;

    public static Color fromString(String color) throws Exception {
        switch (color) {
            case "green":
                return Green;
            case "red":
                return Red;
            case "blue":
                return Blue;
            case "yellow":
                return Yellow;
            case "pink":
                return Pink;
            case "black":
                return Black;
            default:
                throw new Exception("Unknown color");
        }
    }
}
