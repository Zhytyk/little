package method.mmdo.little.models;

import android.util.SparseArray;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import method.mmdo.little.initializers.ComponentFactory;
import method.mmdo.little.initializers.MatrixRowInitializer;

public class MatrixHolder {
    private SparseArray<MatrixRow> matrix;
    private GridLayout matrixLayout;
    private Setting setting;

    private MatrixHolder(GridLayout matrixLayout, int dimension) {
        this.matrixLayout = matrixLayout;
        this.matrix = new SparseArray<>();
        this.setting = Setting.of(dimension);
    }

    public static MatrixHolder of(GridLayout matrixLayout, int dimension) {
        return new MatrixHolder(matrixLayout, dimension);
    }

    public void renderMatrix() {
        final int dimension = setting.getDimension();

        addTitleRow(matrixLayout);
        for (int i = 1; i <= dimension; i++) {
            addRow(matrixLayout, i);
        }
    }

    private void addTitleRow(GridLayout layout) {
        final LinearLayout row =
                ComponentFactory.createLinearLayout( matrixLayout.getContext() );
        final int dimension = setting.getDimension();

        MatrixRow matrixRow = MatrixRow.of(row);
        MatrixRowInitializer.of(row).init();

        matrixRow.addTitleCell(row, StringUtils.EMPTY);
        for (int i = 1; i <= dimension; i++) {
            matrixRow.addTitleCell(row, String.valueOf(i));
        }

        layout.addView(row);
    }

    private void addRow(GridLayout layout, int order) {
        final LinearLayout row =
                ComponentFactory.createLinearLayout( matrixLayout.getContext() );
        final int dimension = setting.getDimension();

        MatrixRow matrixRow = MatrixRow.of(row);
        matrix.put(order, matrixRow);

        MatrixRowInitializer.of(row).init();

        matrixRow.addTitleCell(row, String.valueOf(order));
        for (int i = 1; i <= dimension; i++) {
            matrixRow.addCell(row, order, i);
        }

        layout.addView(row);
    }

    public ArrayList<String> getCellValues() {
        ArrayList<String> values = new ArrayList<>();

        for (int i = 0; i < matrix.size(); i++) {
            MatrixRow row = matrix.valueAt(i);
            SparseArray<MatrixCell> cells = row.getCells();

            for (int j = 0; j < cells.size(); j++) {
                MatrixCell cell = cells.valueAt(j);
                values.add(cell.getCell().getText().toString());
            }
        }

        return values;
    }

    public void setCellValues(List<String> cellValues) {
        int counter = 0;
        for (int i = 0; i < matrix.size(); i++) {
            MatrixRow row = matrix.valueAt(i);
            SparseArray<MatrixCell> cells = row.getCells();

            for (int j = 0; j < cells.size(); j++) {
                MatrixCell cell = cells.valueAt(j);
                cell.getCell().setText( cellValues.get(counter++) );
            }
        }
    }

}
