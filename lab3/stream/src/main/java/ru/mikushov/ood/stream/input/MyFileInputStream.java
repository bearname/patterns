package ru.mikushov.ood.stream.input;

import ru.mikushov.ood.stream.exception.StreamException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyFileInputStream implements MyInputStream {
    private final FileInputStream inputStream;

    public MyFileInputStream(FileInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public MyFileInputStream(File inputFile) throws FileNotFoundException {
        this.inputStream = new FileInputStream(inputFile);
    }

    public MyFileInputStream(String inputFileName) throws FileNotFoundException {
        this.inputStream = new FileInputStream(inputFileName);
    }

    @Override
    public boolean isEOF() throws IOException {
        return inputStream.available() <= 0;
    }

    @Override
    public int readByte() throws IOException {
        if (isEOF()) {
            throw new StreamException("Is eof. Cannot read data");
        }
        return inputStream.read();
    }

    @Override
    public int readBlock(byte[] dstBuffer, int size) throws IOException {
        if (isEOF()) {
            throw new StreamException("Is eof. Cannot read data");
        }
        final int available = inputStream.available();
        if (size > available) {
            dstBuffer = new byte[available];
        }
        return inputStream.read(dstBuffer);
    }
}
