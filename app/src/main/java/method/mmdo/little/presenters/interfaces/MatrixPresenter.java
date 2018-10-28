package method.mmdo.little.presenters.interfaces;


import java.util.ArrayList;
import java.util.List;

public interface MatrixPresenter {
    void renderMatrix();
    void onAfterTextChangedMatrixCell(String value);
    ArrayList<String> getCellValues();
    void setCellValues(List<String> cellValues);
}
