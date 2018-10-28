package method.mmdo.little.presenters;

import java.util.ArrayList;
import java.util.List;

import method.mmdo.little.models.MatrixHolder;
import method.mmdo.little.presenters.interfaces.MatrixPresenter;
import method.mmdo.little.views.MatrixActivityView;

public class MatrixActivityPresenter implements MatrixPresenter {
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
