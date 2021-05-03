package ru.mikushov.ood;

import ru.mikushov.ood.service.Document;
import ru.mikushov.ood.service.DocumentImpl;

public class Editor {
    private final Menu menu = new Menu();
    private final Document document = new DocumentImpl();

    public Editor() {
        menu.addItem("help", "Help", s -> menu.showInstructions());
        menu.addItem("exit", "Exit", s -> menu.exit());
        menu.addItem("list", "Show document", s -> showDocument());
        menu.addItem("undo", "Undo command", s -> undo());
        menu.addItem("redo", "Redo undone command", s -> redo());
        menu.addItem("setTitle", "Changes document title. Args: <new title>", this::setTitle);
        menu.addItem("insertParagraph", "insertParagraph <position> | end <paragraph text>", this::insertParagraph);
        menu.addItem("insertImage", "insertImage <position> | end <width> <height> <path to image file> \n" +
                " 1 < width <= 10000, 1 < height <= 10000", this::insertImage);
        menu.addItem("replaceText", "1 <position> <paragraph text>", this::replaceText);
        menu.addItem("resizeImage", "resizeImage <position> <wight> <height>", this::resizeImage);
        menu.addItem("deleteItem", "deleteItem <position>", this::deleteItem);
        menu.addItem("save", "save <path to output html file>", this::save);

    }

    private void save(String command) {
        String[] arguments = command.split(" ");
        if (arguments.length != 2) {
            info("Invalid command. Usage: save <path to output html file>");
        } else {
            try {
                document.save(arguments[1]);
            } catch (Exception exception) {
                info(exception.getMessage());
            }
        }
    }

    private void deleteItem(String command) {
        String[] arguments = command.split(" ");
        if (arguments.length != 2) {
            info("Invalid command. Usage: deleteItem <position>. 0 <= position <= " + document.getItemsCount());
        } else {
            try {
                document.deleteItem(getPosition(arguments[1]));
            } catch (Exception exception) {
                info(exception.getMessage());
            }
        }
    }

    private void resizeImage(String command) {
        String[] arguments = command.split(" ");
        if (arguments.length != 4) {
            info("Invalid command. Usage: resizeImage <position> <wight> <height>");
        } else {
            try {
                int width = Integer.parseInt(arguments[2]);
                int height = Integer.parseInt(arguments[3]);
                int position = getPosition(arguments[1]);
                document.resizeImage(position, width, height);
            } catch (Exception exception) {
                info(exception.getMessage());
            }
        }
    }

    private void replaceText(String command) {
        try {
            int shortcutEndIndex = command.indexOf(" ");
            int positionEndIndex = command.indexOf(" ", shortcutEndIndex + 1);
            String positionString = command.substring(shortcutEndIndex + 1, positionEndIndex);
            int position = getPosition(positionString);
            String text = command.substring(positionEndIndex + 1);

            document.replaceParagraphText(position, text);
        } catch (Exception exception) {
            info("Failed update value");
        }
    }

    private void showDocument() {
        info("Title: " + document.getTitle());
        for (int i = 0; i < document.getItemsCount(); i++) {
            info(i + ". " + document.getItem(i));
        }
    }

    private void insertImage(String command) {
        String[] commands = command.split(" ");
        if (commands.length != 5) {
            info("insertImage <position> | end <width> <height> <path to image file> \n" +
                    "1 < width <= 10000, 1 < height <= 10000");
        }
        try {
            int position = getPosition(commands[1]);
            int width = Integer.parseInt(commands[2]);
            int height = Integer.parseInt(commands[3]);
            document.insertImage(commands[4], position, width, height);
        } catch (Exception exception) {
            info("Failed insert image");
        }
    }

    private void info(String s) {
        System.out.println(s);
    }

    private void insertParagraph(String commands) {
        int shortCutEndIndex = commands.indexOf(" ");
        int positionEndIndex = commands.indexOf(" ", shortCutEndIndex + 1);
        String positionString = commands.substring(shortCutEndIndex + 1, positionEndIndex);

        int position = getPosition(positionString);
        try {
            String text = commands.substring(positionEndIndex + 1);
            document.insertParagraph(text, position);
        } catch (Exception exception) {
            info("Failed insert paragraph");
        }
    }

    private int getPosition(String position) {
        if (position.equals("end")) {
            return -1;
        }

        return Integer.parseInt(position);
    }

    private void redo() {
        if (!document.canRedo()) {
            info("Can't redo");
        } else {
            try {
                document.redo();
            } catch (Exception exception) {
                info(exception.getMessage());
            }
        }
    }

    private void undo() {
        if (!document.canUndo()) {
            info("Can't undo");
        } else {
            document.undo();
        }
    }

    public void start() {
        menu.run();
    }

    private void setTitle(final String command) {
        try {
            int shortcutEndIndex = command.indexOf(" ");
            String text = command.substring(shortcutEndIndex + 1);
            document.setTitle(text);
        } catch (Exception e) {
            info("Invalid command");
        }
    }
}
