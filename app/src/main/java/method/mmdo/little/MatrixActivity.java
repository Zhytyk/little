package method.mmdo.little;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import method.mmdo.little.models.Setting;
import method.mmdo.little.presenters.MatrixActivityPresenter;
import method.mmdo.little.presenters.interfaces.MatrixPresenter;
import method.mmdo.little.views.MatrixActivityView;

public class MatrixActivity extends AppCompatActivity implements MatrixActivityView {

    private MatrixPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);

        presenter = MatrixActivityPresenter.of(this);
    }
}
