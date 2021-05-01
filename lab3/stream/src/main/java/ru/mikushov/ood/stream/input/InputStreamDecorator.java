package ru.mikushov.ood.stream.input;

import java.io.IOException;

public class InputStreamDecorator implements MyInputStream {
    private final MyInputStream wrapper;

    public InputStreamDecorator(MyInputStream source) {
        this.wrapper = source;
    }

    @Override
    public boolean isEOF() throws IOException {
        return wrapper.isEOF();
    }

    @Override
    public int readByte() throws IOException {
        return wrapper.readByte();
    }

    @Override
    public int readBlock(byte[] dstBuffer, int size) throws IOException {
        return wrapper.readBlock(dstBuffer, size);
    }
}
