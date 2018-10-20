package method.mmdo.little.presenters;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.GridLayout;

import org.apache.commons.lang3.StringUtils;

import method.mmdo.little.MatrixActivity;
import method.mmdo.little.models.MatrixHolder;
import method.mmdo.little.models.Setting;
import method.mmdo.little.presenters.interfaces.MatrixPresenter;
import method.mmdo.little.views.MatrixActivityView;

public class MatrixActivityPresenter implements MatrixPresenter {
    private static MatrixPresenter instance;

    private MatrixActivityView view;
    private final Setting setting;
    private final MatrixHolder matrixHolder;

    private MatrixActivityPresenter(MatrixActivityView view) {
        this.view = view;
        this.setting = getSetting();
        this.matrixHolder = MatrixHolder.of(view.getMatrix(), setting);
    }

    public static MatrixPresenter of(MatrixActivityView view)   {
        instance = new MatrixActivityPresenter(view);
        return instance;
    }

    public static MatrixPresenter getInstance() {
        if (instance == null) {
            throw new RuntimeException("Instance hasn't been initialized yet.");
        }

        return instance;
    }

    @Override
    public void renderMatrix() {
        this.matrixHolder.renderMatrix();
    }

    @Override
    public void onAfterTextChangedMatrixCell(Editable value) {
        Button goBtn = view.getGoBtn();
        goBtn.setEnabled(StringUtils.isNumeric(value));
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
