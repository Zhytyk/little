package method.mmdo.little;

import android.content.Intent;
import android.support.annotation.Dimension;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import method.mmdo.little.presenters.SettingActivitityPresenter;
import method.mmdo.little.presenters.interfaces.SettingPresenter;
import method.mmdo.little.views.SettingActivityView;

public class SettingActivity extends AppCompatActivity implements SettingActivityView {

    private SettingPresenter presenter;
    private EditText dimensionInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        presenter = SettingActivitityPresenter.of(this);
        dimensionInput = findViewById(R.id.dimension);
    }

    public void onClickGoNext(View btn) {
        presenter.goNext();
    }

    public EditText getDimensionInput() {
        return dimensionInput;
    }
}
