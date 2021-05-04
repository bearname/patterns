package ru.mikushov.ood.service;

import ru.mikushov.ood.command.*;
import ru.mikushov.ood.model.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DocumentImpl implements Document {
    private final Text title;
    private final List<DocumentItem> documentItems = new ArrayList<>();
    private final History history = new History();
    private final ImageManager imageManager = new ImageManagerImpl();
    private final HashMap<Integer, Paragraph> markedToDeleteParagraph = new HashMap<>();

    public DocumentImpl() {
        this("Html Document");
    }

    public DocumentImpl(String title) {
        this.title = new Text(title);
    }

    @Override
    public Paragraph insertParagraph(String text, int position) throws Exception {
        checkPosition(position);

        final Paragraph paragraph = new ParagraphImpl(text);
        history.addAndExecuteCommand(new InsertParagraphCommand(documentItems, paragraph, position));
        return paragraph;
    }

    @Override
    public Image insertImage(String path, int position, int width, int height) throws Exception {
        checkPosition(position);
        ImageImpl image = new ImageImpl(path, width, height);
        history.addAndExecuteCommand(new InsertImageCommand(documentItems, imageManager, image, position));
        return image;
    }

    @Override
    public void deleteItem(int index) throws Exception {
        history.addAndExecuteCommand(new DeleteItemCommand(index, documentItems, imageManager, markedToDeleteParagraph));
    }

    private void checkPosition(int position) throws Exception {
        if (position < -1 || position > documentItems.size()) {
            throw new Exception("Invalid position. Position must be in range [-1, " + documentItems.size() + "]");
        }
    }

    @Override
    public int getItemsCount() {
        return documentItems.size();
    }

    @Override
    public DocumentItem getItem(int index) {
        return documentItems.get(index);
    }

    @Override
    public String getTitle() {
        return title.getValue();
    }

    @Override
    public void setTitle(String newTitle) throws Exception {
        final ChangeTitleCommand command = new ChangeTitleCommand(this.title, this.title.getValue(), newTitle);
        this.title.setValue(newTitle);
        history.addAndExecuteCommand(command);
    }

    @Override
    public void replaceParagraphText(int position, String newText) throws Exception {
        checkPosition(position);
        history.addAndExecuteCommand(new ReplaceParagraphCommand(documentItems, position, newText));
    }

    @Override
    public void resizeImage(int position, int width, int height) throws Exception {
        checkPosition(position);
        history.addAndExecuteCommand(new ResizeImageCommand(documentItems, position, width, height));
    }

    @Override
    public boolean canUndo() {
        return history.canUndo();
    }

    @Override
    public void undo() {
        history.undo();
    }

    @Override
    public boolean canRedo() {
        return history.canRedo();
    }

    @Override
    public void redo() throws Exception {
        history.redo();
    }

    @Override
    public void save(String path) {
        File file = new File(path);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <title>" + title.getValue() + "</title\n></head>\n" +
                    "<body>\n");

            for (DocumentItem documentItem : documentItems) {
                writeItem(fileWriter, documentItem);
            }

            fileWriter.write("</body>\n" +
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
