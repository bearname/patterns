package ru.mikushov.ood.command;

import ru.mikushov.ood.model.DocumentItem;

import java.util.List;

public class ReplaceParagraphCommand extends BaseCommand {
    private final List<DocumentItem> documentItems;
    private final String oldValue;
    private final String newValue;
    private final int position;

    public ReplaceParagraphCommand(List<DocumentItem> documentItems, int position, String newValue) throws Exception {
        this.documentItems = documentItems;
        DocumentItem documentItem = documentItems.get(position);
        if (!documentItem.isParagraph()) {
            throw new Exception("Cannot perform ReplaceParagraphCommand on image item");
        }

        this.oldValue = documentItem.getParagraph().getText();
        this.newValue = newValue;
        this.position = position;
    }

    @Override
    void doExecute() {
        DocumentItem documentItem = documentItems.get(position);
        documentItem.getParagraph().setText(newValue);
        documentItems.set(position, documentItem);
    }

    @Override
    void doUnExecute() {
        DocumentItem documentItem = documentItems.get(position);
        documentItem.getParagraph().setText(oldValue);
        documentItems.set(position, documentItem);
    }

    @Override
    public void destroy() {

    }
}
