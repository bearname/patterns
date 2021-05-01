package ru.mikushov.ood.command;

public abstract class BaseCommand implements Command {
    private boolean isExecuted = false;

    @Override
    public void execute() throws Exception {
        if (!isExecuted) {
            doExecute();
            isExecuted = true;
        }
    }

    @Override
    public void unExecute() {
        if (isExecuted) {
            doUnExecute();
            isExecuted = false;
        }
    }

    abstract void doExecute() throws Exception;

    abstract void doUnExecute();
}
