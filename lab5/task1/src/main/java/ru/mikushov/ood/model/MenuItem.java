package ru.mikushov.ood.model;


import java.util.function.Consumer;

public class MenuItem {

    private final String shortcut;
    private final String description;
    private final Consumer<String> command;

    public MenuItem(String shortcut, String description, Consumer<String> command) {
        this.shortcut = shortcut;
        this.description = description;
        this.command = command;
    }

    public String getShortcut() {
        return shortcut;
    }

    public String getDescription() {
        return description;
    }

    public Consumer<String> getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return shortcut + " : " + description;
    }
}