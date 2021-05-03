package ru.mikushov.ood.command;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import ru.mikushov.ood.model.DocumentItem;
import ru.mikushov.ood.model.Image;
import ru.mikushov.ood.model.ParagraphImpl;
import ru.mikushov.ood.service.DocumentImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.mikushov.ood.util.TestUtil.checkDocumentsItem;

public class InsertImageCommandTest {
    public static final String INITIAL_TITLE = "Initial";

    private DocumentImpl document;
    private final List<DocumentItem> expectedItems = new ArrayList<>();

    @BeforeEach
    public void init() {
        try {
            document = new DocumentImpl(INITIAL_TITLE);
            document.insertParagraph("1 paragraph", -1);
            final Image github = document.insertImage("github.png", -1, 400, 300);
            final Image junit = document.insertImage("junit.png", -1, 400, 300);

            expectedItems.add(new DocumentItem(new ParagraphImpl("1 paragraph")));
            expectedItems.add(new DocumentItem(github));
            expectedItems.add(new DocumentItem(junit));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void undoInsertImage() {
        try {
            document.undo();
            document.undo();
            document.insertParagraph("3 paragraph", -1);
            expectedItems.remove(1);
            expectedItems.remove(1);

            assertEquals(2, document.getItemsCount());

            checkDocumentsItem(document, expectedItems);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}