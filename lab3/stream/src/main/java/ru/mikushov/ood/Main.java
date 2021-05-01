package ru.mikushov.ood;

import ru.mikushov.ood.stream.StreamDto;
import ru.mikushov.ood.stream.input.DecompressionDecorator;
import ru.mikushov.ood.stream.input.DecryptionDecorator;
import ru.mikushov.ood.stream.input.MyFileInputStream;
import ru.mikushov.ood.stream.input.MyInputStream;
import ru.mikushov.ood.stream.output.CompressionDecorator;
import ru.mikushov.ood.stream.output.EncryptionDecorator;
import ru.mikushov.ood.stream.output.MyFileOutputStream;
import ru.mikushov.ood.stream.output.MyOutputStream;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    private static StreamDto decorateStream(String[] args) throws FileNotFoundException {
        final int length = args.length;
        final String inputFileName = args[length - 2];
        final String outputFileName = args[length - 1];

        MyInputStream myFileInputStream = new MyFileInputStream(inputFileName);
        MyOutputStream myFileOutputStream = new MyFileOutputStream(outputFileName);
        int compressCount = 0;
        int decompressCount = 0;
        for (int i = 0; i < length - 2; i++) {
            String arg = args[i];
            switch (arg) {
                case "--decrypt":
                    if (i < length - 2) {
                        final long key = Long.parseLong(args[i + 1]);
                        myFileInputStream = new DecryptionDecorator(myFileInputStream, key);
                        i++;
                    } else {
                        printHelp();
                    }
                    break;
                case "--encrypt":
                    if (i < length - 2) {
                        final long key = Long.parseLong(args[i + 1]);
                        myFileOutputStream = new EncryptionDecorator(myFileOutputStream, key);
                        i++;
                    } else {
                        printHelp();
                    }
                    break;
                case "--compress":
                    if (compressCount > 0) {
                        printHelp();
                    } else {
                        compressCount++;
                        myFileOutputStream = new CompressionDecorator(myFileOutputStream);
                    }
                    break;
                case "--decompress":
                    if (decompressCount > 0) {
                        printHelp();
                    } else {
                        decompressCount++;
                        myFileInputStream = new DecompressionDecorator(myFileInputStream);
                    }
                    break;
            }
        }
        return new StreamDto(myFileInputStream, myFileOutputStream);
    }

    public static void main(String[] args) {
        try {
            if (args.length < 2) {
                printHelp();
                throw new Exception("Usage transform [options] <input-file> <output-file> ");
            }
            final byte[] bytes = ("          //            while (true) {\n" +
                    "//                command = scanner.nextLine();\n" +
                    "//                final String[] arra = command.split(\" \");\n" +
                    "//                final String first = arra[0];\n" +
                    "//                if (first.equals(\"q\")) {\n" +
                    "//                    break;\n" +
                    "//                } else if (first.equals(\"--encrypt\")) {\n" +
                    "//\n" +
                    "//                }\n" +
                    "//            }").getBytes();

            final StreamDto streamDto = decorateStream(args);
            handleFile(streamDto.getInputStream(), streamDto.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
            printHelp();
        }
//        long seed = 53123;
//        List<String> in = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");
//
//        Collections.shuffle(in, new Random(seed));
//        System.out.println(in);
//        List<String> in1 = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");
//        Collections.shuffle(in1, new Random(seed));
//        System.out.println(in1);

//        final byte[] bytes = "hello".getBytes();
//        final byte[] decode = decode(encode(bytes));
//        final String x = new String(decode);
//        System.out.println(x);
//        try {
//            List<Byte> bytes = new ArrayList<>(255);
//            byte current = Byte.MIN_VALUE;
//            for (int i = 0; i <= 255; i++) {
//                bytes.add(current);
//                current++;
//            }
//
////            List<Integer> list = new ArrayList<>(256);
////            for (int i = 0; i <= 255; i++) {
////                list.add((i));
////            }
//
////            shuffleList(list);
////            for (int i : list) {
////                System.out.println(i);
////            }
//
////            shuffle(bytes);
//            bytes.forEach(System.out::println);
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//
//        try {
//            final String s = " final MyFileOutputStream myFileOutputStream = new MyFileOutputStream(compression);";
//            final byte[] compress = compress(s.getBytes(StandardCharsets.UTF_8));
//            final byte[] decompress = decompress(compress, s.length());
//            final String x = new String(decompress, StandardCharsets.UTF_8);
//            System.out.println(s.equals(x));
//            System.out.println(x);
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//        File input = new File("E:\\github\\ood\\labs\\lab3\\stream\\index.txt");
//        File output = new File("E:\\github\\ood\\labs\\lab3\\stream\\output.txt");
////        compressionTest(input, output);
//        File file = new File("E:\\github\\ood\\labs\\lab3\\stream\\index.txt");
//        File encrypt = new File("E:\\github\\ood\\labs\\lab3\\stream\\output-encrypt.txt");
//        cryptTest(output, file, encrypt);
    }

    private static void handleFile(MyInputStream inputStream, MyOutputStream outputStream) throws IOException {
        byte[] bytes = new byte[10];
        while (!inputStream.isEOF()) {
            int count = inputStream.readBlock(bytes, 10);
            if (count > 0) {
                outputStream.writeBlock(bytes, count);
            }
        }
    }

    private static void printHelp() {
        System.out.println("transform [options] <input-file> <output-file>\n" +
                "options:" +
                "--encrypt <key>. Adds an encryption step when writing using the key key. Option can be specified multiple times.\n" +
                "--decrypt <key>. Adds a decryption step when reading using key. Option can be specified multiple times.\n" +
                "--compress. Adds compression step when recording.\n" +
                "--decompress. Adds a decompression step when reading.\n");
    }

    private static void cryptTest(File output, File file, File encrypt) {
        final long key = 100;
        try (final FileInputStream fileInputStream = new FileInputStream(file);
             final FileOutputStream outputStream = new FileOutputStream(encrypt);
        ) {
            final MyFileInputStream myFileInputStream = new MyFileInputStream(fileInputStream);
            final MyFileOutputStream myFileOutputStream = new MyFileOutputStream(outputStream);
            final EncryptionDecorator encryptionOutputStreamDecorator = new EncryptionDecorator(myFileOutputStream, key);
            int size = 10;
            byte[] data = new byte[10];
            while (!myFileInputStream.isEOF()) {
                final int i = myFileInputStream.readBlock(data, size);
                encryptionOutputStreamDecorator.writeBlock(data, i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (final FileInputStream fileInputStream = new FileInputStream(encrypt);
             final FileOutputStream outputStream = new FileOutputStream(output);
        ) {
            final MyFileInputStream myFileInputStream = new MyFileInputStream(fileInputStream);

            final DecryptionDecorator decryptionDecoratorInputStream = new DecryptionDecorator(myFileInputStream, key);
            int size = 10;
            byte[] data = new byte[10];
            while (!myFileInputStream.isEOF()) {
                final int i = decryptionDecoratorInputStream.readBlock(data, size);
                outputStream.write(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void compressionTest(File input, File output) {
        try (
                final FileOutputStream compression = new FileOutputStream(input);
                final FileOutputStream outputStream = new FileOutputStream(output);
        ) {
            final MyFileOutputStream myFileOutputStream = new MyFileOutputStream(compression);
            final CompressionDecorator compressionDecorator = new CompressionDecorator(myFileOutputStream);
            final String s = "Hello world.Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output. Run with --scan to get full insights.";
            final byte[] bytes = s.getBytes();
            compressionDecorator.writeBlock(bytes, bytes.length);

//            final EncryptionOutputStreamDecorator encryptionOutputStreamDecorator = new EncryptionOutputStreamDecorator(myFileOutputStream);
//            final DecryptionDecoratorInputStream decryptionDecoratorInputStream = new DecryptionDecoratorInputStream(myFileInputStream);
            final FileInputStream fileInputStream = new FileInputStream(input);
            final MyFileInputStream myFileInputStream = new MyFileInputStream(fileInputStream);
            final DecompressionDecorator decompressionInputStreamDecorator = new DecompressionDecorator(myFileInputStream);
            int size = 10;
            byte[] data = new byte[10];
//            while (!myFileInputStream.isEOF()) {
//                final int count = myFileInputStream.readBlock(data, size);
////                for (int i = 0; i < data.length; i++) {
////                    System.out.println(i);
////                }
//                myFileOutputStream.writeBlock(data, count);
//            }
//            List<Byte> bytes = new ArrayList<>();

            byte[] bytes1 = new byte[s.length()];
            int index = 0;
            while (!decompressionInputStreamDecorator.isEOF()) {
                final int count = decompressionInputStreamDecorator.readBlock(data, size);
                for (int i = 0; i < count; i++) {
                    bytes1[index] = data[i];
                    index++;
                }

                outputStream.write(data);
            }
            for (int i = 0; i < bytes.length; i++) {
                if (bytes[i] != bytes1[i]) {
                    System.out.println("Failed");
                }
            }
            System.out.println(new String(bytes1, StandardCharsets.UTF_8));

//            while (!decryptionDecoratorInputStream.isEOF()) {
//                final int i = decryptionDecoratorInputStream.readBlock(data, size);
//                encryptionOutputStreamDecorator.writeBlock(data, i);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
