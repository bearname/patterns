package ru.mikushov.ood.painter;

import ru.mikushov.ood.canvas.MyCanvas;
import ru.mikushov.ood.pacture.PictureDraft;

public interface Painter {
    void drawPicture(PictureDraft pictureDraft, MyCanvas canvas);
}
