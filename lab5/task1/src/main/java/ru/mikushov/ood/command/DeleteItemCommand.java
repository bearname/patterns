package ru.mikushov.ood.command;

import ru.mikushov.ood.model.DocumentItem;
import ru.mikushov.ood.model.Paragraph;

import java.util.List;

public class DeleteItemCommand extends BaseCommand {
    private final int index;
    private final List<DocumentItem> documentItems;

    public DeleteItemCommand(int index, List<DocumentItem> documentItems) throws Exception {
        if (index < 0 || index >= documentItems.size()) {
            throw new Exception("Invalid index.  Index must be in range [0, " + (documentItems.size() - 1) + "]");
        }
        this.index = index;
        this.documentItems = documentItems;
    }

    @Override
    void doExecute() {
        final DocumentItem documentItem = documentItems.get(index);
        final Paragraph paragraph = documentItem.getParagraph();
        if (paragraph != null) {
            documentItems.remove(index);
        } 
    }

    @Override
    void doUnExecute() {

    }
}
