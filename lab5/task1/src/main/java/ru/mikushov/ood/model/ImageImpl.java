package ru.mikushov.ood.model;

public class ImageImpl implements Image {
    public static final int MAX_HEIGHT = 10000;
    public static final int MAX_WIDTH = 10000;

    private String path;
    private int width;
    private int height;

    public ImageImpl(String path, int width, int height) throws Exception {
        if (path == null || path.length() < 5) {
            throw new Exception("Invalid image file");
        }
        int i = path.lastIndexOf('.');
        String extension = path.substring(i + 1);
        if (!extension.equals("png") && !extension.equals("jpg") && !extension.equals("jpeg")) {
            throw new Exception("Invalid image file");
        }
        check(width, height);
        this.path = path;
        this.width = width;
        this.height = height;
    }

    @Override
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void resize(int width, int height) throws Exception {
        check(width, height);

        this.height = height;
        this.width = width;
    }

    private void check(int width, int height) throws Exception {
        if (width < 1 || width > MAX_WIDTH) {
            throw new Exception("Width must be in range [1, " + MAX_HEIGHT + "]");
        }
        if (height < 1 || height > MAX_HEIGHT) {
            throw new Exception("Height must be in range [1, " + MAX_HEIGHT + "]");
        }
    }

    @Override
    public String toString() {
        return "Image: " + width + " " + height + " " + path;
    }
}
