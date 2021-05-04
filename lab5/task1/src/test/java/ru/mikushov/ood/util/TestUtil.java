package ru.mikushov.ood.util;

import ru.mikushov.ood.model.DocumentItem;
import ru.mikushov.ood.model.Image;
import ru.mikushov.ood.service.DocumentImpl;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestUtil {
    public static void checkDocumentsItem(DocumentImpl document, List<DocumentItem> expectedItems) {
        for (int i = 0; i < document.getItemsCount() - 1; i++) {
            final DocumentItem actualItem = document.getItem(i);
            final DocumentItem expectedItem = expectedItems.get(i);

            if (actualItem.isParagraph() && actualItem.isParagraph()) {
                assertEquals(expectedItem.getParagraph().getText(), actualItem.getParagraph().getText());
            } else if (actualItem.isImage() && expectedItem.isImage()) {
                final Image expectedImage = expectedItem.getImage();
                final Image actualImage = actualItem.getImage();
                final File file = new File(actualImage.getPath());
                assertTrue(file.exists() && file.isFile());
                assertEquals(expectedImage.getWidth(), actualImage.getWidth());
                assertEquals(expectedImage.getHeight(), actualImage.getHeight());
            } else {
                fail();
            }
        }
    }
}
