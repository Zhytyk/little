package method.mmdo.little.problem;

public class BranchingCell {
    private int colIndex;
    private int rowIndex;
    private int col;
    private int row;
    private double value;

    private BranchingCell(int col, int row, int colIndex, int rowIndex, double value) {
        this.col = col;
        this.row = row;
        this.colIndex = colIndex;
        this.rowIndex = rowIndex;
        this.value = value;
    }

    public static BranchingCell of(int col, int row, int colIndex, int rowIndex, double value) {
        return new BranchingCell(col, row, colIndex, rowIndex, value);
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int getColIndex() {
        return colIndex;
    }

    public void setColIndex(int colIndex) {
        this.colIndex = colIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public double getValue() {
        return value;
    }
}
