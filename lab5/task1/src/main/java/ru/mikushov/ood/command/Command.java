package ru.mikushov.ood.command;

public interface Command {
    void execute() throws Exception;

    void unExecute();

    void destroy();
}
