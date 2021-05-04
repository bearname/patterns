package ru.mikushov.ood.service;

import ru.mikushov.ood.model.Image;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ImageManagerImpl implements ImageManager {
    private String directory = "images";

    private final Set<Image> imagesToRemove = ConcurrentHashMap.newKeySet();

    public ImageManagerImpl() {
    }

    public ImageManagerImpl(final String directory) {
        this.directory = directory;
    }

    @Override
    public String copyImageToWorkDirectory(String path) throws Exception {
        final File image = new File(path);
        if (image.isDirectory()) {
            throw new Exception("It's folder. " + path);
        }
        if (!image.exists()) {
            throw new Exception("Image not exist. " + path);
        }

        final File directoryFile = new File(directory);

        final String newFilePath = generateNewFileName(path, directoryFile.getAbsolutePath());
        Path copied = Paths.get(newFilePath);
        Path originalPath = image.toPath();

        if (!directoryFile.exists()) {
            directoryFile.mkdir();
        }

        Path copy = Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
        return copy.toString();
    }

    @Override
    public void markAsToDelete(Image image, boolean needRemove) {
        if (needRemove) {
            imagesToRemove.add(image);
        } else {
            imagesToRemove.remove(image);
        }
    }

    @Override
    public void deleteMarkedToDeleteImages() {
        imagesToRemove.forEach(this::deleteImage);
        imagesToRemove.clear();
    }

    @Override
    public void deleteImage(Image image) {
        if (deleteFile(image.getPath())) {
            imagesToRemove.remove(image);
        }
    }

    private boolean deleteFile(final String path) {
        final File file = new File(path);
        if (file.exists() && file.isFile()) {
            return file.delete();
        }
        return false;
    }

    private String generateNewFileName(String path, String directory) {
        final String[] split = path.split("\\.");
        final String fileExtension = split[split.length - 1];
        return directory + File.separator + UUID.randomUUID() + "." + fileExtension;
    }
}
