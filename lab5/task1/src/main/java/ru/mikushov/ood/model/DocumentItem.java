package ru.mikushov.ood.model;

public class DocumentItem {
    private Image image = null;
    private Paragraph paragraph = null;

    public DocumentItem(Paragraph paragraph) {
        this.paragraph = paragraph;
    }

    public DocumentItem(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public Paragraph getParagraph() {
        return paragraph;
    }
}
