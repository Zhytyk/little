package method.mmdo.little.presenters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import method.mmdo.little.MatrixActivity;
import method.mmdo.little.MatrixTitleCellInitializer;
import method.mmdo.little.initializers.ComponentFactory;
import method.mmdo.little.initializers.MatrixCellInitializer;
import method.mmdo.little.initializers.MatrixRowInitializer;
import method.mmdo.little.models.MatrixHolder;
import method.mmdo.little.models.Setting;
import method.mmdo.little.presenters.interfaces.MatrixPresenter;
import method.mmdo.little.views.MatrixActivityView;

public class MatrixActivityPresenter implements MatrixPresenter {
    private static MatrixPresenter instance;

    private final MatrixActivityView view;
    private final Setting setting;
    private final MatrixHolder matrixHolder;

    private MatrixActivityPresenter(MatrixActivityView view) {
        this.view = view;
        this.setting = getSetting();
        this.matrixHolder = MatrixHolder.of(view.getMatrix(), setting);
    }

    public static MatrixPresenter of(MatrixActivityView view)   {
        if (instance == null) {
            synchronized (SettingActivitityPresenter.class) {
                if (instance == null) {
                    instance = new MatrixActivityPresenter(view);
                }
            }
        }

        return instance;
    }

    @Override
    public void renderMatrix() {
        this.matrixHolder.renderMatrix();
    }

    private Setting getSetting() {
        MatrixActivity activity = (MatrixActivity) view;

        Intent intent = activity.getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            return (Setting) bundle.getSerializable(Setting.SETTING);
        }

        throw new IllegalArgumentException("No settings");
    }
}
