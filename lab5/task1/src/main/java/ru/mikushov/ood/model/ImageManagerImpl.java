package ru.mikushov.ood.model;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ImageManagerImpl implements ImageManager {
    private String directory = "./images";

    private final Set<Image> imagesToRemove = new HashSet<>();

    public ImageManagerImpl() {
    }

    public ImageManagerImpl(String directory) {
        this.directory = directory;
    }

    @Override
    public void copyImageToWorkDirectory(String path) throws Exception {
        final File image = new File(path);
        if (!image.exists() || !image.isDirectory()) {
            throw new Exception("Invalid image file");
        }
        final String newFilePath = getNewFileName(path);
        Path copied = Paths.get(newFilePath);
        Path originalPath = image.toPath();
        Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
    }

    private String getNewFileName(String path) {
        final String[] split = path.split("\\.");
        final String fileExtension = split[split.length - 1];
        return directory + UUID.randomUUID().toString() + "." + fileExtension;
    }

    @Override
    public void deleteImage() {

    }

    @Override
    public void markAsToDelete(Image image, boolean needRemove) {
        if (needRemove) {
            imagesToRemove.remove(image);
        } else {
            imagesToRemove.add(image);
        }
    }

    @Override
    public void deleteNotNeededImages() {
        for (Image image : imagesToRemove) {
            final String path = image.getPath();
            final File file = new File(path);
            if (file.exists() && file.isFile()) {
                if (file.delete()) {
                    imagesToRemove.remove(image);
                }
            }
        }
    }
}
