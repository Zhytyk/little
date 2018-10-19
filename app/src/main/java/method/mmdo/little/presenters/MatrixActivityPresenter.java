package method.mmdo.little.presenters;

import android.content.Intent;
import android.os.Bundle;

import method.mmdo.little.MatrixActivity;
import method.mmdo.little.models.Setting;
import method.mmdo.little.presenters.interfaces.MatrixPresenter;
import method.mmdo.little.views.MatrixActivityView;

public class MatrixActivityPresenter implements MatrixPresenter {
    private static MatrixPresenter instance;

    private final MatrixActivityView view;
    private final Setting setting;

    public MatrixActivityPresenter(MatrixActivityView view) {
        this.view = view;
        this.setting = getSetting();
        System.out.println("2");
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

    private Setting getSetting() {
        MatrixActivity context = (MatrixActivity) view;

        Intent intent = context.getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            return (Setting) bundle.getSerializable(Setting.SETTING);
        }

        throw new IllegalArgumentException("No settings");
    }
}
