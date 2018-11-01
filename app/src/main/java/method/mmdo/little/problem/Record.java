package method.mmdo.little.problem;

import java.util.Collections;
import java.util.List;

public class Record {
    private static Record record = set(Collections.emptyList(), Mark.get());

    private List<BranchingCell> branchingCells;
    private Mark mark;

    private Record(List<BranchingCell> branchingCells, Mark mark) {
        this.branchingCells = branchingCells;
        this.mark = mark;
    }

    public static Record set(List<BranchingCell> branchingCells, Mark mark) {
        record = new Record(branchingCells, mark);
        return record;
    }

    public static Record get() {
        return record;
    }

    public List<BranchingCell> getBranchingCells() {
        return branchingCells;
    }

    public Mark getMark() {
        return mark;
    }
}
