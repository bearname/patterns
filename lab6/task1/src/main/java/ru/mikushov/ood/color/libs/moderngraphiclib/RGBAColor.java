package ru.mikushov.ood.color.libs.moderngraphiclib;

public class RGBAColor {
    private final float r;
    private final float g;
    private final float b;
    private final float a;

    public RGBAColor(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public float getR() {
        return r;
    }

    public float getG() {
        return g;
    }

    public float getB() {
        return b;
    }

    public float getA() {
        return a;
    }
}
