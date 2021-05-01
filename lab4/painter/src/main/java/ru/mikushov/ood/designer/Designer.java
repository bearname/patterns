package ru.mikushov.ood.designer;

import ru.mikushov.ood.pacture.PictureDraft;

import java.io.InputStream;

public interface Designer {
    PictureDraft createDraft(InputStream inputStream);
}
