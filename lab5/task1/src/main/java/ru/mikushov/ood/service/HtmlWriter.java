package ru.mikushov.ood.service;

import ru.mikushov.ood.model.DocumentItem;
import ru.mikushov.ood.model.Image;
import ru.mikushov.ood.model.Paragraph;
import ru.mikushov.ood.model.Text;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlWriter {

    public void save(final String path, final Text title, final List<DocumentItem> documentItems) {
        File file = new File(path);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <title>" + title.getValue() + "</title\n></head>\n" +
                    "<body><style>\n" +
                    " p {\n" +
                    "    text-align: left;\n" +
                    "} \n" +
                    " .container {\n" +
                    "    max-width: 700px;\n" +
                    "    margin: 0 auto;\n" +
                    "}\n" +
                    " </style>   <div class=\"container\">\n");

            for (DocumentItem documentItem : documentItems) {
                writeItem(fileWriter, documentItem);
            }

            fileWriter.write("</div></body>\n" +
                    "</html>");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void writeItem(FileWriter fileWriter, DocumentItem documentItem) throws IOException {
        final Paragraph paragraph = documentItem.getParagraph();
        if (paragraph != null) {
            writeParagraph(fileWriter, paragraph);
        } else if (documentItem.getImage() != null) {
            final Image image = documentItem.getImage();
            writeImage(fileWriter, image);
        }
    }

    private void writeParagraph(FileWriter fileWriter, Paragraph paragraph) throws IOException {
        fileWriter.write("    <p>" + paragraph.getText() + "</p>\n");
    }

    private void writeImage(FileWriter fileWriter, Image image) throws IOException {
        fileWriter.write("    <img src=\"" + image.getPath() + "\" style=\"width:" + image.getWidth() + "px; height:" + image.getHeight() + "px\" alt=\"someImage\">\n");
    }
}
