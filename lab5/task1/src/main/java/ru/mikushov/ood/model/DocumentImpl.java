package ru.mikushov.ood.model;

import ru.mikushov.ood.History;
import ru.mikushov.ood.command.ChangeTitleCommand;
import ru.mikushov.ood.command.CreateParagraph;
import ru.mikushov.ood.command.DeleteItemCommand;
import ru.mikushov.ood.command.InsertImageCommand;

import java.util.ArrayList;
import java.util.List;

public class DocumentImpl implements Document {
    private Title title;
    private final List<DocumentItem> documentItems = new ArrayList<>();
    private final History history = new History();
    private final ImageManager imageManager = new ImageManagerImpl();

    public DocumentImpl() {
        this("Html Document");
    }

    public DocumentImpl(String title) {
        this.title = new Title(title);
    }

    @Override
    public Paragraph insertParagraph(String text, int position) throws Exception {
        checkPosition(position);

        final Paragraph paragraph = new ParagraphImpl(text);
        history.addAndExecuteCommand(new CreateParagraph(documentItems, paragraph, position));
        return paragraph;
    }

    @Override
    public Image insertImage(String path, int width, int height, int position) throws Exception {
        checkPosition(position);
        final ImageImpl image = new ImageImpl(path, width, height);
        history.addAndExecuteCommand(new InsertImageCommand(documentItems, imageManager, image, position));
        return null;
    }

    @Override
    public void deleteItem(int index) throws Exception {
        history.addAndExecuteCommand(new DeleteItemCommand(index, documentItems));
    }

    private void checkPosition(int position) throws Exception {
        if (position < -1 || position > documentItems.size()) {
            throw new Exception("Invalid position. Position must be in range [-1, " + documentItems.size() + "]");
        }
    }

    @Override
    public int getItemsCount() {
        return 0;
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

    }
}
