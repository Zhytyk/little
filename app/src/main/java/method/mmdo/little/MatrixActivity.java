package method.mmdo.little;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridLayout;

import method.mmdo.little.presenters.MatrixActivityPresenter;
import method.mmdo.little.presenters.interfaces.MatrixPresenter;
import method.mmdo.little.views.MatrixActivityView;

public class MatrixActivity extends AppCompatActivity implements MatrixActivityView {

    private MatrixPresenter presenter;
    private GridLayout matrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);

        matrix = findViewById(R.id.matrix);
        presenter = MatrixActivityPresenter.of(this);
        presenter.renderMatrix();
    }

    @Override
    public GridLayout getMatrix() {
        return matrix;
    }
}
