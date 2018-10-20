package method.mmdo.little.initializers;

import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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

        return cell;
    }
}
