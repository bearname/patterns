package ru.mikushov.ood.service;

import ru.mikushov.ood.model.Image;

public interface ImageManager {
    String copyImageToWorkDirectory(String path) throws Exception;

    void deleteImage(Image image);

    void markAsToDelete(Image path, boolean needRemove);

    void deleteMarkedToDeleteImages();
}
