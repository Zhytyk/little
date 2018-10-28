package method.mmdo.little.presenters;


import org.apache.commons.lang3.StringUtils;

import method.mmdo.little.models.Setting;
import method.mmdo.little.presenters.interfaces.SettingPresenter;
import method.mmdo.little.views.SettingActivityView;

public class SettingActivityPresenter implements SettingPresenter {
    private static SettingPresenter instance;

    private SettingActivityView view;
    private Setting setting;

    private SettingActivityPresenter(SettingActivityView view) {
        this.view = view;
    }

    public static SettingPresenter of(SettingActivityView view)   {
        if (instance == null) {
            synchronized (SettingActivityPresenter.class) {
                if (instance == null) {
                    instance = new SettingActivityPresenter(view);
                }
            }
        }

        return instance;
    }

    @Override
    public void initSetting(String dimension) {
        setting = Setting.of(Integer.valueOf(dimension));
    }

    @Override
    public int getDimension() {
        if (setting == null) {
            throw new RuntimeException("Setting is not initialized!");
        }

        return setting.getDimension();
    }

    @Override
    public void onAfterDimensionTextChanged(String value) {
        if (!StringUtils.isNumeric(value)) {
            view.enableGoNextBtn(false);
            return;
        }

        int parsedValue = Integer.parseInt(value);
        view.enableGoNextBtn(parsedValue > 0 && parsedValue <= 12);
    }
}
