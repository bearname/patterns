package ru.mikushov.ood.stream.input;

import java.io.IOException;

public interface MyInputStream {
    boolean isEOF() throws IOException;

    int readByte() throws IOException;

    int readBlock(byte[] dstBuffer, int size) throws IOException;
}
