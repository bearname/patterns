package ru.mikushov.ood.service;

import ru.mikushov.ood.command.BaseCommand;

public interface Executor {
    boolean canUndo();

    void undo();

    boolean canRedo();

    void redo() throws Exception;

    void addAndExecuteCommand(BaseCommand command) throws Exception;
}
