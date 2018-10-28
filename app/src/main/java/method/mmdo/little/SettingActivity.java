package method.mmdo.little;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import method.mmdo.little.listeners.DimensionTextWatcher;
import method.mmdo.little.presenters.SettingActivityPresenter;
import method.mmdo.little.presenters.interfaces.SettingPresenter;
import method.mmdo.little.views.SettingActivityView;

public class SettingActivity extends AppCompatActivity implements SettingActivityView {
    public static final String DIMENSION = "setting";

    private SettingPresenter presenter;
    private Button goNextBtn;
    private EditText dimensionInput;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        progressBar = findViewById(R.id.pbLoading);
        presenter = SettingActivityPresenter.of(this);
        goNextBtn = findViewById(R.id.goNext);
        dimensionInput = findViewById(R.id.dimension);
        dimensionInput.addTextChangedListener(DimensionTextWatcher.get(presenter));

        if (savedInstanceState != null) {
            dimensionInput.setText(savedInstanceState.getString(DIMENSION));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(DIMENSION, dimensionInput.getText().toString());
    }

    @Override
    public void onClickGoNext(View btn) {
        progressBar.setVisibility(View.VISIBLE);
        sendDataToMatrixActivity();
    }

    @Override
    public void enableGoNextBtn(boolean isEnable) {
        goNextBtn.setEnabled(isEnable);
    }

    private void sendDataToMatrixActivity() {
        Intent intent = new Intent(this, MatrixActivity.class);

        presenter.initSetting(dimensionInput.getText().toString());
        intent.putExtra(
                DIMENSION,
                presenter.getDimension()
        );

        startActivity(intent);
    }
}
