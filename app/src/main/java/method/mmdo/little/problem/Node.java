package method.mmdo.little.problem;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node {
    private Mark mark;
    private Node parent;
    private List<Node> children;
    private List<BranchingCell> branchingCell;
    private Cell[][] matrix;
    private int sumConstants;
    private Type type;

    private Node(Cell[][] matrix, Node parent, Type type) {
        this.mark = Mark.get();
        this.matrix = matrix;
        this.parent = parent;
        this.sumConstants = -1;
        this.type = type;
        this.children = new ArrayList<>();
        this.branchingCell = new ArrayList<>();
    }

    public static Node create(Cell[][] matrix) {
        return create(matrix, null, Type.FIRST);
    }

    public static Node create(Cell[][] matrix, Node parent, Type type) {
        return new Node(matrix, parent, type);
    }

    public boolean isRoot() {
        return parent == null;
    }

    public Mark getMark() {
        return mark;
    }

    public boolean isConstructed() {
        return sumConstants != -1;
    }

    public void construct() {
        initSumConstants();
        construct(Strategy.Horizontally);
        construct(Strategy.Vertically);
        findMark();

        if (!isLowerRecord()) {
            return;
        }

        if (matrix.length > 2) {
            initBranchingCell();
            constructChildren();
            return;
        }

        lastBranchingCell();
        trySetNewRecord();
    }

    private boolean isLowerRecord() {
        return Record.get().getBranchingCells().isEmpty() ||
                mark.getValue() < Record.get().getMark().getValue();
    }

    private void lastBranchingCell() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j].getValue() != 0) {
                    continue;
                }

                branchingCell.add(BranchingCell.of(i, j, matrix[i][j].getValue()));
            }
        }
    }

    private void trySetNewRecord() {
        List<BranchingCell> allCells = new ArrayList<>();

        if (!isNewRecord()) {
            return;
        }

        Node current = this;
        while (current.parent != null) {
            current = current.parent;

            allCells.addAll(current.branchingCell);
        }

        Record.set(allCells, mark);
    }

    private boolean isNewRecord() {
        return !Record.get().getBranchingCells().isEmpty() &&
                Record.get().getMark().getValue() > mark.getValue();
    }

    private void findMark() {
        if (parent == null) {
            this.mark.setValue(sumConstants);
            return;
        }

        this.mark.setValue(this.parent.mark.getValue() + sumConstants);
    }

    private void initSumConstants() {
        sumConstants = 0;
    }

    private void constructChildren() {
        constructSecondTypeChild();
        constructFirstTypeChild();

        CollectionUtils.find(children, e -> e.type == Type.FIRST).construct();
        CollectionUtils.find(children, e -> e.type == Type.SECOND).construct();
    }

    private void constructFirstTypeChild() {
        Cell[][] matrixFirstType = new Cell[matrix.length - 1][matrix.length - 1];

        int rowReducer = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (i == branchingCell.get(0).getCol()) {
                rowReducer++;
                continue;
            }

            int colReducer = 0;
            for (int j = 0; j < matrix.length; j++) {
                if (j == branchingCell.get(0).getRow()) {
                    colReducer++;
                    continue;
                }

                Cell currentCell = matrix[i][j];
                matrixFirstType[i - rowReducer][j - colReducer] =
                        Cell.of(
                                currentCell.getColIndex(),
                                currentCell.getRowIndex(),
                                currentCell.getValue()
                        );
            }
        }

        Node node = Node.create(matrixFirstType, this, Type.FIRST);
        prohibitSubCircle(node);
        children.add(node);
    }

    private void prohibitSubCircle(final Node node) {
        List<BranchingCell> allCells = new ArrayList<>(branchingCell);
        BranchingCell currentBranchingCell = branchingCell.get(0);

        Node current = node;
        while (current.parent != null) {
            current = current.parent;

            allCells.addAll(current.branchingCell);
        }

        BranchingCell rightEdge = findRightEdge(allCells, currentBranchingCell);
        BranchingCell leftEdge = findLeftEdge(allCells, currentBranchingCell);

        setInfinity(rightEdge.getCol(), leftEdge.getRow(), node.matrix);
    }

    private BranchingCell findRightEdge(List<BranchingCell> allCells, BranchingCell currentBranchingCell) {

        BranchingCell found = CollectionUtils.find(allCells,
                e -> e.getRow() == currentBranchingCell.getCol());

        if (found != null) {
            return findRightEdge(allCells, found);
        }


        return currentBranchingCell;
    }

    private BranchingCell findLeftEdge(List<BranchingCell> allCells,  BranchingCell currentBranchingCell) {
        BranchingCell found = CollectionUtils.find(allCells,
                e -> e.getCol() == currentBranchingCell.getRow());

        if (found != null) {
            return findRightEdge(allCells, found);
        }

        return currentBranchingCell;
    }

    private void setInfinity(int col, int row, Cell[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j].getColIndex() == col &&
                        matrix[i][j].getRowIndex() == row) {
                    matrix[i][j].setInfinity();
                    return;
                }
            }
        }
    }

    private void constructSecondTypeChild() {
        Cell[][] matrixSecondType = new Cell[matrix.length][];

        for (int i = 0; i < matrixSecondType.length; i++) {
            matrixSecondType[i] = ArrayUtils.clone(matrix[i]);
        }

        matrixSecondType[branchingCell.get(0).getCol()][branchingCell.get(0).getRow()].setInfinity();

        Node child = Node.create(matrixSecondType, this, Type.SECOND);
        children.add(child);
    }

    private void construct(Strategy strategy) {
        for (int i = 0; i < matrix.length; i++) {
            double min = Double.POSITIVE_INFINITY;

            for (int j = 0; j < matrix.length; j++) {
                if (strategy == Strategy.Horizontally) {
                    min = tryReplaceMin(i, j, min);
                }

                if (strategy == Strategy.Vertically) {
                    min = tryReplaceMin(j, i, min);
                }
            }

            for (int j = 0; j < matrix.length; j++) {
                if (strategy == Strategy.Horizontally) {
                    subtract(i, j, min);
                }

                if (strategy == Strategy.Vertically) {
                    subtract(j, i, min);
                }
            }

            sumConstants += min;
        }
    }

    private void initBranchingCell() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j].getValue() != 0) {
                    continue;
                }

                trySetBranchingCell(i, j);
            }
        }
    }

    private void trySetBranchingCell(int col, int row) {
        double minCol = Double.POSITIVE_INFINITY;
        double minRow = Double.POSITIVE_INFINITY;

        for (int i = 0; i < matrix.length; i++) {
            if (i == col) {
                continue;
            }

            minCol = tryReplaceMin(col, i, minCol);
        }

        for (int i = 0; i < matrix.length; i++) {
            if (i == row) {
                continue;
            }

            minRow = tryReplaceMin(i, row, minRow);
        }

        tryReplaceBranchingCell(col, row,minCol + minRow);
    }

    private void tryReplaceBranchingCell(int col, int row, double teta) {
        if (branchingCell.isEmpty()) {
            branchingCell.add(BranchingCell.of(col, row, teta));
            return;
        }

        if (branchingCell.get(0).getValue() < teta) {
            branchingCell.set(0, BranchingCell.of(col, row, teta));
        }
    }

    private double tryReplaceMin(int i, int j, double min) {
        if (min <= matrix[j][i].getValue()) {
            return min;
        }

        return matrix[i][j].getValue();
    }

    private void subtract(int i, int j, double value) {
        matrix[i][j].substractValue(value);
    }
}
