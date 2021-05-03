package ru.mikushov.ood.service;

import ru.mikushov.ood.command.BaseCommand;
import ru.mikushov.ood.command.Command;
import ru.mikushov.ood.command.InsertImageCommand;

import java.util.ArrayList;
import java.util.List;

public class History implements Executor {
    private List<BaseCommand> commands = new ArrayList<>();
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

    @Override
    public void addAndExecuteCommand(BaseCommand command) throws Exception {
        boolean b = needDeleteCommands();
        if (b) {
            deleteCommands();
        }

        commands.add(command);
        nextCommandIndex++;
        command.execute();
    }

    private BaseCommand getCommand() {
        return this.commands.get(nextCommandIndex);
    }

    private void deleteCommands() {
        for (int i = nextCommandIndex; i < commands.size(); i++) {
            final BaseCommand commandToDelete = commands.get(i);
            if ((commandToDelete instanceof InsertImageCommand)
                    && !commandToDelete.isExecuted()
            ) {
                commandToDelete.destroy();
            }
        }

        this.commands = this.commands.subList(0, nextCommandIndex);
    }

    private boolean needDeleteCommands() {
        return nextCommandIndex < commands.size() && nextCommandIndex >= 0;
    }
}
