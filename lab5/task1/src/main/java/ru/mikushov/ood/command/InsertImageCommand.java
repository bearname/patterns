package ru.mikushov.ood.command;

import ru.mikushov.ood.model.DocumentItem;
import ru.mikushov.ood.model.Image;
import ru.mikushov.ood.model.ImageImpl;
import ru.mikushov.ood.service.ImageManager;

import java.util.List;

public class InsertImageCommand extends BaseCommand {
    private final ImageManager imageManager;
    private final List<DocumentItem> documentItems;
    private Image image;
    private final int position;

    public InsertImageCommand(List<DocumentItem> documentItems, ImageManager imageManager, Image image, int position) throws Exception {
        if (position < -1 || position > documentItems.size()) {
            throw new Exception("Invalid position. Valid range [-1, " + (documentItems.size()) + "]");
        }

        this.position = position;
        this.documentItems = documentItems;
        this.imageManager = imageManager;
        this.image = image;
    }

    @Override
    public void doExecute() throws Exception {
        final String newImagePath = imageManager.copyImageToWorkDirectory(image.getPath());
        image = new ImageImpl(newImagePath, this.image.getWidth(), this.image.getHeight());
        final DocumentItem documentItem = new DocumentItem(image);

        if (position == -1) {
            documentItems.add(documentItem);
        } else {
            documentItems.add(position, documentItem);
        }

        imageManager.markAsToDelete(this.image, false);
    }

    @Override
    public void doUnExecute() {
        if (position == -1) {
            documentItems.remove(documentItems.size() - 1);
        } else {
            documentItems.remove(position);
        }

        imageManager.markAsToDelete(image, true);
    }

    @Override
    public void destroy() {
        imageManager.deleteMarkedToDeleteImages();
    }
}
