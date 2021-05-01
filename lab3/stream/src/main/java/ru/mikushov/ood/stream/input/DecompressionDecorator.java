package ru.mikushov.ood.stream.input;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;

public class DecompressionDecorator extends InputStreamDecorator {

    public DecompressionDecorator(MyInputStream source) {
        super(source);
    }

    @Override
    public int readByte() throws IOException {
        return super.readByte();
    }

    @Override
    public int readBlock(byte[] destinationBuffer, int size) throws IOException {
        final int i = super.readBlock(destinationBuffer, size);
        destinationBuffer = decompress(destinationBuffer, i);
        return i;
    }
//
//    private void decompress(byte[] data, int count) throws IOException {
//        if (count > 0) {
//
//        }
//    }

    public static byte[] decompress(byte[] compressed, int count) throws IOException {
        ByteArrayInputStream bis = getInputStream(compressed, count);
        GZIPInputStream gis = new GZIPInputStream(bis);
        BufferedReader br = new BufferedReader(new InputStreamReader(gis, StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        gis.close();
        bis.close();
        return sb.toString().getBytes();
    }
    private static ByteArrayInputStream getInputStream(byte[] data, int i) {
        if (i != data.length) {
            byte[] bytes = new byte[i];
            for (int j = 0; j < i; j++) {
                bytes[j] = data[i];
            }
            return new ByteArrayInputStream(bytes);
        } else {
            return new ByteArrayInputStream(data);
        }
    }

    //
//    public static byte[] decompress(byte[] in) throws IOException {
//        try {
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            InflaterOutputStream infl = new InflaterOutputStream(out);
//            infl.write(in);
//            infl.flush();
//            infl.close();
//
//            return out.toByteArray();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new IOException(e.getMessage());
//        }
//    }


//    private byte[] decompress(byte[] data) {
//        try (InputStream in = new ByteArrayInputStream(data);
//             InflaterInputStream inflaterInputStream = new InflaterInputStream(in);
//             ByteArrayOutputStream bout = new ByteArrayOutputStream(512);) {
//
//            int b;
//            while ((b = inflaterInputStream.read()) != -1) {
//                bout.write(b);
//            }
//
//            in.close();
//            inflaterInputStream.close();
//            bout.close();
//            return bout.toByteArray();
//        } catch (IOException ex) {
//            return null;
//        }
//    }
}
