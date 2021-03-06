package method.mmdo.little.initializers;

import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MatrixTitleCellInitializer {
    private static int CELL_WIDTH = 150;

    private TextView titleCell;
    private LinearLayout.LayoutParams layoutParams;
    private String value;

    private MatrixTitleCellInitializer(TextView titleCell, String value) {
        this.titleCell = titleCell;
        this.layoutParams = new LinearLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        this.value = value;
    }

    public static MatrixTitleCellInitializer of(TextView titleCell, String value) {
        return new MatrixTitleCellInitializer(
                titleCell,
                value
        );
    }

    public TextView init() {
        titleCell.setLayoutParams(layoutParams);
        titleCell.setText(value);
        titleCell.setGravity(Gravity.CENTER_HORIZONTAL);
        titleCell.setWidth(CELL_WIDTH);

        return titleCell;
    }
}
