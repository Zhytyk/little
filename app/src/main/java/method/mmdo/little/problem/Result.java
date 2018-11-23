package method.mmdo.little.problem;

public class Result {
    private Record record;

    private Result(Record record) {
        this.record = record;
    }

    public static Result of(Record record) {
        return new Result(record);
    }

    public String toStringCoords() {
        StringBuilder sb = new StringBuilder();

        sb.append("x* = { ");
        for (BranchingCell cell : record.getBranchingCells()) {
            sb.append(" (")
              .append(cell.getColIndex() + 1)
              .append(", ")
              .append(cell.getRowIndex() + 1)
              .append("); ");
        }

        return sb.append(" } ").toString();
    }

    public String toStringMark() {
        int mark = record.getMark().getValue();
        return " f(x*) = ".concat(Integer.toString(mark));
    }
}
