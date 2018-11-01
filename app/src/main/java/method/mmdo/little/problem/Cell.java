package method.mmdo.little.problem;

public class Cell {
    private int rowIndex;
    private int colIndex;
    private double value;

    private Cell(int colIndex, int rowIndex, double value) {
        this.colIndex = colIndex;
        this.rowIndex = rowIndex;
        this.value = value;
    }

    public static Cell of(int colIndex, int rowIndex, double value) {
        return new Cell(colIndex, rowIndex, value);
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    public double getValue() {
        return value;
    }

    public void substractValue(double value) {
        this.value -= value;
    }

    public void setInfinity() {
        this.value = Double.POSITIVE_INFINITY;
    }
}
