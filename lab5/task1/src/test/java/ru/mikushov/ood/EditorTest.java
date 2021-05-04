package ru.mikushov.ood;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mikushov.ood.service.DocumentImpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class EditorTest {

    private Editor editor;
    private ByteArrayOutputStream output;

    @BeforeEach
    public void init() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        editor = new Editor(new Menu(), new DocumentImpl());
    }

    @Test
    public void test() {
        String s = "help\n" +
                "list\n" +
                "exit\n";
        System.setIn(new ByteArrayInputStream(s.getBytes()));
        editor.start();
        String expected = "> insertImage : insertImage <position> | end <width> <height> <path to image file> \n" +
                " 1 < width <= 10000, 1 < height <= 10000\n" +
                "replaceText : 1 <position> <paragraph text>\n" +
                "save : save <path to output html file>\n" +
                "setTitle : Changes document title. Args: <new title>\n" +
                "redo : Redo undone command\n" +
                "insertParagraph : insertParagraph <position> | end <paragraph text>\n" +
                "list : Show document\n" +
                "help : Help\n" +
                "exit : Exit\n" +
                "undo : Undo command\n" +
                "resizeImage : resizeImage <position> <wight> <height>\n" +
                "deleteItem : deleteItem <position>\n" +
                "begin_macro : begin_macro the user can start recording a new macro command, then enter the set of commands that make up the macro command. Finally, the user must enter end_macro to save the macro.\n" +
                "> Title: Html Document\n" +
                "> ";
        assertEqual(expected, output.toString());

    }

    @Test
    public void emptyCommand() {
        String s = "\n" +
                "exit\n";
        System.setIn(new ByteArrayInputStream(s.getBytes()));
        editor.start();
        String expected = "> > ";
        assertEqual(expected, output.toString());
    }

    @Test
    public void notExistCommand() {
        String s = "somePrettyCommand\n" +
                "exit\n";
        System.setIn(new ByteArrayInputStream(s.getBytes()));
        editor.start();
        String expected = "> Failed execute command: somePrettyCommand> ";
        assertEqual(expected, output.toString());
    }

    @Test
    public void setTitle() {
        String s = "setTitle Awesome document\n" +
                "list\n" +
                "exit\n";
        System.setIn(new ByteArrayInputStream(s.getBytes()));
        editor.start();
        String expected = "> > Title: Awesome document\n" +
                "> ";
        assertEqual(expected, output.toString());
    }

    @Test
    public void invalidSetTitle() {
        String s = "setTitle\n" +
                "list\n" +
                "exit\n";
        System.setIn(new ByteArrayInputStream(s.getBytes()));
        editor.start();
        String expected = "> Invalid setTitle command. Usage: setTitle <title text>" +
                ">" +
                " Title: Html Document> ";
        assertEqual(expected, output.toString());
    }

    @Test
    public void cannotUndo() {
        String s = "undo\n" +
                "exit\n";
        System.setIn(new ByteArrayInputStream(s.getBytes()));
        editor.start();
        String expected = "> Can't undo> ";
        assertEqual(expected, output.toString());
    }

    @Test
    public void cannotRedo() {
        String s = "redo\n" +
                "exit\n";
        System.setIn(new ByteArrayInputStream(s.getBytes()));
        editor.start();
        String expected = "> Can't redo> ";
        assertEqual(expected, output.toString());
    }

    @Test
    public void undoInsertParagraph() {
        String s = "insertParagraph end hello world\n" +
                "list\n" +
                "undo\n" +
                "list\n" +
                "exit\n";
        System.setIn(new ByteArrayInputStream(s.getBytes()));
        editor.start();
        String expected = "> > Title: Html Document\n" +
                "0. Paragraph: hello world\n" +
                "> > Title: Html Document\n" +
                "> ";
        assertEqual(expected, output.toString());
    }

    @Test
    public void insertParagraphInvalidPositionValue() {
        String s = "insertParagraph esdnd hello world\n" +
                "list\n" +
                "exit";

        System.setIn(new ByteArrayInputStream(s.getBytes()));
        editor.start();

        String expected = "> Failed insert paragraph> Title: Html Document> ";
        assertEqual(expected, output.toString());
    }


    @Test
    public void redo() {
        String s = "insertParagraph end hello world\n" +
                "list\n" +
                "undo\n" +
                "redo\n" +
                "list\n" +
                "exit\n";
        System.setIn(new ByteArrayInputStream(s.getBytes()));
        editor.start();
        String expected = "> > Title: Html Document\n" +
                "0. Paragraph: hello world\n" +
                "> > > Title: Html Document\n" +
                "0. Paragraph: hello world\n" +
                "> ";
        assertEqual(expected, output.toString());
    }


    @Test
    public void macroCommand() {
        String s = "begin_macro\n" +
                "autosave\n" +
                "autosave command\n" +
                "save index.html\n" +
                "end_macro\n" +
                "help\n" +
                "exit\n";

        System.setIn(new ByteArrayInputStream(s.getBytes()));

        System.out.println("Enter macro description");
        System.out.print("> ");
//        editor.start();
        String expected = "> > Title: Html Document\n" +
                "0. Paragraph: hello world\n" +
                "> > Title: Html Document\n" +
                "> ";
//        assertEqual(expected, output.toString());
    }

    @Test
    public void insertImageInvalidArgumentCount() {
        String s = "insertImage end 400 300 D:\\Users\\mikha\\Desktop\\github2\\ood\\labs\\lab5\\task1\\github.png 400\n" +
                "list\n" +
                "exit";

        System.setIn(new ByteArrayInputStream(s.getBytes()));
        editor.start();

        String expected = "> Invalid argument count. Usage: insertImage <position> | end <width> <height> <path to image file> 1 < width <= 10000, 1 <";
        String actual = output.toString();
        assertEqual(expected, actual.substring(0, actual.length() - "4ff2c56e-b35f-4a52-8a52-a23d8e4bb8f3.png> ".length() - 2));
    }

    @Test
    public void insertImageInvalidWidth() {
        String s = "insertImage end width 300 github.png 400\n" +
                "list\n" +
                "exit";

        System.setIn(new ByteArrayInputStream(s.getBytes()));
        editor.start();

        String expected = "> Invalid argument count. Usage: insertImage <position> | end <width> <height> <path to image file> 1 < width <= 10000, 1 <";
        String actual = output.toString();
        assertEqual(expected, actual.substring(0, actual.length() - "4ff2c56e-b35f-4a52-8a52-a23d8e4bb8f3.png> ".length() - 2));
    }

    @Test
    public void resizeImage() {
        String s = "insertImage end 400 300 D:\\Users\\mikha\\Desktop\\github2\\ood\\labs\\lab5\\task1\\github.png\n" +
                "resizeImage 0 800 600\n" +
                "exit";

        System.setIn(new ByteArrayInputStream(s.getBytes()));
        editor.start();

        String expected = "> > > ";
        assertEqual(expected, output.toString());
    }

    @Test
    public void resizeImageInvalidArgument() {
        String s = "insertImage end 400 300 D:\\Users\\mikha\\Desktop\\github2\\ood\\labs\\lab5\\task1\\github.png\n" +
                "resizeImage asdfd0 800 600\n" +
                "exit";

        System.setIn(new ByteArrayInputStream(s.getBytes()));
        editor.start();

        String expected = "> > For input string: \"asdfd0\"> ";
        assertEqual(expected, output.toString());
    }

    @Test
    public void resizeImageInvalidArgumentCount() {
        String s = "insertImage end 400 300 D:\\Users\\mikha\\Desktop\\github2\\ood\\labs\\lab5\\task1\\github.png\n" +
                "resizeImage 0 800 600 500\n" +
                "exit";

        System.setIn(new ByteArrayInputStream(s.getBytes()));
        editor.start();

        String expected = "> > Invalid command. Usage: resizeImage <position> <wight> <height>> ";
        assertEqual(expected, output.toString());
    }

    @Test
    public void replaceText() {
        String s = "insertParagraph end hello world\n" +
                "list\n" +
                "replaceText 0 hello worldhello world\n" +
                "list\n" +
                "exit";

        System.setIn(new ByteArrayInputStream(s.getBytes()));
        editor.start();

        String expected = "> > Title: Html Document\n" +
                "0. Paragraph: hello world\n" +
                "> > Title: Html Document\n" +
                "0. Paragraph: hello worldhello world\n" +
                "> ";
        assertEqual(expected, output.toString());
    }


    @Test
    public void replaceTextInvalidPosition() {
        String s = "insertParagraph end hello world\n" +
                "list\n" +
                "replaceText dsf0 hello worldhello world\n" +
                "list\n" +
                "exit";

        System.setIn(new ByteArrayInputStream(s.getBytes()));
        editor.start();

        String expected = "> > Title: Html Document\n" +
                "0. Paragraph: hello world>\n" +
                " Failed update value> Title: Html Document\n" +
                "0. Paragraph: hello world> ";
        assertEqual(expected, output.toString());
    }

    @Test
    public void deleteItem() {
        String s = "insertParagraph end hello world\n" +
                "insertParagraph end awesome paragraph\n" +
                "list\n" +
                "deleteItem 1\n" +
                "list\n" +
                "exit";

        System.setIn(new ByteArrayInputStream(s.getBytes()));
        editor.start();

        String expected = "> > > Title: Html Document\n" +
                "0. Paragraph: hello world\n" +
                "1. Paragraph: awesome paragraph\n" +
                "> > Title: Html Document\n" +
                "0. Paragraph: hello world\n" +
                "> ";
        assertEqual(expected, output.toString());
    }

    @Test
    public void deleteItemInvalidArgument() {
        String s = "insertParagraph end hello world\n" +
                "insertParagraph end awesome paragraph\n" +
                "list\n" +
                "deleteItem sfddsd1\n" +
                "list\n" +
                "exit";

        System.setIn(new ByteArrayInputStream(s.getBytes()));
        editor.start();

        String expected = "> > > Title: Html Document" +
                "0. Paragraph: hello world" +
                "1. Paragraph: awesome paragraph> " +
                "For input string: \"sfddsd1\"> " +
                "Title: Html Document" +
                "0. Paragraph: hello world" +
                "1. Paragraph: awesome paragraph> ";
        assertEqual(expected, output.toString());
    }

    @Test
    public void deleteItemInvalidArgumentCount() {
        String s = "insertParagraph end hello world\n" +
                "insertParagraph end awesome paragraph\n" +
                "list\n" +
                "deleteItem 1 2\n" +
                "list\n" +
                "exit";

        System.setIn(new ByteArrayInputStream(s.getBytes()));
        editor.start();

        String expected = "> > > Title: Html Document" +
                "0. Paragraph: hello world" +
                "1. Paragraph: awesome paragraph> " +
                "Invalid command. Usage: deleteItem <position>. 0 <= position <= 2>" +
                " Title: Html Document" +
                "0. Paragraph: hello world1" +
                ". Paragraph: awesome paragraph> ";
        assertEqual(expected, output.toString());
    }

    @Test
    public void deleteItemInvalidArguments() {
        String s = "insertParagraph end hello world\n" +
                "insertParagraph end awesome paragraph\n" +
                "list\n" +
                "deleteItem asdfas1\n" +
                "list\n" +
                "exit";

        System.setIn(new ByteArrayInputStream(s.getBytes()));
        editor.start();

        String expected = "> > > Title: Html Document\r\n" +
                "0. Paragraph: hello world\r\n" +
                "1. Paragraph: awesome paragraph\r\n" +
                "> For input string: \"asdfas1\"\r\n" +
                "> Title: Html Document\r\n" +
                "0. Paragraph: hello world\r\n" +
                "1. Paragraph: awesome paragraph\r\n" +
                "> ";
        assertEqual(expected, output.toString());
    }

    @Test
    public void invalidSaveCommand() {
        String s = "save\n" +
                "exit";

        System.setIn(new ByteArrayInputStream(s.getBytes()));
        editor.start();

        String expected = "> Invalid command. Usage: save <path to output html file>> ";
        assertEqual(expected, output.toString());
    }

    @Test
    public void canSaveImage() {
        try {
            String s = "save index.html\n" +
                    "exit";

            System.setIn(new ByteArrayInputStream(s.getBytes()));
            editor.start();
            String expected = "> > ";
            assertEqual(expected, output.toString());
        } catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
    }

    private void assertEqual(String expected, String actual) {
        assertEquals(expected.replaceAll("\n", "").replaceAll("\r", ""),
                actual.replaceAll("\n", "").replaceAll("\r", ""));
    }
}