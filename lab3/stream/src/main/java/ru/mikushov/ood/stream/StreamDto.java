package ru.mikushov.ood.stream;

import ru.mikushov.ood.stream.input.MyInputStream;
import ru.mikushov.ood.stream.output.MyOutputStream;

public class StreamDto {
    private final MyInputStream inputStream;
    private final MyOutputStream outputStream;

    public StreamDto(MyInputStream inputStream, MyOutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public MyInputStream getInputStream() {
        return inputStream;
    }

    public MyOutputStream getOutputStream() {
        return outputStream;
    }
}
