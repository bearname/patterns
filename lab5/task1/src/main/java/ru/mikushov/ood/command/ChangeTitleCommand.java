package ru.mikushov.ood.command;

import ru.mikushov.ood.model.Text;

public class ChangeTitleCommand extends BaseCommand {
    private final Text text;
    private final String target;
    private final String newValue;

    public ChangeTitleCommand(Text text, String target, String newValue) {
        this.text = text;
        this.target = target;
        this.newValue = newValue;
    }

    @Override
    void doExecute() {
        this.text.setValue(newValue);
    }

    @Override
    void doUnExecute() {
        this.text.setValue(target);
    }

    @Override
    public void destroy() {

    }
}
