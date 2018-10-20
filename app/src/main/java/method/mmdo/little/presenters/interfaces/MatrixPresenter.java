package method.mmdo.little.presenters.interfaces;

import android.text.Editable;

public interface MatrixPresenter {
    void renderMatrix();
    void onAfterTextChangedMatrixCell(Editable value);
}
