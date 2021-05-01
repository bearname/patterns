package ru.mikushov.ood.painter;

import ru.mikushov.ood.canvas.MyCanvas;
import ru.mikushov.ood.pacture.PictureDraft;

public class PainterImpl implements Painter {
    @Override
    public void drawPicture(PictureDraft pictureDraft, MyCanvas canvas) {
        pictureDraft.getShapes().forEach(shape -> shape.draw(canvas));
    }
}
