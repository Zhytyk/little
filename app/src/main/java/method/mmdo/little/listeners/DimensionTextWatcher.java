package method.mmdo.little.listeners;

import android.text.Editable;
import android.text.TextWatcher;

import method.mmdo.little.presenters.interfaces.SettingPresenter;

public class DimensionTextWatcher implements TextWatcher {
    private SettingPresenter presenter;

    private DimensionTextWatcher(SettingPresenter presenter) {
        this.presenter = presenter;
    }

    public static DimensionTextWatcher get(SettingPresenter presenter) {
        return new DimensionTextWatcher(presenter);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        presenter.onAfterDimensionTextChanged(s.toString());
    }
}
