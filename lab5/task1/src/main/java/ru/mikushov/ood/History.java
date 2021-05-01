package ru.mikushov.ood;

import ru.mikushov.ood.command.Command;

import java.util.ArrayList;
import java.util.List;

public class History implements Executor {

    private final List<Command> commands = new ArrayList<>();
    private int nextCommandIndex = 0;

    @Override
    public boolean canUndo() {
        return nextCommandIndex > 0;
    }

    @Override
    public void undo() {
        if (canUndo()) {
            nextCommandIndex--;
            final Command command = getCommand();
            if (command != null) {
                command.unExecute();
            } else {
                nextCommandIndex++;
            }
        }
    }

    @Override
    public boolean canRedo() {
        return nextCommandIndex < commands.size();
    }

    @Override
    public void redo() throws Exception {
        if (canRedo()) {
            final Command command = getCommand();
            if (command != null) {
                command.execute();
                nextCommandIndex++;
            }
        }
    }

    private Command getCommand() {
        return this.commands.get(nextCommandIndex);
    }

    @Override
    public void addAndExecuteCommand(Command command) throws Exception {
        commands.add(command);
        nextCommandIndex++;
        command.execute();
    }
}
