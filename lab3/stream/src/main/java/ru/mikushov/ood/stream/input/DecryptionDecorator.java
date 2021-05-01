package ru.mikushov.ood.stream.input;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DecryptionDecorator extends InputStreamDecorator {
    private final List<Integer> decryptionTable = new ArrayList<>(256);

    public DecryptionDecorator(MyInputStream source, long key) {
        super(source);
        generateDecryptionTable(key);
    }

    @Override
    public int readByte() throws IOException {
        final int i = super.readByte();
        return decryptByte(i);
    }

    @Override
    public int readBlock(byte[] dstBuffer, int size) throws IOException {
        final int count = super.readBlock(dstBuffer, size);

        decode(dstBuffer, count);

        return count;
    }

    public void decode(byte[] dstBuffer, int count) {
        for (int i = 0; i < count; i++) {
            dstBuffer[i] = (byte) decryptByte(Byte.toUnsignedInt(dstBuffer[i]));
        }
    }

    private void generateDecryptionTable(long key) {
        List<Integer> encryptionTable = new ArrayList<>();

        for (int i = 0; i < 256; i++) {
            encryptionTable.add(i);
        }

        Collections.shuffle(encryptionTable, new Random(key));
        for (int i = 0; i < 256; i++) {
            decryptionTable.add(i);
        }
        for (int i = 0; i < 256; i++) {
            decryptionTable.set(encryptionTable.get(i), i);
        }
    }

    public int decryptByte(int b) {
        return decryptionTable.get(b);
    }

//    //
//    private byte decode(byte b) {
//        byte[] bytes = new byte[1];
//        bytes[0] = b;
//        final byte[] decode = decode(bytes);
//        return decode[0];
//    }

}
