package ru.mikushov.ood.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.mikushov.ood.model.*;
import ru.mikushov.ood.service.DocumentImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.mikushov.ood.util.TestUtil.checkDocumentsItem;

public class DeleteItemCommandTest {
    public static final String INITIAL_TITLE = "Initial";

    private DocumentImpl document;
    private final List<DocumentItem> expectedItems = new ArrayList<>();

    @BeforeEach
    public void init() {
        try {
            document = new DocumentImpl(INITIAL_TITLE);
            document.insertParagraph("1 paragraph", -1);
            final Image image = document.insertImage("github.png", -1, 400, 300);
            document.insertParagraph("2 paragraph", -1);

            expectedItems.add(new DocumentItem(new ParagraphImpl("1 paragraph")));
            expectedItems.add(new DocumentItem(image));
            expectedItems.add(new DocumentItem(new ParagraphImpl("2 paragraph")));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void canDeleteParagraph() {
        try {
            document.insertParagraph("paragraph toDelete", -1);
            final int lastItem = document.getItemsCount() - 1;
            document.deleteItem(lastItem);

            assertEquals(3, document.getItemsCount());

            checkDocumentsItem(document, expectedItems);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void canUndoDeleteParagraph() {
        try {
            document.insertParagraph("paragraph to delete", -1);
            final int lastItem = document.getItemsCount() - 1;
            document.deleteItem(lastItem);
            document.undo();

            expectedItems.add(new DocumentItem(new ParagraphImpl("paragraph to delete")));
            assertEquals(4, document.getItemsCount());

            checkDocumentsItem(document, expectedItems);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    @Test
    public void canRedoCanceledDeleteParagraphCommand() {
        try {
            document.insertParagraph("paragraph to delete", -1);
            final int lastItem = document.getItemsCount() - 1;
            document.deleteItem(lastItem);
            document.undo();
            document.redo();

            assertEquals(3, document.getItemsCount());

            checkDocumentsItem(document, expectedItems);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void canDeleteImage() {
        try {
            final Image insertedImage = document.insertImage("junit.png", document.getItemsCount() - 1, 600, 450);
            final int lastItem = document.getItemsCount() - 1;
            document.deleteItem(lastItem);

            assertEquals(3, document.getItemsCount());
            final File file = new File(insertedImage.getPath());
            assertTrue(file.exists());
            assertTrue(file.isFile());

            checkDocumentsItem(document, expectedItems);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void canUndoDeleteImage() {
        try {
            final Image insertedImage = document.insertImage("junit.png", document.getItemsCount(), 600, 450);
            final int lastItem = document.getItemsCount() - 1;
            document.deleteItem(lastItem);
            document.undo();

            assertEquals(4, document.getItemsCount());
            final File file = new File(insertedImage.getPath());
            assertTrue(file.exists());
            assertTrue(file.isFile());

            expectedItems.add(new DocumentItem(insertedImage));
            checkDocumentsItem(document, expectedItems);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void canRedoCanceledDeleteImage() {
        try {
            final Image insertedImage = document.insertImage("junit.png", -1, 600, 450);
            document.undo();
            document.redo();

            assertEquals(4, document.getItemsCount());
            final File file = new File(insertedImage.getPath());
            assertTrue(file.exists());
            assertTrue(file.isFile());

            expectedItems.add(new DocumentItem(insertedImage));
            checkDocumentsItem(document, expectedItems);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}