package ru.mikushov.ood.service;

import ru.mikushov.ood.command.*;
import ru.mikushov.ood.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DocumentImpl implements Document {
    private final Text title;
    private final List<DocumentItem> documentItems = new ArrayList<>();
    private final History history = new History();
    private final ImageManager imageManager = new ImageManagerImpl();
    private final HashMap<Integer, Paragraph> markedToDeleteParagraph = new HashMap<>();
    private final HtmlWriter htmlWriter = new HtmlWriter();

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
        htmlWriter.save(path, title, documentItems);
    }
}
