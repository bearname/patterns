package ru.mikushov.ood.command;

import ru.mikushov.ood.model.Title;

public class ChangeTitleCommand extends BaseCommand {
    private final Title title;
    private final String target;
    private final String newValue;

    public ChangeTitleCommand(Title title, String target, String newValue) {
        this.title = title;
        this.target = target;
        this.newValue = newValue;
    }

    @Override
    void doExecute() {
        this.title.setValue(newValue);
    }

    @Override
    void doUnExecute() {
        this.title.setValue(target);
    }
}
