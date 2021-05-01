package ru.mikushov.ood.model;

public class ImageImpl implements Image {
    private String path;
    private int width;
    private int height;

    public ImageImpl(String path, int width, int height) throws Exception {
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
        if (width < 1 || height < 1)  {
            throw new Exception("Width and height must be > 0");
        }
    }
}
