package ru.mikushov.ood.stream.output;

import java.io.IOException;

public class OutputStreamDecorator implements MyOutputStream {
    private final MyOutputStream wrapper;

    public OutputStreamDecorator(MyOutputStream wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public void writeByte(byte data) throws IOException {
        wrapper.writeByte(data);
    }

    @Override
    public void writeBlock(byte[] srcData, int size) throws IOException {
        wrapper.writeBlock(srcData, size);
    }
}
