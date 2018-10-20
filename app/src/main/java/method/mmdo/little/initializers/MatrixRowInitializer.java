package method.mmdo.little.initializers;

import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MatrixRowInitializer {
    private LinearLayout row;
    private RelativeLayout.LayoutParams layoutParams;

    private MatrixRowInitializer(LinearLayout row) {
        this.row = row;
        this.layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
    }

    public static MatrixRowInitializer of(LinearLayout row) {
        return new MatrixRowInitializer(row);
    }

    public LinearLayout init() {
        row.setLayoutParams(layoutParams);
        row.setOrientation(LinearLayout.HORIZONTAL);

        return row;
    }

}
