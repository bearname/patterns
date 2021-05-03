package ru.mikushov.ood.command;

import ru.mikushov.ood.model.DocumentItem;
import ru.mikushov.ood.model.Image;

import java.util.List;

public class ResizeImageCommand extends BaseCommand {

    private final List<DocumentItem> documentItems;
    private final int position;
    private final int newWidth;
    private final int newHeight;
    private final int oldHeight;
    private final int oldWidth;

    public ResizeImageCommand(List<DocumentItem> documentItems, int position, int newWidth, int newHeignt) throws Exception {
        this.documentItems = documentItems;
        this.position = position;
        DocumentItem documentItem = documentItems.get(position);
        if (!documentItem.isImage()) {
            throw new Exception("Item by index " + position + " not image");
        }

        Image image = documentItem.getImage();
        this.oldWidth = image.getWidth();
        this.oldHeight = image.getHeight();
        this.newWidth = newWidth;
        this.newHeight = newHeignt;
    }

    @Override
    void doExecute() throws Exception {
        DocumentItem documentItem = documentItems.get(position);
        documentItem.getImage().resize(newWidth, newHeight);
        this.documentItems.set(position, documentItem);
    }

    @Override
    void doUnExecute() {
        try {
            DocumentItem documentItem = documentItems.get(position);
            documentItem.getImage().resize(oldWidth, oldHeight);
            this.documentItems.set(position, documentItem);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}
