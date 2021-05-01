package ru.mikushov.ood.stream.output;

import java.util.ArrayList;
import java.util.List;

public class MemoryOutputStream implements MyOutputStream {
    private final List<Byte> arrayList = new ArrayList<>();

    @Override
    public void writeByte(byte data) {
        arrayList.add(data);
    }

    @Override
    public void writeBlock(byte[] srcData, int size) {
        for (byte b : srcData) {
            writeByte(b);
        }
    }
}
