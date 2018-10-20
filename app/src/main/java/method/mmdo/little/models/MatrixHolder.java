package method.mmdo.little.models;

import android.util.SparseArray;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import org.apache.commons.lang3.StringUtils;

import method.mmdo.little.initializers.ComponentFactory;
import method.mmdo.little.initializers.MatrixRowInitializer;

public class MatrixHolder {
    private SparseArray<MatrixRow> matrix;
    private GridLayout matrixLayout;
    private Setting setting;

    private MatrixHolder(GridLayout matrixLayout, Setting setting) {
        this.matrixLayout = matrixLayout;
        this.matrix = new SparseArray<>();
        this.setting = setting;
    }

    public static MatrixHolder of(GridLayout matrixLayout, Setting setting) {
        return new MatrixHolder(matrixLayout, setting);
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

}
