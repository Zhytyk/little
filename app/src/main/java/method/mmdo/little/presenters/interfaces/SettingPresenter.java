package method.mmdo.little.presenters.interfaces;


public interface SettingPresenter {
    int getDimension();
    void initSetting(String dimension);
    void onAfterDimensionTextChanged(String value);
}
