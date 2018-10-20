package method.mmdo.little;

import android.content.Intent;
import android.support.annotation.Dimension;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import method.mmdo.little.listeners.DimensionTextWatcher;
import method.mmdo.little.presenters.SettingActivitityPresenter;
import method.mmdo.little.presenters.interfaces.SettingPresenter;
import method.mmdo.little.views.SettingActivityView;

public class SettingActivity extends AppCompatActivity implements SettingActivityView {

    private SettingPresenter presenter;
    private Button goNextBtn;
    private EditText dimensionInput;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        progressBar = findViewById(R.id.pbLoading);
        presenter = SettingActivitityPresenter.of(this);
        goNextBtn = findViewById(R.id.goNext);
        dimensionInput = findViewById(R.id.dimension);
        dimensionInput.addTextChangedListener(DimensionTextWatcher.get(presenter));
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClickGoNext(View btn) {
        presenter.goNext();
    }

    @Override
    public EditText getDimensionInput() {
        return dimensionInput;
    }

    @Override
    public Button getGoNextBtn() {
        return goNextBtn;
    }

    @Override
    public ProgressBar getProgressBar() {
        return progressBar;
    }
}
