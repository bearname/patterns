package ru.mikushov.ood.model;

public interface Image {
    String getPath();

    int getWidth();

    int getHeight();

    void resize(int width, int height) throws Exception;
}
