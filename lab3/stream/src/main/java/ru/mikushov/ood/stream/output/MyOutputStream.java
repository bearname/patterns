package ru.mikushov.ood.stream.output;

import java.io.IOException;

public interface MyOutputStream {
    void writeByte(byte data) throws IOException;
    void writeBlock(byte[] srcData, int size) throws IOException;
}