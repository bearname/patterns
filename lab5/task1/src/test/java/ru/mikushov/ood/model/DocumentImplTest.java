package ru.mikushov.ood.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DocumentImplTest {

    private DocumentImpl document;
    public static final String INITIAL_TITLE = "Initial";
    public static final String UPDATED_TITLE = "Awesome document";

    @Before
    public void init() {
        document = new DocumentImpl(INITIAL_TITLE);
    }

    @Test
    public void canSetTitle() {
        final String updatedTitle = "Awesome document";
        document.setTitle(updatedTitle);
        assertEquals("Awesome document", document.getTitle());

        document.undo();
        assertEquals(INITIAL_TITLE, document.getTitle());
    }

    @Test
    public void canUndoSetTitle() {
        document.setTitle(UPDATED_TITLE);
        assertEquals("Awesome document", document.getTitle());

        document.undo();
        assertEquals(INITIAL_TITLE, document.getTitle());
    }

    @Test
    public void canRedoSetTitle() {
        document.setTitle(UPDATED_TITLE);
        assertEquals(UPDATED_TITLE, document.getTitle());

        document.undo();
        assertEquals(INITIAL_TITLE, document.getTitle());

        document.redo();
        assertEquals(UPDATED_TITLE, document.getTitle());
    }


}