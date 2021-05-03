package ru.mikushov.ood.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ImageManagerImplTest {

    private ImageManagerImpl imageManager;

    @BeforeEach
    public void init() {
        imageManager = new ImageManagerImpl();
    }

    @Test
    public void copyDirectory() {
        try {
            imageManager.copyImageToWorkDirectory("./src");
            fail();
        } catch (Exception exception) {
            assertEquals("It's folder. " + "./src", exception.getMessage());
        }
    }

    @Test
    public void notExistImage() {
        try {
            imageManager.copyImageToWorkDirectory("./src/index.jpeg");
            fail();
        } catch (Exception exception) {
            assertEquals("Image not exist. " + "./src/index.jpeg", exception.getMessage());
        }
    }

    @Test
    public void notExistDirectory() {
        try {
            imageManager = new ImageManagerImpl("img");
            imageManager.copyImageToWorkDirectory("./github.png");
            File imgDirectory = new File("img");
            assertTrue(imgDirectory.exists());
            File[] files = imgDirectory.listFiles();
            assertNotNull(files);
            assertEquals(1, files.length);
            String path = files[0].getPath();
            assertEquals("png", path.substring(path.lastIndexOf(".") + 1));

            deleteFolder(imgDirectory);
        } catch (Exception exception) {
            fail();
        }
    }

    private void deleteFolder(File imgDirectory) {
        clearFolder(imgDirectory);
        assertTrue(imgDirectory.delete());
    }

    private void clearFolder(File imgDirectory) {
        String[] entries = imgDirectory.list();
        assertNotNull(entries);
        for (String s : entries) {
            File currentFile = new File(imgDirectory.getPath(), s);
            currentFile.delete();
        }
    }
}