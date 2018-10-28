package method.mmdo.little.views;

import android.widget.GridLayout;

public interface MatrixActivityView {
    void setBtnEnabled(String value);
    int getDimension();
    GridLayout getMatrix();
}
