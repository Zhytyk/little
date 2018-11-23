package method.mmdo.little.presenters;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

import method.mmdo.little.models.MatrixHolder;
import method.mmdo.little.presenters.interfaces.MatrixPresenter;
import method.mmdo.little.problem.Little;
import method.mmdo.little.problem.Result;
import method.mmdo.little.views.MatrixActivityView;

public class MatrixActivityPresenter implements MatrixPresenter {
    public static final String INFINITY = "-1";

    private static MatrixPresenter instance;

    private final MatrixActivityView view;
    private final MatrixHolder matrixHolder;

    private MatrixActivityPresenter(MatrixActivityView view) {
        this.view = view;
        this.matrixHolder = MatrixHolder.of(
                view.getMatrix(),
                view.getDimension()
        );
    }

    public static MatrixPresenter of(MatrixActivityView view)   {
        instance = new MatrixActivityPresenter(view);
        return instance;
    }

    public static MatrixPresenter getInstance() {
        if (instance == null) {
            throw new RuntimeException("Instance hasn't been initialized yet.");
        }

        return instance;
    }

    @Override
    public void solve() {
        int dimension = view.getDimension();

        ArrayList<String> cellValues = matrixHolder.getCellValues();
        double[][] cellValuesArr = new double[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                String currentValue = cellValues.get(i * dimension + j);

                if (currentValue.equals(INFINITY)) {
                    cellValuesArr[i][j] = Double.POSITIVE_INFINITY;
                    continue;
                }

                cellValuesArr[i][j] = Double.parseDouble(currentValue);
            }
        }

//        double[][] cellValuesArr =
//                {
//                        {Double.POSITIVE_INFINITY, 12, 6, 7, 9},
//                        { 3, Double.POSITIVE_INFINITY, 5, 11, 5},
//                        {4, 4, Double.POSITIVE_INFINITY, 2, 6},
//                        {8, 7, 2, Double.POSITIVE_INFINITY, 3},
//                        {1, 9, 13, 8, Double.POSITIVE_INFINITY}
//                };

        Double[][] values = new Double[dimension][];
        for (int i = 0; i < dimension; i++) {
            values[i] = ArrayUtils.toObject(cellValuesArr[i]);
        }

        Result result = Little.get(values).solve();

        view.showResult(result.toStringCoords(), result.toStringMark());
    }

    @Override
    public void renderMatrix() {
        this.matrixHolder.renderMatrix();
    }

    @Override
    public void onAfterTextChangedMatrixCell(String value) {
        view.setBtnEnabled(value);
    }

    @Override
    public ArrayList<String> getCellValues() {
        return matrixHolder.getCellValues();
    }

    @Override
    public void setCellValues(List<String> cellValues) {
        matrixHolder.setCellValues(cellValues);
    }
}
