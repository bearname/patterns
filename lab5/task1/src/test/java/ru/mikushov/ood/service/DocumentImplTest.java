package ru.mikushov.ood.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mikushov.ood.model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static ru.mikushov.ood.util.TestUtil.checkDocumentsItem;

public class DocumentImplTest {
    private DocumentImpl document;
    public static final String INITIAL_TITLE = "Initial";
    public static final String UPDATED_TITLE = "Awesome document";

    @BeforeEach
    public void init() {
        document = new DocumentImpl(INITIAL_TITLE);
    }

    @Test
    public void canSetTitle() {
        try {
            final String updatedTitle = "Awesome document";
            document.setTitle(updatedTitle);
            assertEquals("Awesome document", document.getTitle());

            document.undo();
            assertEquals(INITIAL_TITLE, document.getTitle());
        } catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
    }

    @Test
    public void canUndoSetTitle() {
        try {
            document.setTitle(UPDATED_TITLE);
            assertEquals("Awesome document", document.getTitle());

            document.undo();
            assertEquals(INITIAL_TITLE, document.getTitle());
        } catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
    }

    @Test
    public void canRedoSetTitle() {
        try {
            document.setTitle(UPDATED_TITLE);
            assertEquals(UPDATED_TITLE, document.getTitle());

            document.undo();
            assertEquals(INITIAL_TITLE, document.getTitle());

            document.redo();
            assertEquals(UPDATED_TITLE, document.getTitle());
        } catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
    }

    @Test
    public void invalidInsertPosition() {
        try {
            final String initialParagraphText = "Hello world!";
            document.insertParagraph(initialParagraphText, -1);
            document.insertParagraph(initialParagraphText, -1);
            document.insertParagraph(initialParagraphText, -1);
            document.insertParagraph(initialParagraphText, -100);

            fail();
        } catch (Exception exception) {
            assertEquals("Invalid position. Position must be in range [-1, 3]", exception.getMessage());
        }
    }

    @Test
    public void invalidInsertPositionMoreThanDocumentsCount() {
        try {
            final String initialParagraphText = "Hello world!";
            document.insertParagraph(initialParagraphText, -1);
            document.insertParagraph(initialParagraphText, -1);
            document.insertParagraph(initialParagraphText, -1);
            document.insertParagraph(initialParagraphText, 100);
            fail();
        } catch (Exception exception) {
            assertEquals("Invalid position. Position must be in range [-1, 3]", exception.getMessage());
        }
    }

    @Test
    public void cannotInsertParagraphWithPositionLessThanMinValue() {
        try {
            final String initialParagraphText = "Hello world!";
            document.insertParagraph(initialParagraphText, -20);

            fail();
        } catch (Exception exception) {
            assertEquals("Invalid position. Position must be in range [-1, 0]", exception.getMessage());
        }
    }

    @Test
    public void cannotInsertParagraphWithPositionMoreThanMinValue() {
        try {
            final String initialParagraphText = "Hello world!";
            document.insertParagraph(initialParagraphText, 200);

            fail();
        } catch (Exception exception) {
            assertEquals("Invalid position. Position must be in range [-1, 0]", exception.getMessage());
        }
    }

    @Test
    public void canInsertParagraph() {
        try {
            final String initialParagraphText = "Hello world!";
            document.insertParagraph(initialParagraphText, 0);

            DocumentItem item = document.getItem(0);

            assertEquals(1, document.getItemsCount());
            assertNull(item.getImage());
            assertNotNull(item.getParagraph());
            assertEquals(initialParagraphText, item.getParagraph().getText());
        } catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
    }

