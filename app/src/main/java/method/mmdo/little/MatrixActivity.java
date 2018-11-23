package method.mmdo.little;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import method.mmdo.little.presenters.MatrixActivityPresenter;
import method.mmdo.little.presenters.interfaces.MatrixPresenter;
import method.mmdo.little.views.MatrixActivityView;

public class MatrixActivity extends AppCompatActivity implements MatrixActivityView {
    public static final String CELL_VALUES = "cellValues";
    public static final String RESULT_COORDS_VALUE = "result_coords";
    public static final String RESULT_MARK_VALUE = "result_mark";

    private MatrixPresenter presenter;
    private GridLayout matrix;
    private TextView resultMark;
    private TextView resultCoords;
    private Button goBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);

        matrix = findViewById(R.id.matrix);
        goBtn = findViewById(R.id.goBtn);

        resultMark = findViewById(R.id.result_mark);
        resultCoords = findViewById(R.id.result_coords);

        presenter = MatrixActivityPresenter.of(this);
        presenter.renderMatrix();

        if (savedInstanceState != null) {
            List<String> cellValues =
                    savedInstanceState.getStringArrayList(CELL_VALUES);
            String resultCoords = savedInstanceState.getString(RESULT_COORDS_VALUE);
            String resultMark = savedInstanceState.getString(RESULT_MARK_VALUE);

            presenter.setCellValues(cellValues);
            this.resultCoords.setText(resultCoords);
            this.resultMark.setText(resultMark);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putStringArrayList(CELL_VALUES, presenter.getCellValues());

        outState.putString(RESULT_COORDS_VALUE, resultCoords.getText().toString());
        outState.putString(RESULT_MARK_VALUE, resultMark.getText().toString());
    }

    public void onClickGo(View view) {
        presenter.solve();
    }

    @Override
    public void showResult(String resultCoords, String resultMark) {
        this.resultCoords.setText(resultCoords);
        this.resultMark.setText(resultMark);
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
