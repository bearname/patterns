package ru.mikushov.ood;

import ru.mikushov.ood.command.Command;

public interface Executor {
    boolean canUndo();

    void undo();

    boolean canRedo();

    void redo() throws Exception;

    void addAndExecuteCommand(Command command) throws Exception;
}
