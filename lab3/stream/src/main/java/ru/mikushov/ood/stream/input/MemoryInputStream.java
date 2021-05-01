package ru.mikushov.ood.stream.input;

import ru.mikushov.ood.stream.exception.StreamException;

import java.util.Arrays;

public class MemoryInputStream implements MyInputStream {
    private byte[] data;
    private int offset = 0;

    public MemoryInputStream(byte[] data) {
        this.data = data;
    }

    @Override
    public boolean isEOF() {
        return offset >= data.length;
    }

    @Override
    public int readByte() throws StreamException {
        if (isEOF()) {
            throw new StreamException("Is eof. Cannot read data");
        }
        final byte byteData = data[offset];
        offset++;
        return byteData;
    }

    @Override
    public int readBlock(byte[] destinationBuffer, int size) throws StreamException {
        if (isEOF()) {
            throw new StreamException("Is eof. Cannot read data");
        }
        if (offset + size >= data.length) {
            size = data.length - size;
        }
        destinationBuffer = Arrays.copyOfRange(data, offset, offset + size);
        offset += size;

        return destinationBuffer.length;
    }
}
