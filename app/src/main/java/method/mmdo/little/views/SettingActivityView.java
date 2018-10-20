package method.mmdo.little.views;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public interface SettingActivityView {
    void onClickGoNext(View view);

    void startActivity(Intent intent);

    EditText getDimensionInput();

    Button getGoNextBtn();

    ProgressBar getProgressBar();
}
