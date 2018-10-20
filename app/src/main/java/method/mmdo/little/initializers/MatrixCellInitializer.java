package method.mmdo.little.initializers;

import android.text.InputType;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import method.mmdo.little.listeners.MatrixCellTextWatcher;
import method.mmdo.little.presenters.MatrixActivityPresenter;

public class MatrixCellInitializer {
    private EditText cell;
    private LinearLayout.LayoutParams layoutParams;

    private MatrixCellInitializer(EditText cell) {
        this.cell = cell;
        this.layoutParams = new LinearLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.weight = 1.0f;
    }

    public static MatrixCellInitializer of(EditText editText) {
        return new MatrixCellInitializer(editText);
    }

    public EditText init() {
        cell.setLayoutParams(layoutParams);
        cell.setInputType(InputType.TYPE_CLASS_NUMBER);
        cell.setGravity(Gravity.CENTER_HORIZONTAL);

        addTextWatcher();

        return cell;
    }

    private void addTextWatcher() {
        cell.addTextChangedListener(
                MatrixCellTextWatcher.get( MatrixActivityPresenter.getInstance() )
        );
    }
}
