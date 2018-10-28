package method.mmdo.little;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import method.mmdo.little.presenters.MatrixActivityPresenter;
import method.mmdo.little.presenters.interfaces.MatrixPresenter;
import method.mmdo.little.views.MatrixActivityView;

public class MatrixActivity extends AppCompatActivity implements MatrixActivityView {
    public static final String CELL_VALUES = "cellValues";

    private MatrixPresenter presenter;
    private GridLayout matrix;
    private Button goBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);

        matrix = findViewById(R.id.matrix);
        goBtn = findViewById(R.id.goBtn);

        presenter = MatrixActivityPresenter.of(this);
        presenter.renderMatrix();

        if (savedInstanceState != null) {
            List<String> cellValues =
                    savedInstanceState.getStringArrayList(CELL_VALUES);

            presenter.setCellValues(cellValues);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putStringArrayList(CELL_VALUES, presenter.getCellValues());
    }

    public void onClickGo(View view) {

    }

    @Override
    public void setBtnEnabled(String value) {
        goBtn.setEnabled(StringUtils.isNumeric(value));
    }

    @Override
    public int getDimension() {
        return getIntent().getIntExtra(SettingActivity.DIMENSION, -1);
    }

    @Override
    public GridLayout getMatrix() {
        return matrix;
    }
}
