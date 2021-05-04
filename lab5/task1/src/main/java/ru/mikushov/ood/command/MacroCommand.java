package ru.mikushov.ood.command;

import ru.mikushov.ood.Menu;

import java.util.List;
import java.util.function.Consumer;

public class MacroCommand implements Consumer<String> {
    private final List<String> commands;
    private final Menu menu;

    public MacroCommand(List<String> commands, Menu menu) {
        this.commands = commands;
        this.menu = menu;
    }

    @Override
    public void accept(String s) {
        for (String command : commands) {
            menu.executeCommand(command);
        }
    }
}
