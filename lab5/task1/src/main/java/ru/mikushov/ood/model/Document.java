package ru.mikushov.ood.model;

public interface Document {
    Paragraph insertParagraph(final String text, final int position) throws Exception;

    // Вставляет изображение в указанную позицию (сдвигая последующие элементы)
    // Параметр path задает путь к вставляемому изображению
    // При вставке изображение должно копироваться в подкаталог images
    // под автоматически сгенерированным именем
    Image insertImage(final String path, final int width, final int height, final int position) throws Exception;

    int getItemsCount();

    // Доступ к элементам изображения
    DocumentItem getItem(int index);

    // Удаляет элемент из документа
     void deleteItem(int index) throws Exception;

    String getTitle();
    void setTitle( String title) throws Exception;

    boolean canUndo();
    void undo();

    boolean canRedo();
    void redo();

    // Сохраняет документ в формате html. Изображения сохраняются в подкаталог images.
    // Пути к изображениям указываются относительно пути к сохраняемому HTML файлу
    void save(final String path);
}
