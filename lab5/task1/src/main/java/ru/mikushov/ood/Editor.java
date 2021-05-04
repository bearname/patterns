package ru.mikushov.ood;

import ru.mikushov.ood.command.MacroCommand;
import ru.mikushov.ood.service.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Editor {
    private final Menu menu;
    private final Document document;

    public Editor(Menu menu, Document document) {
        this.menu = menu;
        this.document = document;
        this.menu.addItem("help", "Help", s -> this.menu.showInstructions());
        this.menu.addItem("exit", "Exit", s -> this.menu.exit());
        this.menu.addItem("list", "Show document", s -> showDocument());
        this.menu.addItem("undo", "Undo command", s -> undo());
        this.menu.addItem("redo", "Redo undone command", s -> redo());
        this.menu.addItem("setTitle", "Changes document title. Args: <new title>", this::setTitle);
        this.menu.addItem("insertParagraph", "insertParagraph <position> | end <paragraph text>", this::insertParagraph);
        this.menu.addItem("insertImage", "insertImage <position> | end <width> <height> <path to image file> \n" +
                " 1 < width <= 10000, 1 < height <= 10000", this::insertImage);
        this.menu.addItem("replaceText", "1 <position> <paragraph text>", this::replaceText);
        this.menu.addItem("resizeImage", "resizeImage <position> <wight> <height>", this::resizeImage);
        this.menu.addItem("deleteItem", "deleteItem <position>", this::deleteItem);
        this.menu.addItem("save", "save <path to output html file>", this::save);
        this.menu.addItem("begin_macro", "begin_macro the user can start recording a new macro command, then enter the set of commands that make up the macro command. Finally, the user must enter end_macro to save the macro.", this::macroCommand);
    }

    private void macroCommand(String s) {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        String shortcutOfMacroCommand = "";
        while (true) {
            info("Enter macro shortcut");
            System.out.print("> ");
            shortcutOfMacroCommand = scanner.nextLine();
            if (menu.hasCommand(shortcutOfMacroCommand)) {
                info("Shortcut not available. Choose another");
            } else {
                break;
            }
        }

        System.out.println("Enter macro description");
        System.out.print("> ");
        String descriptionOfMacroCommand = scanner.next();

        List<String> macroCommands = new ArrayList<>();
        info("Available commands");
        menu.showInstructions();

        String command;
        while (true) {
            info("Enter available command to store on macro command or end_macro for save macro command");
            System.out.print("> ");
            command = scanner.next();
            if (command.equals("end_macro")) {
                break;
            }

            if (!command.isEmpty()) {
                String shortcut = command;

                if (command.contains(" ")) {
                    shortcut = command.substring(0, command.indexOf(" "));
                }

                if (menu.hasCommand(shortcut)) {
                    macroCommands.add(command);
                }
            }
        }

        MacroCommand macroCommand = new MacroCommand(macroCommands, menu);
        menu.addItem(shortcutOfMacroCommand, descriptionOfMacroCommand, macroCommand);
        menu.hasCommand("as");
    }

    private void save(String command) {
        try {
            String[] arguments = command.split(" ");
            if (arguments.length != 2) {
                throw new Exception("Invalid command. Usage: save <path to output html file>");
            }
            document.save(arguments[1]);
        } catch (Exception exception) {
            info(exception.getMessage());
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
        try {
            String[] commands = command.split(" ");
            if (commands.length != 5) {
                throw new Exception("Invalid argument count. Usage: insertImage <position> | end <width> <height> <path to image file> \n" +
                        "1 < width <= 10000, 1 < height <= 10000");
            }
            int position = getPosition(commands[1]);
            int width = Integer.parseInt(commands[2]);
            int height = Integer.parseInt(commands[3]);
            document.insertImage(commands[4], position, width, height);
        } catch (Exception exception) {
            info(exception.getMessage());
        }
    }

    private void info(String s) {
        System.out.println(s);
    }

    private void insertParagraph(String commands) {
        try {
            int shortCutEndIndex = commands.indexOf(" ");
            int positionEndIndex = commands.indexOf(" ", shortCutEndIndex + 1);
            String positionString = commands.substring(shortCutEndIndex + 1, positionEndIndex);

            int position = getPosition(positionString);
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
        try {
            if (!document.canRedo()) {
                throw new Exception("Can't redo");
            }
            document.redo();
        } catch (Exception exception) {
            info(exception.getMessage());
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
            if (shortcutEndIndex == -1) {
                throw new Exception("Invalid setTitle command. Usage: setTitle <title text>");
            }

            String text = command.substring(shortcutEndIndex + 1);
            document.setTitle(text);
        } catch (Exception e) {
            info("Invalid setTitle command. Usage: setTitle <title text>");
        }
    }
}
