package method.mmdo.little.views;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public interface SettingActivityView {
    void onClickGoNext(View view);

    EditText getDimensionInput();

    void startActivity(Intent intent);
}
