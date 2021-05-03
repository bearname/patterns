package ru.mikushov.ood.command;

import ru.mikushov.ood.model.DocumentItem;

import java.util.List;

public class ReplaceParagraphCommand extends BaseCommand {
    private final List<DocumentItem> documentItems;
    private final String oldValue;
    private final String newValue;
    private final int position;

    public ReplaceParagraphCommand(List<DocumentItem> documentItems, int position, String newValue) {
        this.documentItems = documentItems;
        this.oldValue = documentItems.get(position).getParagraph().getText();
        this.newValue = newValue;
        this.position = position;
    }

    @Override
    void doExecute() {
        DocumentItem documentItem = documentItems.get(position);
        if (documentItem.isParagraph()) {
            documentItem.getParagraph().setText(newValue);
            documentItems.set(position, documentItem);
        }
    }

    @Override
    void doUnExecute() {
        DocumentItem documentItem = documentItems.get(position);
        if (documentItem.isParagraph()) {
            documentItem.getParagraph().setText(oldValue);
            documentItems.set(position, documentItem);
        }
    }

    @Override
    public void destroy() {

    }
}
