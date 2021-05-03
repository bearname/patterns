package ru.mikushov.ood;

import ru.mikushov.ood.model.MenuItem;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class Menu {
    private final Map<String, MenuItem> menuItems = new HashMap<>();
    private boolean exit = false;

    public void addItem(String shortcut, String description, Consumer<String> command) {
        this.menuItems.put(shortcut, new MenuItem(shortcut, description, command));
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String command;

        while (!exit) {
            System.out.print("> ");
            command = scanner.nextLine();
            if (!command.isEmpty()) {
                if (!executeCommand(command)) {
                    System.out.println("Failed execute command: " + command);
                }
            }
        }
    }

    public void showInstructions() {
        for (var entry : menuItems.entrySet()) {
            System.out.println(entry);
        }
    }

    public void exit() {
        exit = true;
    }

    private boolean executeCommand(String commandLine) {
        try {
            String commandShortcut = getShortcut(commandLine);

            if (menuItems.containsKey(commandShortcut)) {
                try {
                    Consumer<String> command = menuItems.get(commandShortcut).getCommand();
                    command.accept(commandLine);
                    return true;

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    private String getShortcut(String commandLine) {
        if (commandLine.contains(" ")) {
            return commandLine.substring(0, commandLine.indexOf(" "));
        }

        return commandLine;
    }
}
