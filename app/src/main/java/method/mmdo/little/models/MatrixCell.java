package method.mmdo.little.models;

import android.widget.EditText;

public class MatrixCell {
    private int row;
    private int collumn;
    private EditText cell;

    private MatrixCell(int row, int collumn, EditText cell) {
        this.row = row;
        this.collumn = collumn;
        this.cell = cell;
    }

    public static MatrixCell of(int row, int collumn, EditText cell) {
        return new MatrixCell(row, collumn, cell);
    }

    public int getRow() {
        return row;
    }

    public int getCollumn() {
        return collumn;
    }

    public EditText getCell() {
        return cell;
    }
}
