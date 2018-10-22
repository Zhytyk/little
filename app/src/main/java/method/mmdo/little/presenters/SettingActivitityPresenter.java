package method.mmdo.little.presenters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.apache.commons.lang3.StringUtils;

import method.mmdo.little.MatrixActivity;
import method.mmdo.little.models.Setting;
import method.mmdo.little.presenters.interfaces.SettingPresenter;
import method.mmdo.little.views.SettingActivityView;

public class SettingActivitityPresenter implements SettingPresenter {
    private static SettingPresenter instance;

    private SettingActivityView view;
    private Setting setting;

    private SettingActivitityPresenter(SettingActivityView view) {
        this.view = view;
    }

    public static SettingPresenter of(SettingActivityView view)   {
        if (instance == null) {
            synchronized (SettingActivitityPresenter.class) {
                if (instance == null) {
                    instance = new SettingActivitityPresenter(view);
                }
            }
        }

        return instance;
    }

    @Override
    public void goNext() {
        ProgressBar progressBar = view.getProgressBar();
        progressBar.setVisibility(View.VISIBLE);

        Intent intent = new Intent((Context) view, MatrixActivity.class);

        EditText editText = view.getDimensionInput();
        setting = Setting.of(Integer.valueOf(editText.getText().toString()));

        sendDataToMatrixActivity(intent);

        view.startActivity(intent);
    }

    @Override
    public void onAfterDimensionTextChanged(Editable value) {
        Button goNextBtn = view.getGoNextBtn();

        if (StringUtils.isNumeric(value)) {
            int parsedValue = Integer.parseInt(value.toString());
            goNextBtn.setEnabled(parsedValue > 0 && parsedValue <= 12);
        } else {
            goNextBtn.setEnabled(false);
        }
    }

    private void sendDataToMatrixActivity(Intent intent) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Setting.SETTING, setting);
        intent.putExtras(bundle);
    }
}