    @Test
    public void undoInsertParagraph() {
        try {
            document.insertParagraph("1 paragraph", -1);
            document.insertParagraph("2 paragraph", -1);
            document.undo();
            document.undo();

            final Paragraph paragraph = document.insertParagraph("3 paragraph", -1);

            assertEquals(1, document.getItemsCount());

            checkDocumentsItem(document, Collections.singletonList(new DocumentItem(paragraph)));

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void canUndoInsertParagraph() {
        try {
            final String initialParagraphText = "Hello world!";
            document.insertParagraph("Hello world!", 0);
            document.insertParagraph("Buy world!", 1);
            document.undo();

            assertEquals(1, document.getItemsCount());
            DocumentItem item = document.getItem(0);

            assertNull(item.getImage());
            assertNotNull(item.getParagraph());
            assertEquals(initialParagraphText, item.getParagraph().getText());
        } catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
    }

    @Test
    public void canRedoCanceledInsertParagraph() {
        try {
            final String firstParagraph = "Hello world!";
            document.insertParagraph(firstParagraph, 0);
            String secondParagraph = "Buy world!";
            document.insertParagraph(secondParagraph, 1);
            document.undo();
            document.redo();

            List<String> paragraphs = Arrays.asList(firstParagraph, secondParagraph);

            assertEquals(2, document.getItemsCount());
            for (int i = 0; i < document.getItemsCount(); i++) {
                DocumentItem item = document.getItem(i);

                assertNull(item.getImage());
                assertNotNull(item.getParagraph());
                assertEquals(paragraphs.get(i), item.getParagraph().getText());
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
    }

    @Test
    public void canInsertParagraphBeforeSomeItem() {
        try {
            final String firstParagraph = "Inserted first";
            String secondParagraph = "Inserted second";
            document.insertParagraph(firstParagraph, 0);
            document.insertParagraph(secondParagraph, 1);
            String thirdParagraph = "Inserted third";
            document.insertParagraph(thirdParagraph, 1);

            List<String> paragraphs = Arrays.asList(firstParagraph, thirdParagraph, secondParagraph);

            assertEquals(3, document.getItemsCount());
            for (int i = 0; i < document.getItemsCount(); i++) {
                DocumentItem item = document.getItem(i);

                assertNull(item.getImage());
                assertNotNull(item.getParagraph());
                assertEquals(paragraphs.get(i), item.getParagraph().getText());
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
    }

    @Test
    public void canUndoInsertParagraphBeforeSomeItem() {
        try {
            final String firstParagraph = "Inserted first";
            String secondParagraph = "Inserted second";
            document.insertParagraph(firstParagraph, 0);
            document.insertParagraph(secondParagraph, 1);
            String thirdParagraph = "Inserted third";
            document.insertParagraph(thirdParagraph, 1);
            document.undo();

            List<String> paragraphs = Arrays.asList(firstParagraph, secondParagraph);

            assertEquals(2, document.getItemsCount());
            for (int i = 0; i < document.getItemsCount(); i++) {
                DocumentItem item = document.getItem(i);

                assertNull(item.getImage());
                assertNotNull(item.getParagraph());
                assertEquals(paragraphs.get(i), item.getParagraph().getText());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
    }

    @Test
    public void cannotReplaceImageWithReplaceParagraphCommand() {
        try {
            Image insertedImage = document.insertImage("github.png", 0, 400, 300);
            document.replaceParagraphText(0, "Replaced value");
            fail();
        } catch (Exception exception) {
            assertEquals("Cannot perform ReplaceParagraphCommand on image item", exception.getMessage());
        }
    }

    @Test
    public void canReplaceParagraphCommand() {
        try {
            final String firstParagraph = "Inserted first";
            document.insertParagraph(firstParagraph, 0);
            document.replaceParagraphText(0, "Replaced value");

            List<String> paragraphs = Collections.singletonList("Replaced value");

            assertEquals(1, document.getItemsCount());
            for (int i = 0; i < document.getItemsCount(); i++) {
                DocumentItem item = document.getItem(i);

                assertNull(item.getImage());
                assertNotNull(item.getParagraph());
                assertEquals(paragraphs.get(i), item.getParagraph().getText());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
    }

    @Test
    public void canUndoReplaceParagraphCommand() {
        try {
            Paragraph initialParagraph = document.insertParagraph("Inserted first", 0);
            document.replaceParagraphText(0, "Replaced value");
            document.undo();

            List<String> paragraphs = Collections.singletonList(initialParagraph.getText());

            assertEquals(1, document.getItemsCount());
            for (int i = 0; i < document.getItemsCount(); i++) {
                DocumentItem item = document.getItem(i);

                assertNull(item.getImage());
                assertNotNull(item.getParagraph());
                assertEquals(paragraphs.get(i), item.getParagraph().getText());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
    }

    @Test
    public void canSave() {
        try {
            document.insertImage("github.png", -1, 400, 300);
            document.insertImage("junit.png", -1, 400, 300);
            document.insertParagraph("Hello world!", -1);
            document.save("index.html");

            List<DocumentItem> checkDocumentsItem = getDocumentItems("index.html");

            checkDocumentsItem(document, checkDocumentsItem);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void cannotResizeParagraph() {
        try {
            Paragraph paragraph = document.insertParagraph("hello world", -1);
            document.resizeImage(0, 800, 600);

            fail();
        } catch (Exception exception) {
            assertEquals("Item by index 0 not image", exception.getMessage());
        }
    }

    @Test
    public void canResizeImage() {
        try {
            Image image = document.insertImage("github.png", -1, 400, 300);
            document.resizeImage(0, 800, 600);
            image.resize(800, 600);

            List<DocumentItem> checkDocumentsItem = new ArrayList<>();
            checkDocumentsItem.add(new DocumentItem(image));
            checkDocumentsItem(document, checkDocumentsItem);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void canUndoResizeImage() {
        try {
            Image image = document.insertImage("github.png", -1, 400, 300);
            document.resizeImage(0, 800, 600);
            document.undo();

            List<DocumentItem> checkDocumentsItem = new ArrayList<>();
            checkDocumentsItem.add(new DocumentItem(image));
            checkDocumentsItem(document, checkDocumentsItem);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void cannotDeleteItemWithPositionLessThanMinValue() {
        try {
            Paragraph paragraph = document.insertParagraph("github.png", -1);
            Paragraph paragraphTwo = document.insertParagraph("github.png", -1);
            document.deleteItem(-200);
            fail();
        } catch (Exception exception) {
            assertEquals("Invalid index. Index must be in range [0, 1]", exception.getMessage());
        }
    }

    @Test
    public void cannotDeleteItemWithPositionMoreThanMaxValue() {
        try {
            Paragraph paragraph = document.insertParagraph("github.png", -1);
            Paragraph paragraphTwo = document.insertParagraph("github.png", -1);
            document.deleteItem(200);
            fail();
        } catch (Exception exception) {
            assertEquals("Invalid index. Index must be in range [0, 1]", exception.getMessage());
        }
    }

    @Test
    public void canDeleteImage() {
        try {
            Image image = document.insertImage("github.png", -1, 400, 300);
            document.deleteItem(0);

            List<DocumentItem> checkDocumentsItem = new ArrayList<>();
            checkDocumentsItem(document, checkDocumentsItem);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void canDeleteParagraph() {
        try {
            document.insertParagraph("github.png", -1);
            document.deleteItem(0);

            List<DocumentItem> checkDocumentsItem = new ArrayList<>();
            checkDocumentsItem(document, checkDocumentsItem);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    public List<DocumentItem> getDocumentItems(String path) throws Exception {
        String html = htmlFileToString(path);

        Document htmlDocument = Jsoup.parse(html);

        assertEquals(INITIAL_TITLE, htmlDocument.title());

        return parseHtml(htmlDocument);
    }

    private List<DocumentItem> parseHtml(Document htmlDocument) throws Exception {
        Element body = htmlDocument.select("body").first();
        List<DocumentItem> checkDocumentsItems = new ArrayList<>();
        for (Element element : body.children()) {
            DocumentItem documentItem = parseTag(element);
            if (documentItem != null) {
                checkDocumentsItems.add(documentItem);
            } else {
                throw new Exception("Unexpected tag");
            }
        }

        return checkDocumentsItems;
    }

    private DocumentItem parseTag(Element element) throws Exception {
        String name = element.tagName();

        if (name.equals("img")) {
            String src = element.attr("src");
            String style = element.attr("style");
            String[] split = style.split("; ");

            String heightStr = split[1];
            String widthStr = split[0];
            int width = Integer.parseInt(widthStr.substring(widthStr.indexOf(":") + 1, widthStr.length() - 2));
            int height = Integer.parseInt(heightStr.substring(heightStr.indexOf(":") + 1, heightStr.length() - 2));
            ImageImpl image = new ImageImpl(src, width, height);
            return new DocumentItem(image);
        } else if (name.equals("p")) {
            return new DocumentItem(new ParagraphImpl(element.text()));
        }

        return null;
    }

    private String htmlFileToString(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        String line;
        StringBuilder builder = new StringBuilder();

        while (scanner.hasNext()) {
            line = scanner.nextLine();
            if (line != null) {
                builder.append(line);
            }
        }
        return builder.toString();
    }
}