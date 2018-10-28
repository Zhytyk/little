package method.mmdo.little.models;

import android.util.SparseArray;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import method.mmdo.little.initializers.MatrixTitleCellInitializer;
import method.mmdo.little.initializers.ComponentFactory;
import method.mmdo.little.initializers.MatrixCellInitializer;

public class MatrixRow {
    private SparseArray<MatrixCell> row;
    private LinearLayout rowLayout;

    private MatrixRow(LinearLayout rowLayout) {
        this.rowLayout = rowLayout;
        row = new SparseArray<>();
    }

    public static MatrixRow of(LinearLayout rowLayout) {
        return new MatrixRow(rowLayout);
    }

    public void addTitleCell(LinearLayout layout, String value) {
        final TextView titleCell =
                ComponentFactory.createTextView( layout.getContext() );

        MatrixTitleCellInitializer.of(
                titleCell,
                value
        ).init();

        layout.addView(titleCell);
    }

    public void addCell(LinearLayout layout, int row, int column) {
        final EditText cell =
                ComponentFactory.createEditText( layout.getContext() );

        MatrixCell matrixCell = MatrixCell.of(row, column, cell);
        this.row.put(column, matrixCell);

        MatrixCellInitializer.of(cell).init();

        layout.addView(cell);
    }

    public SparseArray<MatrixCell> getCells() {
        return row;
    }
}
