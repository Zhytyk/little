package method.mmdo.little.problem;

public class BranchingCell {
    private int col;
    private int row;
    private double value;

    private BranchingCell(int col, int row, double value) {
        this.col = col;
        this.row = row;
        this.value = value;
    }

    public static BranchingCell of(int col, int row, double value) {
        return new BranchingCell(col, row, value);
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public double getValue() {
        return value;
    }
}
