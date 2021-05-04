package ru.mikushov.ood.command;

import ru.mikushov.ood.model.DocumentItem;
import ru.mikushov.ood.model.Image;
import ru.mikushov.ood.model.Paragraph;
import ru.mikushov.ood.service.ImageManager;

import java.util.List;
import java.util.Map;

public class DeleteItemCommand extends BaseCommand {
    private final int index;
    private final List<DocumentItem> documentItems;
    private final ImageManager imageManager;
    private final Map<Integer, Paragraph> markedToDeleteParagraph;
    private final DocumentItem documentItem;

    public DeleteItemCommand(int index, List<DocumentItem> documentItems, ImageManager imageManager, Map<Integer, Paragraph> markedToDeleteParagraph) throws Exception {
        if (index < 0 || index >= documentItems.size()) {
            throw new Exception("Invalid index. Index must be in range [0, " + (documentItems.size() - 1) + "]");
        }
        this.index = index;
        this.documentItems = documentItems;
        documentItem = documentItems.get(index);
        this.imageManager = imageManager;
        this.markedToDeleteParagraph = markedToDeleteParagraph;
    }

    @Override
    void doExecute() {
        final DocumentItem documentItem = documentItems.get(index);
        final Paragraph paragraph = documentItem.getParagraph();
        if (paragraph != null) {
            markedToDeleteParagraph.put(index, paragraph);
        } else if (documentItem.getImage() != null) {
            final Image image = documentItem.getImage();
            imageManager.markAsToDelete(image, true);
        }

        documentItems.remove(index);
    }

    @Override
    void doUnExecute() {
        if (documentItem.isParagraph()) {
            markedToDeleteParagraph.remove(index, documentItem.getParagraph());
        } else {
            final Image image = documentItem.getImage();
            imageManager.markAsToDelete(image, false);
        }
        documentItems.add(index, documentItem);
    }

    @Override
    public void destroy() {
    }
}
