package ru.mikushov.ood.stream.output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyFileOutputStream implements MyOutputStream {
    private final FileOutputStream outputStream;

    public MyFileOutputStream(FileOutputStream outputStream) {
        this.outputStream = outputStream;
    }
    public MyFileOutputStream(File outputFile) throws FileNotFoundException {
        this.outputStream = new FileOutputStream(outputFile);
    }

    public MyFileOutputStream(String outputFileName) throws FileNotFoundException {
        this.outputStream = new FileOutputStream(outputFileName);
    }

    @Override
    public void writeByte(byte data) throws IOException {
        outputStream.write(data);
    }

    @Override
    public void writeBlock(byte[] srcData, int size) throws IOException {
        outputStream.write(srcData);
    }
}
