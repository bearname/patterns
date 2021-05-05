package ru.mikushov.ood.shape;

public class LineStyle extends FillStyle {
    private int lineDrawingThickness = 2;

    public LineStyle() {
    }

    public LineStyle(RGBColor color) {
        super(color);
    }

    public LineStyle(int lineDrawingThickness) {
        this.lineDrawingThickness = lineDrawingThickness;
    }

    public LineStyle(int lineDrawingThickness, RGBColor color) {
        super(color);
        this.lineDrawingThickness = lineDrawingThickness;
    }

    public int getLineDrawingThickness() {
        return lineDrawingThickness;
    }

    public void setLineDrawingThickness(int lineDrawingThickness) {
        this.lineDrawingThickness = lineDrawingThickness;
    }
}
