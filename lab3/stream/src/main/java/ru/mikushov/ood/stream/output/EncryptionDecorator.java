package ru.mikushov.ood.stream.output;

import java.io.IOException;
import java.util.*;

public class EncryptionDecorator extends OutputStreamDecorator {

    private final List<Integer> encryptionTable = new ArrayList<>(256);

    public EncryptionDecorator(MyOutputStream wrapper, long key) {
        super(wrapper);
        generateEncryptionTable(key);
    }

    @Override
    public void writeByte(byte data) throws IOException {
        super.writeByte((byte) encode(data));
    }

    @Override
    public void writeBlock(byte[] srcData, int size) throws IOException {
        final byte[] encode = encode(srcData, size);
        super.writeBlock(encode, size);
    }

    public byte[] encode(byte[] data, int size) {
        for (int i = 0; i < size; i++) {
            data[i] = (byte) encode(Byte.toUnsignedInt(data[i]));
        }

        return data;
    }

    public int encode(int data) {
        return encryptionTable.get(data);
    }

    private void generateEncryptionTable(final long key) {
        for (int i = 0; i < 256; i++) {
            encryptionTable.add(i);
        }

        Collections.shuffle(encryptionTable, new Random(key));
    }
}
