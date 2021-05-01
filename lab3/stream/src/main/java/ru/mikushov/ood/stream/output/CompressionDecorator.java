package ru.mikushov.ood.stream.output;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.zip.GZIPOutputStream;

public class CompressionDecorator extends OutputStreamDecorator {
    public CompressionDecorator(MyOutputStream wrapper) {
        super(wrapper);
    }

    @Override
    public void writeByte(byte data) throws IOException {
        super.writeByte(data);
    }

    @Override
    public void writeBlock(byte[] srcData, int size) throws IOException {
        super.writeBlock(compress(srcData), size);
    }

    public static byte[] compress(byte[] data) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
        GZIPOutputStream gzip = new GZIPOutputStream(bos);
        gzip.write(data);
        gzip.close();
        byte[] compressed = bos.toByteArray();
        bos.close();
        return compressed;
    }

//    private byte[] compress(byte[]  data) throws IOException {
//        try {
//            ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
//            DeflaterOutputStream dos = new DeflaterOutputStream(bout, new Deflater());
//            dos.write(data);
//            dos.close();
//            bout.close();
//            return bout.toByteArray();
//        } catch (IOException ex) {
//            throw new IOException(ex.getMessage());
//        }
//    }

//    public static byte[] compress(byte[] in) throws IOException {
//        try {
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            DeflaterOutputStream defl = new DeflaterOutputStream(out);
//            defl.write(in);
//            defl.flush();
//            defl.close();
//
//            return out.toByteArray();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new IOException(e.getMessage());
//        }
//    }

//    private byte[] compress(byte[] data) {
//        try {
//            ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
//            DeflaterOutputStream dos = new DeflaterOutputStream(bout);
//            dos.write(data);
//            dos.close();
//            bout.close();
//            return bout.toByteArray();
//        } catch (IOException ex) {
//            return null;
//        }
//    }

//
//    public static byte[] compress(byte[] content){
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        try{
//            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
//            gzipOutputStream.write(content);
//            gzipOutputStream.close();
//        } catch(IOException e){
//            throw new RuntimeException(e);
//        }
//        System.out.printf("Compressiono %f\n", (1.0f * content.length/byteArrayOutputStream.size()));
//        return byteArrayOutputStream.toByteArray();
//    }
//
//    public static byte[] compress(byte[] in) throws IOException {
//        try {
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            DeflaterOutputStream deflate = new DeflaterOutputStream(out);
//            deflate.write(in);
//            deflate.flush();
//            deflate.close();
//
//            return out.toByteArray();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new IOException(e.getMessage());
//        }
//    }
}
