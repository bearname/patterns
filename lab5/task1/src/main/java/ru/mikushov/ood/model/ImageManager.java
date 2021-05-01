package ru.mikushov.ood.model;

public interface ImageManager {
    void copyImageToWorkDirectory(String path) throws Exception;

    void deleteImage();

    void markAsToDelete(Image path, boolean needRemove);

    void deleteNotNeededImages();
}
