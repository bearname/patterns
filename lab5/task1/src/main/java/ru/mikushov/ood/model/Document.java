package ru.mikushov.ood.model;

public interface Document {
    Paragraph insertParagraph(final String text, final int position) throws Exception;

    // Inserts an image at the specified position (shifting subsequent elements)
    // The path parameter sets the path to the inserted image
    // When pasting, the image must be copied to the images subdirectory
    // under an automatically generated name
    Image insertImage(final String path, final int width, final int height, final int position) throws Exception;

    int getItemsCount();

    DocumentItem getItem(int index);

     void deleteItem(int index) throws Exception;

    String getTitle();
    void setTitle( String title) throws Exception;

    boolean canUndo();
    void undo();

    boolean canRedo();
    void redo() throws Exception;

    // Saves the document in html format. Images are saved in the images subdirectory.
    // Image paths are specified relative to the path to the saved HTML file
    void save(final String path);
}
