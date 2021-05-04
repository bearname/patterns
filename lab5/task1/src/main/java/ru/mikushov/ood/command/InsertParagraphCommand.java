package ru.mikushov.ood.command;

import ru.mikushov.ood.model.DocumentItem;
import ru.mikushov.ood.model.Paragraph;

import java.util.List;

public class InsertParagraphCommand extends BaseCommand {
    private final List<DocumentItem> documentItems;
    private final Paragraph paragraph;
    private int position;

    public InsertParagraphCommand(List<DocumentItem> documentItems, Paragraph paragraph, int position) throws Exception {
        if (position < -1 || position > documentItems.size()) {
            throw new Exception("Invalid position. Valid range [-1, " + (documentItems.size()) + "]");
        }
        this.documentItems = documentItems;
        this.paragraph = paragraph;
        this.position = position;
    }

    @Override
    void doExecute() {
        insert((this.position == -1) ? documentItems.size() : position);
    }

    @Override
    void doUnExecute() {
        remove((this.position == -1) ? documentItems.size() - 1 : position);
    }

    private void insert(int position) {
        documentItems.add(position, new DocumentItem(paragraph));
    }

    private void remove(int index) {
        documentItems.remove(index);
    }

    @Override
    public void destroy() {
    }
}
