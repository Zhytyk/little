package method.mmdo.little.listeners;

import android.text.Editable;
import android.text.TextWatcher;

import method.mmdo.little.presenters.interfaces.MatrixPresenter;

public class MatrixCellTextWatcher implements TextWatcher {
    private MatrixPresenter presenter;

    private MatrixCellTextWatcher(MatrixPresenter presenter) {
        this.presenter = presenter;
    }

    public static MatrixCellTextWatcher get(MatrixPresenter presenter) {
        return new MatrixCellTextWatcher(presenter);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        presenter.onAfterTextChangedMatrixCell(s.toString());
    }
}
