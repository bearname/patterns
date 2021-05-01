package ru.mikushov.ood.command;

import ru.mikushov.ood.model.DocumentItem;
import ru.mikushov.ood.model.Paragraph;

import java.util.List;

public class CreateParagraph extends BaseCommand {
    private List<DocumentItem> items;
    private final Paragraph paragraph;
    private int position = 0;

    public CreateParagraph(List<DocumentItem> itemList, Paragraph paragraph, int position) throws Exception {
        if (position < -1) {
            throw new Exception("Invalid position");
        }
        this.items = itemList;
        this.paragraph = paragraph;
        this.position = position;
    }

    @Override
    void doExecute() {
        insert((this.position == -1) ? 0 : position);
    }

    @Override
    void doUnExecute() {
        remove((this.position == -1) ? 0 : position);
    }

    private void insert(int position) {
        items.add(position, new DocumentItem(paragraph));
    }

    private void remove(int index) {
        items.remove(index);
    }
}
