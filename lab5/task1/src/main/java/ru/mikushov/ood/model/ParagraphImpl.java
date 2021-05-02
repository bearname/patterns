package ru.mikushov.ood.model;

public class ParagraphImpl implements Paragraph {
    private String text;

    public ParagraphImpl(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }
}
